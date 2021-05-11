package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.datamodel.login.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.login.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.utils.ResultHandler

interface MainRepository {
    suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse>

    suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse>

}