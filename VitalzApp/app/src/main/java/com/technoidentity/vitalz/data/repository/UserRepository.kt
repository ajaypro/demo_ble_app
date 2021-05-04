package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.network.VitalzService

class UserRepository(private val vitalzService: VitalzService) {

      suspend fun doMobileOTPCall(mobile: String): Boolean = vitalzService.getOTPCall(mobile)
}
