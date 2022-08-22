package com.igmer.mancustomer.ui.sales

import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.igmer.mancustomer.R
import com.igmer.mancustomer.adapters.AdapterProducts
import com.igmer.mancustomer.adapters.AdapterSale
import com.igmer.mancustomer.databinding.AdminCustomerFragmentBinding
import com.igmer.mancustomer.databinding.FragmentSaleDetailBinding
import com.igmer.mancustomer.models.Product
import com.igmer.mancustomer.models.Sale
import com.igmer.mancustomer.ui.MainActivity
import com.igmer.mancustomer.ui.customer.CustomerViewModel
import com.igmer.mancustomer.utils.AlertDialogCustom
import com.igmer.mancustomer.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SaleDetailFragment : Fragment() {

    private val viewModel: SaleDetailViewModel by viewModels()
    lateinit var binding:  FragmentSaleDetailBinding
    private var productSelected: Product? = null
    private lateinit var saleAdapter: AdapterSale

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSaleDetailBinding.inflate(inflater, container, false)
        val currentTime = Calendar.getInstance().time
        val dateFormated = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(currentTime)
        val root: View = binding.root
        val id = arguments?.getInt("id")
        val salesTypes = resources.getStringArray(R.array.sales_types)
        val adapterSaleType = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, salesTypes)
        binding.typeSale.setAdapter(adapterSaleType)
        setupRecyclerView()
        if (id != null) {
            viewModel.getAllSaleByIdCustomerAndDate(id,dateFormated)
        }

        viewModel.getCustomerById(id!!).observe(viewLifecycleOwner){
            binding.tvCustomerName.text = it.name
        }
        viewModel.getAllSaleObserver().observe(viewLifecycleOwner){
            saleAdapter.setProducts(it as ArrayList<Sale>)
            saleAdapter.notifyDataSetChanged()
        }

        viewModel.productList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.idProduct.setAdapter(context?.let { it1 -> ArrayAdapter(it1, android.R.layout.simple_spinner_dropdown_item, it) })
        })

       binding.idProduct.setOnItemClickListener { parent, view, position, id ->
            val selected = parent.getItemAtPosition(position) as Product
           val salePrice = selected.sale_price
           binding.price.setText(salePrice.toString())
            productSelected = selected
        }

        binding.save.setOnClickListener{
            val isValid = validate()
            if (isValid) {
                val quantity = binding.quantity.text.toString().toInt()
                val unitPrice = binding.price.text.toString()
                val totalWithoutDiscount = quantity * unitPrice.toDouble()
                val discountPercent = binding.discoutnt.text.toString()
                val discount = totalWithoutDiscount * discountPercent.toDouble() / 100

                val total = totalWithoutDiscount - discount.toDouble()
                val saleType = binding.typeSale.text.toString()
                val dialog = AlertDialogCustom(requireContext())
                dialog.init()
                dialog.customDialogTitle.setText(getString(R.string.confirm_sale, Utils.decimalFormat(total)))
                dialog.btnCancel.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.btnAccept.setOnClickListener {

                    val sale = productSelected?.let { it1 -> Sale(0, quantity, unitPrice.toDouble(), dateFormated, total, discount,it1.id, id, saleType) }
                    if (sale != null) {
                        val idSale = viewModel.savesale(sale,id,dateFormated)
                        print("idSale $idSale")
                        cleanFields()
                    }
                    dialog.dismiss()
                }
                dialog.showAlert()


            }
        }

        return root
    }

    private fun cleanFields() {
        binding.quantity.text?.clear()
        binding.price.text?.clear()
        binding.total.text?.clear()
        binding.discoutnt.text?.clear()
    }

    private fun validate(): Boolean {
        var isValid = true;
        if (binding.quantity.text.isNullOrEmpty()){
            binding.quantity.error = "Ingrese una cantidad"
            isValid = false
        }
        if (binding.price.text.isNullOrEmpty()){
            binding.price.error = "Ingrese un precio"
            isValid = false
        }
        if(productSelected == null){
            binding.idProduct.error = "Ingrese un producto"
            isValid = false
        }
        return isValid
    }
    private fun setupRecyclerView() {
        //set margin top for recycler view
        binding.rvSales.setPadding(
            0,
            resources.getDimensionPixelSize(R.dimen.margin_top_recycler_view),
            0,
            0
        )
        binding.rvSales.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            saleAdapter = AdapterSale()
            adapter = saleAdapter
            val divider = DividerItemDecoration(context, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
    }


}