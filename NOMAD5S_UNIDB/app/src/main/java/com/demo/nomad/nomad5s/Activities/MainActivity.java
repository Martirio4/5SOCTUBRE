package com.demo.nomad.nomad5s.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterArea;
import com.demo.nomad.nomad5s.Adapter.AdapterTesting;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.DAO.DAOCampania;
import com.demo.nomad.nomad5s.Fragments.FragmentManageAreas;
import com.demo.nomad.nomad5s.Fragments.FragmentManageAuditores;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.R;

import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button boton2;
    private Button boton3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        boton = (Button) findViewById(R.id.agregarCampania);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(MainActivity.this)
                        .title("Add a comment")
                        .contentColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
                        .backgroundColor(ContextCompat.getColor(MainActivity.this, R.color.marfil))
                        .titleColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark))
                        .content("Please, add a comment for this photo")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Comment","", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {



                            }
                        }).show();




            }
        });

        boton2=(Button)findViewById(R.id.manageareas);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDeterminadoFragment("manageAreas");

            }
        });

        boton3=(Button)findViewById(R.id.manageauditores);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDeterminadoFragment("manageAuditores");

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


    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle("Nomad 5S Audit");
    }
}
