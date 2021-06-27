package com.technoidentity.vitalz.user

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants.PREFERENCE_NAME
import com.technoidentity.vitalz.databinding.SettingsBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog


class SettingsFragment : Fragment(){

    lateinit var binding : SettingsBinding
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //For nurse Update Profile Will be visible else invisible
//         binding.layoutUpdateProfile.visibility = View.VISIBLE

        //Logout
        binding.layoutLogout.setOnClickListener {
            progressDialog.showLoadingDialog()
            val preferences = requireContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            val countDownTimer = object : CountDownTimer(2000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                }

                override fun onFinish() {
                    progressDialog.dismissLoadingDialog()
                    findNavController().navigate(R.id.action_settingsFragment_to_userSelectionFragment2)
                }

            }
            countDownTimer.start()
        }
        return binding.root
    }
}
