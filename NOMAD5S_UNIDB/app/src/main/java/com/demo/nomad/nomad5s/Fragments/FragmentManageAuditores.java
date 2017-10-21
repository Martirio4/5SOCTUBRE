package com.demo.nomad.nomad5s.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterAuditores;
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
public class FragmentManageAuditores extends Fragment {

    private ControllerDatos controllerAuditores;
    private List<Auditor> listaAreas;
    private RecyclerView recyclerAreas;
    private AdapterAuditores adapterAuditores;
    private LinearLayoutManager layoutManager;
    private File fotoOriginal;
    private File fotoComprimida;
    private FloatingActionButton fabAgregarArea;
    private Avisable unAvisable;
    private TextView textView;


    public FragmentManageAuditores() {
        // Required empty public constructor
    }

    public interface Avisable{
        public void salirDeAca();
    }

    public void updateAdapter() {
        adapterAuditores.setListaAuditoresOriginales(controllerAuditores.traerListaAuditores());
        adapterAuditores.notifyDataSetChanged();
        /*
        Realm realm=Realm.getDefaultInstance();
        RealmResults<Area> result3 = realm.where(Area.class)
                .findAll();
        listaAreas=new List<>();
        listaAreas.addAll(result3);
        adapterAuditores.setListaAreasOriginales(listaAreas);
        adapterAuditores.notifyDataSetChanged();
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_manage_auditores, container, false);
        controllerAuditores=new ControllerDatos(getContext());
        /*
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Area> result2 = realm.where(Area.class)
                .findAll();
        */
        //PEDIR LISTADO DE AREAS
        listaAreas=new ArrayList<>();
        listaAreas=controllerAuditores.traerListaAuditores();
        recyclerAreas= view.findViewById(R.id.recyclerArea);
        adapterAuditores= new AdapterAuditores();
        adapterAuditores.setContext(getContext());
        layoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerAreas.setLayoutManager(layoutManager);
        adapterAuditores.setListaAuditoresOriginales(listaAreas);
        recyclerAreas.setAdapter(adapterAuditores);

        fabAgregarArea =(FloatingActionButton) view.findViewById(R.id.agregarArea);

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
                    unaFoto.setRutaFoto(fotoComprimida.getAbsolutePath());
                    Boolean seBorro = imageFile.delete();
                    if (seBorro) {
                     //   Toast.makeText(getContext(), R.string.seEliminoFoto, Toast.LENGTH_SHORT).show();

                    } else {
                       // Toast.makeText(getContext(), R.string.noSeEliminoFoto, Toast.LENGTH_SHORT).show();
                    }
                    crearDialogoNombreArea(unaFoto);


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

    public void crearDialogoNombreArea(final Foto unaFoto){

        new MaterialDialog.Builder(getContext())
                .title("New Auditor")
                .content("Name for the new Auditor")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input("Auditor name","", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {

                        final Auditor unAuditor = new Auditor();
                        unAuditor.setNombreAuditor(input.toString());
                        unAuditor.setFotoAuditor(unaFoto);
                        unAuditor.setIdAuditor("AUDITOR" + UUID.randomUUID());
                        /*
                            //guardo nueva area en Realm
                            Realm realm = Realm.getDefaultInstance();
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    Area realmArea = realm.copyToRealm(unArea);
                                }
                            });
                        */
                        try {
                            controllerAuditores.guardarAuditor(unAuditor);
                            updateAdapter();
                           // dialogoExito(unArea);
                            Snackbar.make(getView(),unAuditor.getNombreAuditor()+" account was succesfully created",Snackbar.LENGTH_SHORT)
                                    .show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Snackbar.make(getView(),"Auditor was not saved. Please try again",Snackbar.LENGTH_SHORT)
                                    .show();
                        }

                    }
                }).show();

    }

//    SE COMENTO LA LLAMADA A ESTE METODO, QUEDA OBSOLETO POR UX, SE REEMPLAZO POR SNACKBAR
    public void dialogoExito(Area unArea) {

        new MaterialDialog.Builder(getContext())
                .title("New area successfully created")
                .contentColor(ContextCompat.getColor(getContext(), R.color.primary_text))
                .titleColor(ContextCompat.getColor(getContext(), R.color.tile4))
                .backgroundColor(ContextCompat.getColor(getContext(), R.color.tile1))
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
                        EasyImage.openChooserWithGallery(FragmentManageAuditores.this, "Select image", 1);
                    }
                })
                .show();



    }


}
