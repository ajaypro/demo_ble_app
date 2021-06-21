package com.technoidentity.vitalz.dashboard

import android.content.Context
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
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.MultiplePatientDashboardBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorDashboardFragment : Fragment() {

    private lateinit var binding: MultiplePatientDashboardBinding
    private lateinit var token: String
    val viewModel: DoctorDashboardViewModel by viewModels()
    private lateinit var doctorAdapter: MultiplePatientAdapter
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MultiplePatientDashboardBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //get Shared data
        val sharedPreferences =
            context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        token = sharedPreferences?.getString(Constants.TOKEN, null).toString()

        //RecyclerViewSetup
        setUpRecyclerView()

        //Api call to fetch Latest data
        multiplePatientDashboardApi(token)

        return binding.root
    }

    private fun multiplePatientDashboardApi(token: String) {
        progressDialog.showLoadingDialog()
            viewModel.getMultiplePatientData(token)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is DoctorDashboardViewModel.SinglePatient.Success -> {
                        doctorAdapter.multiplePatient = it.data
                        progressDialog.dismissLoadingDialog()
                        Toast.makeText(context, it.resultText, Toast.LENGTH_SHORT).show()
                    }

                    is DoctorDashboardViewModel.SinglePatient.Failure -> {
                        progressDialog.dismissLoadingDialog()
                        binding.rvMultiplePatientDashboardList.visibility = View.GONE
                        binding.tvNoRecords.visibility = View.VISIBLE
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
    }

    private fun setUpRecyclerView() = binding.rvMultiplePatientDashboardList.apply {
        doctorAdapter = MultiplePatientAdapter(this@DoctorDashboardFragment)
        adapter = doctorAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun onItemClicked(layoutPosition: Int) {
        val bundle = bundleOf("patientId" to doctorAdapter.multiplePatient[layoutPosition].patientId)
        findNavController().navigate(R.id.action_doctorDashboardFragment_to_nurseCareTakerDashboardFragment, bundle )
    }

}