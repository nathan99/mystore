package com.example.student.mystore

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.student.mystore.model.Product
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {

    var product = Product()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        title = "Add Product"
        btn_add_product.setOnClickListener {
            if (true) {
                product.imgURL = R.drawable.firefox
                product.productname = et_product_name.text.toString()
                product.price = et_product_price.text.toString().toDouble()
                product.desc = et_product_desc.text.toString()
                product.stock = et_product_stock.text.toString().toInt()
                product.display = switch_product_display.isChecked

                val intent = Intent()
                intent.putExtra( "PRODUCT_ADD_EXTRA", product)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        }
    }


}
