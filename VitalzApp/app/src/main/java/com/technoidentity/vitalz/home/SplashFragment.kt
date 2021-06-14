package com.technoidentity.vitalz.home

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
import com.technoidentity.vitalz.databinding.FragmentSplashScreenBinding
import com.technoidentity.vitalz.utils.isTablet
import com.technoidentity.vitalz.utils.showSnackbar

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)

        //if Tablet - Get started Button else after 2sec navigate to user Selection
        //shared preferences

        if (isTablet(requireContext())) {
            binding.btnGetStarted.visibility = View.VISIBLE
            binding.btnGetStarted.setOnClickListener {
                findNavController().navigate(R.id.action_splashFragment_to_userSelectionFragment2)
            }
        } else {
            val countDownTimer = object : CountDownTimer(3000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    if (!requireActivity().packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                        binding.root.showSnackbar(
                            R.string.ble_not_suppported,
                            Snackbar.LENGTH_INDEFINITE,
                            R.string.ok
                        ) {
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
