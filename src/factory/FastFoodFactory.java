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
public class FastFoodFactory extends AbstractStoreFactory {
    
    @Override
    //test constructor, should not be called under any circumstances during normal programming
    public Store getStore() {
        return new FastFood("McDonalds", "Lot 231", 3);
    }
    
    @Override
    public Store getStore(String name, String loc, int id) {
        return new FastFood(name, loc, id);
    }
    
    @Override
    public ArrayList<Item> getItems() {
        ArrayList<Item> listOfItems = new ArrayList();
        
        return listOfItems;
    }    
}
