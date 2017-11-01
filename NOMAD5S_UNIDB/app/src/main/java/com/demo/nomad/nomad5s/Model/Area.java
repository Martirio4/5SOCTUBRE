package com.demo.nomad.nomad5s.Model;

/**
 * Created by elmar on 12/8/2017.
 */

public class Area{

    private String idArea;
    private String nombreArea;
    private String responsableArea;
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
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getResponsableArea() {
        return responsableArea;
    }

    public void setResponsableArea(String responsableArea) {
        this.responsableArea = responsableArea;
    }

    public Foto getFotoArea() {
        return fotoArea;
    }

    public void setFotoArea(Foto fotoArea) {
        this.fotoArea = fotoArea;
    }
}
