package br.com.lista_telefonica.asynctask;

import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Contact;
import br.com.lista_telefonica.model.Person;

public class SavePersonTask extends BasePersonWithTelephoneTask {

    private final PersonDAO personDAO;
    private final Person person;
    private final Contact contact;
    private final ContactDAO contactDAO;


    public SavePersonTask(PersonDAO personDAO, Person person, Contact contact, ContactDAO contactDAO,ListenerFinal listener) {
        super(listener);
        this.personDAO = personDAO;
        this.person = person;
        this.contact = contact;
        this.contactDAO = contactDAO;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        int personId = personDAO.save(person).intValue();
        linkPersonWithTelephone(personId,contact);
        contactDAO.save(contact);
        return null;
    }
}
