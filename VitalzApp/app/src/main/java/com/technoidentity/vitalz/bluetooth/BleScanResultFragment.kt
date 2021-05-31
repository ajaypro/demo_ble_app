package com.technoidentity.vitalz.bluetooth

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.DeviceDetails
import com.technoidentity.vitalz.databinding.FragmentBlescanResultBinding
import com.technoidentity.vitalz.utils.showSnackbar
import com.technoidentity.vitalz.utils.showToast
import timber.log.Timber

class BleScanResultFragment : Fragment() {

    lateinit var layout: View

    lateinit var binding: FragmentBlescanResultBinding

    private lateinit var device: BluetoothDevice

    private val bluetoothAdapter: BluetoothAdapter by lazy {
        val bluetoothManager =
            requireActivity().getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val bleScanner by lazy {
        bluetoothAdapter.bluetoothLeScanner
    }

    private val scanSettings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
        .build()

    private var isScanning = false
        set(value) {
            field = value
//            requireActivity().runOnUiThread { scan_button.text = if (value) "Stop Scan" else "Start Scan" }
        }

    private val scanResults = mutableListOf<ScanResult>()
    private val scanResultAdapter: BluetoothScanResultAdapter by lazy {
        BluetoothScanResultAdapter(BluetoothScanResultClickListener { scanResult ->
            if (isScanning) {
                stopBleScan()
            }
            with(scanResult.device) {
                Timber.w("Connecting to $address")
                if (ConnectionManager.connect(this, requireActivity())) {
                    showToast(context, "Device Connected")
                    //TODO(send devicedetails as arguments)
                    //findNavController().navigate(R.id.action_bleScanResultFragment_to_deviceDetailsFragment)
                } else showToast(context, "Device not connected")
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ConnectionManager.registerListener(connectionEventListener)
        super.onCreate(savedInstanceState)

        promptEnableBluetooth()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlescanResultBinding.inflate(inflater)
        layout = binding.root

        setupRecyclerView()
        scanResultAdapter.submitList(scanResults)
        return layout
    }

    private val startBleOnForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode != Activity.RESULT_OK) {
                //val intent = result.data
                layout.showSnackbar(
                    "Bluetooth needs to be turned on to start scanning ",
                    Snackbar.LENGTH_INDEFINITE,
                    "Ok"
                ) {
                    promptEnableBluetooth()
                }
            }
        }

    private fun promptEnableBluetooth() {
        if (!bluetoothAdapter.isEnabled) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startBleOnForResult.launch(enableBtIntent)
            //startActivityForResult(enableBtIntent, ENABLE_BLUETOOTH_REQUEST_CODE)
        } else {
            startBleScan()
        }
    }

    private fun startBleScan() {

        scanResults.clear()
        scanResultAdapter.notifyDataSetChanged()
        bleScanner.startScan(null, scanSettings, scanCallback)
        isScanning = true

    }


    private val connectionEventListener by lazy {
        ConnectionEventListener().apply {
            onConnectionSetupComplete = { gatt ->
                // share gatt witj viewmodel
                device = gatt.device
                findNavController().navigate(R.id.action_bleScanResultFragment_to_bleOperationsFragment, bundleOf(BluetoothDevice.EXTRA_DEVICE to device))
                ConnectionManager.unregisterListener(this)
            }
            onDisconnect = {
//                runOnUiThread {
//                    alert {
//                        title = "Disconnected"
//                        message = "Disconnected or unable to connect to device."
//                        positiveButton("OK") {}
                layout.showSnackbar(R.string.disconnected, Snackbar.LENGTH_SHORT, R.string.ok)
            }
        }
    }


    private fun setupRecyclerView() {
        binding.bleScanResult.apply {
            adapter = scanResultAdapter
            layoutManager = LinearLayoutManager(activity)
            //isNestedScrollingEnabled = false
        }

//        val animator = scan_results_recycler_view.itemAnimator
//        if (animator is SimpleItemAnimator) {
//            animator.supportsChangeAnimations = false
//        }
    }

    private fun stopBleScan() {
        bleScanner.stopScan(scanCallback)
        isScanning = false
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val indexQuery = scanResults.indexOfFirst { it.device.address == result.device.address }
            if (indexQuery != -1) { // A scan result already exists with the same address
                scanResults[indexQuery] = result
                scanResultAdapter.notifyItemChanged(indexQuery)
            } else {
                with(result.device) {
                    Timber.i("Found BLE device! Name: ${name ?: "Unnamed"}, address: $address")
                    //findNavController().navigate(R.id.action_bleScanResultFragment_to_deviceDetailsFragment)
                }
                scanResults.add(result)
                scanResultAdapter.notifyItemInserted(scanResults.size - 1)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Timber.i("onScanFailed: code $errorCode")
            binding.noDevice.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        ConnectionManager.unregisterListener(connectionEventListener)
        if (this::device.isInitialized)
            ConnectionManager.teardownConnection(device)

        super.onDestroy()
    }
}