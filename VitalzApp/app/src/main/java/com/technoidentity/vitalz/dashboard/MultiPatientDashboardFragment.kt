package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.databinding.FragmentMultiplePatientDashboardBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.utils.CustomProgressDialog
import com.technoidentity.vitalz.utils.getQueryTextChangeStateFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MultiPatientDashboardFragment : Fragment() {

    private lateinit var binding: FragmentMultiplePatientDashboardBinding
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

        //RecyclerViewSetup
        setUpRecyclerView()

        //Api call to fetch Latest data
        multiplePatientDashboardApi()

        //Search Functionality
        searchWithStateFlow()

//        binding.etSearchPatient.addTextChangedListener(object : TextWatcher{
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s.isNullOrEmpty()){
//                    binding.ivCancelSearchPatient.visibility = View.GONE
//                    multiplePatientDashboardApi()
//                }else {
//                    binding.ivCancelSearchPatient.visibility = View.VISIBLE
//                    if (start == 2) {
//                        searchApiCall(s)
//                        binding.ivCancelSearchPatient.setOnClickListener {
//                            binding.etSearchPatient.setText("")
//                            multiplePatientDashboardApi()
//                        }
//                    }
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//
//        })

        return binding.root
    }

    @FlowPreview
    private fun searchWithStateFlow() {
        lifecycleScope.launch {
            binding.etSearchPatient.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter {
                    if (it.isEmpty()){
                        return@filter false
                    }else{
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    searchApiCall(it)
                        .catch {
                            emitAll(flowOf())
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect {
                    doctorAdapter.multiplePatient = it.value!!
                }
        }
    }

    private fun searchApiCall(s: CharSequence): Flow<LiveData<MultiplePatientDashboardResponse>> {
//        progressDialog.showLoadingDialog()
//        viewModel.searchPatientInList(s).observe(viewLifecycleOwner) {
//            if (it.isNotEmpty()) {
//                progressDialog.dismissLoadingDialog()
//                doctorAdapter.multiplePatient = it
//            } else {
//                progressDialog.dismissLoadingDialog()
//                binding.apply {
//                    //check for it.reason error case
//                    rvMultiplePatientDashboardList.visibility = View.GONE
//                    tvNoRecords.visibility = View.VISIBLE
//                }
//            }
//        }
        return flow {
            kotlinx.coroutines.delay(2000)
            emit(viewModel.searchPatientInList(s))
        }
    }

    private fun multiplePatientDashboardApi() {
        progressDialog.showLoadingDialog()
        viewModel.getMultiplePatientData().observe(viewLifecycleOwner) {
            //check for it.reason success case
            if (it.isNotEmpty()) {
                progressDialog.dismissLoadingDialog()
                doctorAdapter.multiplePatient = it
            } else {
                progressDialog.dismissLoadingDialog()
                binding.apply {
                    //check for it.reason error case
                    rvMultiplePatientDashboardList.visibility = View.GONE
                    tvNoRecords.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setUpRecyclerView() = binding.rvMultiplePatientDashboardList.apply {
        doctorAdapter = MultiplePatientAdapter(this@MultiPatientDashboardFragment)
        adapter = doctorAdapter
        layoutManager = LinearLayoutManager(context)
    }

    fun onItemClicked(layoutPosition: Int) {
    }

}