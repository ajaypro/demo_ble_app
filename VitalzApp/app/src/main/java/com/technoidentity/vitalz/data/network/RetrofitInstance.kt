package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.data.network.Urls.BASE_URL_Production
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: VitalzApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_Production)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VitalzApi::class.java)
    }
}
