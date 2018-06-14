package com.example.shop.contracts

import com.example.shop.entities.Category

import java.util.ArrayList

class CategoriesContract {

    interface ICategoriesView {
        fun categoriesLoaded(categories: ArrayList<Category>)
    }

    interface ICategoriesPresenter {
        fun load()
    }
}
