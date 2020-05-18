package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;
import android.widget.ListView;

import java.util.List;

import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.model.Contact;
import br.com.lista_telefonica.model.Person;

public class GetAllTelephonePerson extends AsyncTask<Void, Void, List<Contact>> {
    private final ContactDAO contactDAO;
    private final Person person;
    private final TelephoneListener telephoneListener;


    public GetAllTelephonePerson(ContactDAO contactDAO, Person person, TelephoneListener telephoneListener) {
        this.contactDAO = contactDAO;
        this.person = person;
        this.telephoneListener = telephoneListener;
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) {
        super.onPostExecute(contactList);

        telephoneListener.getTelephone(contactList);
    }

    @Override
    protected List<Contact> doInBackground(Void... voids) {

        return contactDAO.all(person.getId());
    }

    public interface TelephoneListener {
        void getTelephone(List<Contact> contactList);
    }
}
