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

public interface Observable {  
    abstract public void notifyObserver();
    abstract public void addObserver(factory.Observer obv);
    abstract public void removeObserver(factory.Observer obv);
}
