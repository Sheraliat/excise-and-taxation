/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tanzil
 */
public class Role {
    int Id_vai_tro;
    String Ten_vai_tro;

    public Role(int Id_vai_tro, String Ten_vai_tro) {
        this.Id_vai_tro = Id_vai_tro;
        this.Ten_vai_tro = Ten_vai_tro;
    }

    @Override
    public String toString() {
        return Ten_vai_tro;
    }
    
    public int getId_vai_tro() {
        return Id_vai_tro;
    }

    public void setId_vai_tro(int Id_vai_tro) {
        this.Id_vai_tro = Id_vai_tro;
    }

    public String getTen_vai_tro() {
        return Ten_vai_tro;
    }

    public void setTen_vai_tro(String Ten_vai_tro) {
        this.Ten_vai_tro = Ten_vai_tro;
    }
    
    
}
