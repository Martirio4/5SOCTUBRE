package com.demo.nomad.nomad5s.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.demo.nomad.nomad5s.Fragments.FragmentManageEse;
import com.demo.nomad.nomad5s.Model.Ese;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 31/5/2017.
 */

public class AdapterPagerEse extends FragmentStatePagerAdapter {

    //EL ADAPTER NECESITA SIEMPRE UNA LISTA DE FRAGMENTS PARA MOSTRAR
    private List<Fragment> listaFragments;
    private List<Ese> listaEses = new ArrayList<>();


    public AdapterPagerEse(FragmentManager fm) {
        super(fm);


        //INICIALIZO LA LISTA DE FRAGMENT
        listaFragments = new ArrayList<>();

        //LE CARGO LOS FRAGMENTS QUE QUIERO. UTILIZO LA LISTA DE PELICULAS Y SERIES PARA CREAR LOS FRAGMENTS.

        for (Ese unaEse : listaEses) {
            listaFragments.add(FragmentManageEse.crearFragmentEse(unaEse));
        }

        //LE AVISO AL ADAPTER QUE CAMBIO SU LISTA DE FRAGMENTS.
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }

    public void setListaEses(List<Ese> listaEses) {
        this.listaEses = listaEses;
        for (Ese unCriterio : listaEses) {
            listaFragments.add(FragmentManageEse.crearFragmentEse(unCriterio));
        }
        notifyDataSetChanged();
    }

    public void addListaCriterios(List<Ese> listaCriterios) {
        this.listaEses.addAll(listaCriterios);
        for (Ese unEse : listaCriterios) {
            listaFragments.add(FragmentManageEse.crearFragmentEse(unEse));
        }
        notifyDataSetChanged();
    }


}
