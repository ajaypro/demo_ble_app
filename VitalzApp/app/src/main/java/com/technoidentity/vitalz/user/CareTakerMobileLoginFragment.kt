package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants

class CareTakerMobileLoginFragment : Fragment(R.layout.fragment_caretaker_login), CareTakerLoginInterface {

    lateinit var careTakerMobileViewModel : CareTakerMobileViewModel

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
                Toast.makeText(context, "Please enter Mobile Number", Toast.LENGTH_SHORT).show()
            }
            mobile.length != 10 -> {
                Toast.makeText(context, "Please enter 10 Digits", Toast.LENGTH_SHORT).show()
            }
            !mobile.matches(Constants.mobilePattern.toRegex()) -> {
                Toast.makeText(context, "Please enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
            }
            mobile.matches(Constants.mobilePattern.toRegex()) -> {
                //do api call request and on success response navigate to next EnterOTP Fragment
                careTakerMobileViewModel.loginApi(mobile)
                Toast.makeText(context, "API Call", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(context, "Please Contact Admin/Hospital", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
