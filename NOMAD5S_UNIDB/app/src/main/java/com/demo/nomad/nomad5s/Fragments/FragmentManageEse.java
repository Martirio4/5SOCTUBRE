package com.demo.nomad.nomad5s.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.demo.nomad.nomad5s.Adapter.AdapterCriterios;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.Model.Ese;
import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManageEse extends Fragment {



    public static final String IDESE = "IDESE";

    public FragmentManageEse() {
        // Required empty public constructor
    }

    private ControllerDatos controllerDatos;
    private String idese;
    AdapterCriterios adapterCriterios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_criterios, container, false);

        controllerDatos= new ControllerDatos(getContext());




        Bundle bundle=getArguments();
        if (bundle!=null){
            idese=bundle.getString(IDESE);
        }
        else{
            idese="";

        }

        FloatingActionButton fabAgregarCriterio= (FloatingActionButton)view.findViewById(R.id.fabAgregarCriterio);
        fabAgregarCriterio.setColorNormal(ContextCompat.getColor(getContext(),R.color.verde3));
        fabAgregarCriterio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Criterio unCrit= new Criterio();
                unCrit.setIdCriterio("crit_"+ UUID.randomUUID());
                controllerDatos.guardarCriterio(unCrit);
               // controllerDatos.AgregarCriterioAEse(unCrit.getIdCriterio());

            }
        });

        RecyclerView recyclerView=view.findViewById(R.id.recyclerCriterios);
        adapterCriterios =new AdapterCriterios();
        adapterCriterios.setContext(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCriterios);

        //REDEFINIR ESTE METODO // REDEFINIR ADAPTER
       // adapterCriterios.setListaCriteriosOriginales(controllerDatos.traerCriteriosParaEse(idese));
      //  adapterCriterios.notifyDataSetChanged();



        return view;
    }

    public static FragmentManageEse crearFragmentEse(Ese unEse){
        FragmentManageEse fragmentManageEse = new FragmentManageEse();
        Bundle unBundle = new Bundle();
        unBundle.putString(IDESE, unEse.getIdEse());
        fragmentManageEse.setArguments(unBundle);
        return fragmentManageEse;
    }



}
