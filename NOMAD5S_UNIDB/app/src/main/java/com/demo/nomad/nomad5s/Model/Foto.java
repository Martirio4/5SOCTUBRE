package com.demo.nomad.nomad5s.Model;

/**
 * Created by elmar on 11/8/2017.
 */

public class Foto {


   private String idFoto;
    private String rutaFoto;
    private String comentario;

    public Foto() {
    }

    public Foto(String idFoto, String rutaFoto, String comentario) {
        this.idFoto = idFoto;
        this.rutaFoto = rutaFoto;
        this.comentario = comentario;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
