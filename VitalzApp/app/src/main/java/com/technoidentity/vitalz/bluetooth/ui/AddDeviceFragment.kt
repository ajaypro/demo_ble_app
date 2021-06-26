package com.technoidentity.vitalz.bluetooth.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentAddDeviceBinding
import com.technoidentity.vitalz.home.SharedViewModel


class AddDeviceFragment : Fragment() {

    lateinit var binding: FragmentAddDeviceBinding

    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDeviceBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addDeviceBtn.setOnClickListener {
            findNavController().navigate(R.id.action_addDeviceFragment_to_bleScanResultFragment)
        }

    }
}
