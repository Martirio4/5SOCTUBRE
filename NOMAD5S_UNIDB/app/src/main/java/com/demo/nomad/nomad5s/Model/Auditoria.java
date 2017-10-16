package com.demo.nomad.nomad5s.Model;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by elmar on 11/8/2017.
 */

public class Auditoria {

    private String idCampania;
    private String idAuditoria;
    private List<SubItem> subItems;
    private String fechaAuditoria;
    private String deadLine;
    private String Auditor;
    private Area areaAuditada;
    private Double puntajeFinal;



    public Auditoria() {
        this.subItems=new ArrayList<>();
        this.areaAuditada=new Area();
    }


    public String getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(String idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public List<SubItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<SubItem> subItems) {
        this.subItems = subItems;
    }

    public String getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(String fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getAuditor() {
        return Auditor;
    }

    public void setAuditor(String auditor) {
        this.Auditor = auditor;
    }

    public Area getAreaAuditada() {
        return areaAuditada;
    }

    public void setAreaAuditada(Area areaAuditada) {
        this.areaAuditada = areaAuditada;
    }

    public Double getPuntajeFinal() {
        return puntajeFinal;
    }

    public void setPuntajeFinal(Double puntajeFinal) {
        this.puntajeFinal = puntajeFinal;
    }

    public String getIdCampania() {
        return idCampania;
    }

    public void setIdCampania(String idCampania) {
        this.idCampania = idCampania;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public void agregarSubitem(SubItem unSubitem) {
        this.subItems.add(unSubitem);
    }
}
