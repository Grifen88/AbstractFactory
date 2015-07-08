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
//todo, create command to select customer focus
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MallDriver {
    private final String itemCSVFile = "/config/ItemCSV.csv";
    private final String storeCSVFile = "/config/StoreCSV.csv";
    private final HashMap<String, Command> cmdlist = new HashMap<>();
    Customer activeCust = null;
    Mall testmall;
    MallUI ui;
    AdminPanel admin;
    CommandManager cmd;
    
    Scanner kb = new Scanner(System.in);
    
    public MallDriver() {
        testmall = new Mall();
        ui = new MallUI(testmall);
        admin = new AdminPanel(testmall);
        cmd = new CommandManager();
        testmall = this.loadPreset(testmall);       
    }
    
    public void runMain() {
        int choice = 0;
        while(choice != -1) {
            ui.displayMainMenu();
            
            choice = kb.nextInt();
            
            switch (choice) {
                case 1:
                    this.runAdmin();
                    break;
                case 2:
                    this.runDriver();
                    break;
                case -1:
                    break;
                default:
                    break;
            }
        }
    }
    
    public void runDriver() {
        int choice = 0;
        while(choice != -1) {
            ui.displayCustomerMenu();
            
            choice = kb.nextInt();
            //todo-encapsulate command
            switch (choice) {
                case 1:
                    cmd.addCommand(new CreateCustomerCommand(testmall));
                    break;
                case 2:
                    cmd.addCommand(new ChooseCustomerCommand(this, testmall));
                    break;
                case 3:
                    cmd.addCommand(new EnterStoreCommand(testmall, activeCust));
                    break;
                case 4:
                    cmd.addCommand(new GetItemCommand(activeCust));
                    break;
                case 5:
                    cmd.addCommand(new CheckOutCommand(testmall, activeCust));
                    break;
                case 6: 
                    this.getActiveCustomerCart();
                    break;
                case 7:
                    this.registerCustomer();
                    break;
                case 9:
                    cmd.undoLast();
                case -1:
                    break;
                default:
                    break;
            }
            cmd.carryOut();
        }
    }
    
    public void runAdmin() {
        int choice = 0;
        while(choice != -1) {
            ui.displayAdminMenu();
            
            choice = kb.nextInt();
            
            switch (choice) {
                case 1:
                    this.addNewStore();
                    break;
                case 2:
                    this.addItemsToStore();
                    break;
                case 3:
                    this.removeItemFromStore();
                    break;
                case 4:
                    this.removeStore();
                case -1:
                    break;
                default:
                    break;
            }
        }
    }
    
    //main method for calling class
    public static void main (String args[]) {
        MallDriver driver = new MallDriver();
        driver.runMain();        
    }
    
    private Mall loadPreset(Mall mall) {
        //loads a preset configuration of the mall from a file
        CSVReader input = new CSVReader();

        
        ArrayList<String[]> storelist = input.readCSV(storeCSVFile);      
        for (String[] s: storelist) {
            admin.addStore(s[1], Integer.parseInt(s[0]), s[2], s[3]);
        }
        
        ArrayList<String[]> itemlist = input.readCSV(itemCSVFile);
        
        for (String[] s2 : itemlist) {
            admin.addItemToStore(mall.getStore(Integer.parseInt(s2[0])), Integer.parseInt(s2[1]), s2[2], Integer.parseInt(s2[3]), s2[4]);
        }
        return mall;
    }

    public void setActiveCustomer(Customer c) {       
        activeCust = c;
    }
    
    public Customer getActiveCustomer() {
        return activeCust;
    }
    
    private void getActiveCustomerCart() {
        if (activeCust != null) {
            int count = 1;
            System.out.println("The customer is currently carrying:");
            for (Item i : activeCust.getCart().getCartContent()) {
                System.out.println(count + " - " + i.getName() + " - " + i.getQuantity());
                count++;
            }
        }
        else 
            System.out.println("Choose a customer first!");
    }
    
    private void registerCustomer() {
        if (activeCust != null) {
            System.out.println("Choose a store to register.");
            if (!testmall.getStoreList().isEmpty()) {                    
                int count = 1;
                for(Store s: testmall.getStoreList()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(count).append(" - ").append(s.getStoreName());
                    System.out.println(sb);
                    count++;
                }
            }
            int obschoice = kb.nextInt();
            if (obschoice > 0) {
                Store obs = testmall.getStoreList().get(obschoice - 1);
                obs.addObserver((Observer)activeCust);
                System.out.println(activeCust.getName() + " is now registered for notifications with " + obs.getStoreName());
            } else 
                System.out.println("No such store exists!");
        }
        else 
            System.out.println("Choose a customer first!");
    }
    
    private void addNewStore() {
        String name, loc, storetype_string;
        int storeid, storetype_int;
        
        System.out.println("Please enter the store id:");
        storeid = kb.nextInt();
        
        System.out.println("Select one of the following store type:");
        System.out.print("1 - Game Store\n"
                + "2 - Boutique\n"
                + "3 - Fast Food\n"
                + "4 - Bookstore\n");
        storetype_int = kb.nextInt();
        switch (storetype_int) {
            case 1:
                storetype_string = "game";
                break;
            case 2:
                storetype_string = "boutique";
                break;
            case 3:
                storetype_string = "fastfood";
                break;
            case 4:
                storetype_string = "bookstore";
                break;
            default:
                storetype_string = "game";
                break;
        }
        kb.nextLine();
        
        System.out.println("Please enter the name of the new store:");
        name = kb.nextLine();
        
        System.out.println("Please enter the location of the new store:");
        loc = kb.nextLine();
        
        admin.addStore(storetype_string, storeid, name, loc);
    }
    
    private void removeStore() {
        System.out.println("Please choose which store to remove:");
        int count = 1;
        for(Store s: testmall.getStoreList()) {
                StringBuilder sb = new StringBuilder();
                count++;
                sb.append(count).append(" - ").append(s.getStoreName());
                System.out.println(sb);
        }
        
        int choice = kb.nextInt();
        Store targetStore = testmall.getStoreList().get(choice - 1);
        
        admin.deleteStore(targetStore);
    }
    
    private void addItemsToStore() {
        int itemprice, itemid;
        String itemname,itemdesc;
        System.out.println("Please choose which store to add it to:");
        int count = 1;
        for(Store s: testmall.getStoreList()) {
                StringBuilder sb = new StringBuilder();
                sb.append(count).append(" - ").append(s.getStoreName());
                System.out.println(sb);
                count++;
        }
        
        int choice = kb.nextInt();
        Store targetStore = testmall.getStoreList().get(choice - 1);
        
        System.out.println("Please enter the item id:");
        itemid = kb.nextInt();
        kb.nextLine();
        System.out.println("Please enter the name of the new item:");
        itemname = kb.nextLine();
        System.out.println("Please enter the price of the new item:");
        itemprice = kb.nextInt();
        kb.nextLine();
        System.out.println("Please enter the description of the new item:");
        itemdesc = kb.nextLine();
        
        admin.addItemToStore(targetStore, itemid, itemname, itemprice, itemdesc);
        
    }
    
    private void removeItemFromStore() {
        System.out.println("Available items are:");
        System.out.println("Please choose which store to add it to:");
        int count = 0;
        for(Store s: testmall.getStoreList()) {
                StringBuilder sb = new StringBuilder();
                count++;
                sb.append(count).append(" - ").append(s.getStoreName());
                System.out.println(sb);
        }
        
        int choice = kb.nextInt();
        Store targetStore = testmall.getStoreList().get(choice - 1);
        
        int itemcount = 0;
        for (Item i : targetStore.getItemList()) {
            StringBuilder sb = new StringBuilder();
            sb.append(itemcount).append(" - ").append(i.getName()).append(" ").append(i.getDesc())
                    .append(" - ").append("RM ").append((float)i.getPrice()/100);
            System.out.println(sb.toString());
            itemcount++;
        }
        
        System.out.println("What do you like to delete?");
        int itemchoice = kb.nextInt();
        
        Item targetItem = targetStore.getItemList().get(itemchoice - 1);
        
        admin.deleteItemFromStore(targetStore, targetItem);
        targetStore.notifyObserver();
    }
}


