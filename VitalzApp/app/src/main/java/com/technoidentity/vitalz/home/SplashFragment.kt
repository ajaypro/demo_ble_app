package com.technoidentity.vitalz.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R


class SplashFragment : Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)
        val handler = Handler()
        val runnable = Runnable { //Second fragment after 5 seconds appears
            navController.navigate(R.id.action_splashFragment2_to_userSelectionFragment2)
        }
        handler.postDelayed(runnable, 2000)
    }
}
