package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.datamodel.login.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.login.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: VitalzApi
) : MainRepository{
    override suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse> {
        return try {
            val response = api.getOTP(mobile)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "An error occured")
        }
    }

    override suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse> {
        return try {
            val response = api.getLogin(otpRequest)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "An error occured")
        }
    }
}
