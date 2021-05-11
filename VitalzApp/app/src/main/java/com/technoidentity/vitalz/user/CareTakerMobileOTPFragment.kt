package com.technoidentity.vitalz.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.databinding.FragmentOtpConfirmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareTakerMobileOTPFragment : Fragment() {

    lateinit var binding: FragmentOtpConfirmBinding
    val viewModel: CareTakerMobileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpConfirmBinding.inflate(layoutInflater)
        val mobile = arguments?.getString("mobileNumber")
        binding.txMobileNumber.text = mobile
        Log.v("Check Mobile", "Stage_1 $mobile")

        binding.btnConfirmOtp.setOnClickListener {
            val etOtp1 = binding.etOtp1.text.toString()
            val etOtp2 = binding.etOtp2.text.toString()
            val etOtp3 = binding.etOtp3.text.toString()
            val etOtp4 = binding.etOtp4.text.toString()
            val etOtp5 = binding.etOtp5.text.toString()
            val etOtp6 = binding.etOtp6.text.toString()
            if (etOtp1.isEmpty() && etOtp2.isEmpty() && etOtp3.isEmpty() && etOtp4.isEmpty() && etOtp5.isEmpty() && etOtp6.isEmpty()) {
                Toast.makeText(context, "Please Enter Otp", Toast.LENGTH_SHORT).show()
            } else {
                val otpReceived: String = (etOtp1 + etOtp2 + etOtp3 + etOtp4 + etOtp5 + etOtp6)
                Log.v("Check Mobile", "Stage_2 $otpReceived")
                lifecycleScope.launchWhenStarted {
                    viewModel.getOtpResponse(mobile, otpReceived.toInt())
                    viewModel.expectedResult.observe(viewLifecycleOwner, {
                        when (it) {
                            is CareTakerMobileViewModel.CareTaker.Success -> {
                                Navigation.findNavController(requireView())
                                    .navigate(R.id.hospitalListFragment)
                            }

                            is CareTakerMobileViewModel.CareTaker.Failure -> {
                            }
                            else -> Unit
                        }
                    })
                }
            }
        }
        return binding.root
    }
}
