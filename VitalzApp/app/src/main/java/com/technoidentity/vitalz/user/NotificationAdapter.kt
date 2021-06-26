package com.technoidentity.vitalz.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.NotificationData

class NotificationAdapter(var hospitalData: List<NotificationData>) :
    RecyclerView.Adapter<NotificationAdapter.HospitalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_notifications_list, parent, false)
        return HospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalData.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val data = hospitalData[position]
        holder.title.text = data.notificationTitle
        holder.date.text = data.notificationDate
    }

    inner class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_notification_title)
        val date: TextView = itemView.findViewById(R.id.tv_notification_date)
    }
}