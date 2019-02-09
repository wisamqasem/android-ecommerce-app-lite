package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.android.billingclient.api.*
import com.example.shop.R
import com.example.shop.adapters.ShoppingCartAdapter
import com.example.shop.contracts.ShoppingCartContract
import com.example.shop.entities.Product
import com.example.shop.presenter.ShoppingCartPresenter
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.app_bar_home.*


class ShoppingCartActivity : BaseActivity(), ShoppingCartContract.IShoppingCartView, View.OnClickListener, PurchasesUpdatedListener {
    override fun onPurchasesUpdated(responseCode: Int, purchases: MutableList<Purchase>?) {
        Log.d("Billing", "onPurchasesUpdated")
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

        shopping_cart_check_out_btn.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(3).isChecked = true
    }

    override fun shoppingCartLoaded(products: ArrayList<Product>) {
        this.products.addAll(products)
        //shopping_cart_items_number.text = getString(R.string._items, products.size)
        shopping_cart_subtotal.text = getString(R.string.subtotal, presenter.sum.toString())
        this.shopping_cart_recycler.adapter.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            shopping_cart_check_out_btn.id -> {
                initBilling()
                return
            }

        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, ShoppingCartActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun initBilling() {
        billingClient = BillingClient.newBuilder(this).setListener(this).build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(@BillingClient.BillingResponse billingResponseCode: Int) {
                when (billingResponseCode) {
                    BillingClient.BillingResponse.OK -> {
                        Log.d("Billing", "BillingResponse.OK")
                        queryInAppProducts()
                    }
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })
    }

    private fun queryInAppProducts() {

        val skuList = ArrayList<String>()
        skuList.add("product")

        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP)

        billingClient.querySkuDetailsAsync(params.build(),
                object : SkuDetailsResponseListener {
                    override fun onSkuDetailsResponse(responseCode: Int, skuDetailsList: MutableList<SkuDetails>?) {

                        Log.d("Billing", "onSkuDetailsResponse")
                        val flowParams = BillingFlowParams.newBuilder()
                                .setSku("product")
                                .setType(BillingClient.SkuType.INAPP) // SkuType.SUB for subscription
                                .build()
                        val responseCode = billingClient.launchBillingFlow(
                                this@ShoppingCartActivity,
                                flowParams
                        )

                    }

                })
    }
}