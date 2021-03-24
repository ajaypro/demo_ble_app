package com.technoidentity.vitalz.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.SplashScreenBinding
import com.technoidentity.vitalz.user_selection.UserSelectionActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenInterface {

  lateinit var splashScreenViewModel: SplashActivityViewModel
  lateinit var activitySplashScreenBinding: SplashScreenBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    //Data binding
    activitySplashScreenBinding =
      DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
    //ViewModel
    splashScreenViewModel = ViewModelProvider(this).get(SplashActivityViewModel::class.java)
    //Attaching viewModel to dataBinding
    activitySplashScreenBinding.viewModel = splashScreenViewModel

    splashScreenViewModel.setInterface(this as SplashScreenInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, UserSelectionActivity::class.java)
    startActivity(intent)
    finish()
  }
}
