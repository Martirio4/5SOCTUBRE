package com.demo.nomad.nomad5s.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by elmar on 18/5/2017.
 */

public class AdapterTesting extends RecyclerView.Adapter implements View.OnClickListener {

    private Context context;
    private List<String> listaStringesOriginales;
    private View.OnClickListener listener;
    private Favoritable favoritable;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setListaStringesOriginales(List<String> listaStringesOriginales) {
        this.listaStringesOriginales = listaStringesOriginales;
    }

    public List<String> getListaStringesOriginales() {
        return listaStringesOriginales;
    }

    //crear vista y viewholder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewCelda = layoutInflater.inflate(R.layout.detalle_celda_strings, parent, false);
        FormatoViewHolder stringsViewHolder = new FormatoViewHolder(viewCelda);
        viewCelda.setOnClickListener(this);
        //ACA AGREGO EL LONG CLICK?!=!=!"="?=!"
        return stringsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String unString = listaStringesOriginales.get(position);
        FormatoViewHolder formatoViewHolder = (FormatoViewHolder) holder;
        formatoViewHolder.cargarString(unString);
    }

    @Override
    public int getItemCount() {
        return listaStringesOriginales.size();
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view);
    }

    public void agregarCamp(Campania unaCamp) {
        this.listaStringesOriginales.add(unaCamp.getNombreCampaña());
    }

    //creo el viewholder que mantiene las referencias
    //de los elementos de la celda
    private static class FormatoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        //private TextView textViewTitulo;
        private ImageButton imageButton;
        private RatingBar ratingBar;
        private TextView textViewNombreString;
        private TextView textViewNombrePersonaje;

        public FormatoViewHolder(View itemView) {
            super(itemView);
            textViewNombreString = (TextView) itemView.findViewById(R.id.visorAuditoria);
        }

        public void cargarString(String unString) {

            textViewNombreString.setText(unString);

        }
    }

    public interface Favoritable {
        public void recibirFormatoFavorito(String unString);
    }
}
