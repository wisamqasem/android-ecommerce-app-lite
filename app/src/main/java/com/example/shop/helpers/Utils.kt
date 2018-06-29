package com.example.shop.helpers

import android.content.Context
import android.graphics.Point
import android.support.v7.app.AppCompatActivity

class Utils {

    companion object {
        fun getDisplayWidth(context: Context): Int {
            context as AppCompatActivity
            val display = context.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x
        }
    }
}