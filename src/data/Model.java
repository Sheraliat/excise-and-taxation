/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author tanzil
 */
public class Model {

    public int IdBrand;
    public int Id;
    public String ModelName;

    public Model(int IdBrand, int Id, String ModelName) {
        this.IdBrand = IdBrand;
        this.Id = Id;
        this.ModelName = ModelName;
    }

    @Override
    public String toString() {
        return ModelName;
    }

    public int getIdBrand() {
        return IdBrand;
    }

    public void setIdBrand(int IdBrand) {
        this.IdBrand = IdBrand;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setTenmodel(String Tenmodel) {
        this.ModelName = ModelName;
    }
}
