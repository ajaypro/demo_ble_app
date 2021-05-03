package com.technoidentity.vitalz.hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.HospitalData

class HospitalAdapter(var hospitalData: List<HospitalData>) :
    RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_hospital_list, parent, false)
        return HospitalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hospitalData.size
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val data = hospitalData[position]
        holder.hName.text = data.hospitalName
        holder.hId.text = data.hospitalId
        holder.hAddress.text = data.hospitalAddress
    }

    inner class HospitalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hName: TextView = itemView.findViewById(R.id.tv_hospital_name)
        val hId: TextView = itemView.findViewById(R.id.tv_hospital_id)
        val hAddress: TextView = itemView.findViewById(R.id.tv_hospital_address)
    }
}