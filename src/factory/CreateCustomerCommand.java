/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class CreateCustomerCommand implements Command { 
    private Customer cust;
    private Mall mall;
    
    public CreateCustomerCommand(Mall m) {
        mall = m;
    }
    
    @Override 
    public void execute() {
        String name;
        Scanner kb = new Scanner(System.in);
        
        System.out.println("Please enter the customer name:");
        name = kb.nextLine();
        
        cust = new Customer(name);
        mall.addCust(cust);
        cust.addObserver(mall);
        System.out.println(cust.getName() + " is added to the mall.");
    }
    
    @Override
    public void undo() {
        mall.removeCust(cust);
    }
}
