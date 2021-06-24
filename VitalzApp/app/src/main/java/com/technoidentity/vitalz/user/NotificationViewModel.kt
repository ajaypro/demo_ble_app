package com.technoidentity.vitalz.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.notification.NotificationCareTakerRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationDoctorRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class NotificationData {
        class Success(val resultText: String, val data: NotificationResponse) : NotificationData()
        class Failure(val errorText: String) : NotificationData()
        object Loading : NotificationData()
        object Empty : NotificationData()
    }

    private val _expectedResult = MutableLiveData<NotificationData>(
        NotificationData.Empty)
    val expectedResult: LiveData<NotificationData> = _expectedResult

    fun getNotificationsCareTakerListData(patientId: String) {
        val request = NotificationCareTakerRequest()
        request.patientId = patientId
        viewModelScope.launch {
            when (val response = userRepository.getNotificationCareTakerList(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        NotificationData.Failure(response.message.toString())
                    }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = NotificationData.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = NotificationData.Success("Notification List", response.data)
                    }
                }
            }
        }
    }

    fun getNotificationsDoctorListData(phoneNo: String) {
        val request = NotificationDoctorRequest()
        request.phoneNo = phoneNo
        viewModelScope.launch {
            when (val response = userRepository.getNotificationDoctorList(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        NotificationData.Failure(response.message.toString())
                }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = NotificationData.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = NotificationData.Success("Notification List", response.data)
                    }
                }
            }
        }
    }

    fun getNotificationsAdminNurseListData() {
        viewModelScope.launch {
            when (val response = userRepository.getNotificationAdminNurseList()) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        NotificationData.Failure(response.message.toString())
                }
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = NotificationData.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = NotificationData.Success("Notification List", response.data)
                    }
                }
            }
        }
    }
}
