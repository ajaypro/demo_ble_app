package com.technoidentity.vitalz.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R

class UserSelectionFragment : Fragment(R.layout.fragment_user_selection) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)
        val hospitalLayout = view.findViewById<View>(R.id.hospital_layout)
        val careTakerLayout = view.findViewById<View>(R.id.patient_care_layout)

        hospitalLayout.setOnClickListener {
            navController.navigate(R.id.doctorNurseLoginFragment)
        }
        careTakerLayout.setOnClickListener {
            navController.navigate(R.id.careTakerMobileLoginFragment)
        }
    }
}
