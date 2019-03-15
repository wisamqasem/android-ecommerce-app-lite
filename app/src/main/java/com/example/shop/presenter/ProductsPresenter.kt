package com.example.shop.presenter

import android.os.AsyncTask
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Product

class ProductsPresenter(private val productsView: ProductsContract.IProductsView, val products: Array<Product>) : ProductsContract.IProductsPresenter {

    override fun load() {
        object : AsyncTask<Void, Void, Array<Product>>() {

            override fun doInBackground(vararg voids: Void): Array<Product> {
                return products
            }

            override fun onPostExecute(products: Array<Product>) {
                super.onPostExecute(products)
                productsView.productsLoaded(products)
            }
        }.execute()
    }

}
