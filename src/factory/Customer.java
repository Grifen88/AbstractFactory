/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

/**
 *
 * @author User
 */
import java.util.ArrayList;

public class Customer implements factory.Observable, factory.Observer {
    //client class
    private ArrayList<Observer> mallObsv = new ArrayList<>();
    private ArrayList<Observable> subjectList = new ArrayList<>();
    private ArrayList<Store> notification = new ArrayList<>();
            
    private String custName;
    private ShoppingCart cart;
    private Store store; //stores reference to the original store, if null, means in the mall but outside
    
    @Override
    public void update(Object o) { 
        Store s = (Store) o;
        notification.add(s);
    }
    
    //notifies the mall of the change of position
    @Override
    public void notifyObserver() {
        for(Observer o : mallObsv) {
            o.update(this);
        }
    }
    
    @Override
    public void addObserver(Observer obv) {
        mallObsv.add(obv);
    }
    
    @Override
    public void removeObserver(Observer obv) {
        mallObsv.remove(obv);
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Customer)) 
            return false;
        
        Customer other = (Customer) o;
        return custName.equals(other.custName);
    }
    
    @Override
    public int hashCode() {
        return custName.hashCode();
    }
    
    public Customer () {
        this.custName = "Test";
        this.cart = new ShoppingCart();
        this.store = null;
    }
    
    public Customer (String n) {
        this.custName = n;
        this.cart = new ShoppingCart();
        this.store = null;
    }
    
    public String getName() {
        return custName;
    }
    
    public Store getStore() {
        return store;
    }
    
    public String getStoreName() {
        if (store != null) 
            return store.getStoreName();
        else
            return "Mall";
    }
    
    public ShoppingCart getCart() {
        return cart;
    }
    
    public void setName(String n) {
        custName = n;
    }
    
    public void setStore(Store s) {
        store = s;
        this.notifyObserver();
    }
    
    public void addItemToCart(Item i, int n) {
        if (!cart.has(i)) {
            i.addQuantity(n);
            cart.addItem(i);
        }
        else {
            cart.addItemQuantity(i, n);
        }
    }
    
    public void removeItemFromCart(Item i) {
        cart.removeItem(i);
    }
    
    public void clearCart() {
        cart.clear();
    }
    
    public void pendingMessage() {
        System.out.println("Checking for notifications...");
        if(!notification.isEmpty()) {
            for(Store s: notification) {
                System.out.println(s.getStoreName() + " has added new items!");
            }
            notification.clear();
        }
    }
    
}
