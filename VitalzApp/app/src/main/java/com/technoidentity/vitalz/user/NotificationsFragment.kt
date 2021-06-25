package com.technoidentity.vitalz.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.technoidentity.vitalz.data.datamodel.NotificationData
import com.technoidentity.vitalz.databinding.FragmentNotificationsBinding
import com.technoidentity.vitalz.hospital.NotificationAdapter

class NotificationsFragment: Fragment() {

    lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater)

        val notificationData = mutableListOf(
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
            NotificationData("Notification Title","21/04/2021"),
        )

        val myAdapter = NotificationAdapter(notificationData)
        binding.rvNotificationList.adapter = myAdapter
        binding.rvNotificationList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}