package com.technoidentity.vitalz.dashboard

import android.util.Log
import androidx.lifecycle.*
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
            Log.v("Check", "Stage_1 $token")
            _expectedResult.value = SinglePatient.Failure("Data Not found")
            return
        }
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(SinglePatient.Loading)
            when (val response = userRepository.getMultiplePatientDashboardList()) {
                is ResultHandler.Error -> {
                    Log.v("Check", "Stage Api Error VM ${response.message}")
                    _expectedResult.postValue(
                        SinglePatient.Failure(response.message.toString())
                    )
                }
                is ResultHandler.Success -> {
                    Log.v("Check", "Stage Api Success VM ${response.message}")
                    if (response.data == null) {
                        Log.v("Check", "Stage Api == Null ${response.message}")
                        _expectedResult.postValue(SinglePatient.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(
                            SinglePatient.Success(
                                "Patient List",
                                response.data
                            )
                        )
                    }
                }
            }
        }
    }
}

class MultiplePatientViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoctorDashboardViewModel::class.java)) {
            return DoctorDashboardViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}