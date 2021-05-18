package com.technoidentity.vitalz.data.repository

import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerOtpResponse
import com.technoidentity.vitalz.data.datamodel.careTakerLogin.CareTakerRequest
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseResponse
import com.technoidentity.vitalz.data.datamodel.docNurseLogin.DocNurseRequest
import com.technoidentity.vitalz.data.datamodel.hospital.HospitalListData
import com.technoidentity.vitalz.data.datamodel.hospital.HospitalListDataItem
import com.technoidentity.vitalz.data.datamodel.otp.OtpRequest
import com.technoidentity.vitalz.data.datamodel.otp.OtpResponse
import com.technoidentity.vitalz.data.datamodel.patient.PatientDataList
import com.technoidentity.vitalz.data.datamodel.patient.PatientRequest
import com.technoidentity.vitalz.utils.ResultHandler

interface MainRepository {

    suspend fun doMobileOTPCall(mobile: CareTakerRequest): ResultHandler<CareTakerOtpResponse>

    suspend fun doOTPSendCall(otpRequest: OtpRequest): ResultHandler<OtpResponse>

    suspend fun sendDocNurseCredentials(docNurseLogin: DocNurseRequest): ResultHandler<DocNurseResponse>

    suspend fun getHospitalList(): ResultHandler<HospitalListData>

    suspend fun getPatientList(request: PatientRequest): ResultHandler<PatientDataList>

}