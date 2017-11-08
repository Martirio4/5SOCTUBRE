package com.demo.nomad.nomad5s.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.nomad.nomad5s.R;


public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button boton2;
    private Button boton3;
    private Button boton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boton =  findViewById(R.id.agregarCampania);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDeterminadoFragment("manageCampanias");




            }
        });

        boton2=findViewById(R.id.manageareas);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDeterminadoFragment("manageAreas");

            }
        });

        boton3=findViewById(R.id.manageauditores);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDeterminadoFragment("manageAuditores");

            }
        });

        boton4=findViewById(R.id.manageCriterios);
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivityViewPager("criterios");
            }
        });

    }

    public void abrirDeterminadoFragment(String unString){
        Intent intent = new Intent(this, ActivityBase.class);
        Bundle bundle= new Bundle();
        bundle.putString(ActivityBase.QUE_FRAGMENT_ABRO, unString);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void abrirActivityViewPager(String parametro){
        Intent intent= new Intent(this, ActivityViewPager.class);
        Bundle bundle=new Bundle();
        bundle.putString(ActivityViewPager.QUEMUESTRO, parametro);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Nomad 5S Audit");
    }
}
