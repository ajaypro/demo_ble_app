package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.databinding.FragmentHospitalListBinding

class HospitalListFragment : Fragment() {

    lateinit var binding: FragmentHospitalListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHospitalListBinding.inflate(inflater)

        //Api call and set response in adapter

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val adapter = HospitalAdapter()
        binding.rvHospitalList.adapter = adapter
        binding.rvHospitalList.layoutManager = LinearLayoutManager(context)
    }
}