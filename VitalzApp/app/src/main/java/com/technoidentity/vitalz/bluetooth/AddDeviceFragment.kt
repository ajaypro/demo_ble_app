package com.technoidentity.vitalz.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentAddDeviceBinding
import com.technoidentity.vitalz.utils.Constants.LOCATION_PERMISSION_REQUEST_CODE
import com.technoidentity.vitalz.utils.hasPermission
import com.technoidentity.vitalz.utils.requestPermission
import com.technoidentity.vitalz.utils.showSnackbar
import timber.log.Timber

class AddDeviceFragment : Fragment() {

    lateinit var binding: FragmentAddDeviceBinding

    lateinit var layout: View

    //lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission has been granted. Start camera preview Activity.
            layout.showSnackbar(
                R.string.location_permission_granted,
                Snackbar.LENGTH_INDEFINITE,
                R.string.ok
            ) {
                findNavController().navigate(R.id.action_addDeviceFragment_to_bleScanResultFragment)
            }
        } else {
            // Permission request was denied.
            layout.showSnackbar(
                R.string.location_permission_denied,
                Snackbar.LENGTH_SHORT,
                R.string.ok
            )
        }
    }

//    private val isLocationPermissionGranted
//        get() = hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDeviceBinding.inflate(inflater)
        layout = binding.root
        return layout
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                // Permission has been granted. Start camera preview Activity.
//                layout.showSnackbar(
//                    R.string.location_permission_granted,
//                    Snackbar.LENGTH_INDEFINITE,
//                    R.string.ok
//                ) {
//                    startBleScan()
//                }
//            } else {
//                // Permission request was denied.
//                layout.showSnackbar(
//                    R.string.location_permission_denied,
//                    Snackbar.LENGTH_SHORT,
//                    R.string.ok) {
//                    //TODO to show rationale
//                }
//            }
//        }
//
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addDeviceBtn.setOnClickListener {

            requestLocationPermission()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            ENABLE_BLUETOOTH_REQUEST_CODE -> {
//                if (resultCode != Activity.RESULT_OK) {
//                    promptEnableBluetooth()
//                }
//            }
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            LOCATION_PERMISSION_REQUEST_CODE -> {
//                if (grantResults.firstOrNull() == PackageManager.PERMISSION_DENIED) {
//                    requestLocationPermission()
//                } else {
//                    startBleScan()
//                }
//            }
//        }
//    }

    /*******************************************
     * Private functions
     *******************************************/



    private fun requestLocationPermission() {
//        if (isLocationPermissionGranted) {
//            return
//        }
//        requireActivity().runOnUiThread {
//            alert {
//                title = "Location permission required"
//                message = "Starting from Android M (6.0), the system requires apps to be granted " +
//                        "location access in order to scan for BLE devices."
//                isCancelable = false
//                positiveButton(android.R.string.ok) {
//                   requestPermission(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        LOCATION_PERMISSION_REQUEST_CODE
//                    )
//                }
//            }.show()
//        }
        //requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, requestPermissionLauncher, layout)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            layout.showSnackbar(
                R.string.location_permission_available,
                Snackbar.LENGTH_INDEFINITE,
                R.string.ok
            ) {
                findNavController().navigate(R.id.action_addDeviceFragment_to_bleScanResultFragment)
            }
        } else {

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Reason why location permission is required.
                layout.showSnackbar(
                    R.string.location_permission_required,
                    Snackbar.LENGTH_INDEFINITE,
                    R.string.ok
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            } else {
                // Directly asking for the permission.
                layout.showSnackbar(
                    R.string.location_permission_not_available,
                    Snackbar.LENGTH_LONG,
                    R.string.ok
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }
}
