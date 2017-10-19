package com.demo.nomad.nomad5s.Model;



/**
 * Created by elmar on 23/8/2017.
 */

public class Auditor {


    private String idAuditor;
    private String nombreAuditor;
    private Integer cantidadAuditoriasRealizada;
    private Foto fotoAuditor;

    public Auditor() {
    }

    public String getIdAuditor() {
        return idAuditor;
    }

    public void setIdAuditor(String idAuditor) {
        this.idAuditor = idAuditor;
    }

    public String getNombreAuditor() {
        return nombreAuditor;
    }

    public void setNombreAuditor(String nombreAuditor) {
        this.nombreAuditor = nombreAuditor;
    }

    public Integer getCantidadAuditoriasRealizada() {
        return cantidadAuditoriasRealizada;
    }

    public void setCantidadAuditoriasRealizada(Integer cantidadAuditoriasRealizada) {
        this.cantidadAuditoriasRealizada = cantidadAuditoriasRealizada;
    }

    public Foto getFotoAuditor() {
        return fotoAuditor;
    }

    public void setFotoAuditor(Foto fotoAuditor) {
        this.fotoAuditor = fotoAuditor;
    }
}