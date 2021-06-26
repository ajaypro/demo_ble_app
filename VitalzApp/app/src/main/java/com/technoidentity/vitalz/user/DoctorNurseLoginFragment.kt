package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentDocnurseLoginBinding
import com.technoidentity.vitalz.home.SharedViewModel
import com.technoidentity.vitalz.utils.CustomProgressDialog
import com.technoidentity.vitalz.utils.isTablet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DoctorNurseLoginFragment : Fragment() {
    private lateinit var binding: FragmentDocnurseLoginBinding
    val viewModel: DoctorNurseLoginViewModel by viewModels()
    val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocnurseLoginBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLoginDocNurse.setOnClickListener {
            validateCredentials(
                binding.etUserName.text.toString(),
                binding.etPassword.text.toString()
            )
        }

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
                progressDialog.showLoadingDialog()
                viewModel.sendDocNurseCredentials(username, password)
                viewModel.expectedResult.observe(viewLifecycleOwner, {
                    when (it) {
                        is DoctorNurseLoginViewModel.DocNurse.Success -> {
                            val pref =
                                context?.getSharedPreferences(Constants.PREFERENCE_NAME, 0)
                            pref?.edit()?.putString(Constants.TOKEN, it.data.token)?.apply()
                            progressDialog.dismissLoadingDialog()

                            //check for tablet or mobile and navigate
                            when (isTablet(requireContext())) {
                                false ->
                                {
                                    findNavController().navigate(R.id.action_doctorNurseLoginFragment_to_multiPatientDashboardFragment)
                                }
                                true ->
                                {
                                        sharedViewModel.isDeviceConnected.observe(viewLifecycleOwner) { deviceConnected ->
                                            if (!deviceConnected) {
                                                findNavController().navigate(R.id.action_doctorNurseLoginFragment_to_addDeviceFragment)
                                            } else {
                                                findNavController().navigate(R.id.action_deviceDetailsFragment_to_singlePatientDashboardFragment)
                                            }
                                        }
                                }
                            }
                        }

                        is DoctorNurseLoginViewModel.DocNurse.Failure -> {
                            progressDialog.dismissLoadingDialog()
                            Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                })
            }
        }
    }
}