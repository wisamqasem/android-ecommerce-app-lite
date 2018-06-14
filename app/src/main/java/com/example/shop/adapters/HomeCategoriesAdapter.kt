package com.example.shop.adapters

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shop.R
import com.example.shop.activities.ProductsActivity
import com.example.shop.entities.Category
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class HomeCategoriesAdapter(private val context: Context, private val categories: ArrayList<Category>) : RecyclerView.Adapter<HomeCategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_category_home, parent, false))
    }


    override fun onBindViewHolder(holder: HomeCategoriesAdapter.ViewHolder, position: Int) {
        val currentItem = categories[position]
        holder.name.text = currentItem.name
        Picasso.get().load(currentItem.imageUrl).into(holder.image)

        holder.itemView.setOnClickListener { ProductsActivity.open(context) }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: AppCompatTextView = itemView.findViewById(R.id.category_name)
        var image: CircleImageView = itemView.findViewById(R.id.category_image)
    }

}
