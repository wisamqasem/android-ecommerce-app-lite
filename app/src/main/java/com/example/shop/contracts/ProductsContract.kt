package com.example.shop.contracts

import com.example.shop.entities.Product

import java.util.ArrayList

class ProductsContract {
    interface IProductsView {
        fun productsLoaded(products: ArrayList<Product>)
    }

    interface IProductsPresenter {
        fun load()
    }
}
