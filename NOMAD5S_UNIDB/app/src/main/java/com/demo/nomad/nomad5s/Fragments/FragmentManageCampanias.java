package com.demo.nomad.nomad5s.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nomad.nomad5s.Adapter.AdapterCampania;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.R;

import java.util.ResourceBundle;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_campanias, container, false);
        controllerCampanias = new ControllerDatos(getContext());

        recyclerCampania= (RecyclerView) view.findViewById(R.id.recyclerCampania);
        layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerCampania.setLayoutManager(layoutManager);
        adapterCampania= new AdapterCampania();
        adapterCampania.setContext(getContext());
        recyclerCampania.setAdapter(adapterCampania);
        adapterCampania.setListaCampaniaesOriginales(controllerCampanias.traerListaCampanias());






        return view
    }

}
