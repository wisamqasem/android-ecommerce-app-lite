package com.example.shop.activities

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shop.App
import com.example.shop.R
import com.example.shop.helpers.CountDrawable
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_bar_home.*


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var defaultMenu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)



    }

    override fun onResume() {
        super.onResume()
        if (defaultMenu != null) {
            setCount(App.instance.shoppingCartList.size.toString())
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        this.defaultMenu = menu

        setCount(App.instance.shoppingCartList.size.toString())
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_cart -> consume {
                ShoppingCartActivity.open(this)
                overridePendingTransition(0, 0)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun consume(function: () -> Unit): Boolean {
        function()
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                HomeActivity.open(this)
            }
            R.id.nav_shop -> {
                CategoriesActivity.open(this)
            }
            R.id.nav_search -> {
                SearchActivity.open(this)
            }
            R.id.nav_my_cart -> {
                ShoppingCartActivity.open(this)
            }
            R.id.nav_my_account -> {
                LoginActivity.open(this)
            }
        }

        overridePendingTransition(0, 0)

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun setCount(count: String) {
        val menuItem: MenuItem = defaultMenu!!.findItem(R.id.action_cart)
        val icon: LayerDrawable = menuItem.icon as LayerDrawable

        val badge: CountDrawable

        val reuse: Drawable = icon.findDrawableByLayerId(R.id.ic_cart_count)
        badge = if (true && reuse is CountDrawable) {
            reuse
        } else {
            CountDrawable(this)
        }

        badge.setCount(count)
        icon.mutate()
        icon.setDrawableByLayerId(R.id.ic_cart_count, badge)
    }
}
