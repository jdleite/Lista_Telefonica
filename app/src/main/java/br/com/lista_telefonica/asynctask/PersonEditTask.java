package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Contact;
import br.com.lista_telefonica.model.Person;

public class PersonEditTask extends BasePersonWithTelephoneTask {

    private final PersonDAO personDao;
    private final Person person;
    private final Contact contact;
    private final ContactDAO contactDAO;
    private final List<Contact> personContacts;

    public PersonEditTask(PersonDAO personDao,
                          Person person,
                          Contact contact,
                          ContactDAO contactDAO,
                          List<Contact> personContacts,ListenerFinal listenerFinal) {
        super(listenerFinal);
        this.personDao = personDao;
        this.person = person;
        this.contact = contact;
        this.contactDAO = contactDAO;
        this.personContacts = personContacts;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        personDao.update(person);
        linkPersonWithTelephone(person.getId(),contact);
        telephoneIdsUpdate(contact);
        contactDAO.update(contact);
        return null;
    }

    private void telephoneIdsUpdate(Contact contact) {
        for (Contact contacts :
                personContacts) {

            contact.setId(contacts.getId());

        }

    }
}
