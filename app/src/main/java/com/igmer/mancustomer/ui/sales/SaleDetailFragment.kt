package com.igmer.mancustomer.ui.sales

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.igmer.mancustomer.R
import com.igmer.mancustomer.ui.MainActivity

class SaleDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SaleDetailFragment()
    }

    private lateinit var viewModel: SaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale_detail, container, false)
    }




}