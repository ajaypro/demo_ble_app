package com.technoidentity.vitalz.home

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            navController.navigate(R.id.action_splashFragment2_to_userSelectionFragment2)
        }
    }
}
