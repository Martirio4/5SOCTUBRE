package com.demo.nomad.nomad5s.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterTesting;
import com.demo.nomad.nomad5s.DAO.DAOAuditorias;
import com.demo.nomad.nomad5s.DAO.DAOCampania;
import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.R;

import java.util.UUID;

public class TestingDB extends AppCompatActivity {

    private Button boton;
    private RecyclerView recyclerAudit;
    private AdapterTesting adapterTesting;
    private LinearLayoutManager layoutManager;

    private DAOCampania daoCampania;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_db);

        daoCampania= new DAOCampania(this);

        boton = (Button) findViewById(R.id.agregarCampania);
        recyclerAudit = (RecyclerView) findViewById(R.id.recyclerAudit);
        adapterTesting= new AdapterTesting();
        adapterTesting.setContext(this);
        layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerAudit.setLayoutManager(layoutManager);
        adapterTesting.setListaStringesOriginales(daoCampania.getAllCampanias());
        adapterTesting.notifyDataSetChanged();
        recyclerAudit.setAdapter(adapterTesting);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Campania unaCamp = new Campania();
                    unaCamp.setFechaLimite("10/12/2017");
                    unaCamp.setIdCampania("campania"+UUID.randomUUID());


                new MaterialDialog.Builder(TestingDB.this)
                    .title("Add a comment")
                    .contentColor(ContextCompat.getColor(TestingDB.this, R.color.colorPrimary))
                                .backgroundColor(ContextCompat.getColor(TestingDB.this, R.color.blanco))
                                .titleColor(ContextCompat.getColor(TestingDB.this, R.color.colorPrimaryDark))
                                .content("Please, add a comment for this photo")
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("Comment","", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {

                                unaCamp.setNombreCampaña(input.toString());
                                daoCampania.addCampania(unaCamp);
                                adapterTesting.agregarCamp(unaCamp);
                                adapterTesting.notifyDataSetChanged();

                            }
                }).show();




            }
        });














    }
}
