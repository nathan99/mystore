package com.example.student.mystore.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by student on 1/21/18.
 */
class Product : Parcelable {
    var id: Int = 0
    var imgURL: Int = 0
    var productname: String = ""
    var desc: String = ""
    var price: Double = 0.0
    var stock: Int = 0
    var display: Boolean = false
    var editable: Boolean = false


    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        imgURL = parcel.readInt()
        productname = parcel.readString()
        desc = parcel.readString()
        price = parcel.readDouble()
        stock = parcel.readInt()
        display = parcel.readByte() != 0.toByte()
    }

    constructor()

    constructor(id: Int, imgURL: Int, productname: String, desc: String, price: Double, stock: Int, display: Boolean, editable: Boolean) {
        this.id = id
        this.imgURL = imgURL
        this.productname = productname
        this.desc = desc
        this.price = price
        this.stock = stock
        this.display = display
        this.editable = editable
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(imgURL)
        parcel.writeString(productname)
        parcel.writeString(desc)
        parcel.writeDouble(price)
        parcel.writeInt(stock)
        parcel.writeByte(if (display) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }


}