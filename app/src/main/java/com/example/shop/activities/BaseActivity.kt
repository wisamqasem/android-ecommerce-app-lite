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
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.app_bar_home.*
import okhttp3.*
import java.io.IOException


@SuppressLint("Registered")
var aa=App()
open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var defaultMenu: Menu? = null


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


fetchJson()
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
 fun fetchJson() {
        val request = Request.Builder().url("https://ppudatabase.000webhostapp.com").build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
               aa.users  = gson.fromJson(body, Array<User>::class.java) as Array<User>
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })

    }


 fun check(name:String,password: String):Boolean{
       for (user in aa.users ){
           if(name==user.email && password==user.password){
               return  true
           }}
        return false
    }




 class User(val id : Int, val first_name : String, val last_name : String, val city : String,
             val address: String, val phone: String,val email :String,val password : String
            ,val credit_card:String,val card_exp_month:Int,val card_exp_year:Int,
            val credit_card_type_id	:Int,val BD_month:Int,val BD_day:Int,
            val BD_year:Int,val image:String,val is_admin :String,val ranking :String,val is_login :String)

