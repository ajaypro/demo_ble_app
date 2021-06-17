package com.technoidentity.vitalz.dashboard

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.single_patient.HeartRate
import com.technoidentity.vitalz.databinding.FragmentDashboardHeartDetailBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import java.util.*
import kotlin.collections.ArrayList


class DashboardDetailsHeartFragment : Fragment() {

    lateinit var binding: FragmentDashboardHeartDetailBinding
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardHeartDetailBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())
        //Getting Arguments From last Fragment
        val heartData: HeartRate? = arguments?.getParcelable("heartData")

        //Calender declaration
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        //condition for 7 days more than the selected day
        c.add(Calendar.DAY_OF_MONTH, +6)

        //Always check for month because they start from 0 as jan and 11 as dec. So, add 1 in month. And Add 2 Days more than present day & change the color of the DatePicker
//        val datePickerDialog = DatePickerDialog(
//            this,
//            R.style.DialogTheme,
//            DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
//                val date = "" + mYear + "-" + (mMonth + 1) + "-" + mDay
//                (v as Button).text = date
//                if (selectedDate == "dateOfOrder") {
//                    cartViewModel.dateOfOrder = date
//                } else {
//                    cartViewModel.dateOfDelivery = date
//                }
//            }, year, month, day
//        )
//        datePickerDialog.datePicker.minDate = c.timeInMillis
//        datePickerDialog.show()

        //Bar Chart
        initializeBarChart()
        heartData?.let { setBarChart(it) }
        return binding.root
    }

    private fun setBarChart(heartData: HeartRate) {
        val entries = ArrayList<BarEntry>()
        for (element in heartData.ratePerMinute)
        entries.addAll(listOf(BarEntry(element.toFloat(), 0f)))

        val barDataSet = BarDataSet(entries, "Cells")
        val data = BarData(barDataSet)
        binding.heartBarChart.data = data // set the data and list of lables into chart

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.button_blue)

        binding.heartBarChart.animateY(5000)
    }

    private fun initializeBarChart() {
        binding.heartBarChart.description.isEnabled = false
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        binding.heartBarChart.setMaxVisibleValueCount(4)
        binding.heartBarChart.xAxis.setDrawGridLines(false)
        // scaling can now only be done on x- and y-axis separately
        binding.heartBarChart.setPinchZoom(false)
        binding.heartBarChart.setDrawBarShadow(false)
        binding.heartBarChart.setDrawGridBackground(false)
        val xAxis: XAxis = binding.heartBarChart.xAxis
        xAxis.setDrawGridLines(false)
        binding.heartBarChart.axisLeft.setDrawGridLines(false)
        binding.heartBarChart.axisRight.setDrawGridLines(false)
        binding.heartBarChart.axisRight.isEnabled = false
        binding.heartBarChart.axisLeft.isEnabled = true
        binding.heartBarChart.xAxis.setDrawGridLines(false)
        // add a nice and smooth animation
        binding.heartBarChart.animateY(1500)
        binding.heartBarChart.legend.isEnabled = false
        binding.heartBarChart.axisRight.setDrawLabels(false)
        binding.heartBarChart.axisLeft.setDrawLabels(true)
        binding.heartBarChart.setTouchEnabled(false)
        binding.heartBarChart.isDoubleTapToZoomEnabled = false
        binding.heartBarChart.xAxis.isEnabled = true
        binding.heartBarChart.xAxis.position = XAxisPosition.BOTTOM
        binding.heartBarChart.invalidate()
    }
}