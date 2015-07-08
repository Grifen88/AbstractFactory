/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ShoppingCart {
    private ArrayList<Item> content = new ArrayList<>();
    
    public void addItem(Item i) {
        content.add(i);
    }
    
    public void removeItem(Item i) {
        content.remove(i);
    }
    
    public void addItemQuantity(Item i, int n) {
        i.addQuantity(n);
    }
    
    public ArrayList<Item> getCartContent() {
        return content;
    }
    
    public void clear() {
        content.clear();
    }
    
    public boolean has(Item i) {
        for (Item temp : content) {
            if (temp.equals(i))
                return true;
        }
        //if not found, return false
        return false;
    }
}
