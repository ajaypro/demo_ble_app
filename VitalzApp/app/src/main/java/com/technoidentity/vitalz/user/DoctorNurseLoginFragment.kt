package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentDocnurseLoginBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorNurseLoginFragment : Fragment() {
    private lateinit var binding: FragmentDocnurseLoginBinding
    val viewModel: DoctorNurseLoginViewModel by viewModels()
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocnurseLoginBinding.inflate(layoutInflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        binding.btnLoginDocNurse.setOnClickListener {
            validateCredentials(
                binding.etUserName.text.toString(),
                binding.etPassword.text.toString()
            )
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
                progressDialog.showLoadingDialog(
                    title = "Vitalz App",
                    message = "Loading...",
                    isCancellable = false
                )
                viewModel.sendDocNurseCredentials(username, password)
                viewModel.expectedResult.observe(viewLifecycleOwner, {
                    when (it) {
                        is DoctorNurseLoginViewModel.DocNurse.Success -> {
                            val pref =
                                context?.getSharedPreferences(Constants.PREFERENCE_NAME, 0)
                            pref?.edit()?.putString(Constants.TOKEN, it.data.token)?.apply()
                            progressDialog.dismissLoadingDialog()
                            findNavController().navigate(R.id.doctorDashboardFragment)
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