/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 *
 * @author User
 */
public class EnterStoreCommand implements Command { 
    private Store store, oldStore;
    private Customer customer;
    private Mall mall;
    
    public EnterStoreCommand(Mall m, Customer c) {
        mall = m;
        customer = c;
    }
    
    @Override
    public void execute() {
        Scanner kb = new Scanner(System.in);
        ArrayList<Store> availableStore = mall.getStoreList();
        int count = 0;
        if (customer != null) {
            System.out.println("Where do you want to go?");
            for(Store s: availableStore) {
                StringBuilder sb = new StringBuilder();
                count++;
                sb.append(count).append(" - ").append(s.getStoreName());
                System.out.println(sb);
            }

            int choice = kb.nextInt(); 
            if (choice > 0) {
                store = availableStore.get(choice - 1);
            } else 
                store = null;

            oldStore = customer.getStore();

            if (oldStore != null) {
                oldStore.removeCustomer(customer);
                System.out.println("Customer exits " + oldStore.getStoreName());
            }

            if (store != null) {
                customer.setStore(store);
                store.addCustomer(customer);
                System.out.println("Customer enters " + store.getStoreName());
            }
            else 
                System.out.println("Customer returns to the mall.");
            customer.notifyObserver();
        }
        else 
            System.out.println("No customer selected!");
    }
    
    
    @Override 
    public void undo() {
        System.out.println("Undoing command... Customer exits " + store.getStoreName());
        customer.setStore(oldStore);
        store.removeCustomer(customer);
        if (oldStore != null) {
            oldStore.addCustomer(customer);
            System.out.println("Customer enters " + oldStore.getStoreName());
        } else {
            System.out.println("Customer enters the mall.");
        }
    }
    
}
