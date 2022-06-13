package com.igmer.mancustomer.interfaces

import com.igmer.mancustomer.models.Product

interface AddDialogListener {
    fun onAddButtonClicked(item: Product)
}