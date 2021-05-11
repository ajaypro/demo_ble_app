package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.data.datamodel.login.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.login.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.network.Urls.CARETAKER_LOGIN
import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(@Body request: CareTakerRequest) : Response<CareTakerOtpResponse>

    // confirm otp url that gives response object
    @POST(CARETAKER_LOGIN)
    suspend fun getLogin(@Body request: OtpRequest) : Response<OtpResponse>

}