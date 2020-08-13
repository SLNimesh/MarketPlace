package com.nimeshnelanga;

public class Seller {
    private int sellerID;

    public Seller(int sellerID) {
        this.sellerID = sellerID;
    }

    public boolean addStock(Item item,int n) {
        if(n>0){
            return Database.changeStock(item.getBarcode(),n);
        }
        return false;
    }

    public boolean removeStock(Item item,int n){
        if(n>0){
            return Database.changeStock(item.getBarcode(),-n);
        }
        return false;
    }

    public boolean setPrice(Item item, double price) {
        return Database.setItemPrice(item.getBarcode(),price);
    }

    public void myItems() {
        //return all the items he sell
    }

    public void ongoingOrders(){

    }
}
