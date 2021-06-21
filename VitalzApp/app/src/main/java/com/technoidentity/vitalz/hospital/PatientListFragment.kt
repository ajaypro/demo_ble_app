package com.technoidentity.vitalz.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentPatientListBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientListFragment : Fragment(), PatientAdapter.OnItemClickListener {

    lateinit var binding: FragmentPatientListBinding
    lateinit var hospitalId : String
    lateinit var mobile : String
    val viewModel: PatientViewModel by viewModels()
    private lateinit var patientAdapter: PatientAdapter
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPatientListBinding.inflate(inflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //Getting Arguments From last Fragment
        mobile = arguments?.getString("mobile").toString()
        hospitalId = arguments?.getString("hospitalId").toString()

        //setup RecyclerView
        setUpRecyclerView()

        //backBtn
        binding.ivBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        mobile.let {mobile->
            hospitalId.let { hospital->
                getPatientList(mobile, hospital)
            }
        }

        //In Search Cancel button visibility GONE , please enable while typing

        return binding.root
    }

    private fun getPatientList(mobile: String, hospitalId: String) {
        progressDialog.showLoadingDialog()
        viewModel.getPatientListData(mobile, hospitalId)
        viewModel.expectedResult.observe(viewLifecycleOwner, {
            when (it) {
                is PatientViewModel.PatientData.Success -> {
                    if (it.data.isEmpty()) {
                        progressDialog.dismissLoadingDialog()
                        binding.rvPatientList.visibility = View.GONE
                        binding.tvNoRecords.visibility = View.VISIBLE
                        binding.tvNoRecordBackMsg.visibility = View.VISIBLE
                    } else {
                        patientAdapter.patient = it.data
                        progressDialog.dismissLoadingDialog()
                    }
                }

                is PatientViewModel.PatientData.Failure -> {
                    progressDialog.dismissLoadingDialog()
                    Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        })
    }

    private fun setUpRecyclerView() = binding.rvPatientList.apply {
        patientAdapter = PatientAdapter(this@PatientListFragment)
        adapter = patientAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClicked(position: Int) {
        val bundle = bundleOf("patientId" to patientAdapter.patient[position].id.toString())
        findNavController().navigate(
            R.id.action_patientListFragment_to_nurseCareTakerDashboardFragment, bundle
        )
    }
}
