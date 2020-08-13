package com.nimeshnelanga;

public class Buyer {
    private String username;
    private Cart myCart;

    public Buyer(String username) {
        this.username = username;
        myCart= new Cart();
    }

    public String getUsername() {
        return username;
    }

    public Cart getMyCart() {
        return myCart;
    }

    public void purchaseItem(Item item){
        //purchase single item

    }

    public void purchaseCart(){
        //purchase entire cart

    }

    public void getMyOrders(){
        //print all his orders
    }
}
