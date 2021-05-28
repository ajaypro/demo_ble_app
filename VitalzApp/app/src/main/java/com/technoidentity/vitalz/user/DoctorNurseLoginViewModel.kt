package com.technoidentity.vitalz.user

import android.util.Log
import androidx.lifecycle.*
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
        if (username == null && password == null) {
            _expectedResult.value = DocNurse.Failure("Not a Valid Credential")
            return
        }
        val request = DocNurseRequest()
        request.username = username
        request.password = password
        viewModelScope.launch(dispatcher.io) {
            _expectedResult.postValue(DocNurse.Loading)
            when (val response = userRepository.sendDocNurseCredentials(request)) {
                is ResultHandler.Error -> {
                    _expectedResult.postValue(
                        DocNurse.Failure(response.message.toString())
                    )}
                is ResultHandler.Success -> {
                    if (response.data == null) {
                        _expectedResult.postValue(DocNurse.Failure("Unexpected Error"))
                    } else {
                        Log.v("Check", "Stage_Suc ${response.data.token}")
                        _expectedResult.postValue(DocNurse.Success(resultText = "Credentials Send", data = response.data))
                    }
                }
            }
        }
    }
}

class DocNurseViewModelFactory(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DoctorNurseLoginViewModel::class.java)) {
            return CareTakerMobileViewModel(
                userRepository, dispatcher
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}