package br.com.lista_telefonica.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import br.com.lista_telefonica.R;
import br.com.lista_telefonica.model.Person;
import br.com.lista_telefonica.ui.listener.TelephoneListListener;
import br.com.lista_telefonica.ui.viewHolder.TelephoneListViewHolder;

public class TelephoneListAdapter extends   RecyclerView.Adapter<TelephoneListViewHolder> {

    private final List<Person> personList;
    private final TelephoneListListener listener;

    public TelephoneListAdapter(List<Person> personList, TelephoneListListener listener) {
        this.personList = personList;
        this.listener = listener;

    }



    @NonNull
    @Override
    public TelephoneListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_row_list,parent,false);


        return new TelephoneListViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull TelephoneListViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.bindData(person,listener);

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void load(List<Person> personList) {
        this.personList.clear();
        this.personList.addAll(personList);
        notifyDataSetChanged();

    }

    public void removePerson(Person person){
        personList.remove(person);
        notifyDataSetChanged();
    }


}
