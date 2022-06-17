package com.igmer.mancustomer.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.DialogAddProductItemBinding
import com.igmer.mancustomer.interfaces.AddDialogListener
import com.igmer.mancustomer.models.Product

class AddProductItemDialog(private val addDialogListener: AddDialogListener) :
    DialogFragment() {
    lateinit var binding: DialogAddProductItemBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_add_product_item, null, false)
            binding.save.setOnClickListener(View.OnClickListener {
                if (validateField()) {
                    val name = binding.name.text.toString()
                    val purchasePrice = binding.purchasePrice.text.toString()
                    val salePrice = binding.salePrice.text.toString()
                    val description = binding.description.text.toString()
                    val stock = binding.stock.text.toString()
                    val product = Product(
                        0,
                        name,
                        purchasePrice.toDouble(),
                        salePrice.toDouble(),
                        description,
                        null,
                        stock.toInt()
                    )
                    addDialogListener.onAddButtonClicked(product)
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
        if (binding.purchasePrice.text!!.isEmpty()) {
            binding.tlPrice.error = getString(R.string.error_field_required)
            isValid = false
        }
        if (binding.salePrice.text!!.isEmpty()) {
            binding.tlSalesPrice.error = getString(R.string.error_field_required)
            isValid = false
        }
        if (binding.stock.text!!.isEmpty()) {
            binding.tlStock.error = getString(R.string.error_field_required)
            isValid = false
        }
        return isValid
    }
}