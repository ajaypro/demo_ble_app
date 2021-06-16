package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.FragmentViewProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        profileData?.let { setDataInUI(it) }

        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    private fun setDataInUI(profileData: SinglePatientDashboardResponse) {
        binding.etName.text = profileData.name
        binding.etAge.text = profileData.age.toString()
        binding.etGender.text = profileData.gender
        binding.etHeight.text = profileData.height.toString()
        binding.etWeight.text = profileData.weight.weight.toString()
        binding.etAddress.text = profileData.address
        binding.tvContactNumber.text = profileData.doctorContactNumber
        binding.etEmergencyContactName.text = profileData.emergencyContactName
        binding.etEmergencyContactNumber.text = profileData.emergencyContactNumber
        binding.etAttendingDoctor.text = profileData.doctorName
        binding.etIdHospital.text = profileData.hospitalId
    }
}