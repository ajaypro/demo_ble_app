package com.technoidentity.vitalz.utils

import com.technoidentity.vitalz.data.network.Constants

fun validateMobileNumber(mobileNumber : String): Boolean {
    when{
        mobileNumber.isEmpty() -> {
            return false
        }
        mobileNumber.length != 10 -> {
        }
        !mobileNumber.matches(Constants.mobilePattern.toRegex()) -> {
        }
        mobileNumber.matches(Constants.mobilePattern.toRegex()) -> {
            //do api call request and on success response navigate to next EnterOTP Fragment
        }
        else -> {
        }
    }
    return false
}