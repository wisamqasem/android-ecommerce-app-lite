package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.shop.R
import com.example.shop.adapters.ProductsAdapter
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ProductsPresenter
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*

class ProductsActivity : BaseActivity(), ProductsContract.IProductsView {

    private lateinit var products: ArrayList<Product>
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var productsPresenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_products, content_container)
        setupViewItems()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return false
    }

    private fun setupViewItems() {
        products = ArrayList()
        productsPresenter = ProductsPresenter(this)
        productsPresenter.load()

        productsAdapter = ProductsAdapter(this, this.products)
        products_recycler.layoutManager = GridLayoutManager(this, 2)
        products_recycler.adapter = productsAdapter

    }


    override fun productsLoaded(products: ArrayList<Product>) {
        this.products.addAll(products)
        productsAdapter.notifyDataSetChanged()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ProductsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
