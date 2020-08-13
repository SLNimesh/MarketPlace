package com.nimeshnelanga;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private Map<Item, Integer> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public boolean addItem(Item item) {
        return addItem(item, 1);
    }

    public boolean addItem(Item item, int amount) {
        if (amount < 0) {
            return false;
        }
        if(Database.checkAvailability(item.getBarcode())>amount){
            int existingItems = cartItems.getOrDefault(item, 0);
            if (existingItems > 0) {
                cartItems.replace(item, (existingItems + amount));
            } else {
                cartItems.put(item, amount);
            }
            Database.updateInCart(item.getBarcode(),amount);
            return true;
        }
        return false;
    }

    public boolean removeItem(Item item) {
        boolean inCart = false;
        for (Item i : this.cartItems.keySet()) {
            if (i.equals(item)) {
                inCart = true;
                break;
            }
        }
        if (inCart) {
            Database.updateInCart(item.getBarcode(),-cartItems.get(item));
            this.cartItems.remove(item);
            return true;
        }
        return false;
    }

    public double getCartTotal() {
        double cartTotal = 0;
        for (Item i : this.cartItems.keySet()) {
            cartTotal += (i.getPrice() * cartItems.get(i));
        }
        return cartTotal;
    }

}
