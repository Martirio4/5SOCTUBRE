package com.demo.nomad.nomad5s.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterArea;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import id.zelory.compressor.Compressor;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeleccionAreas extends Fragment {

    private ControllerDatos controllerAreas;
    private List<Area> listaAreas;
    private RecyclerView recyclerAreas;
    private AdapterArea adapterArea;
    private LinearLayoutManager layoutManager;
    private File fotoOriginal;
    private File fotoComprimida;
    private FloatingActionButton fabAgregarArea;
    private FragmentCrearCampania.Elegible unElegible;

    private TextView textView;


    public FragmentSeleccionAreas() {
        // Required empty public constructor
    }


    public void updateAdapter() {
        adapterArea.setListaAreasOriginales(controllerAreas.traerListaAreas());
        adapterArea.notifyDataSetChanged();
        /*
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Area> result3 = realm.where(Area.class)
                .findAll();
        listaAreas=new List<>();
        listaAreas.addAll(result3);
        adapterArea.setListaAreasOriginales(listaAreas);
        adapterArea.notifyDataSetChanged();
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_auditores, container, false);
        controllerAreas=new ControllerDatos(getContext());
        /*
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Area> result2 = realm.where(Area.class)
                .findAll();
        */
        //PEDIR LISTADO DE AREAS
        listaAreas=new ArrayList<>();
        listaAreas=controllerAreas.traerListaAreas();
        recyclerAreas= view.findViewById(R.id.recyclerArea);
        adapterArea= new AdapterArea();
        adapterArea.setContext(getContext());
        adapterArea.setListaAreasOriginales(listaAreas);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer posicion = recyclerAreas.getChildAdapterPosition(v);
                List<Area> listaActoresOriginales = adapterArea.getListaAreasOriginales();
                Area areaClickeada = listaActoresOriginales.get(posicion);
                unElegible.elegirAuditor(areaClickeada);
            }
        };
        adapterArea.setListener(listener);


        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerAreas.setLayoutManager(layoutManager);
        recyclerAreas.setAdapter(adapterArea);

        fabAgregarArea =(FloatingActionButton) view.findViewById(R.id.agregarArea);
        fabAgregarArea.setVisibility(View.GONE);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.unElegible=(FragmentCrearCampania.Elegible)context;
    }
}
