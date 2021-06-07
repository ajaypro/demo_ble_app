package com.technoidentity.vitalz.home

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.data.network.Constants.TABLET
import com.technoidentity.vitalz.databinding.FragmentSplashScreenBinding
import com.technoidentity.vitalz.utils.showSnackbar

class SplashFragment : Fragment(R.layout.fragment_splash_screen) {

    lateinit var binding: FragmentSplashScreenBinding
    private var TAB = String()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
//        requireActivity().actionBar?.hide()

        //if Tablet - Get started Button else after 2sec navigate to user Selection
        //shared preferences
        val sp = context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        TAB = sp?.getString(TABLET, TAB).toString()

        if (TAB == "Tab"){
            binding.btnGetStarted.visibility = View.VISIBLE
            binding.btnGetStarted.setOnClickListener{
                findNavController().navigate(R.id.action_splashFragment_to_userSelectionFragment2)
            }
        }else{
            val countDownTimer = object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    if (!requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                        binding.root.showSnackbar(R.string.ble_not_suppported, Snackbar.LENGTH_INDEFINITE, R.string.ok){
                            activity?.finish()
                        }
                    } else {
//                    navController.navigate(R.id.action_splashFragment_to_addDeviceFragment)
                        findNavController().navigate(R.id.action_splashFragment_to_userSelectionFragment2)
                    }
                }

            }
            countDownTimer.start()
        }

        return binding.root
    }
}
