package com.example.shop.presenter

import android.os.AsyncTask
import com.example.shop.App
import com.example.shop.contracts.ShoppingCartContract
import com.example.shop.entities.Product
import java.util.*

class ShoppingCartPresenter(private val shoppingCartView: ShoppingCartContract.IShoppingCartView) : ShoppingCartContract.IShoppingCartPresenter {


    var sum :Int = 0;

    override fun load() {
        object : AsyncTask<Void, Void, ArrayList<Product>>() {

            override fun doInBackground(vararg voids: Void): ArrayList<Product> {

                App.instance.shoppingCartList.forEach {
                    sum = (sum + it.product_price)
                }
                return App.instance.shoppingCartList
            }

            override fun onPostExecute(products: ArrayList<Product>) {
                super.onPostExecute(products)
                shoppingCartView.shoppingCartLoaded(products)
            }
        }.execute()
    }


}
