package br.com.lista_telefonica.asynctask;

import android.os.AsyncTask;

import br.com.lista_telefonica.model.Contact;

abstract class BasePersonWithTelephoneTask extends AsyncTask<Void, Void, Void> {
    private final ListenerFinal listenerFinal;

    BasePersonWithTelephoneTask(ListenerFinal listenerFinal) {
        this.listenerFinal = listenerFinal;
    }

    void linkPersonWithTelephone(int personId, Contact... contacts) {
        for (Contact contact :
                contacts) {
            contact.setPersonId(personId);
        }

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listenerFinal.whenFinal();
    }

    public interface ListenerFinal {
        void whenFinal();

    }
}
