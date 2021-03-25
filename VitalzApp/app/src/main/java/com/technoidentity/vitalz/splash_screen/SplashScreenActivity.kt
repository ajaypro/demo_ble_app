package com.technoidentity.vitalz.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.user_selection.UserSelectionActivity

class SplashScreenActivity : AppCompatActivity(), SplashScreenInterface {

  lateinit var splashScreenViewModel: SplashActivityViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_splash_screen)

    //splashScreenViewModel.setInterface(this as SplashScreenInterface)
  }

  override fun navigateToActivity() {
    val intent = Intent(this, UserSelectionActivity::class.java)
    startActivity(intent)
    finish()
  }
}
