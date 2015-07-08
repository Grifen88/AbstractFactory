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
public class AdminPanel {
    
    private Mall mall;
    private StoreManager fact;
    private MallDriver driver;
    private ItemRegistry itemguide;
    
    public AdminPanel (Mall m) {
        mall = m;
        fact = StoreManager.getInstance();
        itemguide = new ItemRegistry();
    }
    
    public void addItemToStore(Store store, int id, String itemName, int price, String description) {
        Item tempItem = itemguide.createBasicItem("Control");
        tempItem.setID(id);
        tempItem.setName(itemName);
        tempItem.setPrice(price);
        tempItem.setDesc(description);
        
        store.addItem(tempItem);
        store.notifyObserver();
    }
    
    public void deleteItemFromStore(Store store, Item item) {
        store.removeItem(item.getName());
    }
    
    public void addStore(String storeType, int id, String storeName, String loc) {
        fact.setFactory(storeType);
        Store tempstore = fact.makeStore(storeName, loc, id);
        
        mall.addStore(tempstore);
    }
    
    public void deleteStore(Store s) {
        mall.removeStore(s);
    }
}
