package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.PatientData
import com.technoidentity.vitalz.databinding.FragmentPatientListBinding

class PatientListFragment : Fragment() {

    lateinit var binding: FragmentPatientListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientListBinding.inflate(inflater)

        val patientData = mutableListOf(
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
            PatientData("Akashay Kumar", "56", "M", "4579", "Yashoda Hospial , SC, Telanagana"),
        )

        val adapter = PatientAdapter(patientData)
        binding.rvPatientList.adapter = adapter
        binding.rvPatientList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}