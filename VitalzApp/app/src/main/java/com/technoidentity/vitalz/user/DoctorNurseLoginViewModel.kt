package com.technoidentity.vitalz.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import com.technoidentity.vitalz.utils.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorNurseLoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    sealed class DocNurse {
        class Success(val resultText: String, val data: DocNurseResponse) : DocNurse()
        class Failure(val errorText: String) : DocNurse()
        object Loading : DocNurse()
        object Empty : DocNurse()
    }

    private val _expectedResult = MutableLiveData<DocNurse>(DocNurse.Empty)
    val expectedResult: LiveData<DocNurse> = _expectedResult

    fun sendDocNurseCredentials(username: String, password: String) {
        val request = DocNurseRequest()
        request.username = username
        request.password = password
        viewModelScope.launch {
            _expectedResult.value = DocNurse.Loading
            when (val response = userRepository.sendDocNurseCredentials(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.value =
                        DocNurse.Failure(response.message.toString()
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.value = DocNurse.Failure("Unexpected Error")
                    } else {
                        _expectedResult.value = DocNurse.Success(resultText = "Credentials Send", data = response.data)
                    }
                }
            }
        }
    }
}
