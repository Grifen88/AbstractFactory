/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author User
 */

//abstract superclase for all store objects
//factory.Observable is used instead of the java built-in observable class
abstract public class Store implements factory.Observable {
    
    private String storeName;
    private int storeID;
    private String location;
    private ArrayList<Customer> customerlist = new ArrayList<Customer>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<factory.Observer> observers = new ArrayList<>();
    
    public Store() {
    //empty constructor
    }
    
    //this public constructor is used to create all the stores in the mall
    public Store(String name, String loc, int id) {
        storeName = name;
        location = loc;
        storeID = id;
    }
    
    public String getStoreName() {
        return storeName;
    }
    
    public int getStoreID() {
        return storeID;
    }
    
    public ArrayList<Customer> getCustomer() {
        return customerlist;
    }
    
    public String getLoc() {
        return location;
    }
    
    public void addCustomer(Customer c) {
        customerlist.add(c);
    }
    
    public void removeCustomer(Customer c) {
        customerlist.remove(c);
        //customerlist.clear();
    }
    
    public ArrayList<Item> getItemList() {
        return items;
    }
    
    public boolean addItem(Item i) {
        return items.add(i);
    }
    
    public boolean removeItem(String name) {
        for(Item a : items) {
            if((a.getName()).equals(name)) { //search for item name, if found, remove from array list, only removes first item found
                items.remove(a);
                return true;
            }
        }
        return false;
    }
    
    /*public String getItemList() {
        StringBuilder sb = new StringBuilder();
        for(Item a : items) {
            sb.append(a.getName()).append(" ");
        }
        
        return sb.toString();
    }*/
    
    public void setLoc (String s) {
        location = s;
    }
    
    @Override
    public void addObserver (factory.Observer o) {
        observers.add(o);
    }
    
    @Override
    public void removeObserver (factory.Observer o) {
        observers.remove(o);
    }
    
    @Override
    public void notifyObserver() {
        for (factory.Observer o: observers) {
            o.update(this);
            Customer c = (Customer) o;
            System.out.println(c.getName() + " has been notified.");
        }
    }
    
    public ArrayList<factory.Observer> getObservers() {
        return observers;
    }
}
