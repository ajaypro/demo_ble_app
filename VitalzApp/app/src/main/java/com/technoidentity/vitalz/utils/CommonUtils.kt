package com.technoidentity.vitalz.utils

import android.content.Context
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.data.network.VitalzService

fun getToken(context:Context){
        val sp = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        VitalzService.token = sp.getString(Constants.TOKEN, VitalzService.token)!!
}