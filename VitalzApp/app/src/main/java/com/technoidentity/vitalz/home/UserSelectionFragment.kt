package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
        val navController: NavController = Navigation.findNavController(container!!)
        binding.hospitalLayout.setOnClickListener {
            navController.navigate(R.id.doctorNurseLoginFragment)
        }
        binding.patientCareLayout.setOnClickListener {
            navController.navigate(R.id.careTakerMobileLoginFragment)
        }
        return binding.root
    }
}
