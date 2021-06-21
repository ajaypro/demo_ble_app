package com.technoidentity.vitalz.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.utils.showToast
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by activityViewModels()

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenResumed {
            homeViewModel.isDeviceConnected.collect {
                showToast(requireContext(), "homefragment - isdeviceconnected $it")
                if (!it) {
                    findNavController().navigate(R.id.action_homeFragment_to_addDeviceFragment)
                } else {
                    //learn conditional navigation to navigate to load singlepatient fragment when device is connected
                    findNavController().navigate(R.id.action_homeFragment_to_singlePatientDashboardFragment)
                }
            }
        }
    }
}