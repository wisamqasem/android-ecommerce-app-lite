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
        gallery.add("https://www.lulus.com/images/product/xlarge/3024307_560802.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/2433092_413622.jpg")
        gallery.add("https://www.lulus.com/images/product/xlarge/3168200_290162.jpg")

        products.add(Product(1, "Tie Front Floral Print Midi Dress", 180, "From Eliza J, this dress features:\n" +
                "V-neckline\n" +
                "Sleeveless\n" +
                "Midi length\n" +
                "Tie front detail\n" +
                "Approx. 47\" length\n" +
                "Polyester\n" +
                "Dry clean", "https://dimg.dillards.com/is/image/DillardsZoom/main/eliza-j-tie-front-floral-print-midi-dress/05349142_zi_green.jpg", gallery))

        products.add(Product(2, "Long Alyce High-Low Taffeta Prom Dress with Slit", 124, "This long high-low prom dress, by the designers at Alyce, is reasonably priced under $250 for formal events. The sleeveless evening gown, made from stretch taffeta, holds its shape and gives a comfortable fit. The crossover ruched v-neck bodice has multiple thin shoulder straps that continue over the open back in a unique design. Extending from the natural waist, the long a-line skirt ripples to a flirty short off-center hem and cascades to a romantic floor-length hem in back. For a striking entrance to prom or a head-turning look on a pageant stage, wear this long high-low taffeta prom dress by Alyce.", "https://img.promgirl.com/_img/PGPRODUCTS/1771859/320/red-dress-AL-60094-a.jpg", gallery))

        products.add(Product(3, "POPPY BLACK SKATER DRESS", 34, "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.", "https://cdn.tobi.com/product_images/md/1/emerald-poppy-layered-skater-dress.jpg", gallery))
        products.add(Product(4, "POPPY BLACK SKATER DRESS", 43, "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.", "https://cdn.tobi.com/product_images/md/1/emerald-poppy-layered-skater-dress.jpg", gallery))
        products.add(Product(5, "POPPY BLACK SKATER DRESS", 42, "Infatuated with you in the Poppy Layered Skater Dress. Featuring a caressing halter neck and chicly fitted bodice. This skater dress ups the sophistication with its double layered skirt and exposed back, all finished with a hidden zipper closure. Thank you for the butterflies.", "https://cdn.tobi.com/product_images/md/1/emerald-poppy-layered-skater-dress.jpg", gallery))


        return products
    }

    companion object {
        lateinit var instance: App
            private set
    }
}