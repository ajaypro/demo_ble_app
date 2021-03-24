package com.technoidentity.vitalz.add_device

import android.view.View
import androidx.lifecycle.ViewModel

class AddDeviceViewModel : ViewModel() {

  lateinit var addDeviceInterface: AddDeviceInterface

  fun setInterface(addDeviceInterface: AddDeviceInterface) {
    this.addDeviceInterface = addDeviceInterface
  }

  fun onAddDevice(view: View) {
    //make BLE connection
    addDeviceInterface.navigateToActivity()
  }
}