package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.CaretakerNurseDashboardBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseCareTakerDashboardFragment : Fragment() {

    val viewModel: NurseCareTakerDashboardViewModel by viewModels()
    lateinit var binding: CaretakerNurseDashboardBinding
    private lateinit var progressDialog: CustomProgressDialog
    private lateinit var responseData: SinglePatientDashboardResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CaretakerNurseDashboardBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //check if nurse or caretaker
        //nurse -> visibility of BLE layout

        //Getting Arguments From last Fragment
        val patientId = arguments?.getString("patientId")
        Log.v("Check","Patient Id receiving $patientId")

        //Api call to fetch Latest data
        if (patientId != null){
            singleDashboardApi(patientId)
            progressDialog.showLoadingDialog(
                title = "Vitalz App",
                message = "Loading...",
                isCancellable = false
            )
        }else{
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
        }

        //ViewProfilePage
        binding.ivViewProfile.setOnClickListener {
            Log.v("Stage VP", "View Profile $responseData")
            findNavController().navigate(R.id.patientProfileFragment , bundleOf("patientData" to responseData))
        }

        return binding.root
    }

    private fun singleDashboardApi(mobile: String) {
            viewModel.getSinglePatientData(mobile)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is NurseCareTakerDashboardViewModel.SinglePatient.Success -> {
                        progressDialog.dismissLoadingDialog()
                        setDataFromApiResponse(it.data)
                        responseData = it.data
                    }

                    is NurseCareTakerDashboardViewModel.SinglePatient.Failure -> {
                        progressDialog.dismissLoadingDialog()
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
    }

    private fun setDataFromApiResponse(data: SinglePatientDashboardResponse) {
        binding.tvSelectedPatient.text = data.name
        binding.tvHeartRateCount.text = data.heartRate.ratePerMinute.last().toString()
        binding.tvRespiratoryCount.text = data.respiratoryRate.ratePerMinute.last().toString()
        binding.tvTemperatureCount.text = data.temperature.bodyTemperature.last().toString()
        binding.tvBpCount.text = (data.bloodPressure.systolicPressure.last().toString()+ "/" +data.bloodPressure.diastolicPressure.last().toString())
        binding.tvActivityCount.text = data.step.stepCount.toString()
        binding.tvPostureCount.text = data.posture.bodyPosture.last()
        binding.tvOxygenSaturationCount.text = data.oxygenSaturation.oxygenPercentage.last().toString()
        binding.tvWeightCount.text = data.weight.weight.toString()
    }
}