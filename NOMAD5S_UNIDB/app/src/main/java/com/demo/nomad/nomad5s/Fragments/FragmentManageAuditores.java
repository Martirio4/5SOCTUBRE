package com.demo.nomad.nomad5s.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterAuditores;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Foto;

import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import id.zelory.compressor.Compressor;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManageAuditores extends Fragment {

    private ControllerDatos controllerAuditores;
    private RecyclerView recyclerAreas;
    private AdapterAuditores adapterAuditores;
    private LinearLayoutManager layoutManager;
    private File fotoOriginal;
    private File fotoComprimida;
    private FloatingActionButton fabAgregarArea;
    private Avisable unAvisable;
    private TextView textView;

    //---COMIENZA ATRIBUTOS DEL DIALOGO DATOS AUDITOR---///
    private EditText editMail;
    private EditText editNombre;
    private EditText editPuesto;
    private TextInputLayout til1;
    private TextInputLayout til2;
    private TextInputLayout til3;
    //---FIN ATRIBUTOS DEL DIALOGO DATOS AUDITOR---///


    public FragmentManageAuditores() {
        // Required empty public constructor
    }

    public interface Avisable{
        public void salirDeAca();
    }

    public void updateAdapter() {
        adapterAuditores.setListaAuditoresOriginales(controllerAuditores.traerListaAuditores());
        adapterAuditores.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_auditores, container, false);
        controllerAuditores=new ControllerDatos(getContext());

        //PEDIR LISTADO DE AREAS
        List<Auditor>listaAuditores;
        listaAuditores =controllerAuditores.traerListaAuditores();
        recyclerAreas= view.findViewById(R.id.recyclerArea);
        adapterAuditores= new AdapterAuditores();
        adapterAuditores.setContext(getContext());
        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerAreas.setLayoutManager(layoutManager);
        adapterAuditores.setListaAuditoresOriginales(listaAuditores);
        recyclerAreas.setAdapter(adapterAuditores);

        fabAgregarArea = view.findViewById(R.id.agregarArea);
        fabAgregarArea.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_person_add_white_24dp));

        fabAgregarArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(FragmentManageAuditores.this, "Select image", 1);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.unAvisable=(Avisable) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source, int type) {

                if (type == 1) {
                    fotoOriginal = imageFile;
                    existeDirectorioImagenesAreas();
                    try {
                        fotoComprimida = new Compressor(getContext())
                                .setMaxWidth(640)
                                .setMaxHeight(480)
                                .setQuality(75)
                                .setCompressFormat(Bitmap.CompressFormat.WEBP)
                                .setDestinationDirectoryPath(fotoOriginal.getParent() + File.separator + "images"+ File.separator + "AUDITORES")
                                .compressToFile(fotoOriginal);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Foto unaFoto = new Foto();
                    unaFoto.setRutaFotoDB(fotoComprimida.getAbsolutePath());
                    unaFoto.setIdFoto("fotoAuditor_"+ UUID.randomUUID());
                    Boolean seBorro = imageFile.delete();
                    if (seBorro) {
                     //   Toast.makeText(getContext(), R.string.seEliminoFoto, Toast.LENGTH_SHORT).show();

                    } else {
                       Snackbar.make(recyclerAreas,getString(R.string.noSeEliminoFoto),Snackbar.LENGTH_SHORT)
                               .show();
                    }
                    crearDialogoDatosAuditor(unaFoto);


                }

            }

            @Override
            public void onCanceled(EasyImage.ImageSource source, int type) {
                super.onCanceled(source, type);
                //Cancel handling, you might wanna remove taken photo if it was canceled
                if (source == EasyImage.ImageSource.CAMERA) {
                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(getActivity());
                    if (photoFile != null) photoFile.delete();
                }
            }
        });
    }
    public void existeDirectorioImagenesAreas() {
        Boolean sePudo = true;
        File dir = new File(fotoOriginal.getParent() + File.separator + "images"+ File.separator + "AUDITORES");
        if (!dir.exists() || !dir.isDirectory()) {
            sePudo = dir.mkdirs();
        }
        if (sePudo) {

        } else {
            Toast.makeText(getContext(), "Target directory was not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void crearDialogoDatosAuditor(final Foto unaFotona){

       final MaterialDialog  dialogasd = new MaterialDialog.Builder(getContext())
                .title(getString(R.string.tituloDarNombreAuditor))
                .customView(R.layout.dialog_layout_auditores,true)
                .positiveText(getString(R.string.save))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {

                        Auditor unAuditor = new Auditor();
                        unAuditor.setIdAuditor(editMail.getText().toString());
                        unAuditor.setFotoAuditor(unaFotona);
                        if (editNombre.getText()==null||editNombre.getText().toString().isEmpty()){
                            editNombre.setText("");
                        }
                        if (editPuesto.getText()==null||editPuesto.getText().toString().isEmpty()){
                            editPuesto.setText("");
                        }
                        unAuditor.setNombreAuditor(editNombre.getText().toString());
                        unAuditor.setPuesto(editPuesto.getText().toString());
                        unAuditor.setMailUsuario(editMail.getText().toString());
                        unAuditor.setCantidadAuditoriasRealizada(0);


                        try {
                            controllerAuditores.guardarAuditor(unAuditor);
                            updateAdapter();
                            // dialogoExito(unArea);
                            Snackbar.make(recyclerAreas,unAuditor.getNombreAuditor()+" account was succesfully created",Snackbar.LENGTH_SHORT)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar.make(recyclerAreas,"Auditor was not saved. Please try again",Snackbar.LENGTH_SHORT)
                                    .show();
                        }

                    }
                })
                .build();
       dialogasd.show();

        dialogasd.getActionButton(DialogAction.POSITIVE).setEnabled(false);

        editMail = dialogasd.getCustomView().findViewById(R.id.ET_mailAuditor);
        editNombre  = dialogasd.getCustomView().findViewById(R.id.ET_nombreAuditor);
        editPuesto  = dialogasd.getCustomView().findViewById(R.id.ET_nombrePuesto);
        til1= dialogasd.getCustomView().findViewById(R.id.TIL_mailAuditor);

        editMail.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (isEmailValid(editMail.getText().toString())) {
                til1.setError("");
                dialogasd.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }
        }
        });

        editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editMail==null|| editMail.getText().toString().isEmpty()){
                    til1.setError(getString(R.string.mailAuditorIncompleto));
                }
            }
        });

        editPuesto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editMail==null|| editMail.getText().toString().isEmpty()){
                    til1.setError(getString(R.string.mailAuditorIncompleto));
                }
            }
        });


    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
