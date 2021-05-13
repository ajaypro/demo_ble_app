package com.technoidentity.vitalz.data.repository

import android.util.Log
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
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
            ResultHandler.Error(e.message ?: "An error occurred")
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
            ResultHandler.Error(e.message ?: "An error occurred")
        }
    }

    override suspend fun sendDocNurseCredentials(docNurseLogin: DocNurseRequest): ResultHandler<DocNurseResponse> {
        val response = api.getDocNurseLogin(docNurseLogin)
        return try {
            Log.v("Check Point", "Stage_6 ${response.body()}")
            val result = response.body()!!
            Log.v("Check Point", "Stage_1 ${response.message()}")
            if (response.isSuccessful){
                Log.v("Check Point", "Token ${result.token}")
                ResultHandler.Success(result)
            }else{
                Log.v("Check Point", "Stage_3 ${response.message()}")
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            Log.v("Check Point", "Stage_5 ${e.message}")
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }
}
