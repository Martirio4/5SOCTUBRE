package com.demo.nomad.nomad5s.Activities;

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


public class MainActivity extends AppCompatActivity implements FragmentManageAreas.Avisable,AdapterArea.EditaEliminable {

    private Button boton;
    private Button boton2;
    private Button boton3;
    private RecyclerView recyclerAudit;
    private AdapterTesting adapterTesting;
    private LinearLayoutManager layoutManager;
    private ControllerDatos controllerDatos;

    private DAOCampania daoCampania;

    FragmentManageAreas fragmentManageAreas;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controllerDatos= new ControllerDatos(this);

        boton = (Button) findViewById(R.id.agregarCampania);
        recyclerAudit = (RecyclerView) findViewById(R.id.recyclerAudit);
        adapterTesting= new AdapterTesting();
        adapterTesting.setContext(this);
        layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerAudit.setLayoutManager(layoutManager);
        adapterTesting.setListaStringesOriginales(controllerDatos.traerCampanias());
        adapterTesting.notifyDataSetChanged();
        recyclerAudit.setAdapter(adapterTesting);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Campania unaCamp = new Campania();
                unaCamp.setFechaLimite("10/12/2017");
                unaCamp.setIdCampania("campania"+ UUID.randomUUID());


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

                                unaCamp.setNombreCampaña(input.toString());
                                daoCampania.addCampania(unaCamp);
                                adapterTesting.agregarCamp(unaCamp);
                                adapterTesting.notifyDataSetChanged();

                            }
                        }).show();




            }
        });

        boton2=(Button)findViewById(R.id.manageareas);
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFragmentManageAreas();
            }
        });
        
        boton3=(Button)findViewById(R.id.manageauditores);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirFragmentManageAuditores();
            }
        });

    }

    private void abrirFragmentManageAreas() {
        FragmentManageAreas fragmentCargarArea = new FragmentManageAreas();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentCargarArea,"fragmentManageAreas");
        fragmentTransaction.commit();
    }
    private void abrirFragmentManageAuditores() {
        FragmentManageAuditores fragmentManageAuditores = new FragmentManageAuditores();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentManageAuditores,"fragmentManageAuditores");
        fragmentTransaction.commit();
    }

    @Override
    public void salirDeAca() {

    }

    @Override
    public void EliminarArea(Area unArea) {
        CrearDialogoBorrarArea(unArea);
    }

    @Override
    public void editarArea(Area unArea) {
        CrearDialogoEditarArea(unArea);
    }

    private void CrearDialogoEditarArea(final Area unArea) {

        new MaterialDialog.Builder(MainActivity.this)
                .title("Rename Area")
                .content("Enter the new name")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("","", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {

                        try {
                            controllerDatos.renombrarArea(unArea,input.toString());

                            fragmentManager = (FragmentManager) MainActivity.this.getSupportFragmentManager();
                            fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");
                            fragmentManageAreas.updateAdapter();

                            // dialogoExito(unArea);
                            Snackbar.make(boton,"Area was succesfully updated",Snackbar.LENGTH_SHORT)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar.make(boton,"Area was not updated. Please try again",Snackbar.LENGTH_SHORT)
                                    .show();
                        }

                    }
                }).show();
    }

    public void CrearDialogoBorrarArea(final Area unArea){
        new MaterialDialog.Builder(this)
                .title("Delete Selected Area")
                .contentColor(ContextCompat.getColor(this, R.color.primary_text))
                .titleColor(ContextCompat.getColor(this, R.color.tile4))
                .backgroundColor(ContextCompat.getColor(this, R.color.tile1))
                .content("The area: " + unArea.getNombreArea() +"\n"+ "will be permanently deleted."+"\n"+"Do you wisht to continue?")
                .positiveText("Delete")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        borrarDefinitivamente(unArea);
                    }
                })
                .negativeText("Cancel")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                    }
                })
                .show();

    }

    public void borrarDefinitivamente(final Area unArea){
       /* Realm realm = Realm.getDefaultInstance();
        final Area mArea=realm.where(Area.class)
                .equalTo("idArea",unArea.getIdArea())
                .findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mArea.deleteFromRealm();
            }
        });*/



         fragmentManager = (FragmentManager) this.getSupportFragmentManager();
         fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");

        if (fragmentManageAreas != null && fragmentManageAreas.isVisible()) {
            try {
                controllerDatos.eliminarArea(unArea);
                fragmentManageAreas.updateAdapter();
                Snackbar.make(boton,unArea.getNombreArea()+" was removed",Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                controllerDatos.guardarArea(unArea);
                                fragmentManageAreas.updateAdapter();
                            }
                        })

                        .show();

            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(boton,"Area could not be removed",Snackbar.LENGTH_LONG)
                        .show();
            }

        } else {

        }

    }
}
