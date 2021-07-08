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
import com.technoidentity.vitalz.utils.*
import timber.log.Timber

class DeviceDetailsFragment : Fragment() {


    val sharedViewmodel: SharedViewModel by activityViewModels()

    lateinit var binding: FragmentDeviceDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeviceDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {

            sharedViewmodel.connectedDeviceData.observe(viewLifecycleOwner, { it ->

                binding.apply {
                    patchId.text = sharedViewmodel.registeredDevice?.patchId

                    sharedViewmodel.deviceBattery.observe(viewLifecycleOwner) { batteryValue ->
                        battery.text = batteryValue.toString().plus("%")
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

        binding.patientDetailsBtn.setOnClickListener {

            when (isTablet(requireContext())) {
                true -> findNavController().navigate(R.id.action_deviceDetailsFragment_to_multiPatientDashboardFragment)
                else -> findNavController().navigate(R.id.action_deviceDetailsFragment_to_singlePatientDashboardFragment)
            }

        }
    }
}