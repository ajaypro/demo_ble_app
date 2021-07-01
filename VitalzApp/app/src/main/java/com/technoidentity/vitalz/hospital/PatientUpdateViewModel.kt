package com.technoidentity.vitalz.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.technoidentity.vitalz.data.datamodel.notification.NotificationCareTakerRequest
import com.technoidentity.vitalz.data.datamodel.notification.NotificationResponse
import com.technoidentity.vitalz.data.datamodel.updateProfile.ProfileUpdateRequest
import com.technoidentity.vitalz.data.datamodel.updateProfile.ProfileUpdateResponse
import com.technoidentity.vitalz.data.repository.UserRepositoryImpl
import com.technoidentity.vitalz.utils.CoroutinesDispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientUpdateViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {

    fun updatePatient(
        patientName: String,
        age: String,
        gender: String,
        height: String,
        weight: String,
        address: String,
        phoneNumber: String,
        emergencyContactName: String,
        emergencyContactNumber: String,
        attendingDoctor: String,
        hospitalId: String
    ): LiveData<ProfileUpdateResponse> {
        val request = ProfileUpdateRequest()
        request.patientName = patientName
        request.age = age
        request.gender = gender
        request.height = height
        request.weight = weight
        request.address = address
        request.phoneNumber = phoneNumber
        request.emergencyContactName = emergencyContactName
        request.emergencyContactNumber = emergencyContactNumber
        request.attendingDoctor = attendingDoctor
        request.hospitalId = hospitalId
        return liveData {
            emit(userRepositoryImpl.updatePatientData(request))
        }
    }
}