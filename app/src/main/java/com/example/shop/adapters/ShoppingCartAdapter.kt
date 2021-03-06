package com.example.shop.adapters

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.entities.Product
import com.squareup.picasso.Picasso
import java.util.*


open class ShoppingCartAdapter(private val context: Context, private val products: ArrayList<Product>) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_shopping_cart, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = products[position]
        Picasso.get().load(currentItem.image).into(holder.image)
      //  holder.price.text = context.getString(R.string.price, currentItem.price)
        holder.price.text =  context.getString(R.string.price, currentItem.product_price)
        holder.name.text = currentItem.product_name

    }

    override fun getItemCount(): Int {
        return products.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: AppCompatImageView = itemView.findViewById(R.id.row_shopping_cart_product_image)
        var name: AppCompatTextView = itemView.findViewById(R.id.row_shopping_cart_product_name)
        var price: AppCompatTextView = itemView.findViewById(R.id.row_shopping_cart_product_price)

    }
}
