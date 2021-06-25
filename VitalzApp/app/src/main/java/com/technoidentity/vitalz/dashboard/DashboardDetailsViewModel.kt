package com.technoidentity.vitalz.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.dashboardDetail.DashboardDetailResponse
import com.technoidentity.vitalz.data.datamodel.dashboardDetail.DashboardDetailsRequest
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardDetailsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    private val _expectedResult = MutableLiveData<DashboardDetailResponse>()
    val expectedResult: LiveData<DashboardDetailResponse> = _expectedResult

    fun getDashboardDetailsData(patientId: String, item: String, startDate: String, endDate: String) {
        val request = DashboardDetailsRequest(
            patientId = patientId,
            item = item,
            startDate = startDate,
            endDate = endDate)
        viewModelScope.launch {
            userRepository.getDashboardDetailsList(request).apply {
               _expectedResult.value = this
            }
        }
    }
}
