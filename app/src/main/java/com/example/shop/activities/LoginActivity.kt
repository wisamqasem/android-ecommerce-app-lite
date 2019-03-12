package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.shop.App
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_home.*




class LoginActivity : BaseActivity(), View.OnClickListener {
    //val b = findViewById(R.id.login_btn)

  var app=App()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_login, content_container)
        setupViewItems()


        supportActionBar!!.title = getString(R.string.login)
    }



    private fun setupViewItems() {
        create_new_account_btn.setOnClickListener(this)
        login_btn.setOnClickListener(this)


        //   login_btn.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }


    //Multiple click listeners on buttons

    override fun onClick(v: View?) {
        val username: EditText = findViewById(R.id.login_username_input) as EditText

        val password: EditText = findViewById(R.id.login_password_input) as EditText
        when (v!!.id) {

            R.id.login_btn -> {
if(check(username.text.toString(),password.text.toString())){
              //  Toast.makeText(this, , Toast.LENGTH_SHORT).show()
    val mypreference = mypref(this)
    mypreference.setstr("username",username.text.toString())

             HomeActivity.open(this)
   // Toast.makeText(this,"login successfully" , Toast.LENGTH_SHORT).show()



  //  supportActionBar!!.title="wer"
                }else Toast.makeText(this,"email or password is wrong" , Toast.LENGTH_SHORT).show()
                return
            }
            create_new_account_btn.id -> {
                CreateAccountActivity.open(this)//there is a function in the CreateAccountAcivity named open used to open creataccount activity
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