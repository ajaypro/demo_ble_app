package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.technoidentity.vitalz.databinding.MultiplePatientDashboardBinding

class DoctorDashboardFragment : Fragment() {

    private lateinit var binding: MultiplePatientDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MultiplePatientDashboardBinding.inflate(layoutInflater)

        return binding.root
    }
}