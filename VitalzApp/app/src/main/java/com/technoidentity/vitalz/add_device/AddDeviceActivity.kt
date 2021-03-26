package com.technoidentity.vitalz.add_device

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.device.DeviceActivity

class AddDeviceActivity : AppCompatActivity(), AddDeviceInterface {

  lateinit var addDeviceViewModel: AddDeviceViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_device)

    addDeviceViewModel.setInterface(this as AddDeviceInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, DeviceActivity::class.java)
    startActivity(intent)
    finish()
  }
}