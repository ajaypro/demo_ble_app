package com.technoidentity.vitalz.data.repository

import android.util.Log
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: VitalzApi
) : MainRepository{
    override suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse> {
        return try {
            val response = api.getOTP(mobile)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse> {
        return try {
            val response = api.getLogin(otpRequest)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun sendDocNurseCredentials(docNurseLogin: DocNurseRequest): ResultHandler<DocNurseResponse> {
        val response = api.getDocNurseLogin(docNurseLogin)
        return try {
            val result = response.body()!!
            if (response.isSuccessful){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getHospitalList(): ResultHandler<HospitalListData> {
        val response = api.getHospitalList()
        return try {
            val result = response.body()!!
            if (response.isSuccessful){
                ResultHandler.Success(result)
            }else{
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getPatientList(request: PatientRequest): ResultHandler<PatientDataList> {
        val response = api.getPatientList(request)
        return try {
            val result = response.body()!!
            if (response.isSuccessful){
                Log.v("Check Point", "Token ${result.size}")
                ResultHandler.Success(result)
            }else{
                Log.v("Check Point", "Stage_3 ${response.message()}")
                ResultHandler.Error(response.message())
            }
        } catch (e: Exception){
            Log.v("Check Point", "Stage_5 ${e.message}")
            ResultHandler.Error(e.message ?: "Contact Admin")
        }
    }
}
