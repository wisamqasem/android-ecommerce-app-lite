package com.example.shop.contracts

import com.example.shop.entities.Product
import java.util.*

class ShoppingCartContract {
    interface IShoppingCartView {
        fun shoppingCartLoaded(products: ArrayList<Product>)
    }

    interface IShoppingCartPresenter {
        fun load()
    }
}