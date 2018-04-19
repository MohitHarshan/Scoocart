package com.example.mohit.scoocart;

import android.widget.TextView;

/**
 * Created by mohit on 3/5/2018.
 */

public class Cart {
    
    String carttitle;
    String cartprice;
    int cartimageid;

    public Cart(String carttitle, String cartprice, int cartimageid) {
        this.carttitle = carttitle;
        this.cartprice = cartprice;
        this.cartimageid = cartimageid;
    }

    public String getCarttitle() {
        return carttitle;
    }

    public void setCarttitle(String carttitle) {
        this.carttitle = carttitle;
    }

    public String getCartprice() {
        return cartprice;
    }

    public void setCartprice(String cartprice) {
        this.cartprice = cartprice;
    }

    public int getCartimageid() {
        return cartimageid;
    }

    public void setCartimageid(int cartimageid) {
        this.cartimageid = cartimageid;
    }
}
