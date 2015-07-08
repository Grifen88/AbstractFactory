/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class StoreManager {
    
    public static StoreManager mall = null;
    AbstractStoreFactory activeFactory;
    HashMap<String, AbstractStoreFactory> reserveFact = new HashMap<>();
    
    protected StoreManager() {
        // empty protected constructor
    }
    
    public static StoreManager getInstance() {
        if (mall == null) 
            mall = new StoreManager();
        return mall;
    }
    
    //if factory available in the reservelist, then retrieve that instance. Else instantiate a new factory and store it in the reservelist
    public void setFactory(String choice) {
        switch(choice) {
            case "game":
                if (reserveFact.get("game") != null) {
                    activeFactory = reserveFact.get("game");
                } else {
                    reserveFact.put("game", new GameStoreFactory());
                    activeFactory = reserveFact.get("game");
                }
                break;
            case "boutique":
                if (reserveFact.get("boutique") != null) {
                    activeFactory = reserveFact.get("game");
                } else {
                    reserveFact.put("boutique", new BoutiqueFactory());
                    activeFactory = reserveFact.get("boutique");
                }
                break;
            case "fastfood":
                if (reserveFact.get("fastfood") != null) {
                    activeFactory = reserveFact.get("fastfood");
                } else {
                    reserveFact.put("fastfood", new FastFoodFactory());
                    activeFactory = reserveFact.get("fastfood");
                }
                break;
            case "bookstore":
                if (reserveFact.get("bookstore") != null) {
                    activeFactory = reserveFact.get("bookstore");
                } else {
                    reserveFact.put("bookstore", new BookStoreFactory());
                    activeFactory = reserveFact.get("bookstore");
                }
                break;
            default:
                activeFactory = null;
                System.out.println("No factory selected!");
                break;
        }
    }
    
    //different stores will be created, depending on the activeFactory loaded
    public Store makeStore() {
        if (activeFactory != null)
            return activeFactory.getStore();
        else
            return null;
    }
    
    public Store makeStore(String name, String loc, int id) {
        if (activeFactory != null)
            return activeFactory.getStore(name, loc, id);
        else
            return null;
    }
    
    public ArrayList<Item> getItems() {
        return activeFactory.getItems();
    }
}
