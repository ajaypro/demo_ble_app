package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentCaretakerLoginBinding
import com.technoidentity.vitalz.user.CareTakerMobileViewModel.CareTaker.Success

class CareTakerMobileLoginFragment : Fragment() {

    private lateinit var binding: FragmentCaretakerLoginBinding
//    private lateinit var viewModel : CareTakerMobileViewModel
//    val viewModel by viewModels<CareTakerMobileViewModel>(){CareTakerMobileViewModelFactory(
//        UserRepository(),
//    )  }
    val viewModel : CareTakerMobileViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCaretakerLoginBinding.inflate(layoutInflater)

//        viewModel = ViewModelProvider(this, CareTakerMobileViewModelFactory(userRepository)).get(CareTakerMobileViewModel::class.java)

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
                            is Success -> {
                                Navigation.findNavController(requireView()).navigate(R.id.careTakerMobileOTPFragment)
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
