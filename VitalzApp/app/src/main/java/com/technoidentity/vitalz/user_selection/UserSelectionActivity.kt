package com.technoidentity.vitalz.user_selection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.UserSelectionBinding
import com.technoidentity.vitalz.request_otp.RequestOtpActivity

class UserSelectionActivity : AppCompatActivity(), UserSelectionInterface {

  lateinit var userSelectionViewModel: UserSelectionViewModel
  lateinit var userSelectionBinding : UserSelectionBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    userSelectionBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_selection)
    userSelectionViewModel = ViewModelProvider(this).get(UserSelectionViewModel::class.java)
    userSelectionBinding.viewModel = userSelectionViewModel

    userSelectionViewModel.setInterface(this as UserSelectionInterface)
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