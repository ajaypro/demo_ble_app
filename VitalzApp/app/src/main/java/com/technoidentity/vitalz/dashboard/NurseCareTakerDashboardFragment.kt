package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.single_patient.HeartRate
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
    lateinit var lineDataSet: LineDataSet
    lateinit var lineListHeart: ArrayList<HeartRate>
    lateinit var singlePatientDashboardResponse: SinglePatientDashboardResponse
    lateinit var lineData: LineData

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

        //ViewProfilePage
        binding.ivViewProfile.setOnClickListener {
            navController!!.navigate(R.id.patientProfileFragment)
        }

        //pieChart
        lineListHeart = ArrayList()

        return binding.root
    }

    private fun singleDashboardApi(mobile: String) {
            viewModel.getSinglePatientData(mobile)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is NurseCareTakerDashboardViewModel.SinglePatient.Success -> {
                        progressDialog.dismissLoadingDialog()
                        singlePatientDashboardResponse = it.data
                        setDataFromApiResponse(it.data)
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