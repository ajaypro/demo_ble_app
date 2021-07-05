package com.technoidentity.vitalz.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.repository.DeviceRepository
import com.technoidentity.vitalz.data.repository.UserRepositoryImpl
import com.technoidentity.vitalz.notifications.datamodel.VitalzTelemetryNotification
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.HEART_RATE_DATA
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinglePatientDashboardViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val deviceRepository: DeviceRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class SinglePatient {
        class Success(val resultText: String, val data: SinglePatientDashboardResponse) : SinglePatient()
        class Failure(val errorText: String) : SinglePatient()
        object Loading : SinglePatient()
        object Empty : SinglePatient()
    }

    private val _expectedResult = MutableLiveData<SinglePatient>(
        SinglePatient.Empty)
    val expectedResult: LiveData<SinglePatient> = _expectedResult

    fun getSinglePatientData(patientId: String) {

        viewModelScope.launch{
            _expectedResult.value = SinglePatient.Loading
            when (val response = userRepositoryImpl.getSinglePatientDashboardList(patientId)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        SinglePatient.Failure(response.message.toString())
                    }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = SinglePatient.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = SinglePatient.Success(resultText = "Single Patient Dashboard Data",data = response.data)
                    }
                }
            }
        }
    }

    fun sendTelemetryNotification(notification: VitalzTelemetryNotification) : Boolean{
        var data: Boolean = false
        viewModelScope.launch {
                 data = deviceRepository.sendTelemetryNotification(notification)
        }
        return data
    }
}
