package com.technoidentity.vitalz.request_otp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.RequestOtpBinding
import com.technoidentity.vitalz.otp_confirm.OtpConformActivity

class RequestOtpActivity : AppCompatActivity(), RequestOtpInterface {

  lateinit var requestOtpViewModel: RequestOtpViewModel
  lateinit var requestOtpBinding: RequestOtpBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    requestOtpBinding = DataBindingUtil.setContentView(this, R.layout.activity_request_otp)
    requestOtpViewModel = ViewModelProvider(this).get(RequestOtpViewModel::class.java)
    requestOtpBinding.viewModel = requestOtpViewModel

    requestOtpViewModel.setInterface(this as RequestOtpInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, OtpConformActivity::class.java)
    startActivity(intent)
    finish()
  }
}