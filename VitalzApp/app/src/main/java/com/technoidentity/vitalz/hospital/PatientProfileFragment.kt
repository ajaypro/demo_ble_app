package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.databinding.FragmentViewProfileBinding

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentViewProfileBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewProfileBinding.inflate(layoutInflater)
        navController =  Navigation.findNavController(container!!)

        binding.ivBackBtn.setOnClickListener {
            navController.navigateUp()
        }



        return binding.root
    }
}