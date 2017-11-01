package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by elmar on 11/8/2017.
 */

public class Auditoria {


    private String idAuditoria;
    private Area areaAuditada;
    private Auditor auditor;
    private Double puntajeFinal;
    private String fechaAuditoria;
    private List<Ese> listaEses;


    public Auditoria() {
        this.listaEses = new ArrayList<>();
        this.areaAuditada = new Area();
        this.auditor = new Auditor();
    }

    public String getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(String idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Area getAreaAuditada() {
        return areaAuditada;
    }

    public void setAreaAuditada(Area areaAuditada) {
        this.areaAuditada = areaAuditada;
    }

    public Auditor getAuditor() {
        return auditor;
    }

    public void setAuditor(Auditor auditor) {
        this.auditor = auditor;
    }

    public Double getPuntajeFinal() {
        return puntajeFinal;
    }

    public void setPuntajeFinal(Double puntajeFinal) {
        this.puntajeFinal = puntajeFinal;
    }

    public String getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(String fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public List<Ese> getListaEses() {
        return listaEses;
    }

    public void setListaEses(List<Ese> listaEses) {
        this.listaEses = listaEses;
    }

    public void agregarEse(Ese unEse){
        this.listaEses.add(unEse);
    }
}
