package com.technoidentity.vitalz.dashboard

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.technoidentity.vitalz.databinding.CaretakerNurseDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NurseCareTakerDashboardFragment : Fragment() {

    val viewModel: NurseCareTakerDashboardViewModel by viewModels()
    private var mobile : String? = null
    lateinit var binding: CaretakerNurseDashboardBinding
    var navController: NavController? = null
    private var exit: Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CaretakerNurseDashboardBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(container!!)

        //Getting Arguments From last Fragment
        mobile = arguments?.getString("mobileNumber")

        //Api call to fetch Latest data
        if (mobile != null){
            singleDashboardApi(mobile!!)
        }else{
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun singleDashboardApi(mobile: String) {
        lifecycleScope.launchWhenCreated {
            viewModel.getSinglePatientData(mobile)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is NurseCareTakerDashboardViewModel.SinglePatient.Success -> {

                    }

                    is NurseCareTakerDashboardViewModel.SinglePatient.Failure -> {
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
        }
    }
}