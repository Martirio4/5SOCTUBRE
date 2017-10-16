package com.demo.nomad.nomad5s.Model;

/**
 * Created by elmar on 12/8/2017.
 */

public class Area{

    private String idArea;
    private String NombreArea;
    private Foto fotoArea;

    public Area() {
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return NombreArea;
    }

    public void setNombreArea(String nombreArea) {
        NombreArea = nombreArea;
    }

    public Foto getFotoArea() {
        return fotoArea;
    }

    public void setFotoArea(Foto fotoArea) {
        this.fotoArea = fotoArea;
    }
}
