package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.CaretakerNurseDashboardBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseCareTakerDashboardFragment : Fragment() {

    val viewModel: NurseCareTakerDashboardViewModel by viewModels()
    private var patientId : String? = null
    lateinit var binding: CaretakerNurseDashboardBinding
    var navController: NavController? = null
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CaretakerNurseDashboardBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(container!!)
        progressDialog = CustomProgressDialog(this.requireContext())

        //check if nurse or caretaker
        //nurse -> visibility of BLE layout

        //Getting Arguments From last Fragment
        patientId = arguments?.getString("patientId")
        Log.v("Check","Patient Id receiving $patientId")

        //Api call to fetch Latest data
        if (patientId != null){
            singleDashboardApi(patientId!!)
            progressDialog.showLoadingDialog(
                title = "Vitalz App",
                message = "Loading...",
                isCancellable = false
            )
        }else{
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun singleDashboardApi(mobile: String) {
        lifecycleScope.launchWhenCreated {
            viewModel.getSinglePatientData(mobile)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is NurseCareTakerDashboardViewModel.SinglePatient.Success -> {
                        progressDialog.dismissLoadingDialog()
                        setDataFromApiResponse(it.data)
                        Toast.makeText(context, it.resultText, Toast.LENGTH_SHORT).show()
                    }

                    is NurseCareTakerDashboardViewModel.SinglePatient.Failure -> {
                        progressDialog.dismissLoadingDialog()
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
        }
    }

    private fun setDataFromApiResponse(data: SinglePatientDashboardResponse) {
        binding.tvSelectedPatient.text = data.name
//        binding.tvHeartRateCount.text = data.heartRate.ratePerMinute
//        binding.tvRespiratoryCount.text = data
//        binding.tvTemperatureCount.text = data
//        binding.tvBpCount.text = data
//        binding.tvActivityCount.text = data
//        binding.tvPostureCount.text = data
//        binding.tvOxygenSaturationCount.text = data
//        binding.tvWeightCount.text = data
    }
}