/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Scanner;
/**
 *
 * @author User
 */
public class GetItemCommand implements Command {
    private ArrayList<Item> itemlist;
    private Customer cust;
    
    //supports adding single items
    public GetItemCommand (Customer c) {
        cust = c;
    }
    
    @Override
    public void execute() {
        if (cust.getStore() != null) {
            ArrayList<Item> availableItems = cust.getStore().getItemList();
            System.out.println("Available items are:");
            int count = 1;
            for (Item i : availableItems) {
                StringBuilder sb = new StringBuilder();
                sb.append(count).append(" - ").append(i.getName()).append(" ").append(i.getDesc())
                        .append(" - ").append("RM ").append((float)i.getPrice()/100);
                System.out.println(sb.toString());
                count++;
            }
            Scanner kb = new Scanner(System.in);
            
            System.out.println("What do you like to buy?");
            int choice = kb.nextInt();
            
            System.out.println("How many?"); 
            int number = kb.nextInt();
            
            if (number > 0) {
                cust.addItemToCart(availableItems.get(choice - 1).clone(), number);
            }
            else {
                System.out.println("Invalid number!");
            }
        } else 
            System.out.println("There is nothing here to buy!");
        
    }
    
    @Override
    public void undo() {
        System.out.println("Removing items from customer shopping cart...");
        for (Item i : itemlist) 
            cust.removeItemFromCart(i);
    }
    
}
