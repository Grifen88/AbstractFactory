/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.HashMap;

/**
 *
 * @author User
 */

//implementing Prototype design pattern
public class ItemRegistry {
    public HashMap<String, Item> itemlist = new HashMap<>();
    
    public ItemRegistry () {
        loadCache();
    }
    
    private void loadCache() {
        Item generic = new Item();
        Item book = new Item("Book", 4999);
        Item shirt = new Item("Shirt", 9999);
        
        itemlist.put("Control", generic);
        itemlist.put("Book", book);
        itemlist.put("Cloth", shirt);
    }
    
    public Item createBasicItem(String type) {
        return itemlist.get(type).clone();
    }
    
    public void addNewPrototype(String name, int price, String category) {
        Item newItem = new Item(name, price);
        
        itemlist.put(category, newItem);
    }
    
    public void deletePrototype(String category) {
        itemlist.remove(category);
    }
}
