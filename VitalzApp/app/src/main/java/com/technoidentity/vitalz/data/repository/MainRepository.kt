package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.utils.ResultHandler

interface MainRepository {
    suspend fun doMobileOTPCall(mobile: String): ResultHandler<Boolean>

}