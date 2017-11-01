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

import com.demo.nomad.nomad5s.Fragments.FragmentManageAreas;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterArea extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    private List<Area> listaAreasOriginales;
    private List<Area> listaAreasFavoritos;
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

    public void setListaAreasOriginales(List<Area> listaAreasOriginales) {
        this.listaAreasOriginales = listaAreasOriginales;
    }

    public void addListaAreasOriginales(List<Area> listaAreasOriginales) {
        this.listaAreasOriginales.addAll(listaAreasOriginales);
    }


    public List<Area> getListaAreasOriginales() {
        return listaAreasOriginales;
    }

    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda;
        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        FragmentManageAreas fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");

        if (fragmentManageAreas != null && fragmentManageAreas.isVisible()) {
            viewCelda = layoutInflater.inflate(R.layout.detalle_celda_manage_auditores, parent, false);
        }
        else{
            viewCelda=layoutInflater.inflate(R.layout.detalle_celda_manage_auditores,parent,false);
        }
        viewCelda.setOnClickListener(listener);
        AreaViewHolder areasViewHolder = new AreaViewHolder(viewCelda);


        return areasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Area unArea = listaAreasOriginales.get(position);
        AreaViewHolder AreaViewHolder = (AreaViewHolder) holder;
        AreaViewHolder.cargarArea(unArea);

        FragmentActivity unaActivity = (FragmentActivity) context;
        FragmentManager fragmentManager = (FragmentManager) unaActivity.getSupportFragmentManager();
        FragmentManageAreas fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");


        if (fragmentManageAreas != null && fragmentManageAreas.isVisible()) {

            AreaViewHolder.fabEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editaEliminable = (EditaEliminable) context;
                    editaEliminable.EliminarArea(unArea);

                }
            });

            AreaViewHolder.fabEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editaEliminable=(EditaEliminable)context;
                    editaEliminable.editarArea(unArea);
                }
            });
        }
        else{
            AreaViewHolder.fabEditar.setVisibility(View.GONE);
            AreaViewHolder.fabEliminar.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return listaAreasOriginales.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }


    //creo el viewholder que mantiene las referencias
    //de los elementos de la celda

    private static class AreaViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imageView;
        private TextView textView;
        private ImageButton fabEliminar;
        private ImageButton fabEditar;



        public AreaViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.imagenCamara);
            textView= (TextView) itemView.findViewById(R.id.nombreAuditor);

            fabEliminar = (ImageButton) itemView.findViewById(R.id.botonEliminar);


            fabEditar=(ImageButton) itemView.findViewById(R.id.botonEditar);

        }

        public void cargarArea(Area unArea) {

            if (unArea.getFotoArea()!=null) {


            File f =new File(unArea.getFotoArea().getRutaFotoDB());
            Picasso.with(imageView.getContext())
                    .load(f)
                    .into(imageView);

            textView.setText(unArea.getNombreArea());
            }
        }


    }

    public interface EditaEliminable {
        void EliminarArea(Area unArea);
        void editarArea(Area unArea);
    }
}
