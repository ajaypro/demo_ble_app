package com.technoidentity.vitalz.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentDocnurseLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorNurseLoginFragment : Fragment() {
    private lateinit var binding: FragmentDocnurseLoginBinding
    val viewModel: DoctorNurseLoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocnurseLoginBinding.inflate(layoutInflater)
        binding.btnLoginDocNurse.setOnClickListener {
            validateCredentials(binding.etUserName.text.toString(), binding.etPassword.text.toString())
        }
        return binding.root
    }

    private fun validateCredentials(username: String, password: String) {
        when {
            username.isEmpty() -> {
                Toast.makeText(context, "Please Enter username", Toast.LENGTH_SHORT).show()
            }
            password.isEmpty() -> {
                Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
            }
            else -> {
                lifecycleScope.launchWhenCreated {
                    viewModel.sendDocNurseCredentials(username, password)
                    viewModel.expectedResult.observe(viewLifecycleOwner, {
                        when(it){
                            is DoctorNurseLoginViewModel.DocNurse.Success -> {
                                val pref = context?.getSharedPreferences(Constants.PREFERENCE_NAME, 0)
                                pref?.edit()?.putString(Constants.TOKEN, it.data.token)?.apply()
                                Log.v("Check", "Stage_Pref ${it.data.token}")
                                Navigation.findNavController(requireView()).navigate(R.id.doctorDashboardFragment)
                            }
                            is DoctorNurseLoginViewModel.DocNurse.Failure -> {
                                Toast.makeText(context,it.errorText, Toast.LENGTH_SHORT).show()
                            }
                            else -> Unit
                        }
                    })
                }
            }
        }
    }
}