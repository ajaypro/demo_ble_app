package com.technoidentity.vitalz.data.repository


import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: VitalzApi
) : MainRepository{
    override suspend fun doMobileOTPCall(mobile: String): ResultHandler<Boolean> {
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

}
