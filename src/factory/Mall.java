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
public class Mall implements Observer {
    private HashMap<Integer, Store> storeList;
    private ArrayList<Customer> custList;
    private HashMap<Customer, Store> custLoc;
    
    public Mall() {
        storeList = new HashMap<>();
        custList = new ArrayList<>();
        custLoc = new HashMap<>();
    }
    
    @Override
    public void update(Object o) {       
        Customer cust = (Customer) o;
        custLoc.put(cust, cust.getStore());
    }
    
    public ArrayList<Store> getStoreList() {
        ArrayList<Store> temp = new ArrayList<>();
        for (Store s : storeList.values()) 
            temp.add(s);
        return temp;
    }
    
    public ArrayList<Customer> getCustList() {
        return custList;
    }
    
    public HashMap<Customer, Store> getVerboseCustList() {
        return custLoc;
    }
    
    public Store getStore(int i) {
        return storeList.get(i);
    }
    
    public Store getLastStore() {
        return storeList.get(storeList.size() - 1);
    }
    
    public void addStore(Store s) {
        storeList.put(s.getStoreID(), s);
    }
    
    public void removeStore(Store s) {
        storeList.remove(s.getStoreID());
    }
    
    public void addCust(Customer c) {
        custList.add(c);
    }
    
    public void removeCust(int index) {
        custList.remove(index);
    }
    
    public void removeCust(Customer c) {
        custList.remove(c);
    }
}
