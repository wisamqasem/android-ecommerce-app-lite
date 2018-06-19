package com.example.shop.adapters

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.activities.ProductsActivity
import com.example.shop.entities.Category
import com.squareup.picasso.Picasso
import java.util.*


open class CategoriesAdapter(private val context: Context, private val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categories[position]
        holder.name.text = currentItem.name
        Picasso.get().load(currentItem.imageUrl).into(holder.image)

        holder.itemView.setOnClickListener { ProductsActivity.open(context, currentItem.name) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: AppCompatTextView = itemView.findViewById(R.id.category_name)
        var image: AppCompatImageView = itemView.findViewById(R.id.category_image)
    }
}
