package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.technoidentity.vitalz.databinding.SettingsBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.utils.isTablet

class SettingsFragment : Fragment(){

    lateinit var binding : SettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SettingsBinding.inflate(layoutInflater)

        //For nurse Update Profile Will be visible else invisible
//         binding.layoutUpdateProfile.visibility = View.VISIBLE

        return binding.root
    }
}
