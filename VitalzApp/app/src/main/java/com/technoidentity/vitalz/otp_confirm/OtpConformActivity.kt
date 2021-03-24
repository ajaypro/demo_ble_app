package com.technoidentity.vitalz.otp_confirm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.add_device.AddDeviceActivity
import com.technoidentity.vitalz.databinding.OtpConfirmBinding

class OtpConformActivity : AppCompatActivity(), OtpConfirmInterface {

  lateinit var otpConfirmViewModel: OtpConfirmViewModel
  lateinit var otpConfirmBinding: OtpConfirmBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    otpConfirmBinding = DataBindingUtil.setContentView(this,R.layout.activity_otp_conform)
    otpConfirmViewModel = ViewModelProvider(this).get(OtpConfirmViewModel::class.java)
    otpConfirmBinding.viewModel = otpConfirmViewModel

    otpConfirmViewModel.setInterface(this as OtpConfirmInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this,AddDeviceActivity::class.java)
    startActivity(intent)
    finish()
  }
}