package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.MultiplePatientDashboardBinding

class DoctorDashboardFragment : Fragment() {

    private lateinit var binding: MultiplePatientDashboardBinding
    var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MultiplePatientDashboardBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(container!!)

        //Check Mobile or Tablet
        //mobile -> open multiple patient Dashboard page
        //tablet -> check BLE Connected or not
        //yes -> open Multiple Patient dashboard page
        //no -> connect BLE and then navigate to Multiple patient dashboard page

        return binding.root
    }

}