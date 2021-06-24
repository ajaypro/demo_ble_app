package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.dashboardDetail.DashboardDetailResponse
import com.technoidentity.vitalz.data.datamodel.dashboardDetail.DashboardDetailsRequest
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
    override suspend fun doMobileOTPCall(mobile: CareTakerRequest): CareTakerOtpResponse {
        return kotlin.runCatching {
            api.getOTP(mobile)
        }.getOrThrow()
    }

    override suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse>? {
        val response = api.getLogin(otpRequest)
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun sendDocNurseCredentials(docNurseLogin: DocNurseRequest): ResultHandler<DocNurseResponse>? {
        val response = api.getDocNurseLogin(docNurseLogin)
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getHospitalList(mobile: HospitalListRequest): ResultHandler<HospitalListData>? {
        val response = api.getHospitalList(mobile)
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getPatientList(request: PatientRequest): ResultHandler<PatientDataList>? {
        val response = api.getPatientList(request)
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getSinglePatientDashboardList(id: String): ResultHandler<SinglePatientDashboardResponse>? {
        val response = api.getSinglePatientDashboardList(id)
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getMultiplePatientDashboardList(): ResultHandler<MultiplePatientDashboardResponse>? {
        val response = api.getMultiplePatientDashboardList()
        return try {
            response.let { it ->
                if (it.isSuccessful) {
                    it.body()?.let {
                        ResultHandler.Success(it)
                    }
                } else {
                    Error(response.message())
                }
            }
        } catch (e: Exception) {
            Error(e.message ?: "Contact Admin")
        }
    }

    override suspend fun getDashboardDetailsList(request: DashboardDetailsRequest): DashboardDetailResponse {
        return kotlin.runCatching {
            api.getDashboardDetailsList(request)
        }.getOrThrow()
    }
}
