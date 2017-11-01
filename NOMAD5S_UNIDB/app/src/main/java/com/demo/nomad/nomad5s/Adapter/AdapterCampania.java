package com.demo.nomad.nomad5s.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterCampania extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<Campania> listaCampaniaesOriginales;
    private List<Campania> listaCampaniaesFavoritos;
    private View.OnClickListener listener;
    private AdapterView.OnLongClickListener listenerLong;
    private EditaEliminable editaEliminable;

    public void setLongListener(View.OnLongClickListener unLongListener) {
        this.listenerLong = unLongListener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListaCampaniaesOriginales(List<Campania> listaCampaniaesOriginales) {
        this.listaCampaniaesOriginales = listaCampaniaesOriginales;
    }

    public void addListaCampaniaesOriginales(List<Campania> listaCampaniaesOriginales) {
        this.listaCampaniaesOriginales.addAll(listaCampaniaesOriginales);
    }


    public List<Campania> getListaCampaniaesOriginales() {
        return listaCampaniaesOriginales;
    }

    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda = layoutInflater.inflate(R.layout.detalle_celda_manage_campanias, parent, false);
        return  new CampaniaViewHolder(viewCelda);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Campania unCampania = listaCampaniaesOriginales.get(position);
        CampaniaViewHolder CampaniaViewHolder = (CampaniaViewHolder) holder;
        CampaniaViewHolder.cargarCampania(unCampania);

    }



    @Override
    public int getItemCount() {
        return listaCampaniaesOriginales.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    @Override
    public boolean onLongClick(View v) {
        listenerLong.onLongClick(v);
        return true;
    }

    //creo el viewholder que mantiene las referencias
    //de los elementos de la celda

    private static class CampaniaViewHolder extends RecyclerView.ViewHolder {

        private TextView fechaInicio;
        private TextView fechaFin;
        private TextView cantidadAuditoriasProgramadas;
        private TextView cantidadAuditoriasTerminadas;




        public CampaniaViewHolder(View itemView) {
            super(itemView);
            fechaInicio = (TextView) itemView.findViewById(R.id.editFechaInicio);
            fechaFin= (TextView) itemView.findViewById(R.id.editFechaFin);
            cantidadAuditoriasProgramadas= (TextView) itemView.findViewById(R.id.cantidadAuditoriasProgramadas);
            cantidadAuditoriasTerminadas= (TextView) itemView.findViewById(R.id.cantidadAuditoriasTerminadas);

        }

        public void cargarCampania(Campania unCampania) {


        }


    }

    public interface EditaEliminable {
        void EliminarCampania(Campania unCampania);
        void editarCampania(Campania unCampania);
    }
}
