package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.FragmentViewProfileBinding

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentViewProfileBinding
    private lateinit var navController: NavController
    private lateinit var profileData: SinglePatientDashboardResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewProfileBinding.inflate(layoutInflater)
        navController =  Navigation.findNavController(container!!)

        //Getting Arguments From last Fragment
//        profileData = arguments?.getBundle("patientData")

        binding.ivBackBtn.setOnClickListener {
            navController.navigateUp()
        }



        return binding.root
    }
}