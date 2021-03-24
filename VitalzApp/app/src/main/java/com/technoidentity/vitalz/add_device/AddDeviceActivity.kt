package com.technoidentity.vitalz.add_device

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.AddDeviceBinding
import com.technoidentity.vitalz.device.DeviceActivity

class AddDeviceActivity : AppCompatActivity(), AddDeviceInterface {

  lateinit var addDeviceViewModel: AddDeviceViewModel
  lateinit var addDeviceBinding: AddDeviceBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    addDeviceBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_device)
    addDeviceViewModel = ViewModelProvider(this).get(AddDeviceViewModel::class.java)
    addDeviceBinding.viewModel = addDeviceViewModel

    addDeviceViewModel.setInterface(this as AddDeviceInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, DeviceActivity::class.java)
    startActivity(intent)
    finish()
  }
}