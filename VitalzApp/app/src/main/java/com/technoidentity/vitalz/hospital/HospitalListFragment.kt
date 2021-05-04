package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.HospitalData
import com.technoidentity.vitalz.databinding.FragmentHospitalListBinding

class HospitalListFragment : Fragment() {

    lateinit var binding: FragmentHospitalListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHospitalListBinding.inflate(inflater)

        val hospitalList = mutableListOf(
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
            HospitalData("Yashoda Hospital", "7894", "Secundarbad Hyderabad, Telangana"),
        )

        val adapter = HospitalAdapter(hospitalList)
        binding.rvHospitalList.adapter = adapter
        binding.rvHospitalList.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}