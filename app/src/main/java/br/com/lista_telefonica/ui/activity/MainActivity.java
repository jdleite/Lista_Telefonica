package br.com.lista_telefonica.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.lista_telefonica.R;
import br.com.lista_telefonica.asynctask.GetPersonTask;
import br.com.lista_telefonica.asynctask.RemovePersonTask;
import br.com.lista_telefonica.database.TelephoneListDatabase;
import br.com.lista_telefonica.database.dao.PersonDAO;
import br.com.lista_telefonica.model.Person;
import br.com.lista_telefonica.ui.ListPersonView;
import br.com.lista_telefonica.ui.adapter.TelephoneListAdapter;
import br.com.lista_telefonica.ui.listener.TelephoneListListener;

import static br.com.lista_telefonica.ui.activity.Constants.PERSON_KEY;

public class MainActivity extends AppCompatActivity {
    private static final String TITLE_APP = "Lista de Telefone";
    private ListPersonView listPersonView;
    private List<Person> personList = new ArrayList<Person>();
    private TelephoneListAdapter adapter;
    private RecyclerView recyclerView;
    private TelephoneListListener listener;
    private PersonDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITLE_APP);
        listPersonView = new ListPersonView(this, adapter);
        recyclerView = findViewById(R.id.id_recyclerView);
        TelephoneListDatabase database = TelephoneListDatabase.getInstance(this);
        dao = database.getPersonDAO();
        getForm();
        getListener();





    }

    public void getListener(){
        listener = new TelephoneListListener() {
            @Override
            public void onListClick(Person person) {
                openFormEditMode(person);
            }

            @Override
            public void onDeleteClick(Person person) {

                remove(person);

            }
        };
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.activity_remove_menu, menu);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }


    private void openFormEditMode(Person person) {
        Intent intent = new Intent(MainActivity.this, Form.class);
        intent.putExtra(PERSON_KEY, person);
        startActivity(intent);
    }

    public void getForm() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(this, Form.class)));

    }

    private void getList() {
        adapter = new TelephoneListAdapter(personList, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new GetPersonTask(dao, adapter).execute();

    }

    public void remove(Person person) {
        new RemovePersonTask(dao, adapter, person).execute();
    }


}
