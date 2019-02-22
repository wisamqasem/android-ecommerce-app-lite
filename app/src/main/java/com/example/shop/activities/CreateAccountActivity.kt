package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.app_bar_home.*
import okhttp3.*
import java.io.IOException


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


    //Multiple click listeners on buttons
    override fun onClick(v: View?) {
        when (v!!.id) {
            create_account_btn.id -> {
                //write you code here...

                var firstname :EditText = findViewById(R.id.create_account_first_name_input) as EditText
                val lastname: EditText = findViewById(R.id.create_account_last_name_input) as EditText
                val city: EditText = findViewById(R.id.create_account_city_input) as EditText
                val email: EditText = findViewById(R.id.create_account_email_input) as EditText
                val phone: EditText = findViewById(R.id.create_account_phone_input) as EditText
                val password: EditText = findViewById(R.id.create_account_password_input) as EditText

                val formBody = FormBody.Builder()
                        .add("first_name", firstname.text.toString() )
                        .add("last_name", lastname.text.toString() )
                        .add("city", city.text.toString() )
                        .add("email", email.text.toString() )
                        .add("password", password.text.toString() )
                        .add("phone", phone.text.toString() )
                        .build()
                val request = Request.Builder().url("https://ppudatabase.000webhostapp.com/CA.php") .post(formBody).build()

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