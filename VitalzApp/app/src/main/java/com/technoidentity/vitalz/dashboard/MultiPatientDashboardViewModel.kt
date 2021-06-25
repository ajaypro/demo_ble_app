package com.technoidentity.vitalz.dashboard

import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponseItem
import com.technoidentity.vitalz.data.repository.UserRepositoryImpl
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MultiPatientDashboardViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    fun getMultiplePatientData(): LiveData<MultiplePatientDashboardResponse> {
//        if (token == null) {
//            _expectedResult.value = SinglePatient.Failure("Data Not found")
//            return
//        }
        return liveData {
            emit(userRepositoryImpl.getMultiplePatientDashboardList())
        }

//            when (val response = userRepository.getMultiplePatientDashboardList()) {
//                is ResultHandler.Error -> {
//                    _expectedResult.value =
//                        SinglePatient.Failure(response.message.toString())
//                }
//                is ResultHandler.Success -> {
//                    if (response.data == null) {
//                        _expectedResult.value = SinglePatient.Failure("Unexpected Error")
//                    } else {
//                        _expectedResult.value =
//                            SinglePatient.Success(
//                                "Patient List",
//                                response.data
//                            )
//                    }
//                }
//            }
        }
    }

