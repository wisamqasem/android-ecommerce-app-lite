package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.app_bar_home.*

class SearchActivity : BaseActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_search, content_container)

        setupViewItems()
    }

    override fun onResume() {
        super.onResume()
        nav_view.menu.getItem(2).isChecked = true
    }

    private fun setupViewItems() {
        search_cancel_btn.setOnClickListener(this)
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

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }
}