package com.example.shop.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.app_bar_home.*
import okhttp3.FormBody


class CreateAccountActivity : BaseActivity(), View.OnClickListener {


    lateinit var option : Spinner
lateinit var   result :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_create_account, content_container)
        setupViewItems()
        option = findViewById(R.id.sp_option) as Spinner

        //spinnner staff
        val options = arrayOf("Option 1", "Option 2","Option 3")//add more options

        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //write code here.....
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //write code here.....
               result= options.get(position).toString()
            }
        }
        //spinner staff
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
               val confirem_password : EditText = findViewById(R.id.create_account_password_again_input) as EditText
                val email: EditText = findViewById(R.id.create_account_email_input) as EditText
                val phone: EditText = findViewById(R.id.create_account_phone_input) as EditText
                val password: EditText = findViewById(R.id.create_account_password_input) as EditText
                val city1 : Spinner = findViewById(R.id.sp_option) as Spinner


                //if the passowrd not match show toast
if (password.text.toString()!=confirem_password.text.toString())
{

    Toast.makeText(this,"password not match" , Toast.LENGTH_SHORT).show()
    return}
                //find if there is any semiler email
                for (user in aa.users){
                    if(user.email==email.text.toString()){
                        Toast.makeText(this,"this email is already used" , Toast.LENGTH_SHORT).show()
                        return
                    }


                }

                val formBody = FormBody.Builder()
                        .add("first_name", firstname.text.toString() )
                        .add("last_name", lastname.text.toString() )
                        .add("city", result )
                        .add("email", email.text.toString() )
                        .add("password", password.text.toString() )
                        .add("phone", phone.text.toString() )
                        .build()

                //make sure all fildes filled
if(firstname.text.toString()!=""&&lastname.text.toString()!=""&&email.text.toString()!=""&&password.text.toString()!=""&&phone.text.toString()!="")
{setdata("https://ppudatabase.000webhostapp.com/CA.php",formBody)
    HomeActivity.open(this)
    Toast.makeText(this,"registered successfully" , Toast.LENGTH_SHORT).show()
    return
}

                //in case there is filed is empty toast
                Toast.makeText(this,"please fill all fileds" , Toast.LENGTH_SHORT).show()

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