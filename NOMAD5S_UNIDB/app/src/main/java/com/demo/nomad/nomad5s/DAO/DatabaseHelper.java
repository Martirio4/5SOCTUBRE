package com.demo.nomad.nomad5s.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String AUDITORIA_DB = "formatosDB";
    public static final Integer VERSION_DB = 1;


    private Context context;

    //DEFINE LA BASE DE DATOS CON EL NOMBRE personDB y con version 1
    public DatabaseHelper(Context context) {
        super(context, AUDITORIA_DB, null, VERSION_DB);
    }

    //ACA ADENTRO DEFINIMOS LA ESTRUCTURA QUE VA A TENER MI BD EN VERSION 1
    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREO TABLA DE CAMPANIAS
        String query =  "CREATE TABLE " + DAODbase.TABLE_CAMPANIA  + "(" +
                DAODbase.IDCAMPANIA + " TEXT, " +
                DAODbase.FECHA_FIN_CAMPANIA + " TEXT, " +
                DAODbase.FECHA_INICIO_CAMPANIA + " TEXT, " +
                DAODbase.IDAUDITORIA + " TEXT, " +
                DAODbase.NOMBRE_CAMPANIA + " TEXT )";
        db.execSQL(query);

        //CREO TABLA DE AUDITORIAS
        String query2 =  "CREATE TABLE " + DAODbase.TABLE_AUDITORIAS  + "(" +
                DAODbase.IDAUDITORIA + " TEXT, " +
                DAODbase.IDAREA_FK + " TEXT, " +
                DAODbase.IDAUDITOR_FK + " TEXT, " +
                DAODbase.IDESE_FK + " TEXT, " +
                DAODbase.PUNTAJE_FINAL + " NUMERIC, " +
                DAODbase.FECHA_AUDITORIA + " TEXT )";
        db.execSQL(query2);

        //CREO TABLA DE ESE
        String query3 =  "CREATE TABLE " + DAODbase.TABLE_ESE  + "(" +
                DAODbase.IDESE + " TEXT, " +
                DAODbase.IDCRITERIO_FK + " TEXT, " +
                DAODbase.NOMBRE_ESE + " TEXT, " +
                DAODbase.PUNTAJEESE + " NUMERIC )";
        db.execSQL(query3);


        //CREO TABLA DE AUDITORES
        String query4 =  "CREATE TABLE " + DAODbase.TABLE_AUDITORES  + "(" +
                DAODbase.IDAUDITOR + " TEXT, " +
                DAODbase.NOMBRE_AUDITOR + " TEXT, " +
                DAODbase.MAILAUDITOR + " TEXT, " +
                DAODbase.IDFOTO_AUDITOR + " TEXT, " +
                DAODbase.CANTIDAD_AUDITORIAS_REALIZADAS + " NUMERIC, " +
                DAODbase.PUESTOAUDITOR + " TEXT )";
        db.execSQL(query4);

        //CREO TABLA DE FOTOS
        String query5 =  "CREATE TABLE " + DAODbase.TABLE_FOTOS  + "(" +
                DAODbase.IDFOTO + " TEXT, " +
                DAODbase.COMENTARIO + " TEXT, " +
                DAODbase.RUTAFOTODB + " TEXT, " +
                DAODbase.RUTAFOTOFB + " TEXT )";
        db.execSQL(query5);

        //CREO TABLA DE AREAS
        String query6 =  "CREATE TABLE " + DAODbase.TABLE_AREAS  + "(" +
                DAODbase.IDAREA + " TEXT, " +
                DAODbase.NOMBRE_AREA + " TEXT, " +
                DAODbase.IDFOTO_AREA + " TEXT, " +
                DAODbase.RESPONSABLEAREA + " TEXT )";
        db.execSQL(query6);

        //CREO TABLA DE AREAS
        String query7 =  "CREATE TABLE " + DAODbase.TABLE_CRITERIOS  + "(" +
                DAODbase.IDCRITERIO + " TEXT, " +
                DAODbase.TEXTOCRITERIO + " TEXT, " +
                DAODbase.TEXTOOPCION1 + " TEXT, " +
                DAODbase.TEXTOOPCION2 + " TEXT, " +
                DAODbase.TEXTOOPCION3 + " TEXT, " +
                DAODbase.TEXTOOPCION4 + " TEXT, " +
                DAODbase.TEXTOOPCION5 + " TEXT, " +
                DAODbase.PUNTAJEELEGIDO + " TEXT, " +
                DAODbase.IDFOTOCRITERIO + " TEXT )";
        db.execSQL(query7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }







}
