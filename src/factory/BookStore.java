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
public class BookStore extends Store {
    
    public BookStore (String name, String loc, int id) {
        super(name, loc, id);
        //System.out.println("Video store now open!");
    }
    
    public BookStore () {
        super();
    }
}
