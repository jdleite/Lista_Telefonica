package br.com.lista_telefonica.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String telephone;
    private String cellphone;
    @ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "contactId", onUpdate = CASCADE, onDelete = CASCADE)
    private int personId;

    public Contact(){}

    public Contact(String telephone, String celular) {
        this.telephone = telephone;
        this.cellphone = celular;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
