package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elmar on 31/10/2017.
 */

public class Ese {
    private String idEse;
    private Double puntajeEse;
    private List<Criterio> listaCriterios;
    private String nombreEse;

    public Ese() {
        this.listaCriterios=new ArrayList<>();
    }

    //GETTER AND SETTER

    public String getIdEse() {
        return idEse;
    }

    public void setIdEse(String idEse) {
        this.idEse = idEse;
    }

    public Double getPuntajeEse() {
        return puntajeEse;
    }

    public void setPuntajeEse(Double puntajeEse) {
        this.puntajeEse = puntajeEse;
    }

    public List<Criterio> getListaCriterios() {
        return listaCriterios;
    }

    public void setListaCriterios(List<Criterio> listaCriterios) {
        this.listaCriterios = listaCriterios;
    }

    public String getNombreEse() {
        return nombreEse;
    }

    public void setNombreEse(String nombreEse) {
        this.nombreEse = nombreEse;
    }
    public void agregarCriterio(Criterio unCriterio){
        this.listaCriterios.add(unCriterio);
    }
}
