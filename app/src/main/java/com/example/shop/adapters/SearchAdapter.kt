package com.example.shop.adapters

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.shop.R
import com.example.shop.entities.Product
import com.example.shop.helpers.RoundedTransformation
import com.example.shop.helpers.SearchFilter
import com.squareup.picasso.Picasso


open class SearchAdapter(context: Context, products: ArrayList<Product>) : ProductsAdapter(context, products), Filterable {

    private var filterList: ArrayList<Product> = products
    private var filter: SearchFilter? = null
    var imageSize = 300;


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_search, parent, false))
    }


    override fun loadImage(currentItem: Product, image: AppCompatImageView) {
        Picasso.get().load(currentItem.imageUrl).transform(RoundedTransformation(15, 0)).resize(imageSize, imageSize).centerCrop().into(image)
    }


    override fun getFilter(): Filter {

        if (filter == null) {
            filter = SearchFilter(this, filterList)
        }

        return filter as SearchFilter
    }

}
