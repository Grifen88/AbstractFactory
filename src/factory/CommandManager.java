/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factory;

import java.util.ArrayList;
import java.util.Stack;
/**
 *
 * @author User
 */
public class CommandManager {
    private ArrayList<Command> cmdlist = new ArrayList<>();
    private Stack<Command> oldcmdlist = new Stack<>();
    
    public void addCommand (Command c) {
        cmdlist.add(c);
    }
    
    public void carryOut() {
        for (Command c : cmdlist) {
            c.execute();
            oldcmdlist.push(c);
        }
        cmdlist.clear();
    }
    
    public void undoLast() {
        if (!oldcmdlist.isEmpty()) {
            Command last = oldcmdlist.pop();
            last.undo();
        }
        else 
            System.out.println("There are no commands to undo!");
    }
    
    public void removeOld() {
        if (oldcmdlist.size() > 20) {
            oldcmdlist.subList((oldcmdlist.size() - 20), (oldcmdlist.size() - 1)).clear();
        }
    }
}
