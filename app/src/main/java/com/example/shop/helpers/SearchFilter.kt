package com.example.shop.helpers

import android.widget.Filter
import com.example.shop.adapters.SearchAdapter
import com.example.shop.entities.Product


class SearchFilter(var adapter: SearchAdapter, private var filterList: ArrayList<Product>?) : Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val results = Filter.FilterResults()
        var noSpaces: CharSequence = constraint!!.trim()
        //CHECK CONSTRAINT VALIDITY
        if (noSpaces != null && noSpaces.isNotEmpty()) {
            //CHANGE TO UPPER
            //STORE OUR FILTERED PLAYERS
            val filteredPlayers = ArrayList<Product>()
            for (i in 0 until filterList!!.size) {
                //CHECK
                if (filterList!![i].product_name.toUpperCase().contains(noSpaces.toString().toUpperCase())) {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPlayers.add(filterList!![i])
                }
            }

            results.count = filteredPlayers.size
            results.values = filteredPlayers
        } else {
            results.count = filterList!!.size
            results.values = filterList

        }

        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter!!.products = results!!.values as ArrayList<Product>
        adapter!!.notifyDataSetChanged()
    }
}