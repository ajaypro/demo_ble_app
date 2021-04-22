package com.technoidentity.vitalz.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.user.UserActivity


class UserSelectionFragment: Fragment() {
    var intent = Intent()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_user_selection, container, false)
        intent = Intent(activity, UserActivity::class.java)
        val hospitalButton: View = rootView.findViewById(R.id.hospital_layout)
        val patientButton: View = rootView.findViewById(R.id.patient_care_layout)

        //when Hospital Block is clicked
        hospitalButton.setOnClickListener {
            intent.putExtra("hospitalLayout", "HospitalLogin")
            startActivity(intent)
        }
        //when patient Block is clicked
        patientButton.setOnClickListener {
            intent.putExtra("patientLayout", "PatientLogin")
            startActivity(intent)
        }
        return rootView
    }
}