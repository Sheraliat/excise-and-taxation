/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tanzil
 */
public class Brand {

    public int Id;
    public String Name;
    public String Address;
    public String Phone;
    public String Email;

    public Brand(int Id, String Name, String Address, String Phone, String Email) {
        this.Id = Id;
        this.Name = Name;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }

    @Override
    public String toString() {
        return Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
