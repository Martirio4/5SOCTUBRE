package com.demo.nomad.nomad5s.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nomad.nomad5s.Adapter.AdapterCriterios;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManageCriterios extends Fragment {

   
    public static final String IDCRITERIO = "IDCRITERIO";
    public static final String IDESE = "IDESE";

    public FragmentManageCriterios() {
        // Required empty public constructor
    }

    private ControllerDatos controllerDatos;
    private String idese;
    private String idCriterio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_criterios, container, false);
        controllerDatos= new ControllerDatos(getContext());
        Bundle bundle=getArguments();
        if (bundle!=null){
            idCriterio=bundle.getString(IDCRITERIO);
            idese=bundle.getString(IDESE);
        }
        else{
            idCriterio="";
            idese="";

        }

        RecyclerView recyclerView=view.findViewById(R.id.recyclerCriterios);
        AdapterCriterios adapterCriterios=new AdapterCriterios();
        adapterCriterios.setContext(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterCriterios);

        //REDEFINIR ESTE METODO // REDEFINIR ADAPTER
        adapterCriterios.setListaCriteriosOriginales(controllerDatos.traerCriteriosParaEse(idese));
        adapterCriterios.notifyDataSetChanged();



        return view;
    }
    
    public static FragmentManageCriterios crearFragmentCriterios(Criterio unCriterio){
        FragmentManageCriterios CriterioFragment = new FragmentManageCriterios();
        Bundle unBundle = new Bundle();
        unBundle.putString(IDCRITERIO, unCriterio.getIdCriterio());
        CriterioFragment.setArguments(unBundle);
        return CriterioFragment;
    }



}
