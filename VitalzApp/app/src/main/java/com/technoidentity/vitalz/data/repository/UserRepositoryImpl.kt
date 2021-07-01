package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListRequest
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.datamodel.notification.NotificationCareTakerRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationDoctorRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationResponse
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.datamodel.updateProfile.ProfileUpdateRequest
import com.technoidentity.vitalz.data.datamodel.updateProfile.ProfileUpdateResponse
import com.technoidentity.vitalz.data.network.VitalzApi
import com.technoidentity.vitalz.utils.ResultHandler
import com.technoidentity.vitalz.utils.ResultHandler.Error
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: VitalzApi
) : UserRepository {
    override suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse>? {
        val response = api.getOTP(mobile)
        return response.let {
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

    override suspend fun sendDocNurseCredentials(request: DocNurseRequest): DocNurseResponse {
        return kotlin.runCatching {
            api.getDocNurseLogin(request)
        }.getOrThrow()
    }

    override suspend fun getHospitalList(request: HospitalListRequest): ResultHandler<HospitalListData>? {
        val response = api.getHospitalList(request)
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

    override suspend fun getMultiplePatientDashboardList(): MultiplePatientDashboardResponse {
        return kotlin.runCatching {
            api.getMultiplePatientDashboardList()
        }.getOrThrow()
    }

    override suspend fun getNotificationCareTakerList(request: NotificationCareTakerRequest): NotificationResponse {
        return kotlin.runCatching {
            api.getCareTakerNotification(request)
        }.getOrThrow()
    }

    override suspend fun getNotificationDoctorList(request: NotificationDoctorRequest): NotificationResponse {
        return kotlin.runCatching {
            api.getDoctorNotification(request)
        }.getOrThrow()
    }

    override suspend fun getNotificationNurseList(): NotificationResponse {
        return kotlin.runCatching {
            api.getNurseNotification()
        }.getOrThrow()
    }

    override suspend fun updatePatientData(request: ProfileUpdateRequest): ProfileUpdateResponse {
        return kotlin.runCatching {
            api.updateProfileData(request)
        }.getOrThrow()
    }
}
