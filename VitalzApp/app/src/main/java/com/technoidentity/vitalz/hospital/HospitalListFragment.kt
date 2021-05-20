package com.technoidentity.vitalz.hospital

import android.content.Context
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentHospitalListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalListFragment : Fragment(), HospitalAdapter.OnItemClickListener  {

    val viewModel: HospitalViewModel by viewModels()
    private var token = String()
    private var mobile = String()
    lateinit var binding: FragmentHospitalListBinding
    private lateinit var hospitalAdapter: HospitalAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHospitalListBinding.inflate(inflater)

        //get Shared data
        val sharedPreferences =
            context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        token = sharedPreferences?.getString(Constants.TOKEN, null).toString()
        mobile = sharedPreferences?.getString(Constants.MOBILE, null).toString()

        //setup RecyclerView
        setUpRecyclerView()
        if (token != null) {
            getHospitalList(token)
        } else {
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
//           TODO Logout
            Log.v("Check", "StageFail_NewFrag $token")
        }

        return binding.root
    }

    private fun getHospitalList(token: String) {
        lifecycleScope.launchWhenCreated {
            viewModel.getHospitalListData(token)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is HospitalViewModel.HospitalData.Success -> {
                        hospitalAdapter.hospitals = it.data
                    }

                    is HospitalViewModel.HospitalData.Failure -> {
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
        }
    }

    private fun setUpRecyclerView() = binding.rvHospitalList.apply {
        hospitalAdapter = HospitalAdapter(this@HospitalListFragment)
        adapter = hospitalAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onItemClicked(position: Int) {
        val bundle = Bundle()
        bundle.putString("mobile", mobile)
        bundle.putString("hospitalId", hospitalAdapter.hospitals[position].id)
        if (hospitalAdapter.hospitals.isEmpty()){
            Toast.makeText(context, "No Patient Available", Toast.LENGTH_SHORT).show()
        }else{
            Navigation.findNavController(requireView()).navigate(R.id.patientListFragment, bundle)
        }
    }
}
