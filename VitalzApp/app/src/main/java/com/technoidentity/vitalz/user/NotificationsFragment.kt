package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.databinding.FragmentNotificationsBinding
import com.technoidentity.vitalz.hospital.PatientViewModel
import com.technoidentity.vitalz.utils.CustomProgressDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    lateinit var notificationAdapter: NotificationAdapter
    val viewModel: NotificationViewModel by viewModels()
    private lateinit var userId: String
    private lateinit var progressDialog: CustomProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater)
        progressDialog = CustomProgressDialog(this.requireContext())

        //RecyclerView Setup
        setupRecyclerView()
        userId.let{
            getNotificationsListApi(userId)
        }?: run {
            Toast.makeText(context, "Un-Authorized", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun getNotificationsListApi(userId: String) {
        progressDialog.showLoadingDialog(
            title = "Vitalz App",
            message = "Loading...",
            isCancellable = false)
        viewModel.getNotificationsListData(userId)
        viewModel.expectedResult.observe(viewLifecycleOwner, {
            when (it) {
                is NotificationViewModel.NotificationData.Success -> {
                    if (it.data.isEmpty()){
                        progressDialog.dismissLoadingDialog()
                    }else{
                        notificationAdapter.notification = it.data
                        progressDialog.dismissLoadingDialog()
                    }
                }

                is NotificationViewModel.NotificationData.Failure -> {
                    progressDialog.dismissLoadingDialog()
                    Toast.makeText(context, it.errorText, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        })
    }

    private fun setupRecyclerView() = binding.rvNotificationList.apply {
        notificationAdapter = NotificationAdapter()
        adapter = notificationAdapter
        layoutManager = LinearLayoutManager(context)
    }
}