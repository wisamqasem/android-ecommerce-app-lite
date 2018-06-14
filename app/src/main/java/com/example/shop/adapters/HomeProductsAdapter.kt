package com.example.shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.entities.Product
import java.util.*


class HomeProductsAdapter(context: Context, products: ArrayList<Product>) : ProductsAdapter(context, products) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_product_home, parent, false))
    }

}
