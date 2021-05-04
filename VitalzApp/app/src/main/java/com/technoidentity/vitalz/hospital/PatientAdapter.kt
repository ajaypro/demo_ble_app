package com.technoidentity.vitalz.hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technoidentity.vitalz.R
import com.technoidentity.vitalz.data.datamodel.PatientData

class PatientAdapter(var patientData: List<PatientData>) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_patient_list, parent, false)
        return PatientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patientData.size
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val data = patientData[position]
        holder.pName.text = data.patientName
        holder.pAge.text = data.patientAge
        holder.pSex.text = data.patientSex
        holder.pId.text = data.patientId
        holder.pAddress.text = data.patientHospitalAddress
    }

    inner class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pName: TextView = itemView.findViewById(R.id.tv_patient_name)
        val pAge: TextView = itemView.findViewById(R.id.tv_patient_age)
        val pSex: TextView = itemView.findViewById(R.id.tv_patient_sex)
        val pId: TextView = itemView.findViewById(R.id.tv_patient_id)
        val pAddress: TextView = itemView.findViewById(R.id.tv_patient_hospital_address)
    }
}