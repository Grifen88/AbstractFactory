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
abstract public class AbstractStoreFactory {
    
    private String storeName;
    private String location;
    private int storeID;
    
    abstract public Store getStore(); //test method
    abstract public Store getStore(String name, String loc, int id); //actual method of initialising a store
        
    abstract public ArrayList<Item> getItems();
}
