package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_home.*

class CreateAccountActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_create_account, content_container)
        setupViewItems()

        supportActionBar!!.title = getString(R.string.signup)
    }

    private fun setupViewItems() {
        create_account_login_btn.setOnClickListener(this)
        create_account_btn.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            create_account_btn.id -> {

                return
            }

            create_account_login_btn.id -> {
                LoginActivity.open(this)
                return
            }
        }
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, CreateAccountActivity::class.java)
            context.startActivity(intent)
        }
    }

}