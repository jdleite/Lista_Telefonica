package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Person;
import br.com.lista_telefonica.ui.adapter.TelephoneListAdapter;

public class GetPersonTask extends AsyncTask<Void,Void, List<Person>> {
    private final PersonDAO dao;
    private final TelephoneListAdapter adapter;


    public GetPersonTask(PersonDAO dao, TelephoneListAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Person> doInBackground(Void[] voids) {
        return dao.all();
    }

    @Override
    protected void onPostExecute(List<Person> personList) {
        super.onPostExecute(personList);
        adapter.load(personList);
    }
}
