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
import java.lang.StringBuilder;
import java.util.Scanner;


public class ChooseCustomerCommand implements Command {
    private MallDriver driver;
    private Mall mall;
    private Customer oldFocus, newFocus;
    
    public ChooseCustomerCommand(MallDriver d, Mall m) {
        driver = d;
        mall = m;
    }
    
    @Override
    public void execute() {
        ArrayList<Customer> custlist = mall.getCustList();
        if (!custlist.isEmpty()) {
            System.out.println("The following customer can be chosen:");
            int count = 1;
            for (Customer c : custlist) {
                StringBuilder sb = new StringBuilder();
                sb.append(count).append(" - ").append(c.getName()).append(", ").append(c.getStoreName());
                System.out.println(sb);
                count++;
            }
            
            Scanner kb = new Scanner(System.in);
            int choice = kb.nextInt();
            
            newFocus = custlist.get(choice - 1);
            oldFocus = driver.getActiveCustomer();
            
            System.out.println(newFocus.getName() + " is now selected as the active customer!");
            driver.setActiveCustomer(newFocus);
            newFocus.pendingMessage();
        }
        
    }
    
    @Override
    public void undo() {
        System.out.println("Undoing customer selection...");
        driver.setActiveCustomer(oldFocus);
    }
}
