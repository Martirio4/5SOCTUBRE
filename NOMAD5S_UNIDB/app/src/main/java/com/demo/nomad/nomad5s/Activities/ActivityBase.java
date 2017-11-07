package com.demo.nomad.nomad5s.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterArea;
import com.demo.nomad.nomad5s.Adapter.AdapterAuditores;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Fragments.FragmentCrearCampania;
import com.demo.nomad.nomad5s.Fragments.FragmentManageAreas;
import com.demo.nomad.nomad5s.Fragments.FragmentManageAuditores;
import com.demo.nomad.nomad5s.Fragments.FragmentManageCampanias;
import com.demo.nomad.nomad5s.Fragments.FragmentSeleccionAreas;
import com.demo.nomad.nomad5s.Fragments.FragmentSeleccionAuditores;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Auditoria;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.R;

import java.util.UUID;

public class ActivityBase extends AppCompatActivity implements FragmentManageAreas.Avisable,AdapterArea.EditaEliminable, FragmentSeleccionAuditores.Terminable, FragmentCrearCampania.Elegible, FragmentManageAuditores.Avisable, AdapterAuditores.EditaEliminable,FragmentManageCampanias.Creable {

   
    private ControllerDatos controllerDatos;
    private FragmentManageAreas fragmentManageAreas;
    private FragmentManageAuditores fragmentManageAuditores;
    private FragmentManager fragmentManager;

    //---COMIENZA ATRIBUTOS DEL DIALOGO DATOS AUDITOR---///
    private EditText editMail;
    private EditText editNombre;
    private EditText editPuesto;
    private TextInputLayout til1;
    private TextInputLayout til2;
    private TextInputLayout til3;
    //---FIN ATRIBUTOS DEL DIALOGO DATOS AUDITOR---///

    //---COMIENZA ATRIBUTOS DIALOGO AREA---//
    private EditText editNombreResponsable;
    //---FIN ATRIBUTOS DIALOGO AREA---//


    public static final String QUE_FRAGMENT_ABRO="QUE_FRAGMENT_ABRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_trabajo);
        controllerDatos= new ControllerDatos(this);

        Intent intent = getIntent();
        Bundle bundle= intent.getExtras();
        String queFragmentTengoQueAbrir=bundle.getString(QUE_FRAGMENT_ABRO);

        switch (queFragmentTengoQueAbrir) {
            case "manageAuditores":
                abrirFragmentManageAuditores();

                break;
            case "manageAreas":
                abrirFragmentManageAreas();

                break;
            case "manageCampanias":
                abrirFragmentManageCampanias();

            case "crearCampania":
                abrirFragmentCrearCampanias();


        }

    }

    private void abrirFragmentManageAreas() {
        FragmentManageAreas fragmentCargarArea = new FragmentManageAreas();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentCargarArea,"fragmentManageAreas");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Manage audit areas");

    }
    private void abrirFragmentSeleccionAreas() {
        FragmentSeleccionAreas fragmentCargarArea = new FragmentSeleccionAreas();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerFragment,fragmentCargarArea,"fragmentSeleccionAreas");
        fragmentTransaction.addToBackStack("seleccionArea");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Select audit area");

    }
    private void abrirFragmentManageAuditores() {
        FragmentManageAuditores fragmentManageAuditores = new FragmentManageAuditores();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentManageAuditores,"fragmentManageAuditores");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Manage audit team");
    }
    private void abrirFragmentSeleccionAuditores(Area unArea){
        FragmentSeleccionAuditores fragmentSeleccionAuditores = new FragmentSeleccionAuditores();

        Bundle bundle= new Bundle();
        bundle.putString(FragmentSeleccionAuditores.IDAREA,unArea.getIdArea());
        fragmentSeleccionAuditores.setArguments(bundle);

        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerFragment,fragmentSeleccionAuditores,"fragmentSeleccionAuditores");
        fragmentTransaction.addToBackStack("seleccionAuditor");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Select Auditor");

    }
    private void abrirFragmentManageCampanias() {
        FragmentManageCampanias fragmentManageCampanias = new FragmentManageCampanias();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentManageCampanias,"fragmentManageCampanias");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Manage Campaigns");
    }
    private void abrirFragmentCrearCampanias(){
        FragmentCrearCampania fragmentCrearCampania = new FragmentCrearCampania();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragmentCrearCampania,"fragmentCrearCampanias");
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Create Campaign");
    }






    @Override
    public void abrirFragmentCreacionCampanias() {
        abrirFragmentCrearCampanias();
    }

    @Override
    public void salirDeAca() {

    }

    @Override
    public void EliminarAuditor(Auditor unAuditor) {
        crearDialogoBorrarAuditor(unAuditor);
    }

    @Override
    public void editarAuditor(Auditor unAuditor) {
        crearDialogoEditarAuditor(unAuditor);
    }

    @Override
    public void EliminarArea(Area unArea) {
        CrearDialogoBorrarArea(unArea);
    }

    @Override
    public void editarArea(Area unArea) {
        crearDialogoEditarArea(unArea);
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
        fragmentManager = (FragmentManager) this.getSupportFragmentManager();
        fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");

        if (fragmentManageAreas != null && fragmentManageAreas.isVisible()) {
            try {
                controllerDatos.eliminarArea(unArea.getIdArea());
                fragmentManageAreas.updateAdapter();
                Snackbar.make(fragmentManageAreas.getView(),unArea.getNombreArea()+" was removed",Snackbar.LENGTH_LONG)
                        .show();

            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(fragmentManageAreas.getView(),"Area could not be removed",Snackbar.LENGTH_LONG)
                        .show();
            }

        } else {

        }

    }
    public void borrarDefinitivamente(final Auditor unAuditor){
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
        fragmentManageAuditores = (FragmentManageAuditores) fragmentManager.findFragmentByTag("fragmentManageAuditores");

        if (fragmentManageAuditores != null && fragmentManageAuditores.isVisible()) {
            try {
                controllerDatos.eliminarAuditor(unAuditor.getIdAuditor());
                fragmentManageAuditores.updateAdapter();
                Snackbar.make(fragmentManageAuditores.getView(),unAuditor.getNombreAuditor()+" was removed",Snackbar.LENGTH_LONG)
                        .show();

            } catch (Exception e) {
                e.printStackTrace();
                Snackbar.make(fragmentManageAuditores.getView(),"Area could not be removed",Snackbar.LENGTH_LONG)
                        .show();
            }

        } else {

        }

    }

    private void crearDialogoEditarAuditor(final Auditor unAuditor) {

            final MaterialDialog  dialogasd = new MaterialDialog.Builder(this)
                    .title(getString(R.string.tituloDarNombreAuditor))
                    .customView(R.layout.dialog_layout_auditores,true)
                    .positiveText(getString(R.string.save))
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {


                            if (editNombre.getText()==null||editNombre.getText().toString().isEmpty()){

                            }
                            else{
                                unAuditor.setNombreAuditor(editNombre.getText().toString());
                            }
                            if (editPuesto.getText()==null||editPuesto.getText().toString().isEmpty()){

                            }
                            else{
                                unAuditor.setPuesto(editPuesto.getText().toString());
                            }
                            fragmentManager = getSupportFragmentManager();
                            fragmentManageAuditores = (FragmentManageAuditores) fragmentManager.findFragmentByTag("fragmentManageAuditores");

                            if (fragmentManageAuditores != null && fragmentManageAuditores.isVisible()) {

                                try {

                                    controllerDatos.actualizarAuditor(unAuditor);
                                    fragmentManageAuditores.updateAdapter();
                                    Snackbar.make(fragmentManageAuditores.getView(), unAuditor.getNombreAuditor() + " data was updated", Snackbar.LENGTH_LONG)
                                            .show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Snackbar.make(fragmentManageAuditores.getView(), "data could not be updated, try again.", Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            }

                        }
                    })
                    .build();
            dialogasd.show();

            editMail=dialogasd.getCustomView().findViewById(R.id.ET_mailAuditor);
            editMail.setVisibility(View.GONE);

            editNombre  = dialogasd.getCustomView().findViewById(R.id.ET_nombreAuditor);
            editNombre.setText(unAuditor.getNombreAuditor());

            editPuesto  = dialogasd.getCustomView().findViewById(R.id.ET_nombrePuesto);
            editPuesto.setText(unAuditor.getPuesto());



    }

    private void crearDialogoEditarArea(final Area unArea) {

        final MaterialDialog  dialogasd = new MaterialDialog.Builder(this)
                .title(getString(R.string.tituloDarNombreArea))
                .customView(R.layout.dialog_layout_areas,true)
                .positiveText(getString(R.string.save))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {


                        if (editNombre.getText()==null||editNombre.getText().toString().isEmpty()){
                        }
                        else{
                            unArea.setNombreArea(editNombre.getText().toString());
                        }
                        if (editNombreResponsable.getText()==null||editNombreResponsable.getText().toString().isEmpty()){
                        }
                        else{
                            unArea.setNombreResponsableArea(editNombreResponsable.getText().toString());
                        }
                        if (editMail==null || editMail.getText().toString().isEmpty()){
                        }
                        else{
                            unArea.setMailResponsableArea(editMail.getText().toString());
                        }

                        fragmentManager = getSupportFragmentManager();
                        fragmentManageAreas = (FragmentManageAreas) fragmentManager.findFragmentByTag("fragmentManageAreas");

                        if (fragmentManageAreas != null && fragmentManageAreas.isVisible()) {

                            try {

                                controllerDatos.actualizarArea(unArea);
                                fragmentManageAreas.updateAdapter();
                                Snackbar.make(fragmentManageAreas.getView(), unArea.getNombreArea() + " was updated", Snackbar.LENGTH_LONG)
                                        .show();

                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(ActivityBase.this, e.toString(), Toast.LENGTH_LONG).show();
                                Snackbar.make(fragmentManageAreas.getView(), "data could not be updated, try again.", Snackbar.LENGTH_LONG)
                                        .show();
                            }
                        }

                    }
                })
                .build();
        dialogasd.show();

        editMail=dialogasd.getCustomView().findViewById(R.id.ET_mailResponsableArea);
        editMail.setText(unArea.getMailResponsableArea());


        editNombre  = dialogasd.getCustomView().findViewById(R.id.ET_nombreArea);
        editNombre.setText(unArea.getNombreArea());

        editNombreResponsable  = dialogasd.getCustomView().findViewById(R.id.ET_nombreResponsableArea);
        editNombreResponsable.setText(unArea.getNombreResponsableArea());

    }

    public void crearDialogoBorrarAuditor(final Auditor unAuditor){
        new MaterialDialog.Builder(this)
                .title("Delete Selected Area")
                .content("The user: " + unAuditor.getMailUsuario() +"\n"+ "will be permanently deleted."+"\n"+"Do you wisht to continue?")
                .positiveText("Delete")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        borrarDefinitivamente(unAuditor);
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

    @Override
    public void armarAuditoria( Area unArea,  Auditor unAuditor) {
        FragmentManager fm = this.getSupportFragmentManager();
        fm.popBackStack("seleccionArea",fm.POP_BACK_STACK_INCLUSIVE);
        fm.popBackStack("seleccionAuditor",fm.POP_BACK_STACK_INCLUSIVE);

        Auditoria unaAuditoria = new Auditoria();
        unaAuditoria.setIdAuditoria("audit_"+ UUID.randomUUID());
        unaAuditoria.setAuditor(unAuditor);
        unaAuditoria.setAreaAuditada(unArea);
        unaAuditoria.setListaEses(controllerDatos.traerlistaEses());



        Toast.makeText(this, "armar auditoria", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void elegirArea() {
        abrirFragmentSeleccionAreas();
    }



    @Override
    public void elegirAuditor(Area unArea) {
        abrirFragmentSeleccionAuditores(unArea);
    }
}
