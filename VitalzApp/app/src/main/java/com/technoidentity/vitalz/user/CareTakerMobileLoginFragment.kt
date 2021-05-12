package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentCaretakerLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CareTakerMobileLoginFragment : Fragment() {

    private lateinit var binding: FragmentCaretakerLoginBinding
    val viewModel : CareTakerMobileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCaretakerLoginBinding.inflate(layoutInflater)

        binding.btnRequestOtp.setOnClickListener {
            val mobileNumber = binding.etMobileNumber.text.toString()
            validateMobileNumber(mobileNumber)
        }
        return binding.root
    }

    private fun validateMobileNumber(mobile: String) {
        when {
            mobile.isEmpty() -> {
                binding.responseMsg.visibility = View.VISIBLE
                binding.responseMsg.setText(R.string.empty)
            }
            mobile.length != 10 -> {
                binding.responseMsg.visibility = View.VISIBLE
                binding.responseMsg.setText(R.string.shortLength)
            }
            !mobile.matches(Constants.mobilePattern.toRegex()) -> {
                binding.responseMsg.visibility = View.VISIBLE
                binding.responseMsg.setText(R.string.invalid)
            }
            mobile.matches(Constants.mobilePattern.toRegex()) -> {
                //do api call request and on success response navigate to next EnterOTP Fragment
                binding.responseMsg.visibility = View.GONE
                binding.responseMsg.text = ""
                lifecycleScope.launchWhenCreated {
                    viewModel.getCareTakerResponse(mobile)
                    viewModel.expectedResult.observe(viewLifecycleOwner, {
                        when(it){
                            is CareTakerMobileViewModel.CareTaker.Success -> {
                                val bundle = bundleOf("mobileNumber" to mobile)
                                Navigation.findNavController(requireView()).navigate(R.id.careTakerMobileOTPFragment, bundle)
                            }

                            is CareTakerMobileViewModel.CareTaker.Failure -> {
                            }
                            else -> Unit
                        }
                    })
                }
            }
            else -> {
                binding.responseMsg.visibility = View.VISIBLE
                binding.responseMsg.setText(R.string.unknown)
            }
        }
    }
}
