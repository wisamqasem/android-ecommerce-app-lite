package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.shop.R
import com.example.shop.adapters.ShoppingCartAdapter
import com.example.shop.contracts.ShoppingCartContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ShoppingCartPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.app_bar_home.*

class ShoppingCartActivity : BaseActivity(), ShoppingCartContract.IShoppingCartView {


    private lateinit var presenter: ShoppingCartPresenter
    private lateinit var products: ArrayList<Product>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_shopping_cart, content_container)
        setupViewItems()

        supportActionBar!!.title = getString(R.string.shopping_cart)
    }

    private fun setupViewItems() {
        presenter = ShoppingCartPresenter(this)
        presenter.load()

        products = ArrayList()

        shopping_cart_recycler.layoutManager = LinearLayoutManager(this)
        shopping_cart_recycler.adapter = ShoppingCartAdapter(this, products)

        shopping_cart_items_number.text = getString(R.string._items, products.size)
        shopping_cart_subtotal.text = getString(R.string.subtotal, presenter.sum.toString())
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(3).isChecked = true
    }

    override fun shoppingCartLoaded(products: ArrayList<Product>) {
        this.products.addAll(products)
        shopping_cart_items_number.text = getString(R.string._items, products.size)
        this.shopping_cart_recycler.adapter.notifyDataSetChanged()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ShoppingCartActivity::class.java)
            context.startActivity(intent)
        }
    }
}