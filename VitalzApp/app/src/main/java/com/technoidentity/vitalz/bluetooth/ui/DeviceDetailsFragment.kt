package com.technoidentity.vitalz.bluetooth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.bluetooth.connection.BleConnection
import com.technoidentity.vitalz.databinding.FragmentDeviceDetailsBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.utils.asciiToChar
import com.technoidentity.vitalz.utils.isTablet
import com.technoidentity.vitalz.utils.showToast
import kotlinx.coroutines.flow.collect
import timber.log.Timber

class DeviceDetailsFragment : Fragment() {


     val sharedViewmodel: SharedViewModel by activityViewModels()

    lateinit var binding: FragmentDeviceDetailsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDeviceDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
//            sharedViewmodel.isDeviceConnected.collect{
//                Timber.d("devicedetails $it")
//                if (it) {
//                    showToast(requireContext(), "Device is connected devicedetails")

                    sharedViewmodel.connectedDevice.observe(viewLifecycleOwner, {
                        Timber.d("${it.device} ${ it.connectionStatus.toString()}")
                        binding.apply {
                            patchId.text = it.device.name
                            battery.text = it.battery.also {
                                Timber.i("Battery ${it}")
                            }
                            when (it.connectionStatus) {
                                BleConnection.DeviceConnected -> {
                                    connection.text = getString(R.string.connected)
                                }
                                else -> {
                                    connection.text = getString(R.string.disconnected)
                                }
                            }

                        }

                    })
                }
//                showToast(requireContext(), "Device is disconnected devicedetails")
//            }


        binding.patientDetailsBtn.setOnClickListener {
            //findNavController().navigate(R.id.action_deviceDetailsFragment_to_singlePatientDashboardFragment)
            when (isTablet(requireContext())) {
                true -> findNavController().navigate(R.id.action_deviceDetailsFragment_to_multiPatientDashboardFragment)
                else -> findNavController().navigate(R.id.action_deviceDetailsFragment_to_singlePatientDashboardFragment)
            }

        }
    }
}