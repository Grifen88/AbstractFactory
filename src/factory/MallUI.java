/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;


import java.lang.StringBuilder;

/**
 *
 * @author User
 */
public class MallUI {
    
    Mall mall;
    
    public MallUI(Mall m) {
        mall = m;
    }
    
    public void displayMainMenu() {
        System.out.println("What would you like to do?");
        System.out.print("1 - Admin Menu \n"
            + "2 - Customer Menu\n"
            + "Type -1 to exit the program.\n");  
    }
    
    public void displayAdminMenu() {
        this.displayStatus();
        System.out.println("What would you like to do?");
        System.out.print("1 - Add Store \n"
            + "2 - Add Item to Store\n"
            + "3 - Remove Item from Store\n"
            + "4 - Remove Store\n"
            + "Type -1 to return to the main menu.\n");     
    }
            
    public void displayCustomerMenu() {
        this.displayStatus();
        System.out.println("What would you like to do?");
        System.out.print("1 - Add New Customer \n"
            + "2 - Choose Customer\n"
            + "3 - Move Customer\n"
            + "4 - Add Item to Customer Shopping Cart\n"
            + "5 - Check Out Customer\n"
            + "6 - Check Item in Cart\n"
            + "7 - Register with a store for notifications\n"
            + "9 - Undo Last Command\n"
            + "Type -1 to return to the main menu.\n");          
        
    }
    
    public void displayStatusMenu() {
        System.out.print("1 - View status of customers in mall\n"
            + "2 - View status of customer in a specific shop\n"
            + "3 - View all shops in the mall\n"
            + "4 - View all customers in a shop\n"
            + "5 - View all items for sale in a shop\n"
            + "6 - View customer status\n"
            + "9 - Return to the main menu\n"
            + "Type -1 to exit the program.\n");          
    }
    
    public void displayActionMenu() {
        
    }
    
    private void displayStatus() {
        System.out.println("Welcome to XYZ Mall.");
        
        //prints all the customer in the mall.
        System.out.println("The following customers are currently in the mall:");
        if (!mall.getCustList().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(Customer c: mall.getCustList()) {
                sb.append(c.getName()).append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            System.out.println(sb);
        } else
            System.out.println("There are no customers in the mall currently.");
        
        //prints all the stores in the mall.
        System.out.println("The following stores are currently open:");
        if (!mall.getStoreList().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(Store s: mall.getStoreList()) {
                sb.append(s.getStoreName()).append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            System.out.println(sb);
            
        } else
            System.out.println("There are no stores open currently.");
    }
}