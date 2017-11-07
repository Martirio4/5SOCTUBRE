package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elmar on 2/10/2017.
 */

public class Criterio {


    private String idCriterio;
    private String textoCriterio;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String opcion5;
    private Integer puntajeElegido;
    private List<Foto>listaFotosCriterio;


    //CONSTRUCTOR

    //CONSTRUCTOR VACIO

    public Criterio() {
        this.listaFotosCriterio= new ArrayList<>();
    }

    //GETTER Y SETTER

    public String getIdCriterio() {
        return idCriterio;
    }

    public void setIdCriterio(String idCriterio) {
        this.idCriterio = idCriterio;
    }

    public String getTextoCriterio() {
        return textoCriterio;
    }

    public void setTextoCriterio(String textoCriterio) {
        this.textoCriterio = textoCriterio;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getOpcion5() {
        return opcion5;
    }

    public void setOpcion5(String opcion5) {
        this.opcion5 = opcion5;
    }

    public Integer getPuntajeElegido() {
        return puntajeElegido;
    }

    public void setPuntajeElegido(Integer puntajeElegido) {
        this.puntajeElegido = puntajeElegido;
    }

    public List<Foto> getListaFotosCriterio() {
        return listaFotosCriterio;
    }

    public void setListaFotosCriterio(List<Foto> listaFotosCriterio) {
        this.listaFotosCriterio = listaFotosCriterio;
    }
    public void agregarFoto(Foto unaFoto){
        this.listaFotosCriterio.add(unaFoto);
    }
}
