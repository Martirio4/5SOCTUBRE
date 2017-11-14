package com.demo.nomad.nomad5s.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.nomad.nomad5s.Adapter.AdapterPagerEse;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.R;

public class ActivityViewPager extends AppCompatActivity {

    public static final String QUEMUESTRO="QUEMUESTRO";
    private ViewPager viewPager;
    private AdapterPagerEse adapterPagerEse;
    private ControllerDatos controllerDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String queMuestro="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        controllerDatos= new ControllerDatos(this);
        if (bundle!=null){
            queMuestro=bundle.getString(QUEMUESTRO);
        }
        switch (queMuestro){
            case "criterios":
                abrirCriterios();
        }
    }
    public void abrirCriterios(){
        viewPager=this.findViewById(R.id.viewPagerCriterios);
        adapterPagerEse = new AdapterPagerEse(getSupportFragmentManager());
        viewPager.setAdapter(adapterPagerEse);

        adapterPagerEse.setListaEses(controllerDatos.traerlistaEses());

    }
}
