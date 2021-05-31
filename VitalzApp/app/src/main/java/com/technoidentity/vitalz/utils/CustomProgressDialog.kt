package com.technoidentity.vitalz.utils

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.technoidentity.vitalz.R

class CustomProgressDialog(context: Context) {
  var m_Dialog: AlertDialog.Builder = AlertDialog.Builder(context)
  val m_Message: TextView
  var mDialogBox: AlertDialog

  init {
    val mView = LayoutInflater.from(context).inflate(R.layout.progress_bar_layout, null)
    m_Dialog.setView(mView)
    m_Message = mView.findViewById(R.id.progressBar_text)
    mDialogBox = m_Dialog.create()
  }

  fun showLoadingDialog(title: String, message: String, isCancellable: Boolean) {
    mDialogBox.setTitle(title)
    m_Message.text = message
    mDialogBox.setCancelable(isCancellable)
    mDialogBox.show()
  }

  fun dismissLoadingDialog() {
    if (mDialogBox.isShowing) {
      mDialogBox.dismiss()
    }
  }
}
