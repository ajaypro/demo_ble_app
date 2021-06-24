package com.technoidentity.vitalz.data.network

object Urls {

    const val BASE_URL = "http://20.204.98.236:8023/"

    const val SEND_OTP = "caretaker/send-otp"

    const val CARETAKER_LOGIN = "caretaker/login"

    const val DOC_NURSE_LOGIN = "staff/login"

    const val HOSPITAL_LIST = "hospital-name"

    const val PATIENT_LIST = "patient-caretakers"

    const val SINGLE_PATIENT_DASHBOARD = "http://20.204.14.2/graph/patient/{id}"

    const val MULTIPLE_PATIENT_DASHBOARD = "http://52.140.120.88:8082/agastya/v1/patients"

    const val NOTIFICATION_CARE_TAKER = "/notification/patient"

    const val NOTIFICATION_DOCTOR = "/notification/doctor"

    const val NOTIFICATION_ADMIN_NURSE = "/notification/admin-nurse"
}
