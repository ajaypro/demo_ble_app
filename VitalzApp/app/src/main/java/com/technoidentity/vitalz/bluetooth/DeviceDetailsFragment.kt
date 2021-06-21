package com.technoidentity.vitalz.bluetooth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentAddDeviceBinding
import com.technoidentity.vitalz.databinding.FragmentDeviceDetailsBinding
import com.technoidentity.vitalz.home.HomeViewModel
import com.technoidentity.vitalz.utils.DEVICE_BATTERY_CHAR_UUID
import com.technoidentity.vitalz.utils.showToast
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class DeviceDetailsFragment : Fragment() {


     val homeViewmodel: HomeViewModel by activityViewModels()

    lateinit var binding: FragmentDeviceDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDeviceDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            homeViewmodel.isDeviceConnected.collect{
                Timber.d("devicedetails $it")
                if (it == true) {
                    showToast(requireContext(), "Device is connected devicedetails")

                    homeViewmodel.connectedDevice.observe(viewLifecycleOwner, {
                        binding.apply {
                            patchId.text = it.device.name
                            battery.text = "11%"
//                            batteryCharacteristic(it.services, DEVICE_BATTERY_CHAR_UUID).observe(viewLifecycleOwner,{
//                                it.forEach {
//                                    it.toString()
//                                }
//                            }).toString()
                            when (it.connectionStatus) {
                                BleConnection.BtConnectedState() -> {
                                    connection.text = getString(R.string.connected)
                                }
                                else -> {
                                    connection.text = getString(R.string.disconnected)
                                }
                            }

                        }

                    })
                }
            }
            showToast(requireContext(), "Device is disconnected devicedetails")

        }
    }
}