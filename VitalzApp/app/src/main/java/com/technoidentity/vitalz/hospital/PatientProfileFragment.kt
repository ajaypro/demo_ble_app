package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.FragmentViewProfileBinding

class PatientProfileFragment : Fragment() {

    private lateinit var binding: FragmentViewProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewProfileBinding.inflate(layoutInflater)

        //Getting Arguments From last Fragment
        val profileData: SinglePatientDashboardResponse? = arguments?.getParcelable("patientData")
        Log.v("Check", "Profile $profileData")
        setDataInUI(profileData)

        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setDataInUI(profileData: SinglePatientDashboardResponse?) {
        binding.etName.text = profileData?.name
        binding.etAge.text = profileData?.dateOfBirth
    }
}