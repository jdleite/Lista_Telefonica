package br.com.lista_telefonica.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;

import br.com.lista_telefonica.asynctask.GetPersonTask;
import br.com.lista_telefonica.database.TelephoneListDatabase;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.ui.adapter.TelephoneListAdapter;

public class ListPersonView  {

    private final PersonDAO dao;
    private final Context context;
    private final TelephoneListAdapter adapter;

    public ListPersonView(Context context, TelephoneListAdapter adapter) {
        this.context = context;
        this.dao = TelephoneListDatabase.getInstance(context).getPersonDAO();
        this.adapter = adapter;
    }
    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo aluno")
                .setMessage("Tem certeza que quer remover o aluno?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

                })
                .setNegativeButton("Não", null)
                .show();
    }




//    public void confAdapter(ListView listView){
//        listView.setAdapter(adapter);
//    }

    public void loadPerson(){
        new GetPersonTask(dao,adapter).execute();
    }
}
