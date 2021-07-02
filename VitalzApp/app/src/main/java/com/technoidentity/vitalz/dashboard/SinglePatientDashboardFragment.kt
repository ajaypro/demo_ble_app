package com.technoidentity.vitalz.dashboard

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentSinglePaitentDashboardBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.notifications.datamodel.VitalzTelemetryNotification
import com.technoidentity.vitalz.utils.CustomProgressDialog
import com.technoidentity.vitalz.utils.HEART_RATE_DATA
import com.technoidentity.vitalz.utils.isTablet
import com.technoidentity.vitalz.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class SinglePatientDashboardFragment : Fragment() {

    private val singlePatientDashboardViewModel: SinglePatientDashboardViewModel by viewModels()
    val sharedViewModel: SharedViewModel by activityViewModels()
    lateinit var binding: FragmentSinglePaitentDashboardBinding
    private lateinit var progressDialog: CustomProgressDialog
    lateinit var singlePatientDashboardResponse: SinglePatientDashboardResponse
    lateinit var patientId: String
    var heartRateList = mutableListOf<Byte>()
    lateinit var heartEntries: List<Entry>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSinglePaitentDashboardBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //check if nurse or caretaker
        //nurse -> visibility of BLE layout
        if (isTablet(requireContext())) {
            binding.layoutBle.visibility = View.VISIBLE
        } else {
            binding.layoutBle.visibility = View.GONE
        }

        //Get Patient Id from Shared Prefs
        val sharedPreferences =
            context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        patientId = sharedPreferences?.getString(Constants.PATIENTID, null).toString()

        //ViewProfilePage
        binding.ivViewProfile.setOnClickListener {
            findNavController().navigate(
                R.id.action_singlePatientDashboardFragment_to_patientProfileFragment,
                bundleOf("patientData" to singlePatientDashboardResponse)
            )
        }

        binding.layoutHeartRate.setOnClickListener {
            findNavController().navigate(
                R.id.action_singlePatientDashboardFragment_to_singlePatientDetailFragment , bundleOf("isAlive" to "heart"))
        }

        binding.layoutRespiratory.setOnClickListener {
            findNavController().navigate(
                R.id.action_singlePatientDashboardFragment_to_singlePatientDetailFragment , bundleOf("isAlive" to "respiratory"))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(isTablet(requireContext())) {
            false -> {
                bluetoothData(patientId)
            }
            true -> {
                singleDashboardApi(patientId)
            }
        }
    }

    private fun setPieChartData(heartEntries: List<Entry>) {
        //Heart
        with(binding.pieChartHeartLayout) {
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
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

    private fun bluetoothData(patientId: String) {
        progressDialog.showLoadingDialog()

        lifecycleScope.launchWhenCreated {

            sharedViewModel.apply {
                heartRateData.collect {
                    progressDialog.dismissLoadingDialog()

                    it.forEach { heartRate ->
                        if(heartRate.toInt() > 30) {
                            singlePatientDashboardViewModel.sendTelemetryNotification(
                                VitalzTelemetryNotification(patientId, HEART_RATE_DATA, heartRate.toString()))
                        }
                        binding.tvHeartRateCount.text = heartRate.toString().also {
                            Timber.d("heartrate $it")
                        }
                        heartRateList.add(heartRate)

                    }
                    sharedViewModel.sendHeartRateToServer(patientId, HEART_RATE_DATA, heartRateList)

                    it.mapIndexed { index, i ->
                        Entry(index.toFloat(), i.toFloat())
                    }.apply {
                        setPieChartData(this)
                    }

                    bodyPosture.observe(viewLifecycleOwner) {
                        if (!it.isNullOrEmpty()) {
                            binding.tvPostureValue.text = it.also {
                                Timber.i("body ${it}")
                            }
                        } else {
                            binding.tvPostureValue.text = "".also {
                                Timber.i("body ${it}")
                            }
                        }
                    }

                    bodyTemperature.observe(viewLifecycleOwner) {
                        if (!it.isNullOrEmpty()) {
                            binding.tvTemperatureValue.text = it.also {
                                Timber.i("temp ${it}")
                            }
                        } else {
                            binding.tvTemperatureValue.text = "".also {
                                Timber.i("temp ${it}")
                            }
                        }
                    }

                }
            }

        }
    }

        private fun singleDashboardApi(patientId: String) {
            progressDialog.showLoadingDialog()
            singlePatientDashboardViewModel.getSinglePatientData(patientId)
            singlePatientDashboardViewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is SinglePatientDashboardViewModel.SinglePatient.Success -> {
                        progressDialog.dismissLoadingDialog()
                        it.data.apply {
                            singlePatientDashboardResponse = this
                            setDataFromApiResponse(this)
                            this.heartRate.ratePerMinute.mapIndexed { index, i ->
                                Entry(index.toFloat(), i.toFloat())
                            }.run {
                                setPieChartData(this)
                            }
                        }

                    }

                    is SinglePatientDashboardViewModel.SinglePatient.Failure -> {
                        progressDialog.dismissLoadingDialog()
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
        }

        private fun setDataFromApiResponse(data: SinglePatientDashboardResponse) {
            binding.apply {
                tvSelectedPatient.text = data.name
                tvHeartRateCount.text = data.heartRate.ratePerMinute.last().toString()
                tvRespiratoryCount.text = data.respiratoryRate.ratePerMinute.last().toString()
                tvTemperatureValue.text = data.temperature.bodyTemperature.last().toString()
                tvBpValue.text = (data.bloodPressure.systolicPressure.last()
                    .toString() + "/" + data.bloodPressure.diastolicPressure.last().toString())
                tvActivityValue.text = data.step.stepCount.toString()
                tvPostureValue.text = data.posture.bodyPosture.last()
                tvOxygenSaturationValue.text =
                    data.oxygenSaturation.oxygenPercentage.last().toString()
                tvWeightValue.text = data.weight.weight.toString()
                tvTimeLeft.text = data.device.batteryPercentage.toString()
            }
        }
    }