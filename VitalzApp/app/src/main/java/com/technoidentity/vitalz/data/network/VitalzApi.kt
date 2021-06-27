package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital_list.HospitalListRequest
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponse
import com.technoidentity.vitalz.data.datamodel.multiple_patient.MultiplePatientDashboardResponseItem
import com.technoidentity.vitalz.data.datamodel.notification.NotificationCareTakerRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationDoctorRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationResponse
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.network.Urls.CARETAKER_LOGIN
import com.technoidentity.vitalz.data.network.Urls.DOCTOR_NOTIFICATION
import com.technoidentity.vitalz.data.network.Urls.DOC_NURSE_LOGIN
import com.technoidentity.vitalz.data.network.Urls.GET_DEVICE_LIST
import com.technoidentity.vitalz.data.network.Urls.HOSPITAL_LIST
import com.technoidentity.vitalz.data.network.Urls.MULTIPLE_PATIENT_DASHBOARD
import com.technoidentity.vitalz.data.network.Urls.NURSE_NOTIFICATION
import com.technoidentity.vitalz.data.network.Urls.PATIENT_LIST
import com.technoidentity.vitalz.data.network.Urls.SEND_DEVICE
import com.technoidentity.vitalz.data.network.Urls.SEND_HEARTRATE
import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import com.technoidentity.vitalz.data.network.Urls.SINGLE_PATIENT_DASHBOARD
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(@Body request: CareTakerRequest) : Response<CareTakerOtpResponse>

    @POST(CARETAKER_LOGIN)
    suspend fun getLogin(@Body request: OtpRequest) : Response<OtpResponse>

    @POST(DOC_NURSE_LOGIN)
    suspend fun getDocNurseLogin(@Body request: DocNurseRequest) : DocNurseResponse

    @POST(HOSPITAL_LIST)
    suspend fun getHospitalList(@Body request: HospitalListRequest) : Response<HospitalListData>

    @POST(PATIENT_LIST)
    suspend fun getPatientList(@Body request: PatientRequest) : Response<PatientDataList>

    @GET(SINGLE_PATIENT_DASHBOARD)
    suspend fun getSinglePatientDashboardList(@Path("id") id: String) : Response<SinglePatientDashboardResponse>

    @GET(MULTIPLE_PATIENT_DASHBOARD)
    suspend fun getMultiplePatientDashboardList() : MultiplePatientDashboardResponse

    @POST(SEND_DEVICE)
    suspend fun sendDevicesForRegisteration(@Body request: BleMac): RegisteredDevice

    @GET(GET_DEVICE_LIST)
    suspend fun getRegisteredDevices(): List<RegisteredDevice>

    @POST(SEND_HEARTRATE)
    suspend fun sendHeartRate(patientId: String, telemetryKey: String, heartRate :ByteArray): Boolean

    @POST(DOCTOR_NOTIFICATION)
    suspend fun getDoctorNotification(request: NotificationDoctorRequest): NotificationResponse

    @GET(NURSE_NOTIFICATION)
    suspend fun getNurseNotification(): NotificationResponse

    @POST(CARETAKER_LOGIN)
    suspend fun getCareTakerNotification(request: NotificationCareTakerRequest): NotificationResponse

}