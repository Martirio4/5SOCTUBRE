package com.demo.nomad.nomad5s.Model;



/**
 * Created by elmar on 23/8/2017.
 */

public class Auditor {


    private String idAuditor;
    private String nombreAuditor;
    private String mailUsuario;
    private String puesto;
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

    public String getMailUsuario() {
        return mailUsuario;
    }

    public void setMailUsuario(String mailUsuario) {
        this.mailUsuario = mailUsuario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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
