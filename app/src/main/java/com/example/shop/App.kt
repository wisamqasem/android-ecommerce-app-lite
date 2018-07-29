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
        gallery.add("https://www.lulus.com/images/product/xlarge/3115840_581362.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/3115860_581362.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/3115870_581362.jpg")

        products.add(Product(1, "BLUE CHAMBRAY HALTER DRESS", 180, "From Eliza J, this dress features:\n" +
                "V-neckline\n" +
                "Sleeveless\n" +
                "Midi length\n" +
                "Tie front detail\n" +
                "Approx. 47\" length\n" +
                "Polyester\n" +
                "Dry clean", "https://www.lulus.com/images/product/xlarge/2078562_323852.jpg", gallery))

        products.add(Product(2,
                "NAVY BLUE POLKA",
                124,
                "There is nothing you can't do in the Girl Like You Navy Blue Polka Dot Two-Piece Maxi Dress! \n\n Soft and breezy woven poly creates a fluttering short sleeve crop top with a tie-front, and blue and white polka dot print. \n\n Pair with the matching high-waisted maxi skirt, with a ruffled, high-low hem, and elastic at back for fit. Hidden side zipper/clasp.",
                "https://www.lulus.com/images/product/xlarge/3115840_581362.jpg", gallery))

        products.add(Product(3,
                "OFF-THE-SHOULDER MAXI DRESS", 34,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.lulus.com/images/product/xlarge/3174390_413622.jpg", gallery))
        products.add(Product(4,
                "ZENITH MAXI DRESS",
                43,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.lulus.com/images/product/xlarge/1842402_302862.jpg", gallery))
        products.add(Product(5,
                "Whenever you call DRESS",
                42,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.lulus.com/images/product/xlarge/3101740_384862.jpg", gallery))

        return products
    }

    fun getHomeGridProducts(): java.util.ArrayList<Product> {
        val products = java.util.ArrayList<Product>()

        val gallery = java.util.ArrayList<String>()
        gallery.add("https://www.lulus.com/images/product/xlarge/3115840_581362.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/3115860_581362.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/3115870_581362.jpg")

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
                "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dw8ae616f3/images/KCC0039002_000_MAIN.jpg?sw=373&sh=498&sm=cut", gallery))

        products.add(Product(2,
                "NGunmetal Watch",
                115,
                "There is nothing you can't do in the Girl Like You Navy Blue Polka Dot Two-Piece Maxi Dress! \n\n Soft and breezy woven poly creates a fluttering short sleeve crop top with a tie-front, and blue and white polka dot print. \n\n Pair with the matching high-waisted maxi skirt, with a ruffled, high-low hem, and elastic at back for fit. Hidden side zipper/clasp.",
                "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dw3a5b9c26/images/KCC0039003_000_MAIN.jpg?sw=373&sh=498&sm=cut", gallery))

        products.add(Product(3,
                "White Lether Round Watch", 95,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dwf76bbd58/images/KC15109002_000_MAIN.jpg?sw=373&sh=498&sm=cut", gallery))
        products.add(Product(4,
                "Kenneth Cole New York",
                99,
                "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.",
                "https://www.kennethcole.com/dw/image/v2/AAVO_PRD/on/demandware.static/-/Sites-kennethcole-master-catalog/default/dwb8294957/images/KCC0060002_000_MAIN.jpg?sw=373&sh=498&sm=cut", gallery))


        return products
    }

    companion object {
        lateinit var instance: App
            private set
    }
}