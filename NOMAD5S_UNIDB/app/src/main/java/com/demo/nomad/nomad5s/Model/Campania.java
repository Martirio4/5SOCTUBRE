package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elmar on 2/10/2017.
 */

public class Campania {

    private String idCampania;
    private String nombreCampania;
    private String fechaInicio;
    private String fechaLimite;
    private List<Auditoria>auditoriasCampania;

//CONSTRUCTOR
    public Campania(String idCampania, String fechaLimite, List<Auditoria> auditoriasCampania) {
        this.idCampania = idCampania;
        this.fechaLimite = fechaLimite;
        this.auditoriasCampania = auditoriasCampania;
    }
//CONSTRUCTOR VACIO
    public Campania() {
        this.auditoriasCampania=new ArrayList<>();
    }

//GETTER AND SETTER

    public String getIdCampania() {
        return idCampania;
    }

    public void setIdCampania(String idCampania) {
        this.idCampania = idCampania;
    }

    public String getNombreCampania() {
        return nombreCampania;
    }

    public void setNombreCampania(String nombreCampania) {
        this.nombreCampania = nombreCampania;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public List<Auditoria> getAuditoriasCampania() {
        return auditoriasCampania;
    }

    public void setAuditoriasCampania(List<Auditoria> auditoriasCampania) {
        this.auditoriasCampania = auditoriasCampania;
    }

    public void agregarAuditoria(Auditoria unAudit){
        this.auditoriasCampania.add(unAudit);
    }
}
