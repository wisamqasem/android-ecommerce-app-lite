package com.example.shop.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.shop.App
import com.example.shop.R
import com.example.shop.entities.Product
import com.example.shop.helpers.CountDrawable
import com.example.shop.messges.LatestMesages
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize
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
        var prod:Array<Product>

//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


getdata("https://ppudatabase.000webhostapp.com","users")
        getdata("https://ppudatabase.000webhostapp.com/products.php","products")

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
            R.id.nav_sell ->{
                SellActivity.open(this)
            }
            R.id.nav_message ->{
                val intent = Intent(this, LatestMesages::class.java)
                this.startActivity(intent)
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

    fun check(name:String,password: String):Boolean{
        val mypreference = mypref(this)
        for (user in aa.users ){
            if(name==user.email && password==user.password){
                mypreference.setstr("id",user.id.toString())
                mypreference.setstr("first_name",user.first_name.toString())
                mypreference.setstr("last_name",user.last_name.toString())
                mypreference.setstr("city",user.city.toString())
//                mypreference.setstr("address",user.address.toString())
  //              mypreference.setstr("phone",user.phone.toString())
                mypreference.setstr("email",user.email.toString())
                mypreference.setstr("password",user.password.toString())
    //            mypreference.setstr("image",user.image.toString())




                return  true
            }


        }
        return false
    }






}

 fun getdata(url:String,name:String) {
     //"https://ppudatabase.000webhostapp.com"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                println(body)
                val gson = GsonBuilder().create()
                if(name=="users"){
               aa.users  = gson.fromJson(body, Array<User>::class.java) as Array<User>
for (user in aa.users) {
    if (user.email == null)
        user.email = ""
    if (user.password == null)
        user.password = ""
    if (user.first_name == null)
        user.first_name = ""
    if (user.BD_day == null)
        user.BD_day = 0
    if (user.BD_month == null)
        user.BD_month = 0
    if (user.BD_year == null)
        user.BD_year = 0
    if (user.address == null)
        user.address = ""
    if (user.card_exp_month == null)
        user.card_exp_month = 0
    if (user.card_exp_year == null)
        user.card_exp_year = 0
    if (user.city == null)
        user.city = ""
    if (user.credit_card == null)
        user.credit_card = ""
    if (user.credit_card_type_id == null)
        user.credit_card_type_id = 0
    if (user.id == null)
        user.id = ""
    if (user.image == null)
        user.image = ""
    if (user.is_admin == null)
        user.is_admin = ""
    if (user.is_login == null)
        user.is_login = ""
    if (user.phone == null)
        user.phone = ""
    if (user.ranking == null)
        user.ranking = ""
}


}//end if name is users
                else if(name=="products"){
                    println("done p")
                  aa.products = gson.fromJson(body, Array<Product>::class.java) as Array<Product>

                }
           }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
            }
        })

    }
fun getUsers():Array<User>{
    return  aa.users
}
fun getProducts():Array<Product>{

    getdata("https://ppudatabase.000webhostapp.com/products.php","products")
    var  pp : Array<Product> = aa.products

    return  pp
}


fun setdata(url:String,formBody: FormBody){

    val request = Request.Builder().url(url) .post(formBody).build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object: Callback {
        override fun onResponse(call: Call?, response: Response?) {
            val body = response?.body()?.string()

//
        }

        override fun onFailure(call: Call?, e: IOException?) {
            println("Failed to execute request")
        }
    })




}





@Parcelize
 class User(var id : String, var first_name : String, var last_name : String, var city : String,
             var address: String, var phone: String,var email :String,var password : String
            ,var credit_card:String,var card_exp_month:Int,var card_exp_year:Int,
            var credit_card_type_id	:Int,var BD_month:Int,var BD_day:Int,
            var BD_year:Int,var image:String,var is_admin :String,var ranking :String,var is_login :String) : Parcelable {
    constructor() : this("", "", "","","","","","","",0,0,0,0,0,0,"","","","")
}

