package com.demo.nomad.nomad5s.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterCampania;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.ResourceBundle;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManageCampanias extends Fragment {


    public FragmentManageCampanias() {
        // Required empty public constructor
    }

    private ControllerDatos controllerCampanias;
    private AdapterCampania adapterCampania;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerCampania;
    private FloatingActionButton fabAgregarCampania;
    private Creable creable;

    public interface Creable{
        void abrirFragmentCreacionCampanias();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_campanias, container, false);
        controllerCampanias = new ControllerDatos(getContext());

        recyclerCampania= view.findViewById(R.id.recyclerCampania);
        layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerCampania.setLayoutManager(layoutManager);
        adapterCampania= new AdapterCampania();
        adapterCampania.setContext(getContext());
        recyclerCampania.setAdapter(adapterCampania);
        adapterCampania.setListaCampaniaesOriginales(controllerCampanias.traerListaCampanias());
        adapterCampania.notifyDataSetChanged();

        fabAgregarCampania=view.findViewById(R.id.agregarCampania);
        fabAgregarCampania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               creable.abrirFragmentCreacionCampanias();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.creable=(Creable) context;
    }
}
