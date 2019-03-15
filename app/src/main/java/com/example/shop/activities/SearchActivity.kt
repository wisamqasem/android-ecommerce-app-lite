package com.example.shop.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import com.example.shop.App
import com.example.shop.R
import com.example.shop.adapters.SearchAdapter
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ProductsPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_home.*
import java.util.*


class SearchActivity : BaseActivity(), ProductsContract.IProductsView, android.support.v7.widget.SearchView.OnQueryTextListener {

    private lateinit var products: ArrayList<Product>
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var productsPresenter: ProductsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_search, content_container)

        setupViewItems()
        supportActionBar!!.title = getString(R.string.search)

        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent!!)
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(2).isChecked = true
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow

            Log.d("SEARCH:::", query);
        }
    }

    private fun setupViewItems() {

        products = ArrayList()
        productsPresenter = ProductsPresenter(this, getProducts())
        productsPresenter.load()

        searchAdapter = SearchAdapter(this, this.products)
        search_recycler_view.layoutManager = LinearLayoutManager(this)
        search_recycler_view.adapter = searchAdapter
        search_recycler_view.itemAnimator = DefaultItemAnimator()


    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        this.defaultMenu = menu

        setCount(App.instance.shoppingCartList.size.toString())
        menu.findItem(R.id.search).isVisible = true
        menu.findItem(R.id.search).expandActionView()


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));

        searchView.setOnQueryTextListener(this)
        searchView.requestFocus();

        return true
    }

    override fun productsLoaded(products: Array<Product>) {
        this.products.addAll(products)
        searchAdapter.notifyDataSetChanged()
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchAdapter.getFilter().filter(newText)
        return false
    }

    //open function to open this activity from any activity else
    companion object {
        fun open(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}