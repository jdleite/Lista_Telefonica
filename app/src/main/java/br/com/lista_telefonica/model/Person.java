package br.com.lista_telefonica.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Person implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String name;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean validateId(){
        return id > 0;
    }
}
