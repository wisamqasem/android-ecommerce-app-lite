package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.shop.R
import com.example.shop.adapters.HomeCategoriesAdapter
import com.example.shop.adapters.HomeProductsAdapter
import com.example.shop.adapters.ProductsAdapter
import com.example.shop.contracts.CategoriesContract
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Category
import com.example.shop.entities.Product
import com.example.shop.helpers.CenterZoomLayoutManager
import com.example.shop.presenter.CategoriesPresenter
import com.example.shop.presenter.ProductsPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import java.util.*

class HomeActivity : BaseActivity(), ProductsContract.IProductsView, CategoriesContract.ICategoriesView, View.OnClickListener {


    private lateinit var products: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var productsPresenter: ProductsPresenter

    private lateinit var categories: ArrayList<Category>
    private lateinit var categoriesAdapter: HomeCategoriesAdapter
    private lateinit var categoriesPresenter: CategoriesPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.content_home, content_container)
        setupViewItems()
        supportActionBar!!.title = getString(R.string.instamobile)
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(0).isChecked = true
    }

    private fun setupViewItems() {
        products = ArrayList(4)
        productsPresenter = ProductsPresenter(this)
        productsPresenter.load()

        var viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categories = ArrayList()
        categoriesAdapter = HomeCategoriesAdapter(this, categories)

        categories_recycler.layoutManager = viewManager
        categories_recycler.adapter = categoriesAdapter

        categoriesPresenter = CategoriesPresenter(this)
        categoriesPresenter.load()

        productsAdapter = HomeProductsAdapter(this, this.products)
        products_recycler.layoutManager = CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        products_recycler.adapter = productsAdapter

        products_recycler_new.layoutManager = GridLayoutManager(this, 2)
        products_recycler_new.adapter = productsAdapter
        products_recycler_new.isNestedScrollingEnabled = false


        view_all_products_btn.setOnClickListener(this)
        view_collection_btn.setOnClickListener(this)
        search_btn.setOnClickListener(this)

    }

    override fun productsLoaded(products: ArrayList<Product>) {
        products.forEach() {
            if (this.products.size < 4) {
                this.products.add(it)
            }
        }

        productsAdapter.notifyDataSetChanged()
    }


    override fun categoriesLoaded(categories: ArrayList<Category>) {
        this.categories.addAll(categories)
        categoriesAdapter.notifyDataSetChanged();
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            view_all_products_btn.id -> ProductsActivity.open(this);
            view_collection_btn.id -> ProductsActivity.open(this);
            search_btn.id -> SearchActivity.open(this);
        }

        this.overridePendingTransition(0,0)
    }


    companion object {
        fun open(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}