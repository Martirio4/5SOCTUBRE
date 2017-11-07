package com.demo.nomad.nomad5s.Fragments;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.demo.nomad.nomad5s.Adapter.AdapterArea;
import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCrearCampania extends Fragment {

    private TextInputLayout inputFechaInicio;
    private TextInputLayout inputFechaFin;

    private EditText editFechaInicio;
    private EditText editFechaFin;

    private FloatingActionMenu menuCrearCampania;
    private FloatingActionButton fabGuardarCampania;
    private FloatingActionButton fabAgregarAuditoria;

    private RecyclerView recyclerArea;
    private AdapterArea adapterArea;

    private ControllerDatos controllerDatos;

    private Elegible elegible;

    public FragmentCrearCampania() {
        // Required empty public constructor
    }

    public interface Elegible{
        void elegirArea();
        void elegirAuditor(Area unArea);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_crear_campania, container, false);
        controllerDatos= new ControllerDatos(getContext());

        final Campania nuevaCampania= new Campania();
        nuevaCampania.setIdCampania("campania_"+UUID.randomUUID());

        editFechaInicio =(EditText) view.findViewById(R.id.editFechaInicioEnCampania);

        editFechaFin =(EditText) view.findViewById(R.id.editFechaFinEnCampania);
        menuCrearCampania=(FloatingActionMenu) view.findViewById(R.id.menuAgregarCampania);
        menuCrearCampania.setMenuButtonColorPressed(ContextCompat.getColor(getActivity(),R.color.verde0));

        editFechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoFechaEnEdit(editFechaInicio);
            }
        });

        editFechaInicio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nuevaCampania.setFechaInicio(editFechaInicio.getText().toString());
                Toast.makeText(getContext(), "hola", Toast.LENGTH_SHORT).show();
            }
        });

        editFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoFechaEnEdit(editFechaFin);
            }
        });

        editFechaFin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                nuevaCampania.setFechaLimite(editFechaFin.getText().toString());
                Toast.makeText(getContext(), editFechaFin.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

               fabAgregarAuditoria = new FloatingActionButton(getActivity());

        fabAgregarAuditoria.setColorPressed(ContextCompat.getColor(getActivity(),R.color.verde4));
        fabAgregarAuditoria.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabAgregarAuditoria.setColorNormal(ContextCompat.getColor(getActivity(),R.color.verde0));
        fabAgregarAuditoria.setLabelText(getString(R.string.addAuditoria));
        fabAgregarAuditoria.setImageResource(R.drawable.ic_note_add_black_24dp);
        menuCrearCampania.addMenuButton(fabAgregarAuditoria);


        fabAgregarAuditoria.setLabelColors(ContextCompat.getColor(getActivity(), R.color.verde1),
                ContextCompat.getColor(getActivity(), R.color.light_grey),
                ContextCompat.getColor(getActivity(), R.color.white_transparent));
        fabAgregarAuditoria.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));


        fabAgregarAuditoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            elegible.elegirArea();


                //ABRIR FRAGMENT SELECCION AREAS
                /*ONCLICK DE ESE RECYCLER ABRIR SELECCION DE AUDITORES
                        ONCLICK DE ESE RECYCLER CREA AUDITORIA
                        EL PROBLEMA VA A SER EL CANCEL DURANTE EL PROCESO
                        O PUEDO PONER UN RECYCLER EN ESTE FRAGMENT
                */
                menuCrearCampania.close(true);
            }
        });

        fabGuardarCampania = new FloatingActionButton(getActivity());

        fabGuardarCampania.setButtonSize(FloatingActionButton.SIZE_MINI);
        fabGuardarCampania.setColorPressed(ContextCompat.getColor(getActivity(),R.color.verde4));
        fabGuardarCampania.setColorNormal(ContextCompat.getColor(getActivity(),R.color.verde0));
        fabGuardarCampania.setLabelText(getString(R.string.saveCampaign));
        fabGuardarCampania.setImageResource(R.drawable.ic_save_black_24dp);
        menuCrearCampania.addMenuButton(fabGuardarCampania);


        fabGuardarCampania.setLabelColors(ContextCompat.getColor(getActivity(), R.color.verde1),
                ContextCompat.getColor(getActivity(), R.color.light_grey),
                ContextCompat.getColor(getActivity(), R.color.white_transparent));
        fabGuardarCampania.setLabelTextColor(ContextCompat.getColor(getActivity(), R.color.black));


        fabGuardarCampania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ABRIR FRAGMENT SELECCION AREAS
                /*ONCLICK DE ESE RECYCLER ABRIR SELECCION DE AUDITORES
                        ONCLICK DE ESE RECYCLER CREA AUDITORIA
                        EL PROBLEMA VA A SER EL CANCEL DURANTE EL PROCESO
                        O PUEDO PONER UN RECYCLER EN ESTE FRAGMENT
                */
                menuCrearCampania.close(true);
            }
        });






        return view;
    }

    private void mostrarDialogoFechaEnEdit(final EditText unEdit) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                unEdit.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.elegible = (Elegible) context;
    }
    public String getDeadline(){
        return editFechaFin.getText().toString();
    }
}
