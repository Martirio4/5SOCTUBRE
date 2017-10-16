package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;


public class SubItem {
    private String idSubitem;
    private Criterio criteriosSubitem;
    private Integer puntuacion;
    private List<Foto> listaFotos;

    //CONTRUCTOR
    public SubItem(String idSubitem, Criterio criteriosSubitem, Integer puntuacion, List<Foto> listaFotos) {
        this.idSubitem = idSubitem;
        this.criteriosSubitem = criteriosSubitem;
        this.puntuacion = puntuacion;
        this.listaFotos = listaFotos;
    }
    //CONSTRUCTOR VACIO
    public SubItem() {
        this.listaFotos=new ArrayList<>();
    }
    //GETTER AND SETTER
    public String getIdSubitem() {
        return idSubitem;
    }

    public void setIdSubitem(String idSubitem) {
        this.idSubitem = idSubitem;
    }

    public Criterio getCriteriosSubitem() {
        return criteriosSubitem;
    }

    public void setCriteriosSubitem(Criterio criteriosSubitem) {
        this.criteriosSubitem = criteriosSubitem;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<Foto> getListaFotos() {
        return listaFotos;
    }

    public void setListaFotos(List<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    public void agregarFoto(Foto unaFoto){
        this.listaFotos.add(unaFoto);
    }
}
