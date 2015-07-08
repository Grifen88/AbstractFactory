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
public class Item implements Cloneable {
    private String itemName;
    private int itemID; 
    private int itemPrice; //stored internally as cents
    private String description; //optional
    private int itemQuantity; //only used when in customer shopping cart
    
    @Override
    public Item clone() {
        try {
            return (Item) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item)) 
            return false;
        
        Item other = (Item) o;
        return itemName.equals(other.itemName);
    }
    
    @Override
    public int hashCode() {
        return itemName.hashCode();
    }
    
    public Item () {
        itemName = "Generic Item #1";
        itemID = 0;
    }
    
    public Item(String name, int price) {
        itemName = name;
        itemPrice = price;
    }
    
    public String getName() {
        return itemName;
    }
    
    public int getID() {
        return itemID;
    }
    
    public int getPrice() {
        return itemPrice;
    }
    
    public String getDesc() {
        return description;
    }
    
    public int getQuantity() {
        return itemQuantity;
    }
    
    public void setName(String s) {
        itemName = s;
    }
    
    public void setID(int id) {
        itemID = id;
    }
    
    public void setPrice(int price) {
        itemPrice = price;
    }
    
    public void setDesc(String desc) {
        description = desc;
    }
    
    public void setQuantity(int i) {
        itemQuantity = i;
    }
    
    public void addQuantity(int i) {
        itemQuantity += i;
    } 
}
