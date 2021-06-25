package com.technoidentity.vitalz.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentMultiplePatientDashboardBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.utils.CustomProgressDialog
import com.technoidentity.vitalz.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultiPatientDashboardFragment : Fragment() {

    private lateinit var binding: FragmentMultiplePatientDashboardBinding
    private lateinit var token: String
    val viewModel: MultiPatientDashboardViewModel by viewModels()
    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var doctorAdapter: MultiplePatientAdapter
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMultiplePatientDashboardBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //get Shared data
        val sharedPreferences =
            context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        token = sharedPreferences?.getString(Constants.TOKEN, null).toString()

        //Check Mobile or Tablet
        //mobile -> open multiple patient Dashboard page
        //tablet -> check BLE Connected or not
        //yes -> open Multiple Patient dashboard page
        //no -> connect BLE and then navigate to Multiple patient dashboard page

        //RecyclerViewSetup
        setUpRecyclerView()

        //Api call to fetch Latest data
        if(token.isNotEmpty()) {
            multiplePatientDashboardApi(token)
        } else {
            showToast(context, "Un-Authorized")
        }

        return binding.root
    }

    private fun multiplePatientDashboardApi(token: String) {
        progressDialog.showLoadingDialog()
            viewModel.getMultiplePatientData().observe(viewLifecycleOwner){
                //check for it.reason success case
               if (it.multiPatientDashboardList.isNullOrEmpty()) {
                   progressDialog.dismissLoadingDialog()
                  doctorAdapter.multiplePatient = it.multiPatientDashboardList
               } else {

                   progressDialog.dismissLoadingDialog()
                   binding.apply {
                       //check for it.reason error case
                       rvMultiplePatientDashboardList.visibility = View.GONE
                       tvNoRecords.visibility = View.VISIBLE
                   }
               }
            }
//            viewModel.expectedResult.observe(viewLifecycleOwner, {
//                when (it) {
//                    is MultiPatientDashboardViewModel.SinglePatient.Success -> {
//                        doctorAdapter.multiplePatient
//                        progressDialog.dismissLoadingDialog()
//                        Toast.makeText(context, it.resultText, Toast.LENGTH_SHORT).show()
//                    }
//
//                    is MultiPatientDashboardViewModel.SinglePatient.Failure -> {
//                        progressDialog.dismissLoadingDialog()
//                        binding.rvMultiplePatientDashboardList.visibility = View.GONE
//                        binding.tvNoRecords.visibility = View.VISIBLE
//                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
//                    }
//                    else -> Unit
//                }
//            })
    }

    private fun setUpRecyclerView() = binding.rvMultiplePatientDashboardList.apply {
        doctorAdapter = MultiplePatientAdapter(this@MultiPatientDashboardFragment)
        adapter = doctorAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun onItemClicked(layoutPosition: Int) {
    }

}