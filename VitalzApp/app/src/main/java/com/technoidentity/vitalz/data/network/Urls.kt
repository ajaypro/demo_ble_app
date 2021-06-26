package com.technoidentity.vitalz.data.network

object Urls {

    const val BASE_URL = "http://20.204.98.236:8023/"

    const val SEND_OTP = "caretaker/send-otp"

    const val CARETAKER_LOGIN = "caretaker/login"

    const val DOC_NURSE_LOGIN = "staff/login"

    const val HOSPITAL_LIST = "hospital-name"

    const val PATIENT_LIST = "patient-caretakers"

    const val SINGLE_PATIENT_DASHBOARD = "http://20.204.14.2/graph/patient/{id}"

    const val MULTIPLE_PATIENT_DASHBOARD = "http://20.204.14.2/graph/patients"

    const val SEND_DEVICE = "/device/save-device"

    const val GET_DEVICE_LIST = "/device/device-list"

    const val SEND_HEARTRATE = ""

    const val DOCTOR_NOTIFICATION = "/notification/doctor"

    const val NURSE_NOTIFICATION = "/notification/admin-nurse"

    const val CARE_TAKER_NOTIFICATION = "/notification/patient"
}
