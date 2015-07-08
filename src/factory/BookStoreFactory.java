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
public class BookStoreFactory extends AbstractStoreFactory {
    
    @Override
    public Store getStore() {
        return new BookStore("Borders", "Lot 238", 3);
    }
    
    @Override
    public Store getStore(String name, String loc, int id) {
        return new BookStore(name, loc, id);
    }
    
    @Override
    public ArrayList<Item> getItems() {
        ArrayList<Item> listOfItems = new ArrayList();
        
        return listOfItems;
    }
}
