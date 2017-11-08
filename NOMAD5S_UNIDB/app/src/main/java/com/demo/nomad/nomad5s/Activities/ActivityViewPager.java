package com.demo.nomad.nomad5s.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.nomad.nomad5s.R;

public class ActivityViewPager extends AppCompatActivity {

    public static final String QUEMUESTRO="QUEMUESTRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String queMuestro="";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        if (bundle!=null){

            queMuestro=bundle.getString(QUEMUESTRO);
        }
        switch (queMuestro){
            case "criterios":
                abrirCriterios();

        }

    }
}
