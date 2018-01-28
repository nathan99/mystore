package com.example.student.mystore

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.student.mystore.model.Product
import kotlinx.android.synthetic.main.product_layout.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by student on 1/21/18.
 */
class ProductAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var c: Context? = null
    var lists: ArrayList<Product> = ArrayList()
    private var activity:MainActivity?  = null

    constructor(c: Context?, lists: ArrayList<Product>) : this() {
        this.c = c
        this.lists = lists
    }
    fun addProduct(product: Product){
        lists.add(0, product)
        notifyItemInserted(lists.size-1)
        notifyItemRangeInserted(0,lists.size)
    }

    fun deleteProduct(position: Int){
        activity =  c as MainActivity

        lists.removeAt(position)
        notifyItemRemoved(lists.size-1)
        notifyItemRangeRemoved(0,lists.size)

        activity!!.updateCount(lists.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var v = LayoutInflater.from(c).inflate(R.layout.product_layout, parent, false)
        return Item(v)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(position)
    }

    inner class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(position: Int) {

            val product: Product = lists[position]

            itemView.imageicon.setImageResource(product.imgURL)
            itemView.prod_name.text = product.productname
            itemView.prod_desc.text = product.desc
            itemView.prod_price.text = priceToPhp(product.price)
            itemView.prod_stock.text = "Stock: " + product.stock.toString()
            itemView.menu_button.setOnClickListener {
             var item_menu =PopupMenu(c!!, itemView.menu_button)
                item_menu.inflate(R.menu.menu_popup)
                item_menu.show()
                item_menu.setOnMenuItemClickListener { item_menu ->
                    when (item_menu.itemId) {
//                        R.id.menu_show_details -> {
//
//                            var intent = Intent(c, ProductDetailActivity::class.java)
//                            intent.putExtra("PRODUCT_EXTRA",product)
//                            c!!.startActivity(intent)
//
//                        }
                        R.id.menu_remove -> {
                            AlertDialog.Builder(c!!).apply {
                                setTitle("Delete Product")
                                setMessage("Are you sure you want to remove this product?")
                                setNegativeButton("No", null)
                                setPositiveButton("Yes",{dialogInterface, i ->
                                    dialogInterface.dismiss()
                                    deleteProduct(lists.indexOf(product))
                                }).show()
                            }
                        }
                        R.id.menu_edit -> {
                            var intent = Intent(c!!, AddProductActivity::class.java)
                            product.editable=true
                            product.id = position
                            intent.putExtra("PRODUCT_EDIT_EXTRA",product)

                            val mActivity:MainActivity = c!! as MainActivity
                            mActivity.startActivityForResult(intent, 123)
                        }
                    }

                    true
                }
            }
        }

        fun priceToPhp(value: Double): String {
            val local = Locale("ph", "PH")
            val formatter = NumberFormat.getCurrencyInstance(local);
            return formatter.format(value)
        }
    }
}