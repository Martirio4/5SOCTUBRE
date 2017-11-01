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
import com.demo.nomad.nomad5s.Adapter.AdapterArea;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import id.zelory.compressor.Compressor;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManageAreas extends Fragment {

    private ControllerDatos controllerAreas;
    private List<Area> listaAreas;
    private RecyclerView recyclerAreas;
    private AdapterArea adapterArea;
    private LinearLayoutManager layoutManager;
    private File fotoOriginal;
    private File fotoComprimida;
    private FloatingActionButton fabAgregarArea;
    private Avisable unAvisable;
    private TextView textView;

    private EditText editNombre;
    private EditText editResponsable;
    private TextInputLayout til1;
    private TextInputLayout til2;



    public FragmentManageAreas() {
        // Required empty public constructor
    }

    public interface Avisable{
        public void salirDeAca();
    }

    public void updateAdapter() {
        adapterArea.setListaAreasOriginales(controllerAreas.traerListaAreas());
        adapterArea.notifyDataSetChanged();
        /*
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Area> result3 = realm.where(Area.class)
                .findAll();
        listaAreas=new List<>();
        listaAreas.addAll(result3);
        adapterArea.setListaAreasOriginales(listaAreas);
        adapterArea.notifyDataSetChanged();
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_auditores, container, false);
        controllerAreas=new ControllerDatos(getContext());
        /*
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Area> result2 = realm.where(Area.class)
                .findAll();
        */
        //PEDIR LISTADO DE AREAS
        listaAreas=new ArrayList<>();
        listaAreas=controllerAreas.traerListaAreas();
        recyclerAreas= view.findViewById(R.id.recyclerArea);
        adapterArea= new AdapterArea();
        adapterArea.setContext(getContext());
        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerAreas.setLayoutManager(layoutManager);
        adapterArea.setListaAreasOriginales(listaAreas);
        recyclerAreas.setAdapter(adapterArea);

        fabAgregarArea =(FloatingActionButton) view.findViewById(R.id.agregarArea);
        fabAgregarArea.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_note_add_white_24dp));

        fabAgregarArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EasyImage.openChooserWithGallery(FragmentManageAreas.this, "Select image", 1);
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
                                .setDestinationDirectoryPath(fotoOriginal.getParent() + File.separator + "images"+ File.separator + "AREAS")
                                .compressToFile(fotoOriginal);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Foto unaFoto = new Foto();
                    unaFoto.setIdFoto("fotoArea_"+ UUID.randomUUID());
                    unaFoto.setRutaFotoDB(fotoComprimida.getAbsolutePath());
                    Boolean seBorro = imageFile.delete();
                    if (seBorro) {
                     //   Toast.makeText(getContext(), R.string.seEliminoFoto, Toast.LENGTH_SHORT).show();

                    } else {
                       // Toast.makeText(getContext(), R.string.noSeEliminoFoto, Toast.LENGTH_SHORT).show();
                    }
                    crearDialogoDatosArea(unaFoto);


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
        File dir = new File(fotoOriginal.getParent() + File.separator + "images"+ File.separator + "AREAS");
        if (!dir.exists() || !dir.isDirectory()) {
            sePudo = dir.mkdirs();
        }
        if (sePudo) {

        } else {
            Toast.makeText(getContext(), "Target directory was not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void crearDialogoDatosArea(final Foto unaFoto){

        final MaterialDialog  dialogasd = new MaterialDialog.Builder(getContext())
                .title(getString(R.string.tituloDarNombreArea))
                .customView(R.layout.dialog_layout_areas,true)
                .positiveText(getString(R.string.save))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull final MaterialDialog dialog, @NonNull DialogAction which) {

                        Area unArea = new Area();
                        unArea.setIdArea(editNombre.getText().toString());
                        unArea.setFotoArea(unaFoto);
                        if (editResponsable.getText()==null||editNombre.getText().toString().isEmpty()){
                            editNombre.setText("");
                        }
                        unArea.setNombreArea(editNombre.getText().toString());
                        unArea.setResponsableArea(editResponsable.getText().toString());

                        try {
                            controllerAreas.guardarArea(unArea);
                            updateAdapter();
                            // dialogoExito(unArea);
                            Snackbar.make(recyclerAreas,unArea.getNombreArea()+" account was succesfully created",Snackbar.LENGTH_SHORT)
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


        editNombre  = dialogasd.getCustomView().findViewById(R.id.ET_nombreArea);
        editResponsable  = dialogasd.getCustomView().findViewById(R.id.ET_responsableArea);
        til1= dialogasd.getCustomView().findViewById(R.id.TIL_nombreArea);
        til2=dialogasd.getCustomView().findViewById(R.id.TIL_responsableArea);

        editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                til1.setError("");
                dialogasd.getActionButton(DialogAction.POSITIVE).setEnabled(true);
            }
        });

        editResponsable.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (!editResponsable.getText().toString().isEmpty()){
                    if (isEmailValid(editResponsable.getText().toString())){
                        til2.setError("");
                    }
                    else{
                        til2.setError(getString(R.string.mailInvalido));
                    }
                }
                else{
                    til2.setError("");
                }


            }
        });
        editNombre.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (editNombre.getText().toString().isEmpty()){
                    til1.setError(getString(R.string.nombreAreaIncompleto));
                }
                else{
                    til1.setError("");
                }

            }
        });


    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

//    SE COMENTO LA LLAMADA A ESTE METODO, QUEDA OBSOLETO POR UX, SE REEMPLAZO POR SNACKBAR
    public void dialogoExito(Area unArea) {

        new MaterialDialog.Builder(getContext())
                .title("New area successfully created")
                .content("The area: " + unArea.getNombreArea() +"\n"+ "has been succesfully added to the system")
                .positiveText("Go back")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                   updateAdapter();
                    }
                })
                .negativeText("Add new area")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        updateAdapter();
                        EasyImage.openChooserWithGallery(FragmentManageAreas.this, "Select image", 1);
                    }
                })
                .show();



    }


}
