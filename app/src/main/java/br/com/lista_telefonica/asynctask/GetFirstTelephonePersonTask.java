package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;

import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.model.Contact;

public class GetFirstTelephonePersonTask extends AsyncTask<Void,Void, Contact> {

    private final ContactDAO dao;
    private final int personId;
    private final firstGetTelephoneListener listener;


    public GetFirstTelephonePersonTask(ContactDAO dao, int personId, firstGetTelephoneListener listener) {
        this.dao = dao;
        this.personId = personId;
        this.listener = listener;

    }


    @Override
    protected Contact doInBackground(Void... voids) {
        return dao.getFirstTelephonePerson(personId);
    }

    @Override
    protected void onPostExecute(Contact contact) {
        super.onPostExecute(contact);
        listener.get(contact);
    }


    public interface firstGetTelephoneListener {
        void get(Contact contact);
    }
}
