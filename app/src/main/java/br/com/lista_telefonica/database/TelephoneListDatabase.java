package br.com.lista_telefonica.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Contact;
import br.com.lista_telefonica.model.Person;

import static br.com.lista_telefonica.database.Migrations.AllMigrations;

@Database(entities = {Person.class,Contact.class},version = 2,exportSchema = false)
public abstract class TelephoneListDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "telephoneList";
    public abstract PersonDAO getPersonDAO();
    public abstract ContactDAO getContactDAO();

    public static TelephoneListDatabase getInstance(Context context){
        return Room.databaseBuilder(context,TelephoneListDatabase.class,DATABASE_NAME)
                .addMigrations(AllMigrations)
                .build();

    }

}
