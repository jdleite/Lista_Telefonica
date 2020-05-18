package br.com.lista_telefonica.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.lista_telefonica.model.Person;


@Dao
public interface PersonDAO {

    @Insert
    Long save(Person person);

    @Query("SELECT * FROM person ORDER BY ID DESC ")
    List<Person> all();

    @Delete
    void remove(Person person);

    @Update
    void update(Person person);

    @Query("DELETE FROM person")
    void removeAll();



}
