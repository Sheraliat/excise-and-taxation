/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tanzil
 */
public class UserSystem {

    int Maqt;
    int Id_vai_tro;
    String Tendd;
    String Diachi;
    String Dienthoai;
    String Email;
    int Gioitinh;
    String Tendn;
    String Matkhau;

    public UserSystem(int Maqt,int Id_vai_tro, String Tendn, String Matkhau,String Tendd , int Gioitinh, String Diachi, String Dienthoai, String Email) {
        this.Maqt = Maqt;
        this.Id_vai_tro = Id_vai_tro;
        this.Tendd = Tendd;
        this.Diachi = Diachi;
        this.Dienthoai = Dienthoai;
        this.Email = Email;
        this.Gioitinh = Gioitinh;
        this.Tendn = Tendn;
        this.Matkhau = Matkhau;
    }

    public int getId_vai_tro() {
        return Id_vai_tro;
    }

    public void setId_vai_tro(int Id_vai_tro) {
        this.Id_vai_tro = Id_vai_tro;
    }

    @Override
    public String toString() {
        return Tendd;
    }

    public int getMaqt() {
        return Maqt;
    }

    public void setMaqt(int Maqt) {
        this.Maqt = Maqt;
    }

    public String getTendd() {
        return Tendd;
    }

    public void setTendd(String Tendd) {
        this.Tendd = Tendd;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getDienthoai() {
        return Dienthoai;
    }

    public void setDienthoai(String Dienthoai) {
        this.Dienthoai = Dienthoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(int Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getTendn() {
        return Tendn;
    }

    public void setTendn(String Tendn) {
        this.Tendn = Tendn;
    }

    public String getMatkhau() {
        return Matkhau;
    }

    public void setMatkhau(String Matkhau) {
        this.Matkhau = Matkhau;
    }
}
