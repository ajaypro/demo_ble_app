package com.technoidentity.vitalz.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListRequest
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class HospitalData {
        class Success(val resultText: String, val data: HospitalListData) : HospitalData()
        class Failure(val errorText: String) : HospitalData()
        object Loading : HospitalData()
        object Empty : HospitalData()
    }

    private val _expectedResult = MutableLiveData<HospitalData>(
        HospitalData.Empty)
    val expectedResult: LiveData<HospitalData> = _expectedResult

    fun getHospitalListData(mobile: String) {
        val request = HospitalListRequest()
        request.mobile = mobile
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(HospitalData.Loading)
            when (val response = userRepository.getHospitalList(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        HospitalData.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(HospitalData.Failure("Unexpected Error"))
                    } else {
                        _expectedResult.postValue(HospitalData.Success("Hospital List", response.data))
                    }
                }
            }
        }
    }
}
