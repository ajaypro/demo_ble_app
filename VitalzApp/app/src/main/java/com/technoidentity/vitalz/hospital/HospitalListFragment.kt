package com.technoidentity.vitalz.hospital

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.databinding.FragmentHospitalListBinding
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HospitalListFragment : Fragment(), HospitalAdapter.OnItemClickListener  {

    val viewModel: HospitalViewModel by viewModels()
    private lateinit var token : String
    private lateinit var mobile : String
    lateinit var binding: FragmentHospitalListBinding
    private lateinit var hospitalAdapter: HospitalAdapter
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHospitalListBinding.inflate(inflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //get Shared data
        val sharedPreferences =
            context?.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        token = sharedPreferences?.getString(Constants.TOKEN, null).toString()
        mobile = sharedPreferences?.getString(Constants.MOBILE, null).toString()

        //setup RecyclerView
        setUpRecyclerView()
        mobile.let {
            getHospitalList(it)
        }. run {
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
        }

        //Search has Cancel icon with visibility GONE

        return binding.root
    }

    private fun getHospitalList(mobile: String) {
        progressDialog.showLoadingDialog()
            viewModel.getHospitalListData(mobile)
            viewModel.expectedResult.observe(viewLifecycleOwner, {
                when (it) {
                    is HospitalViewModel.HospitalData.Success -> {
                        hospitalAdapter.hospitals = it.data
                        progressDialog.dismissLoadingDialog()
                    }

                    is HospitalViewModel.HospitalData.Failure -> {
                        progressDialog.dismissLoadingDialog()
                        Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            })
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
            Navigation.findNavController(requireView()).navigate(
                R.id.action_hospitalListFragment_to_patientListFragment, bundle)
        }
    }
}
