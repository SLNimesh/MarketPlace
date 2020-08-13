package com.nimeshnelanga;

import java.util.Objects;

public class Item {
    private int barcode;
    private double price;
    private int sellerID;

    public Item(int barcode) {
        this.barcode = barcode;
        this.sellerID = Database.getItemSeller(barcode);
        this.price=Database.findItemPRice(barcode,sellerID);
    }

    public int getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public int getSellerID() {
        return sellerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Item) {
            return barcode == ((Item) obj).getBarcode() &&
                    sellerID == ((Item) obj).getSellerID();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, sellerID) + 37;
    }
}
