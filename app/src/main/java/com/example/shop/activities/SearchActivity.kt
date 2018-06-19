package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.shop.R
import com.example.shop.adapters.SearchAdapter
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ProductsPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*

class SearchActivity : BaseActivity(), View.OnClickListener, ProductsContract.IProductsView, android.support.v7.widget.SearchView.OnQueryTextListener {

    private lateinit var products: ArrayList<Product>
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var productsPresenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_search, content_container)

        setupViewItems()
        supportActionBar!!.title = getString(R.string.search)
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(2).isChecked = true
    }

    private fun setupViewItems() {
        search_cancel_btn.setOnClickListener(this)

        products = ArrayList()
        productsPresenter = ProductsPresenter(this)
        productsPresenter.load()

        searchAdapter = SearchAdapter(this, this.products)
        search_recycler_view.layoutManager = LinearLayoutManager(this)
        search_recycler_view.adapter = searchAdapter
        search_recycler_view.itemAnimator = DefaultItemAnimator()

        search_view.setOnQueryTextListener(this)

    }


    override fun productsLoaded(products: ArrayList<Product>) {
        this.products.addAll(products)
        searchAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            search_cancel_btn.id -> {
                finish()
                overridePendingTransition(0, 0)
                return
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchAdapter.getFilter().filter(newText)
        return false
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}