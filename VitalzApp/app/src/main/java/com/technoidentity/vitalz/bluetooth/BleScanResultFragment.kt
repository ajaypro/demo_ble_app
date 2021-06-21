package com.technoidentity.vitalz.bluetooth

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.databinding.FragmentBlescanResultBinding
import com.technoidentity.vitalz.home.HomeViewModel
import com.technoidentity.vitalz.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class BleScanResultFragment : Fragment() {

    val viewModel: HomeViewModel by activityViewModels()

    private lateinit var binding: FragmentBlescanResultBinding

    private val devices = ArrayList<BluetoothDevice>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlescanResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val deviceListAdapter = BluetoothScanResultAdapter(BleDeviceClickListener { bluetoothDevice ->
            viewModel.toggleScan().also {
                showToast(requireContext(), "Scanning stopped")
            }
//                viewModel.sendDeviceForRegisteration(BleMac(bluetoothDevice.address)).observe(viewLifecycleOwner,{
//
//                     it?.let {
//                         //show alert dialog
//                     }
//                        if (it.macId.isNotEmpty())
//                            when(equalsDevice(it.macId, bluetoothDevice)) {
//                                true -> {
//                                    //diaply dialog and navigate to add device page
//                                }
//                                else -> {
//                                    "Something went wrong patchId not generated"
//                                }
//                            }
//                    })
            lifecycleScope.launchWhenResumed {
                viewModel.connectDevice(bluetoothDevice, requireContext().applicationContext)
                viewModel.isDeviceConnected.collect{
                    if (it) {
                        showToast(requireContext(), "Device is connected BleScanresult")
                        findNavController().navigate(R.id.action_bleScanResultFragment_to_deviceDetailsFragment) // will take bledevice object from viewmodel
                    } else showToast(requireContext(), "Device is not connected")
                }
            }
        }, viewModel)


        binding.bleScanResult.apply {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = deviceListAdapter
            layoutManager = LinearLayoutManager(context)
        }
        deviceListAdapter.submitList(devices)

        lifecycleScope.launchWhenResumed {

            binding.scanBtn.setOnClickListener {
                viewModel.toggleScan() }

            binding.scanBtn.apply {
                viewModel.isScanning.observe(viewLifecycleOwner, {
                    if (it) setText(R.string.stop_scan) else setText(R.string.start_scan) })
            }

            viewModel.scanFlow.distinctUntilChangedBy { it.address }.collect { device ->
                val indexQuery = devices.indexOfFirst { it.address == device.address }
                if (indexQuery != -1) {
                    devices[indexQuery] = device
                } else {
                    devices.add(device)
                    deviceListAdapter.notifyItemChanged(devices.size - 1)
                    Timber.d("found device with address = ${device.address}")
                }

            }
        }

    }
}