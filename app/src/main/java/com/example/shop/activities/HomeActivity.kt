package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.example.shop.App
import com.example.shop.R
import com.example.shop.adapters.CarouselProductsAdapter
import com.example.shop.adapters.HomeCategoriesAdapter
import com.example.shop.adapters.HomeProductsAdapter
import com.example.shop.contracts.CategoriesContract
import com.example.shop.contracts.ProductsContract
import com.example.shop.entities.Category
import com.example.shop.entities.Product
import com.example.shop.helpers.ItemDecoration
import com.example.shop.helpers.ItemOffsetDecoration
import com.example.shop.presenter.CategoriesPresenter
import com.example.shop.presenter.ProductsPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*
import java.util.*

class HomeActivity : BaseActivity(), ProductsContract.IProductsView, CategoriesContract.ICategoriesView, View.OnClickListener {


    private lateinit var products: ArrayList<Product>
    private lateinit var gridProducts: ArrayList<Product>
    private lateinit var productsAdapter: HomeProductsAdapter
    private lateinit var carouselProductsAdapter: CarouselProductsAdapter
    private lateinit var productsPresenter: ProductsPresenter
    private lateinit var gridProductsPresenter: ProductsPresenter

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
        gridProducts = ArrayList(4)

        productsPresenter = ProductsPresenter(this, App.instance.getProducts())
        productsPresenter.load()

        gridProductsPresenter = ProductsPresenter(this, App.instance.getHomeGridProducts())
        gridProductsPresenter.load()

        var viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categories = ArrayList()
        categoriesAdapter = HomeCategoriesAdapter(this, categories)

        categories_recycler.layoutManager = viewManager
        categories_recycler.adapter = categoriesAdapter

        categoriesPresenter = CategoriesPresenter(this)
        categoriesPresenter.load()

        productsAdapter = HomeProductsAdapter(this, this.gridProducts)
        carouselProductsAdapter = CarouselProductsAdapter(this, this.products)

       // products_carousel_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
      //  products_carousel_recycler.adapter = carouselProductsAdapter
      //  products_carousel_recycler.scrollToPosition(0)

      //  products_carousel_recycler.addItemDecoration(ItemDecoration(this))


        products_recycler_new.layoutManager = GridLayoutManager(this, 2)
        products_recycler_new.adapter = productsAdapter
        products_recycler_new.addItemDecoration(ItemOffsetDecoration(this, R.dimen.margin))
        products_recycler_new.isNestedScrollingEnabled = false


        view_all_products_btn.setOnClickListener(this)
        view_collection_btn.setOnClickListener(this)
        search_btn.setOnClickListener(this)

    }

    override fun productsLoaded(products: ArrayList<Product>) {
        if (products.size == 4) {
            products.forEach() {
                this.gridProducts.add(it)
            }
        } else {
            products.forEach() {
                this.products.add(it)
            }
        }

        productsAdapter.notifyDataSetChanged()
        carouselProductsAdapter.notifyDataSetChanged()
    }


    override fun categoriesLoaded(categories: ArrayList<Category>) {
        this.categories.addAll(categories)
        categoriesAdapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            view_all_products_btn.id -> ProductsActivity.open(this, "Dresses");
            view_collection_btn.id -> ProductsActivity.open(this, "Women Watches");
            search_btn.id -> SearchActivity.open(this);
        }

        this.overridePendingTransition(0, 0)
    }

//open function to open the activity
    companion object {
        fun open(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
}