package com.example.shop.data;

import com.example.shop.entities.Category;

import java.util.ArrayList;

public class Data {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Dresses", "https://www.lulus.com/images/product/xlarge/2451702_458742.jpg"));
        categories.add(new Category("Woman Watches", "https://ae01.alicdn.com/kf/HTB1RA4VKFXXXXa7XXXXq6xXFXXXu/KEVIN-New-Design-Women-Watches-Fashion-Black-Round-Dial-Stainless-Steel-Band-Quartz-Wrist-Watch-Mens.jpg_640x640.jpg"));
        categories.add(new Category("Bags", "https://gloimg.samcdn.com/S/pdm-product-pic/Clothing/2017/10/12/source-img/20171012190941_23624.jpg"));
        categories.add(new Category("Men Watches", "https://cdn.shopify.com/s/files/1/1381/5713/products/product-image_93643775-023e-4442-b724-17d2ebda57f9.jpg?v=1468366698"));
        categories.add(new Category("T-Shirts", "https://media.missguided.com/s/missguided/TJ416135_set/1/white-pocket-front-t-shirt"));
        categories.add(new Category("Shoes", "https://dsw.scene7.com/is/image/DSWShoes/388939_093_ss_01?$pdp-image$"));

        return categories;
    }


    public static ArrayList<Category> getCategoryDresses() {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("Dresses", "https://drive.google.com/open?id=1woBmil7cQFMCeuRbhaBHpvENVOBFcaoj"));
        categories.add(new Category("Woman Watches", "https://images.pexels.com/photos/792772/pexels-photo-792772.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Bags", "https://images.pexels.com/photos/792772/pexels-photo-792772.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Men Watches", "https://images.pexels.com/photos/792772/pexels-photo-792772.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("T-Shirts", "https://images.pexels.com/photos/792772/pexels-photo-792772.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));
        categories.add(new Category("Shoes", "https://images.pexels.com/photos/792772/pexels-photo-792772.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"));

        return categories;
    }


}
