package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.shop.App
import com.example.shop.R
import com.example.shop.adapters.SlideGalleryAdapter
import com.example.shop.entities.Product
import kotlinx.android.synthetic.main.activity_product_details.*



class ProductDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        layoutInflater.inflate(R.layout.activity_product_details, content_container)
        setContentView(R.layout.activity_product_details)
      //  product = intent.extras.getParcelable(KEY_PRODUCT)
        setupViewItems()

    }

    private fun setupViewItems() {
        slide_view_pager.adapter = SlideGalleryAdapter(this, product.gallery)
        product_name.text = product.product_name
        product_price.text = product.product_price.toString()
        product_description.text = product.product_description

        indicator.setViewPager(slide_view_pager)

        close_btn.setOnClickListener(this)
        buy_now_btn.setOnClickListener(this)
    }

    companion object {
        private val KEY_PRODUCT: String? = "PRODUCT"

        fun open(context: Context, product: Product) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
        //    intent.putExtra(KEY_PRODUCT, product)
            context.startActivity(intent)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            close_btn.id -> {
                finish()
                return
            }

            buy_now_btn.id -> {
                if (!checkForExistingProduct(product.product_Id)) {
                    App.instance.shoppingCartList.add(product)
                    Toast.makeText(this, getString(R.string.product_added_to_cart), Toast.LENGTH_SHORT).show()
                    finish()
//                    setCount(App.instance.shoppingCartList.size.toString())
                } else {
                    Toast.makeText(this, getString(R.string.product_already_on_cart), Toast.LENGTH_SHORT).show()
                }
                return
            }

        }
    }

    private fun checkForExistingProduct(productId: String): Boolean {
        App.instance.shoppingCartList.forEach() {
            if (it.product_Id == productId) {
                return true
            }
        }
        return false
    }

}