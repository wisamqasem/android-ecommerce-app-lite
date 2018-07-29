package com.example.shop.data

import com.example.shop.entities.Category

import java.util.ArrayList

object Data {

    val categories: ArrayList<Category>
        get() {
            val categories = ArrayList<Category>()
            categories.add(Category("Dresses", "https://www.lulus.com/images/product/xlarge/2666152_508042.jpg"))
            categories.add(Category("Women Watches", "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dwb8294957/images/KCC0060002_000_MAIN.jpg?sw=373&sh=498&sm=cut"))
            categories.add(Category("Bags", "http://www.uzwelo.co.za/wp-content/uploads/2016/05/uzwelo-shopper-laptop-bags-4.jpg"))
            categories.add(Category("Men Watches", "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dwfe93b055/images/KC15114003_000_MAIN.jpg?sw=373&sh=498&sm=cut"))
            categories.add(Category("T-Shirts", "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcQlZEz9ljsdeNDtza6fwwGz6Eq5SJmftmhdP-TGCIKMsZ7VzYPVF7kMXL_IKQ&usqp=CAE"))
            categories.add(Category("Shoes", "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcToxf2-E1xjuH33bQC6Rg8bhHGmb3FZWO3onMkzrvj45yI_y80&usqp=CAY"))

            return categories
        }
}
