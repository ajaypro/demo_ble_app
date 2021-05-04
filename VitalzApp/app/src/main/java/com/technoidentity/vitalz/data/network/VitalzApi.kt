package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.data.datamodel.login.CareTakerLoginResponse
import com.technoidentity.vitalz.data.network.Urls.CARETAKER_LOGIN
import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(@Body phoneNo: String) : Boolean

    // confirm otp url that gives response object
    @POST(CARETAKER_LOGIN)
    suspend fun getLogin(@Body otp : Int) : Response<CareTakerLoginResponse>

}
