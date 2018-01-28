package com.example.student.mystore

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.student.mystore.StaticData.Companion.getLists
import com.example.student.mystore.model.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter = ProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ProductAdapter(this, StaticData.getLists())
        rv_product.layoutManager = LinearLayoutManager(this)
        rv_product.hasFixedSize()
        rv_product.adapter = adapter

        fab_add_product.setOnClickListener {
            var intent = Intent(this@MainActivity, AddProductActivity::class.java)
            startActivityForResult(intent, 123)
        }
        updateCount(adapter.lists.size)
    }

    fun updateCount(count: Int) {
            tv_total_products.text = "Total Products: " + count
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == 123){
                var product = data?.getParcelableExtra<Product>("PRODUCT_ADD_EXTRA")
                adapter.addProduct(product!!)
                rv_product.adapter = adapter
                updateCount(adapter.lists.size)
            }
        }
    }

}

