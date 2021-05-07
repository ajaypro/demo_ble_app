package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.BuildConfig
import com.technoidentity.vitalz.data.network.Urls.BASE_URL_Production
import com.technoidentity.vitalz.data.response.LoginResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object VitalzService {

    private lateinit var restApi: VitalzApi
    var token = String()

    private fun init() {
        val interceptor = HttpLoggingInterceptor()
        val headerAuthorizationInterceptor = object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                val builder: Request.Builder =
                    request.newBuilder().header("Authorization", String.format("bearer %s", token))

                val request1 = builder.build()
                return chain.proceed(request1)
            }
        }

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(headerAuthorizationInterceptor)
            .addInterceptor(interceptor).connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS).readTimeout(100, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL_Production)
            .client(client).addConverterFactory(GsonConverterFactory.create()).build()

        restApi = retrofit.create(VitalzApi::class.java)
    }

    private fun getRestApi(): VitalzApi {
        init()
        return restApi
    }

    private fun getBaseUrl(): String {
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> BASE_URL_Production
            else -> ""
        }
    }

    suspend fun getOTPCall(mobile: String) : Boolean{
        restApi = getRestApi()
        return restApi.getOTP(mobile)
    }

    suspend fun doLogin(userId: String, password: String) : LoginResponse  =
        restApi.getLogin()

}