package com.technoidentity.vitalz.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.R

//class SplashFragment : Fragment(R.layout.activity_splash_screen) {
//
//    lateinit var binding: FragmentSplashScreenBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentSplashScreenBinding.inflate(inflater)
//        requireActivity().actionBar?.hide()
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val navController: NavController = Navigation.findNavController(view)
//        val countDownTimer = object : CountDownTimer(3000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//            }
//
//            override fun onFinish() {
//                if (!requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//                    binding.root.showSnackbar(R.string.ble_not_suppported, Snackbar.LENGTH_INDEFINITE, R.string.ok){
//                        activity?.finish()
//                    }
//                }
//                else {
//                    navController.navigate(R.id.action_splashFragment_to_addDeviceFragment)
//                }
//            }
//
//        }
//        countDownTimer.start()
//    }
//}
