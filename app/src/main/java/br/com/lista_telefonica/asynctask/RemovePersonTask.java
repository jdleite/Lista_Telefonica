package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;

import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Person;
import br.com.lista_telefonica.ui.adapter.TelephoneListAdapter;

public class RemovePersonTask extends AsyncTask {
    private final PersonDAO dao;
    private final TelephoneListAdapter adapter;
    private final Person person;

    public RemovePersonTask(PersonDAO dao, TelephoneListAdapter adapter, Person person) {
        this.dao = dao;
        this.adapter = adapter;
        this.person = person;
    }


    @Override
    protected Object doInBackground(Object[] objects) {
        dao.remove(person);
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        adapter.removePerson(person);
    }
}
