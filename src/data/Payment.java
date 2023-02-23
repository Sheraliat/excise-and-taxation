/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tanzil
 */
public class Payment {
    int ID;
    String Name;

    public Payment(int ID, String Name) {
        this.ID = ID;
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name ;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
