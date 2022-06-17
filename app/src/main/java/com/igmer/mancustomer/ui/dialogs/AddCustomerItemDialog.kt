package com.igmer.mancustomer.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.DialogAddCustomerItemBinding
import com.igmer.mancustomer.databinding.DialogAddProductItemBinding
import com.igmer.mancustomer.interfaces.AddDialogListener
import com.igmer.mancustomer.models.Customer
import com.igmer.mancustomer.models.Product

class AddCustomerItemDialog(private val addDialogListener: AddDialogListener) :
    DialogFragment() {
    lateinit var binding: DialogAddCustomerItemBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_add_customer_item, null, false)
            binding.save.setOnClickListener(View.OnClickListener {
                if (validateField()) {
                    val name = binding.name.text.toString()
                    val phone = binding.phone.text.toString()
                    val address = binding.address.text.toString()
                    val email = binding.email.text.toString()
                    val customer = Customer(0,name, phone, address,email)
                    addDialogListener.onAddButtonClicked(customer)
                    dismiss()
                }
            })
            builder.setView(binding.root)
            builder.setMessage(R.string.add_product)
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun validateField(): Boolean {
        var isValid = true
        if (binding.name.text!!.isEmpty()) {
            binding.tlName.error = getString(R.string.error_field_required)
            isValid = false
        }
        if (binding.phone.text!!.isEmpty()) {
            binding.tlPhone.error = getString(R.string.error_field_required)
            isValid = false
        }

        return isValid
    }
}