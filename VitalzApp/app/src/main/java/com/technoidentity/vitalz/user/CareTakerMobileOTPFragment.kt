package com.technoidentity.vitalz.user

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentOtpConfirmBinding
import com.technoidentity.vitalz.user.CareTakerMobileViewModel.CareTaker.Failure
import com.technoidentity.vitalz.user.CareTakerMobileViewModel.CareTaker.Success
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareTakerMobileOTPFragment : Fragment() {

    lateinit var binding: FragmentOtpConfirmBinding
    val viewModel: OtpMobileViewModel by viewModels()
    private val viewModelCareTaker: CareTakerMobileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpConfirmBinding.inflate(layoutInflater)

        //Getting Arguments From last Fragment
        val mobile = arguments?.getString("mobileNumber")
        binding.txMobileNumber.text = mobile

        //Counter Timer For Resend OTP
        object : CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = "Resend OTP in ${millisUntilFinished/1000} Seconds"
            }

            override fun onFinish() {
                binding.tvTimer.text = "Resend OTP"
                binding.tvTimer.setTextColor(resources.getColor(R.color.button_blue))
                binding.tvTimer.setOnClickListener {
                    resendOtpApiCall(mobile)
                }
            }
        }.start()

        //Changing focus when otp is entered
        setFocusChangeOnTextEntered()

        //on Confirm Otp Btn Clicked
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
                lifecycleScope.launchWhenCreated {
                    viewModel.getOtpResponse(mobile, otpReceived.toInt())
                    viewModel.expectedResult.observe(viewLifecycleOwner, {
                        when (it) {
                            is OtpMobileViewModel.OtpResponse.Success -> {
                                val pref = context?.getSharedPreferences(Constants.PREFERENCE_NAME, 0)
                                pref?.edit()?.putString(Constants.TOKEN, it.data?.token)?.apply()
                                pref?.edit()?.putString(Constants.MOBILE, it.data?.user?.phoneNo)?.apply()
                                Log.v("Check", "Stage_Pref ${it.data?.token}")
                                Navigation.findNavController(requireView())
                                    .navigate(R.id.hospitalListFragment)
                            }

                            is OtpMobileViewModel.OtpResponse.Failure -> {
                            }
                            else -> Unit
                        }
                    })
                }
            }
        }
        return binding.root
    }

    private fun resendOtpApiCall(mobile: String?) {

        lifecycleScope.launchWhenCreated {
            if (mobile != null) {
                viewModelCareTaker.getCareTakerResponse(mobile)
            }
            viewModelCareTaker.expectedResult.observe(viewLifecycleOwner, {
                when(it){
                    is Success -> {
                        Toast.makeText(context,"Otp Sent", Toast.LENGTH_SHORT).show()
                    }

                    is Failure -> {
                    }
                    else -> Unit
                }
            })
        }
    }

    private fun setFocusChangeOnTextEntered() {
        binding.etOtp1.doOnTextChanged { text, start, before, count ->
            binding.etOtp2.requestFocus()
        }
        binding.etOtp2.doOnTextChanged { text, start, before, count ->
            binding.etOtp3.requestFocus()
        }
        binding.etOtp3.doOnTextChanged { text, start, before, count ->
            binding.etOtp4.requestFocus()
        }
        binding.etOtp4.doOnTextChanged { text, start, before, count ->
            binding.etOtp5.requestFocus()
        }
        binding.etOtp5.doOnTextChanged { text, start, before, count ->
            binding.etOtp6.requestFocus()
        }
    }
}
