package br.com.lista_telefonica.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.lista_telefonica.R;
import br.com.lista_telefonica.asynctask.GetFirstTelephonePersonTask;
import br.com.lista_telefonica.database.TelephoneListDatabase;
import br.com.lista_telefonica.database.dao.ContactDAO;
import br.com.lista_telefonica.model.Person;
import br.com.lista_telefonica.ui.listener.TelephoneListListener;

public class TelephoneListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgView;
    private CardView cardView;
    private TextView txtName,txtTelephone, txtCellphone;
    private Context context;
    private ContactDAO dao;


    public TelephoneListViewHolder(@NonNull View itemView,Context context) {
        super(itemView);

        dao = TelephoneListDatabase.getInstance(context).getContactDAO();
        imgView = itemView.findViewById(R.id.id_img_remove);
        txtName = itemView.findViewById(R.id.id_txt_row_name);
        txtTelephone = itemView.findViewById(R.id.id_txt_row_telephone);
        txtCellphone = itemView.findViewById(R.id.id_txt_row_cellphone);
        cardView  = itemView.findViewById(R.id.id_cardView);
        this.context = context;
    }

    public void bindData(Person person, TelephoneListListener listListener){
        txtName.setText(person.getName());
        new GetFirstTelephonePersonTask(dao,person.getId(), contact -> {
            txtTelephone.setText(contact.getTelephone());
            txtCellphone.setText(contact.getCellphone());
        }).execute();


        cardView.setOnClickListener(view -> {
                    listListener.onListClick(person);
        });


        imgView.setOnClickListener(view -> {
            new AlertDialog
                    .Builder(context)
                    .setTitle(R.string.titulo_remover)
                    .setMessage(R.string.msg_contato)
                    .setPositiveButton(R.string.sim,((dialogInterface, i) -> {
                       listListener.onDeleteClick(person);
                    }))
                    .setNegativeButton(R.string.nao,null)
                    .show();
        });






    }


}
