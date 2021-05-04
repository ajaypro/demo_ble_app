package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import retrofit2.http.POST

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(mobile: String) : Boolean

    // confirm otp url that gives response object
    suspend fun getLogin()

}