package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.databinding.FragmentNotificationsBinding
import com.technoidentity.vitalz.home.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment: Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    private val homeViewModel: HomeActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater)


//        val notificationData = mutableListOf(
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//            NotificationData("Notification Title","21/04/2021"),
//        )

        val myAdapter = NotificationAdapter(notificationData)
        binding.rvNotificationList.adapter = myAdapter
        binding.rvNotificationList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}