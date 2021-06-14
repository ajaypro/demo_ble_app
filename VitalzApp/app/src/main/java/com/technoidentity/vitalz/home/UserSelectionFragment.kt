package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentUserSelectionBinding

class UserSelectionFragment : Fragment() {

    private lateinit var binding: FragmentUserSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserSelectionBinding.inflate(layoutInflater)
        binding.hospitalLayout.setOnClickListener {
            findNavController().navigate(R.id.action_userSelectionFragment2_to_doctorNurseLoginFragment)
        }
        binding.patientCareLayout.setOnClickListener {
            findNavController().navigate(R.id.action_userSelectionFragment2_to_careTakerMobileLoginFragment)
        }
        return binding.root
    }
}
