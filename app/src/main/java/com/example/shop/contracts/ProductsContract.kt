package com.example.shop.contracts

import com.example.shop.entities.Product

class ProductsContract {
    interface IProductsView {
        fun productsLoaded(products: Array<Product>)
    }

    interface IProductsPresenter {
        fun load()
    }
}
