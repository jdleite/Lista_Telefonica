package br.com.lista_telefonica.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.lista_telefonica.model.Contact;

@Dao
public interface ContactDAO {


    @Query("SELECT * FROM Contact" +
            " WHERE personId = :personId LIMIT 1")
    Contact getFirstTelephonePerson(int personId);

    @Insert
    Long save(Contact contact);

    @Query("SELECT * FROM contact" +
            " WHERE personId = :personId")
    List<Contact> all(int personId);

    @Update
    void update(Contact contact);

    @Query("DELETE FROM contact")
    void removeAll();

}
