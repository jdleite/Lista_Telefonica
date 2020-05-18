package br.com.lista_telefonica.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import br.com.lista_telefonica.R;
import br.com.lista_telefonica.asynctask.GetAllTelephonePerson;
import br.com.lista_telefonica.asynctask.PersonEditTask;
import br.com.lista_telefonica.asynctask.SavePersonTask;
import br.com.lista_telefonica.database.TelephoneListDatabase;
import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Contact;
import br.com.lista_telefonica.model.Person;

import static br.com.lista_telefonica.ui.activity.Constants.PERSON_KEY;

public class Form extends AppCompatActivity{

    private StartFields startFilds = new StartFields();
    private final String TITLE_NEW_CONTACT = "Cadastrar Contato";
    private final String TITLE_UPDATE_CONTACT = "Alterar Contato";
    private PersonDAO personDAO;
    private Person person;
    private ContactDAO contactDAO;
    private List<Contact> personContactList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        TelephoneListDatabase database = TelephoneListDatabase.getInstance(this);
        personDAO = database.getPersonDAO();
        contactDAO = database.getContactDAO();
        startFilds.edtName = findViewById(R.id.id_edt_name);
        startFilds.edtTelephone = findViewById(R.id.id_edt_telephone);
        startFilds.edtCellphone = findViewById(R.id.id_edt_cellphone);
        startFilds.btnSave = findViewById(R.id.id_btn_save);
        loadPerson();
        btnSave();
    }


    private class StartFields {
        EditText edtName, edtTelephone,edtCellphone;
        Button btnSave;
    }

    private void savePerson(Contact contact) {
        setPerson();
        new SavePersonTask(personDAO, person, contact, contactDAO, () -> finish()).execute();

    }

    private void loadPerson() {
        Intent intent = getIntent();
        if (intent.hasExtra(PERSON_KEY)) {
            setTitle(TITLE_UPDATE_CONTACT);
            person = (Person) intent.getSerializableExtra(PERSON_KEY);
            fillField();
        } else {
            setTitle(TITLE_NEW_CONTACT);
            person = new Person();
        }
    }

    private void setPerson() {
        String name = startFilds.edtName.getText().toString();
        person.setName(name);

    }

    private void editPerson(Contact contact) {
        new PersonEditTask(personDAO, person, contact, contactDAO, personContactList, this::finish).execute();

    }

    private void finishForm() {
        setPerson();
        Contact contact = setContact();
        if (person.validateId()){
            editPerson(contact);
        }else {
            savePerson(contact);

        }
    }

    private Contact setContact() {
        String telephone = startFilds.edtTelephone.getText().toString();
        String cellphone = startFilds.edtCellphone.getText().toString();
        return new Contact(telephone,cellphone);
    }

    private void btnSave(){
        startFilds.btnSave.setOnClickListener(view ->{
            finishForm();
        });
    }

    private void fillField(){
        startFilds.edtName.setText(person.getName());
        getContactField();
    }

    private void getContactField() {
        new GetAllTelephonePerson(contactDAO,person,contactList -> {
            this.personContactList = contactList;

            for (Contact contact : personContactList){
                startFilds.edtTelephone.setText(contact.getTelephone());
                startFilds.edtCellphone.setText(contact.getCellphone());
            }


        }).execute();
    }


}
