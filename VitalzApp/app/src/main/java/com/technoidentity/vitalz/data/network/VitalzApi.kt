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
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient_list.PatientRequest
import com.technoidentity.vitalz.data.datamodel.single_patient.SinglePatientDashboardResponse
import com.technoidentity.vitalz.data.network.Urls.CARETAKER_LOGIN
import com.technoidentity.vitalz.data.network.Urls.DOC_NURSE_LOGIN
import com.technoidentity.vitalz.data.network.Urls.GET_DEVICE_LIST
import com.technoidentity.vitalz.data.network.Urls.HOSPITAL_LIST
import com.technoidentity.vitalz.data.network.Urls.MULTIPLE_PATIENT_DASHBOARD
import com.technoidentity.vitalz.data.network.Urls.PATIENT_LIST
import com.technoidentity.vitalz.data.network.Urls.REGISTER_DEVICE
import com.technoidentity.vitalz.data.network.Urls.SEND_ECGDATA
import com.technoidentity.vitalz.data.network.Urls.SEND_HEARTRATE
import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import com.technoidentity.vitalz.data.network.Urls.SINGLE_PATIENT_DASHBOARD
import com.technoidentity.vitalz.notifications.datamodel.PushNotification
import com.technoidentity.vitalz.utils.FirebaseConstants.CONTENT_TYPE
import com.technoidentity.vitalz.utils.FirebaseConstants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(@Body request: CareTakerRequest) : Response<CareTakerOtpResponse>

    @POST(CARETAKER_LOGIN)
    suspend fun getLogin(@Body request: OtpRequest) : Response<OtpResponse>

    @POST(DOC_NURSE_LOGIN)
    suspend fun getDocNurseLogin(@Body request: DocNurseRequest) : Response<DocNurseResponse>

    @POST(HOSPITAL_LIST)
    suspend fun getHospitalList(@Body request: HospitalListRequest) : Response<HospitalListData>

    @POST(PATIENT_LIST)
    suspend fun getPatientList(@Body request: PatientRequest) : Response<PatientDataList>

    @GET(SINGLE_PATIENT_DASHBOARD)
    suspend fun getSinglePatientDashboardList(@Path("id") id: String) : Response<SinglePatientDashboardResponse>

    @GET(MULTIPLE_PATIENT_DASHBOARD)
    suspend fun getMultiplePatientDashboardList() : MultiplePatientDashboardResponse

    @POST(REGISTER_DEVICE)
    suspend fun sendDevicesForRegisteration(@Body request: BleMac): RegisteredDevice

    @GET(GET_DEVICE_LIST)
    suspend fun getRegisteredDevices(): List<RegisteredDevice>

    @POST(SEND_HEARTRATE)
    suspend fun sendHeartRate(patientId: String, telemetryKey: String, heartRate :List<Byte>): Boolean

    @POST(SEND_ECGDATA)
    suspend fun sendEcgData(patientId: String, telemetryKey: String, ecgData :List<Byte>): Boolean

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>

}