package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.response.LoginResponse

interface MainRepository {

    suspend fun login(userId: String, password: String): LoginResponse
}