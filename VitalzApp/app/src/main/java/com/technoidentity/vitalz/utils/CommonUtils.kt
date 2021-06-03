package com.technoidentity.vitalz.utils

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.technoidentity.vitalz.data.network.Constants
import com.technoidentity.vitalz.data.network.VitalzService

fun getToken(context: Context) {
    val sp = context.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
    VitalzService.token = sp.getString(Constants.TOKEN, VitalzService.token)!!
}

fun Fragment.hasPermission(permissionType: String): Boolean {
    return ContextCompat.checkSelfPermission(requireActivity(), permissionType) ==
            PackageManager.PERMISSION_GRANTED
}

fun Fragment.requestPermission(permission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
}

fun showToast(context: Context?, message: String) {
    context?.let {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

/**
 * View Extensions
 */

fun View.showSnackbar(msgId: Int, length: Int, actionMessageId: Int) {
    showSnackbar(msgId, length, actionMessageId) {}
}

fun View.showSnackbar(
    msgId: Int,
    length: Int,
    actionMessageId: Int,
    action: (View) -> Unit
) {
    showSnackbar(context.getString(msgId), length, context.getString(actionMessageId), action)
}

fun View.showSnackbar(
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(this, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(it)
        }.show()
    }
}

fun showAlert(activity: Activity, title: String, message: String, positiveButtonMsg: String,
              onClickListener: DialogInterface.OnClickListener ) {

    AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonMsg){ _, _ ->
            onClickListener
        }
        .show()
}

fun showAlertWithNegative(activity: Activity, title: String, message: String, positiveButtonMsg: String, negativeButtonMsg: String,
              onClickListener: DialogInterface.OnClickListener ) {

    AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButtonMsg){ _, _ ->
            activity.onBackPressed()
        }
        .setNegativeButton(negativeButtonMsg){ dialog, _ ->
            dialog.cancel()
        }
        .show()
}

fun isTablet(ctx: Context): Boolean {
    return ctx.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
}