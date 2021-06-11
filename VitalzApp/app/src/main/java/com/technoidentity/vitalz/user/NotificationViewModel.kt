package com.technoidentity.vitalz.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.notification.NotificationRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
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

    fun getNotificationsListData(userId: String) {
        val request = NotificationRequest()
        request.userId = userId
        viewModelScope.launch {
            _expectedResult.value = NotificationData.Loading
            when (val response = userRepository.getNotificationList(request)) {
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
