package com.example.shop.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.example.shop.R
import com.example.shop.entities.Product
import com.example.shop.helpers.SearchFilter


open class SearchAdapter(context: Context, products: ArrayList<Product>) : ProductsAdapter(context, products), Filterable {

    private var filterList: ArrayList<Product> = products
    private var filter: SearchFilter? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_search, parent, false))
    }


    override fun getFilter(): Filter {

        if (filter == null) {
            filter = SearchFilter(this, filterList)
        }

        return filter as SearchFilter
    }

}
