package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.example.shop.R
import com.example.shop.adapters.ShoppingCartAdapter
import com.example.shop.contracts.ShoppingCartContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ShoppingCartPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.app_bar_home.*

class ShoppingCartActivity : BaseActivity(), ShoppingCartContract.IShoppingCartView, PurchasesUpdatedListener {
    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private lateinit var presenter: ShoppingCartPresenter
    private lateinit var products: ArrayList<Product>
    lateinit private var billingClient: BillingClient

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

        initBilling()
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(3).isChecked = true
    }

    override fun shoppingCartLoaded(products: ArrayList<Product>) {
        this.products.addAll(products)
        shopping_cart_items_number.text = getString(R.string._items, products.size)
        shopping_cart_subtotal.text = getString(R.string.subtotal, presenter.sum.toString())
        this.shopping_cart_recycler.adapter.notifyDataSetChanged()
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ShoppingCartActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun initBilling(){
        billingClient = BillingClient.newBuilder(this).setListener(this).build()
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(@BillingClient.BillingResponse billingResponseCode: Int) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    // The billing client is ready. You can query purchases here.
                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }
}