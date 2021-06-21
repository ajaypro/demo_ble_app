package com.technoidentity.vitalz.data.repository

import android.util.Log
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListRequest
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import com.technoidentity.vitalz.utils.ResultHandler.Error
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: VitalzApi
) : MainRepository {
    override suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse>? {
        return try {
            val response = api.getOTP(mobile)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResultHandler.Success(result)
            } else {
                Error(response.message())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse> {
        return try {
            val response = api.getLogin(otpRequest)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                ResultHandler.Success(result)
            } else {
                Error(response.message())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun sendDocNurseCredentials(docNurseLogin: DocNurseRequest): ResultHandler<DocNurseResponse> {
        val response = api.getDocNurseLogin(docNurseLogin)
        return try {
            val result = response.body()!!
            if (response.isSuccessful) {
                ResultHandler.Success(result)
            } else {
                Error(response.message())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getHospitalList(mobile: HospitalListRequest): ResultHandler<HospitalListData> {
        val response = api.getHospitalList(mobile)
        return try {
            val result = response.body()!!
            if (response.isSuccessful) {
                ResultHandler.Success(result)
            } else {
                Error(response.message())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getPatientList(request: PatientRequest): ResultHandler<PatientDataList> {
        val response = api.getPatientList(request)
        return try {
            val result = response.body()!!
            if (response.isSuccessful) {
                ResultHandler.Success(result)
            } else {
                Error(response.message())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getSinglePatientDashboardList(id: String): ResultHandler<SinglePatientDashboardResponse> {
        val response = api.getSinglePatientDashboardList(id)
        return try {
            val result = response.body()!!
            if (response.code() == 200) {
                ResultHandler.Success(result)
            } else {
                Error(response.code())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getMultiplePatientDashboardList(): ResultHandler<MultiplePatientDashboardResponse> {
        val response = api.getMultiplePatientDashboardList()
        return try {
            val result = response.body()!!
            if (response.code() == 200) {
                Log.v("Check", "Stage Success ${response.code()}")
                ResultHandler.Success(result)
            } else {
                Log.v("Check", "Stage Error new Api ${response.message()}")
                Error(response.code())
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }
}
