package com.technoidentity.vitalz.data.network

import com.technoidentity.vitalz.bluetooth.data.BleMac
import com.technoidentity.vitalz.bluetooth.data.RegisteredDevice
import com.technoidentity.vitalz.data.datamodel.SearchHospitalRequest
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
import com.technoidentity.vitalz.data.network.Urls.SEARCH_HOSPITAL
import com.technoidentity.vitalz.data.network.Urls.SEARCH_MULTI_PATIENT
import com.technoidentity.vitalz.data.network.Urls.SEARCH_PATIENT
import com.technoidentity.vitalz.data.network.Urls.SEND_DEVICE
import com.technoidentity.vitalz.data.network.Urls.SEND_HEARTRATE
import com.technoidentity.vitalz.data.network.Urls.SEND_OTP
import com.technoidentity.vitalz.data.network.Urls.SINGLE_PATIENT_DASHBOARD
import retrofit2.Response
import retrofit2.http.*

interface VitalzApi {

    @POST(SEND_OTP)
    suspend fun getOTP(@Body request: CareTakerRequest) : CareTakerOtpResponse

    @POST(CARETAKER_LOGIN)
    suspend fun getLogin(@Body request: OtpRequest) : OtpResponse

    @POST(DOC_NURSE_LOGIN)
    suspend fun getDocNurseLogin(@Body request: DocNurseRequest) : DocNurseResponse

    @POST(HOSPITAL_LIST)
    suspend fun getHospitalList(@Body request: HospitalListRequest) : HospitalListData

    @POST(PATIENT_LIST)
    suspend fun getPatientList(@Body request: PatientRequest) : PatientDataList

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

    @GET(SEARCH_MULTI_PATIENT)
    suspend fun searchMultiPatientList(@Path("parameter") parameter:String): MultiplePatientDashboardResponse

    @POST(SEARCH_HOSPITAL)
    suspend fun searchHospitalList(@Query("hospitalName") hospitalName:String, @Body request: SearchHospitalRequest): HospitalListData

    @GET(SEARCH_PATIENT)
    suspend fun searchPatientList(@Path("parameter") parameter:String): PatientDataList

}