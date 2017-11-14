package com.demo.nomad.nomad5s.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.demo.nomad.nomad5s.Fragments.FragmentManageEse;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.R;

import java.util.List;


/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterCriterios extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<Criterio> listaCriteriosOriginales;
    private List<Criterio> listaCriteriosFavoritos;
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

    public void setListaCriteriosOriginales(List<Criterio> listaCriteriosOriginales) {
        this.listaCriteriosOriginales = listaCriteriosOriginales;
    }

    public void addListaCriteriosOriginales(List<Criterio> listaCriteriosOriginales) {
        this.listaCriteriosOriginales.addAll(listaCriteriosOriginales);
    }


    public List<Criterio> getListaCriteriosOriginales() {
        return listaCriteriosOriginales;
    }



    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda;

            viewCelda = layoutInflater.inflate(R.layout.detalle_celda_manage_auditores, parent, false);


        viewCelda.setOnClickListener(listener);
        CriterioViewHolder CriteriosViewHolder = new CriterioViewHolder(viewCelda);

        return CriteriosViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Criterio unCriterio = listaCriteriosOriginales.get(position);
        CriterioViewHolder CriterioViewHolder = (CriterioViewHolder) holder;
        CriterioViewHolder.cargarCriterio(unCriterio);
        Integer unint=position+1;
        String elString=unint.toString();
        ((CriterioViewHolder) holder).posicion.setText(elString);

        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        FragmentManageEse FragmentManageEse = (FragmentManageEse) fragmentManager.findFragmentByTag("fragmentManageCriterios");


        if (FragmentManageEse != null && FragmentManageEse.isVisible()) {

//            COMPORTAMIENTO DE LOS BOTONES DE LA CELDA
        }
        else{
//            COMPORTAMIENTO DE LOS BOTONES DE LA CELDA
        }

    }

    @Override
    public int getItemCount() {
        return listaCriteriosOriginales.size();
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

    private static class CriterioViewHolder extends RecyclerView.ViewHolder {
        private TextView puntaje1;
        private TextView puntaje2;
        private TextView puntaje3;
        private TextView puntaje4;
        private TextView puntaje5;
        private TextView enunciado;
        private TextView posicion;

        public CriterioViewHolder(View itemView) {
            super(itemView);

        puntaje1=itemView.findViewById(R.id.TV_descripcionPuntaje1);
        puntaje2=itemView.findViewById(R.id.TV_descripcionPuntaje2);
        puntaje3=itemView.findViewById(R.id.TV_descripcionPuntaje3);
        puntaje4=itemView.findViewById(R.id.TV_descripcionPuntaje4);
        puntaje5=itemView.findViewById(R.id.TV_descripcionPuntaje5);
        enunciado=itemView.findViewById(R.id.TV_enunciado);
        posicion=itemView.findViewById(R.id.TV_numeroItem);
        }

        public void cargarCriterio(Criterio unCriterio) {
        puntaje1.setText(unCriterio.getOpcion1());
        puntaje2.setText(unCriterio.getOpcion2());
        puntaje3.setText(unCriterio.getOpcion3());
        puntaje4.setText(unCriterio.getOpcion4());
        puntaje5.setText(unCriterio.getOpcion5());
        enunciado.setText(unCriterio.getTextoCriterio());
        }

    }

    public interface EditaEliminable {
        void EliminarCriterio(Criterio unCriterio);
        void editarCriterio(Criterio unCriterio);
    }


}
