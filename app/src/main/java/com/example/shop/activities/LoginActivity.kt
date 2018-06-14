package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_home.*

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_login, content_container)
        setupViewItems()
    }

    private fun setupViewItems() {
        create_new_account_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            create_new_account_btn.id -> {
                CreateAccountActivity.open(this)
                return
            }
        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

}