package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentCaretakerLoginBinding

class CareTakerMobileLoginFragment : Fragment(), CareTakerLoginInterface {

    lateinit var careTakerMobileViewModel : CareTakerMobileViewModel
    lateinit var bindingCareTakerLogin: FragmentCaretakerLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        bindingCareTakerLogin = FragmentCaretakerLoginBinding.inflate(inflater)
        careTakerMobileViewModel = ViewModelProvider(this).get(CareTakerMobileViewModel::class.java)
        return bindingCareTakerLogin.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController: NavController = Navigation.findNavController(view)
        val btnRequestOtp = view.findViewById<View>(R.id.btn_request_otp)
        val mobileNumber: EditText = view.findViewById<View>(R.id.et_mobile_number) as EditText
        careTakerMobileViewModel.setInterface(this as CareTakerLoginInterface)

        btnRequestOtp.setOnClickListener {
            validateMobileNumber(mobileNumber.text.toString())
        }
    }

    private fun validateMobileNumber(mobile : String) {
        when {
            mobile.isEmpty() -> {
                bindingCareTakerLogin.responseMsg.visibility = View.VISIBLE
                bindingCareTakerLogin.responseMsg.setText(R.string.empty)
            }
            mobile.length != 10 -> {
                bindingCareTakerLogin.responseMsg.visibility = View.VISIBLE
                bindingCareTakerLogin.responseMsg.setText(R.string.shortLength)
            }
            !mobile.matches(Constants.mobilePattern.toRegex()) -> {
                bindingCareTakerLogin.responseMsg.visibility = View.VISIBLE
                bindingCareTakerLogin.responseMsg.setText(R.string.invalid)
            }
            mobile.matches(Constants.mobilePattern.toRegex()) -> {
                //do api call request and on success response navigate to next EnterOTP Fragment
                bindingCareTakerLogin.responseMsg.visibility = View.GONE
                bindingCareTakerLogin.responseMsg.setText("")
                careTakerMobileViewModel.loginApi(mobile)
            }
            else -> {
                bindingCareTakerLogin.responseMsg.visibility = View.VISIBLE
                bindingCareTakerLogin.responseMsg.setText(R.string.unknown)
            }
        }
    }
}
