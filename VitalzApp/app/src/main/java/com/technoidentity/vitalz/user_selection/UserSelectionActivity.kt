package com.technoidentity.vitalz.user_selection

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.request_otp.RequestOtpActivity

class UserSelectionActivity : AppCompatActivity(), UserSelectionInterface {

  lateinit var userSelectionViewModel: UserSelectionViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView( R.layout.activity_user_selection)
    //userSelectionViewModel.setInterface(this as UserSelectionInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, RequestOtpActivity::class.java)
    startActivity(intent)
    finish()
  }

  override fun toastMsg() {
    Toast.makeText(this, "Coming Soon !!", Toast.LENGTH_SHORT).show()
  }
}