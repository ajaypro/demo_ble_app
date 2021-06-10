package com.technoidentity.vitalz.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorDashboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class SinglePatient {
        class Success(val resultText: String, val data: MultiplePatientDashboardResponse) :
            SinglePatient()

        class Failure(val errorText: String) : SinglePatient()
        object Loading : SinglePatient()
        object Empty : SinglePatient()
    }

    private val _expectedResult = MutableLiveData<SinglePatient>(
        SinglePatient.Empty
    )
    val expectedResult: LiveData<SinglePatient> = _expectedResult

    fun getMultiplePatientData(token: String?) {
        if (token == null) {
            _expectedResult.value = SinglePatient.Failure("Data Not found")
            return
        }
        viewModelScope.launch {
            _expectedResult.value = SinglePatient.Loading
            when (val response = userRepository.getMultiplePatientDashboardList()) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        SinglePatient.Failure(response.message.toString())
                }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = SinglePatient.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value =
                            SinglePatient.Success(
                                "Patient List",
                                response.data
                            )
                    }
                }
            }
        }
    }
}
