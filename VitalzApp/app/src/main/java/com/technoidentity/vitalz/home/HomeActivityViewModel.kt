package com.technoidentity.vitalz.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.technoidentity.vitalz.data.repository.UserRepository
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    private var _isCareTaker = MutableStateFlow(false)
    val isSelected: StateFlow<Boolean> = _isCareTaker

    fun isCareTakerSelected(selected : Boolean){
        _isCareTaker.value = selected
    }
}
