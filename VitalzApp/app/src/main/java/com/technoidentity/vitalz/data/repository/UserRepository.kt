package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.network.VitalzService
import com.technoidentity.vitalz.data.response.LoginResponse

class UserRepository(private val vitalzService: VitalzService): MainRepository {

      suspend fun doMobileOTPCall(mobile: String): Boolean = vitalzService.getOTPCall(mobile)

      override suspend fun login(userId: String, password: String): LoginResponse  = vitalzService.doLogin(userId, password)
}
