package com.example.shop

import android.app.Application
import com.example.shop.entities.Product

class App : Application() {

    lateinit var shoppingCartList: ArrayList<Product>

    override fun onCreate() {
        super.onCreate()
        shoppingCartList = ArrayList()
        instance = this
    }

    fun getProducts(): java.util.ArrayList<Product> {
        val products = java.util.ArrayList<Product>()

        val gallery = java.util.ArrayList<String>()
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")

        products.add(Product(1, "BLUE CHAMBRAY HALTER DRESS", 180, "From Eliza J, this dress features:\n" +
                "V-neckline\n" +
                "Sleeveless\n" +
                "Midi length\n" +
                "Tie front detail\n" +
                "Approx. 47\" length\n" +
                "Polyester\n" +
                "Dry clean", "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))

        products.add(Product(2,
                "NAVY BLUE POLKA",
                124,
                "There is nothing you can't do in the Girl Like You Navy Blue Polka Dot Two-Piece Maxi Dress! \n\n Soft and breezy woven poly creates a fluttering short sleeve crop top with a tie-front, and blue and white polka dot print. \n\n Pair with the matching high-waisted maxi skirt, with a ruffled, high-low hem, and elastic at back for fit. Hidden side zipper/clasp.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))

        products.add(Product(3,
                "OFF-THE-SHOULDER MAXI DRESS", 34,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))
        products.add(Product(4,
                "ZENITH MAXI DRESS",
                43,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))
        products.add(Product(5,
                "Whenever you call DRESS",
                42,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))

        return products
    }

    fun getHomeGridProducts(): java.util.ArrayList<Product> {
        val products = java.util.ArrayList<Product>()

        val gallery = java.util.ArrayList<String>()
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")
        gallery.add("https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png")

        products.add(Product(1,
                "Light Blue Silver Tone Watch",
                105,
                "From Eliza J, this dress features:\n" +
                "V-neckline\n" +
                "Sleeveless\n" +
                "Midi length\n" +
                "Tie front detail\n" +
                "Approx. 47\" length\n" +
                "Polyester\n" +
                "Dry clean",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))

        products.add(Product(2,
                "NGunmetal Watch",
                115,
                "There is nothing you can't do in the Girl Like You Navy Blue Polka Dot Two-Piece Maxi Dress! \n\n Soft and breezy woven poly creates a fluttering short sleeve crop top with a tie-front, and blue and white polka dot print. \n\n Pair with the matching high-waisted maxi skirt, with a ruffled, high-low hem, and elastic at back for fit. Hidden side zipper/clasp.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))

        products.add(Product(3,
                "White Lether Round Watch", 95,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))
        products.add(Product(4,
                "Kenneth Cole New York",
                99,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.delta-controls.com/wp-content/uploads/2018/05/Product-Image-Coming-Soon.png", gallery))


        return products
    }

    companion object {
        lateinit var instance: App
            private set
    }
}