package com.technoidentity.vitalz.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.databinding.CaretakerNurseDashboardBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import com.technoidentity.vitalz.utils.isTablet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseCareTakerDashboardFragment : Fragment() {

    val viewModel: NurseCareTakerDashboardViewModel by viewModels()
    lateinit var binding: CaretakerNurseDashboardBinding
    private lateinit var progressDialog: CustomProgressDialog
    lateinit var singlePatientDashboardResponse: SinglePatientDashboardResponse

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CaretakerNurseDashboardBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //check if nurse or caretaker
        //nurse -> visibility of BLE layout
        if (isTablet(requireContext())) {
            binding.layoutBle.visibility = View.VISIBLE
        }else {
            binding.layoutBle.visibility = View.GONE
        }
            //Getting Arguments From last Fragment
            val patientId = arguments?.getString("patientId")

            //Api call to fetch Latest data
            patientId?.let {
                singleDashboardApi(it)
                progressDialog.showLoadingDialog()
            } ?: run {
                Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
            }

            //ViewProfilePage
            binding.ivViewProfile.setOnClickListener {
                findNavController().navigate(
                    R.id.action_nurseCareTakerDashboardFragment_to_patientProfileFragment,
                    bundleOf("patientData" to singlePatientDashboardResponse)
                )
            }

        //heartRate Detail
        binding.layoutHeartRate.setOnClickListener {
            findNavController().navigate(R.id.action_nurseCareTakerDashboardFragment_to_dashboardDetailsHeartFragment,
                bundleOf("heartData" to singlePatientDashboardResponse.heartRate))
        }

        return binding.root
    }

    private fun setPieChartData() {
        //Heart
        with(binding.pieChartHeartLayout) {
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
        }

        val heartEntries: List<Entry> =
            singlePatientDashboardResponse.heartRate.ratePerMinute.mapIndexed { index, i ->
                Entry(
                    index.toFloat(),
                    i.toFloat()
                )
            }

        val dataSetHeart = LineDataSet(heartEntries, "Unused label").apply {
            color = Color.RED
            setDrawCircles(false)
            valueTextColor = Color.GRAY
            highLightColor = Color.RED
            setDrawValues(false)
            lineWidth = 3f
            isHighlightEnabled = true
            setDrawHighlightIndicators(false)
        }

        binding.pieChartHeartLayout.data = LineData(dataSetHeart)
        binding.pieChartHeartLayout.invalidate()

        //Respiratory
        with(binding.pieChartRespiratoryLayout) {
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
        }

        val respiratoryEntries: List<Entry> =
            singlePatientDashboardResponse.respiratoryRate.ratePerMinute.mapIndexed { index, i ->
                Entry(
                    index.toFloat(),
                    i.toFloat()
                )
            }
        val dataSetRespiratory = LineDataSet(respiratoryEntries, "Unused label").apply {
            color = Color.BLUE
            setDrawCircles(false)
            valueTextColor = Color.GRAY
            highLightColor = Color.RED
            setDrawValues(false)
            lineWidth = 3f
            isHighlightEnabled = true
            setDrawHighlightIndicators(false)
        }

        binding.pieChartRespiratoryLayout.data = LineData(dataSetRespiratory)
        binding.pieChartRespiratoryLayout.invalidate()
    }

    private fun singleDashboardApi(mobile: String) {
        viewModel.getSinglePatientData(mobile)
        viewModel.expectedResult.observe(viewLifecycleOwner, {
            when (it) {
                is NurseCareTakerDashboardViewModel.SinglePatient.Success -> {
                    progressDialog.dismissLoadingDialog()
                    singlePatientDashboardResponse = it.data
                    setDataFromApiResponse(it.data)
                    setPieChartData()
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
        binding.tvBpCount.text = (data.bloodPressure.systolicPressure.last()
            .toString() + "/" + data.bloodPressure.diastolicPressure.last().toString())
        binding.tvActivityCount.text = data.step.stepCount.toString()
        binding.tvPostureCount.text = data.posture.bodyPosture.last()
        binding.tvOxygenSaturationCount.text =
            data.oxygenSaturation.oxygenPercentage.last().toString()
        binding.tvWeightCount.text = data.weight.weight.toString()
        binding.tvTimeLeft.text = data.device.batteryPercentage.toString()
    }
}
