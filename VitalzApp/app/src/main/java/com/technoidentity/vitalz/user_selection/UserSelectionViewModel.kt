package com.technoidentity.vitalz.user_selection

import android.view.View
import androidx.lifecycle.ViewModel

class UserSelectionViewModel: ViewModel() {

  lateinit var userSelectionInterface: UserSelectionInterface

  fun setInterface(userSelectionInterface: UserSelectionInterface){
    this.userSelectionInterface = userSelectionInterface
  }

  fun patientCareTaker(view : View){
    userSelectionInterface.navigateToActivity()
  }

  fun hospitalOperative(view: View){
    userSelectionInterface.toastMsg()
  }
}