package br.com.lista_telefonica.ui.listener;

import br.com.lista_telefonica.model.Person;

public interface TelephoneListListener {

    void onListClick(Person person);


    void onDeleteClick(Person person);
}
