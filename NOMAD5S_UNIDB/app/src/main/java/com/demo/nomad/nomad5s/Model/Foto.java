package com.demo.nomad.nomad5s.Model;

/**
 * Created by elmar on 11/8/2017.
 */

public class Foto {


   private String idFoto;
    private String rutaFotoDB;
    private String rutaFotoFB;
    private String comentario;

    public Foto() {
    }

    public Foto(String idFoto, String rutaFotoDB, String rutaFotoFB, String comentario) {
        this.idFoto = idFoto;
        this.rutaFotoDB = rutaFotoDB;
        this.rutaFotoFB = rutaFotoFB;
        this.comentario = comentario;
    }

    //GETTER AND SETTER


    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public String getRutaFotoDB() {
        return rutaFotoDB;
    }

    public void setRutaFotoDB(String rutaFotoDB) {
        this.rutaFotoDB = rutaFotoDB;
    }

    public String getRutaFotoFB() {
        return rutaFotoFB;
    }

    public void setRutaFotoFB(String rutaFotoFB) {
        this.rutaFotoFB = rutaFotoFB;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
