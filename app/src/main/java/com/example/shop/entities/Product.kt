package com.example.shop.entities

class Product( val product_Id:String ,var product_name :String,var product_description:String,var product_price:Int,var seller_id:String,var category_id:String,
               var size:String,var color:String,var discount:String,var image:String,var note:String,var buyer_id:String,
               var payment_id :String,var quantity:String)  {




    lateinit var gallery: ArrayList<String>



    constructor() : this("", "", "",0,"","","","","","","","","","")










    }
