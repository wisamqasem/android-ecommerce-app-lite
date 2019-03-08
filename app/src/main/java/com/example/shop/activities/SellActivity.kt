package com.example.shop.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.shop.R
import kotlinx.android.synthetic.main.activity_sell.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class SellActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sell)
        var add_photo :Button = findViewById(R.id.add_photo)
        var add_photo_album :Button = findViewById(R.id.album)
        add_photo_album.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, 91)

        }
        add_photo.setOnClickListener {
            val i =Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i,90)
           // upload("https://ppudatabase.000webhostapp.com/up.php")
        }
        var up:Button=findViewById(R.id.button1)
up.setOnClickListener {
    upload("https://ppudatabase.000webhostapp.com/up.php")


    println("done")
}





    }


    var b :Bitmap?=null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==90&& resultCode==Activity.RESULT_OK && data!=null)
        {

            b = data.extras.get("data") as Bitmap
            imageView.setImageBitmap(b)
        }
       else if (requestCode==91&& resultCode==Activity.RESULT_OK && data!=null) {
            val contentURI = data!!.data
          b = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)

            imageView!!.setImageBitmap(b)
        }




    }
    var i=0
    fun upload(url: String){

     //   val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
     //   val currentDate = sdf.format(Date())


        i++
     //   var bool :Boolean = true
        val stream = ByteArrayOutputStream()
        try {

            b!!.compress(Bitmap.CompressFormat.JPEG,100,stream)

        }
        catch(ex:Exception){
            Toast.makeText(this,"upload filled" , Toast.LENGTH_SHORT).show()
          //  bool=false
        return
        }
        val byteArray= stream.toByteArray()

        val requestBody=MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image","image"+ i +".jpg", RequestBody.create(MediaType.parse("image/*jpg"),byteArray))
                .build()

        val request = Request.Builder().url(url) .post(requestBody).build()
       // if(bool==true)
        Toast.makeText(this,"upload sucssefuly" , Toast.LENGTH_SHORT).show()

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
    //open function to open the activity
    companion object {
        fun open(context: Context) {
            val intent = Intent(context, SellActivity::class.java)
            context.startActivity(intent)
        }
    }
}
