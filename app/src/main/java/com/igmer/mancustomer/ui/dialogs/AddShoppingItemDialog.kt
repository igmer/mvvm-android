package com.igmer.mancustomer.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.DialogFragment
import com.igmer.mancustomer.R
import com.igmer.mancustomer.databinding.DialogAddShoppingItemBinding
import com.igmer.mancustomer.interfaces.AddDialogListener
import com.igmer.mancustomer.models.Product

class AddShoppingItemDialog(private val addDialogListener: AddDialogListener) :
    DialogFragment() {
    lateinit var binding: DialogAddShoppingItemBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            binding =
                DataBindingUtil.inflate(inflater, R.layout.dialog_add_shopping_item, null, false)
            builder.setView(binding.root)
            builder.setMessage(R.string.action_sign_in)
                .setPositiveButton(R.string.add) { _, _ ->
                    if (validateField()) {
                        val name = binding.name.text.toString()
                        val purchasePrice = binding.purchasePrice.text.toString()
                        val salePrice = binding.salePrice.text.toString()
                        val purchase_price = binding.purchasePrice.text.toString()
                        val description = binding.description.text.toString()
                        val stock = binding.stock.text.toString()
                        val product = Product(
                            0,
                            name,
                            purchasePrice.toDouble(),
                            salePrice.toDouble(),
                            purchase_price,
                            description,
                            stock.toInt()
                        )
                        addDialogListener.onAddButtonClicked(product)
                        dismiss()
                    }
                }
                .setNegativeButton(R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
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