package com.technoidentity.vitalz.bluetooth.ui

import androidx.lifecycle.ViewModel
import com.technoidentity.vitalz.data.repository.DeviceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BleScanResultViewModel @Inject constructor(private val deviceRepository: DeviceRepository): ViewModel() {

}