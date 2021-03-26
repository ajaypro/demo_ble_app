package com.technoidentity.vitalz.request_otp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.otp_confirm.OtpConformActivity

class RequestOtpActivity : AppCompatActivity(), RequestOtpInterface {

  lateinit var requestOtpViewModel: RequestOtpViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_request_otp)
    //requestOtpViewModel.setInterface(this as RequestOtpInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, OtpConformActivity::class.java)
    startActivity(intent)
    finish()
  }
}