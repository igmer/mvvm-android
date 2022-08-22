package com.igmer.mancustomer.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.CustomDialogBinding

class AlertDialogCustom(context: Context) {
    private val builder = Dialog(context)
    private val bindingCard: CustomDialogBinding = CustomDialogBinding.inflate(LayoutInflater.from(context))
    val btnCancel = bindingCard.btnCancel
    val btnAccept = bindingCard.btnAccept
    val customDialogTitle = bindingCard.customDialogTitle

    fun init() {
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.setContentView(bindingCard.root)
        builder.setCanceledOnTouchOutside(false)
    }

    fun dismiss() {
        builder.dismiss()
    }

    fun showAlert() {
        builder.show()
        builder.window?.setBackgroundDrawableResource(R.color.transparent)
    }
}