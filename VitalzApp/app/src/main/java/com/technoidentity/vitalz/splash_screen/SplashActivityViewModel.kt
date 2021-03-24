package com.technoidentity.vitalz.splash_screen

import android.view.View
import androidx.lifecycle.ViewModel

class SplashActivityViewModel: ViewModel(){

  private lateinit var splashScreenInterface: SplashScreenInterface

  fun setInterface(splashScreenInterface: SplashScreenInterface){
    this.splashScreenInterface = splashScreenInterface
  }

  fun onGetStarted(view : View){
    splashScreenInterface.navigateToActivity()
  }
}