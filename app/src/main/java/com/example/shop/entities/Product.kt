package com.example.shop.entities

import android.os.Parcel
import android.os.Parcelable

class Product() : Parcelable {
    var id: Int = 0
    lateinit var name: String
    var price: Int = 0
    lateinit var description: String
    lateinit var imageUrl: String
    lateinit var gallery: ArrayList<String>

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        price = parcel.readInt()
        description = parcel.readString()
        imageUrl = parcel.readString()
        gallery = parcel.readArrayList(null) as ArrayList<String>
    }

    constructor(id: Int, name: String, price: Int, description: String, imageUrl: String, gallery: ArrayList<String>) : this() {
        this.id = id
        this.name = name
        this.price = price
        this.description = description
        this.imageUrl = imageUrl
        this.gallery = gallery
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeList(gallery)
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