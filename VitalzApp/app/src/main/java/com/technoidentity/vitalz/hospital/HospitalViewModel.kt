package com.technoidentity.vitalz.hospital

import androidx.lifecycle.*
import com.technoidentity.vitalz.data.datamodel.SearchHospitalRequest
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListRequest
import com.technoidentity.vitalz.data.repository.UserRepositoryImpl
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HospitalViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class HospitalData {
        class Success(val resultText: String, val data: HospitalListData) : HospitalData()
        class Failure(val errorText: String) : HospitalData()
        object Loading : HospitalData()
        object Empty : HospitalData()
    }

    private val _expectedResult = MutableLiveData<HospitalData>(
        HospitalData.Empty
    )
    val expectedResult: LiveData<HospitalData> = _expectedResult

    fun getHospitalListData(mobile: String) {
        val request = HospitalListRequest()
        request.mobile = mobile
        viewModelScope.launch {
            _expectedResult.value = HospitalData.Loading
            when (val response = userRepositoryImpl.getHospitalList(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        HospitalData.Failure(response.message.toString())
                }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = HospitalData.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = HospitalData.Success("Hospital List", response.data)
                    }
                }
            }
        }
    }

    fun searchHospitalInList(text: CharSequence, mobile: SearchHospitalRequest): LiveData<HospitalListData> {
        return liveData {
            emit(userRepositoryImpl.searchHospitalList(text.toString(), mobile))
        }
    }
}
