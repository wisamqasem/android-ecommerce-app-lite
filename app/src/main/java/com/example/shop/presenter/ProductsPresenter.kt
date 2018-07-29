package com.example.shop.presenter

import android.os.AsyncTask
import com.example.shop.App
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Product
import java.util.*

class ProductsPresenter(private val productsView: ProductsContract.IProductsView, val products: ArrayList<Product>) : ProductsContract.IProductsPresenter {

    override fun load() {
        object : AsyncTask<Void, Void, ArrayList<Product>>() {

            override fun doInBackground(vararg voids: Void): ArrayList<Product> {
                return products
            }

            override fun onPostExecute(products: ArrayList<Product>) {
                super.onPostExecute(products)
                productsView.productsLoaded(products)
            }
        }.execute()
    }

}
