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

import com.demo.nomad.nomad5s.Fragments.FragmentManageAuditores;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterCriterios extends RecyclerView.Adapter implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<Criterio> listaCriteriosOriginales;
    private List<Criterio> listaAuditoresFavoritos;
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

    public void addListaAuditoresOriginales(List<Criterio> listaAuditoresOriginales) {
        this.listaCriteriosOriginales.addAll(listaAuditoresOriginales);
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
        AuditorViewHolder AuditoresViewHolder = new AuditorViewHolder(viewCelda);

        return AuditoresViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Criterio unCriterio = listaCriteriosOriginales.get(position);
        AuditorViewHolder AuditorViewHolder = (AuditorViewHolder) holder;
        AuditorViewHolder.cargarAuditor(unCriterio);

        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        FragmentManageAuditores FragmentManageAuditores = (FragmentManageAuditores) fragmentManager.findFragmentByTag("fragmentManageAuditores");


        if (FragmentManageAuditores != null && FragmentManageAuditores.isVisible()) {

            AuditorViewHolder.fabEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editaEliminable = (EditaEliminable) context;
                    editaEliminable.EliminarAuditor(unCriterio);

                }
            });

            AuditorViewHolder.fabEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editaEliminable=(EditaEliminable) context;
                    editaEliminable.editarAuditor(unCriterio);
                }
            });
        }
        else{
            AuditorViewHolder.fabEditar.setVisibility(View.GONE);
            AuditorViewHolder.fabEliminar.setVisibility(View.GONE);
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

    private static class AuditorViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView textViewNombre;
        private TextView textViewPuesto;
        private TextView textViewMail;
        private TextView textViewAudits;
        private ImageButton fabEliminar;
        private ImageButton fabEditar;



        public AuditorViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.imagenCamara);
            textViewNombre= (TextView) itemView.findViewById(R.id.nombreAuditor);
            textViewAudits= (TextView) itemView.findViewById(R.id.cantAudits);
            textViewMail=(TextView)itemView.findViewById(R.id.mailAuditor);
            textViewPuesto=(TextView)itemView.findViewById(R.id.puestoAuditor);
            fabEliminar = (ImageButton) itemView.findViewById(R.id.botonEliminar);
            fabEditar=(ImageButton) itemView.findViewById(R.id.botonEditar);


        }

        public void cargarAuditor(Criterio unCriterio) {

            if (unCriterio.getFotoAuditor()!=null) {
                File f =new File(unCriterio.getFotoAuditor().getRutaFotoDB());
                Picasso.with(circleImageView.getContext())
                        .load(f)
                        .into(circleImageView);
            }
            else{
                circleImageView.setImageResource(R.drawable.desconocidocolor);
            }
            //si tiene nombre carga el nombre, sino pone el mail como nombre
            if (unCriterio.getNombreAuditor()!= null && !unCriterio.getNombreAuditor().isEmpty()){
                textViewNombre.setText(unCriterio.getNombreAuditor().toUpperCase());
            }
            else {
                textViewNombre.setText(unCriterio.getMailUsuario());
            }
            //si tiene puesto lo carga, sino vacio
            if (unCriterio.getPuesto()!= null && !unCriterio.getPuesto().isEmpty()){
                textViewPuesto.setText(unCriterio.getPuesto());
            }

            if (unCriterio.getMailUsuario()!= null && !unCriterio.getMailUsuario().isEmpty()&&unCriterio.getNombreAuditor()!=null &&!unCriterio.getNombreAuditor().isEmpty()){
                textViewMail.setText(unCriterio.getMailUsuario());
            }
            if (unCriterio.getCantidadAuditoriasRealizada()!= null){
                textViewAudits.setText(unCriterio.getCantidadAuditoriasRealizada().toString());
            }




        }


    }

    public interface EditaEliminable {
        void EliminarAuditor(Criterio unCriterio);
        void editarAuditor(Criterio unCriterio);
    }
}
