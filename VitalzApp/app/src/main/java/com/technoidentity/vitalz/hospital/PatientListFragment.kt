package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.databinding.FragmentPatientListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientListFragment : Fragment(), PatientAdapter.OnItemClickListener {

    lateinit var binding: FragmentPatientListBinding
    private var mobile : String? = null
    private var hospitalId : String? = null
    val viewModel: PatientViewModel by viewModels()
    private lateinit var patientAdapter: PatientAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientListBinding.inflate(inflater)

        //Getting Arguments From last Fragment
        mobile = arguments?.getString("mobile")
        hospitalId = arguments?.getString("hospitalId")
        Log.v("Check", "Stage ID $hospitalId and mobile $mobile")

        //setup RecyclerView
        setUpRecyclerView()

        if (mobile != null && hospitalId != null){
            getPatientList(mobile, hospitalId)
        }else{
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
//           TODO Logout
        }

        return binding.root
    }

    private fun getPatientList(mobile: String?, hospitalId: String?) {
        lifecycleScope.launchWhenCreated {
            viewModel.getPatientListData(mobile!!,hospitalId!!)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is PatientViewModel.PatientData.Success -> {
                        patientAdapter.patient = it.data!!
                    }

                    is PatientViewModel.PatientData.Failure -> {
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
        }
    }

    private fun setUpRecyclerView() = binding.rvPatientList.apply {
        patientAdapter = PatientAdapter(this@PatientListFragment)
        adapter = patientAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClicked(position: Int) {
        Toast.makeText(context, "Selected Item $position", Toast.LENGTH_SHORT).show()
    }
}