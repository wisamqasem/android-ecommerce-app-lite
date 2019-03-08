package com.example.shop.activities

import android.content.Context

class mypref(context : Context){


    val preference = context.getSharedPreferences("mypref", Context.MODE_PRIVATE)

    fun getLoginCount() : Int{

        return preference.getInt("logincount",0)
    }

    fun getstr(KEY : String) : String{

        return preference.getString(KEY,"")
    }

    fun setstr(key:String,value:String){
        val editor = preference.edit()
        editor.putString(key,value)
        editor.commit()
        editor.apply()

    }

}