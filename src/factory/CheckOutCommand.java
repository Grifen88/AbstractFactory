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

public class CheckOutCommand implements Command{
    Customer cust;
    Mall mall;
    ArrayList<Item> itemlist;
    int total, subtotal;
    
    public CheckOutCommand (Mall m, Customer c) {
        this.cust = c;
        this.mall = m;
        itemlist = cust.getCart().getCartContent();
    }
    
    
    @Override
    public void execute() {
        for (Item i : itemlist) {
            subtotal += (i.getPrice() * i.getQuantity());
            System.out.println(i.getQuantity() + " " + i.getName() + " is checked out!");
        }
        
        System.out.println("Customer has to pay RM " + (float) subtotal/100);
        
        //checkoutCustomer and removes them from any existing shops.
        if (cust.getStore() != null) {
            System.out.println("Customer exits " + cust.getStoreName());
            cust.getStore().removeCustomer(cust);
        }
        mall.removeCust(cust);
        System.out.println("Customer exits the mall.");
        
    }
    
    @Override
    public void undo() {
        System.out.println("Returning customer to the mall.");
        mall.addCust(cust);
        if (cust.getStore() != null) {
            cust.getStore().addCustomer(cust);
        }
    }
}
