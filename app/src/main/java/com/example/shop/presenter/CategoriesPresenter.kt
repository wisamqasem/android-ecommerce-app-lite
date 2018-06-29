package com.example.shop.presenter

import android.os.AsyncTask

import com.example.shop.contracts.CategoriesContract
import com.example.shop.data.Data
import com.example.shop.entities.Category

import java.util.ArrayList

class CategoriesPresenter(private val shopView: CategoriesContract.ICategoriesView) : CategoriesContract.ICategoriesPresenter {

    override fun load() {
        object : AsyncTask<Void, Void, ArrayList<Category>>() {

            override fun doInBackground(vararg voids: Void): ArrayList<Category> {
                return Data.categories
            }

            override fun onPostExecute(categories: ArrayList<Category>) {
                super.onPostExecute(categories)
                shopView.categoriesLoaded(categories)
            }
        }.execute()
    }

}
