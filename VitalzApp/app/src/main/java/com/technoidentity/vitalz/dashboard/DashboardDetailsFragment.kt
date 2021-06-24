package com.technoidentity.vitalz.dashboard

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentDashboardHeartDetailBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class DashboardDetailsFragment : Fragment() {

    private lateinit var datePickerDialog: DatePickerDialog
    lateinit var binding: FragmentDashboardHeartDetailBinding
    private lateinit var progressDialog: CustomProgressDialog
    val viewModel: DashboardDetailsViewModel by viewModels()
    lateinit var selectedDate: Date
    var selectedDay: Int = 0
    var selectedMonth: Int = 0
    var selectedYear: Int = 0
    var isEndDateSelected: Boolean = false
    val c = Calendar.getInstance()
    val day = c.get(Calendar.DAY_OF_MONTH)
    val month = c.get(Calendar.MONTH)
    val year = c.get(Calendar.YEAR)

    init {
        selectedDay = day
        selectedMonth = month+1
        selectedYear = year
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardHeartDetailBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //setupDefaultDates
        setInitialDates()

        //Getting Arguments From last Fragment
//        - heartrate
//        - respiratory
//        - bloodpressure
//        - temprature
//        - oxygen

        //Getting Arguments From last Fragment
        val isAlive = arguments?.getString("isAlive")
        if (isAlive == "heart") {
            binding.tvHeartRate.text = "Heart Rate"
        } else if (isAlive == "respiratory") {
            binding.tvHeartRate.text = "Respiratory"
        }

        //Start Date
        binding.layoutStartDateSelect.setOnClickListener {
            //fetching selected End date as Calender instance
            val cal: Calendar = millisToDate()
            //Subtracting 7 days from selected End date
            cal.add(Calendar.DAY_OF_MONTH, -6)

            //Always check for month because they start from 0 as jan and 11 as dec. So, add 1 in month. And Add 2 Days more than present day & change the color of the DatePicker
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    val date = "" + mYear + "-" + (mMonth + 1) + "-" + mDay
                    binding.tvSelectedStartDate.text = date
                }, year, month, day
            )
            //Setting Mini. Date from Selected End date
            datePickerDialog.datePicker.minDate = cal.timeInMillis
            //Updating Start date if end date is selected
            if (isEndDateSelected) {
                datePickerDialog.updateDate(
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                )
            }
            //Setting Max. Date from Selected End date
            datePickerDialog.datePicker.maxDate = millisToDate().timeInMillis
            datePickerDialog.show()
        }

        //End Date
        binding.layoutEndDateSelect.setOnClickListener {
            datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.DialogTheme,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    val date = "" + mYear + "-" + (mMonth + 1) + "-" + mDay
                    binding.tvSelectedEndDate.text = date
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                    selectedDate = dateFormat.parse(date)
                    isEndDateSelected = true
                    selectedYear = mYear
                    selectedMonth = mMonth
                    selectedDay = mDay
                }, year, month, day
            )
            if (isEndDateSelected) {
                datePickerDialog.updateDate(selectedYear, selectedMonth, selectedDay)
            }
            datePickerDialog.datePicker.maxDate = c.timeInMillis
            datePickerDialog.show()
        }

        //Bar Chart
        initializeBarChart()
        setBarChart()
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    private fun setInitialDates() {
        val endDateConfig = millisToDate()
        binding.tvSelectedEndDate.text =
            endDateConfig.get(Calendar.YEAR).toString() +"-"+ endDateConfig.get(Calendar.MONTH).toString()+"-"+ endDateConfig.get(
                Calendar.DAY_OF_MONTH).toString()
        endDateConfig.add(Calendar.DAY_OF_MONTH, -6)
        binding.tvSelectedStartDate.text =
            endDateConfig.get(Calendar.YEAR).toString() +"-"+ endDateConfig.get(Calendar.MONTH).toString()+"-"+ endDateConfig.get(
                Calendar.DAY_OF_MONTH).toString()
    }

    private fun millisToDate(): Calendar {
        val c = Calendar.getInstance()
        c.set(selectedYear, selectedMonth, selectedDay)
        return c
    }

    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(100f, 10f))
        entries.add(BarEntry(200f, 20f))
        entries.add(BarEntry(300f, 30f))
        entries.add(BarEntry(400f, 40f))
        entries.add(BarEntry(500f, 50f))
        entries.add(BarEntry(600f, 60f))

//        for (element in heartData.ratePerMinute)
//            entries.addAll(listOf(BarEntry(element.toFloat(), 0f)))

        val barDataSet = BarDataSet(entries, "Cells")

        val data = BarData(barDataSet)
        binding.heartBarChart.data = data // set the data and list of labels into chart

        data.barWidth = 30f
        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.button_blue)

        binding.heartBarChart.animateY(5000)
    }

    private fun initializeBarChart() {
        binding.heartBarChart.description.isEnabled = false
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        binding.heartBarChart.setMaxVisibleValueCount(5)
        binding.heartBarChart.setVisibleXRange(1F, 5F);
        binding.heartBarChart.xAxis.setDrawGridLines(true)
        // scaling can now only be done on x- and y-axis separately
        binding.heartBarChart.setPinchZoom(false)
        binding.heartBarChart.setDrawBarShadow(true)
        binding.heartBarChart.setDrawGridBackground(false)
        val xAxis: XAxis = binding.heartBarChart.xAxis
        xAxis.setDrawGridLines(true)
        binding.heartBarChart.axisLeft.setDrawGridLines(false)
        binding.heartBarChart.axisRight.setDrawGridLines(false)
        binding.heartBarChart.axisRight.isEnabled = false
        binding.heartBarChart.axisLeft.isEnabled = true
        binding.heartBarChart.xAxis.setDrawGridLines(false)
        // add a nice and smooth animation
        binding.heartBarChart.animateY(1500)
        binding.heartBarChart.legend.isEnabled = true
        binding.heartBarChart.axisRight.setDrawLabels(false)
        binding.heartBarChart.axisLeft.setDrawLabels(true)
        binding.heartBarChart.setTouchEnabled(false)
        binding.heartBarChart.isDoubleTapToZoomEnabled = false
        binding.heartBarChart.xAxis.isEnabled = true
        binding.heartBarChart.xAxis.position = XAxisPosition.BOTTOM
        binding.heartBarChart.invalidate()
    }
}