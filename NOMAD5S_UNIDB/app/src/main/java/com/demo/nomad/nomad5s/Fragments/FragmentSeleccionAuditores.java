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
import com.demo.nomad.nomad5s.Adapter.AdapterAuditores;
import com.demo.nomad.nomad5s.Adapter.AdapterAuditores;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;

import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditor;
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
public class FragmentSeleccionAuditores extends Fragment {

    public static final String IDAREA ="IDAREA" ;
    private ControllerDatos controllerAuditores;
    private List<Auditor> listaAuditores;
    private RecyclerView recyclerAuditores;
    private AdapterAuditores adapterAuditores;
    private LinearLayoutManager layoutManager;
    private File fotoOriginal;
    private File fotoComprimida;
    private FloatingActionButton fabAgregarAuditores;
 
    private TextView textView;
    private Area unArea;

    private Terminable terminable;

    public FragmentSeleccionAuditores() {
        // Required empty public constructor
    }

    public interface Terminable{
        void armarAuditoria(Auditor unAuditor);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_auditores, container, false);

        /*
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Auditores> result2 = realm.where(Auditores.class)
                .findAll();
        */
        //PEDIR LISTADO DE Auditores
        listaAuditores=new ArrayList<>();
        listaAuditores=controllerAuditores.traerListaAuditores();
        recyclerAuditores= view.findViewById(R.id.recyclerArea);
        adapterAuditores= new AdapterAuditores();
        adapterAuditores.setContext(getContext());
        adapterAuditores.setListaAuditoresOriginales(listaAuditores);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer posicion = recyclerAuditores.getChildAdapterPosition(v);
                List<Auditor> listaActoresOriginales = adapterAuditores.getListaAuditoresOriginales();
                Auditor auditorClickeado = listaActoresOriginales.get(posicion);
                terminable.armarAuditoria(auditorClickeado );
            }
        };
        adapterAuditores.setListener(listener);


        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerAuditores.setLayoutManager(layoutManager);
        recyclerAuditores.setAdapter(adapterAuditores);

        fabAgregarAuditores =(FloatingActionButton) view.findViewById(R.id.agregarArea);
        fabAgregarAuditores.setVisibility(View.GONE);



        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.terminable=(Terminable)context;
    }
}
