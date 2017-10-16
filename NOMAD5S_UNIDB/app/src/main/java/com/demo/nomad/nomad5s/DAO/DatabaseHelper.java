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
        String query =  "CREATE TABLE " + DAOCampania.TABLE_CAMPANIA  + "(" +
                DAOCampania.IDCAMPANIA + " TEXT, " +
                DAOCampania.FECHA_CAMPANIA + " TEXT, " +
                DAOCampania.IDAUDITORIA + " TEXT, " +
                DAOCampania.NOMBRE_CAMPANIA + " TEXT )";
        db.execSQL(query);

        //CREO TABLA DE AUDITORIAS
        String query2 =  "CREATE TABLE " + DAOAuditorias.TABLE_AUDITORIAS  + "(" +
                DAOAuditorias.IDAUDITORIA + " TEXT, " +
                DAOAuditorias.IDCAMPANIA + " TEXT, " +
                DAOAuditorias.DEADLINE + " TEXT, " +
                DAOAuditorias.IDAREA + " TEXT, " +
                DAOAuditorias.NOMBRE_AREA + " TEXT, " +
                DAOAuditorias.IDFOTO_AREA + " TEXT, " +
                DAOAuditorias.RUTA_FOTO_AREA + " TEXT, " +
                DAOAuditorias.AUDITOR + " TEXT, " +
                DAOAuditorias.FECHA_AUDITORIA + " TEXT, " +
                DAOAuditorias.IDSUBITEM + " TEXT, " +
                DAOAuditorias.PUNTAJE_SUBITEM + " TEXT, " +
                DAOAuditorias.IDFOTO_SUBITEM + " TEXT, " +
                DAOAuditorias.RUTA_FOTO_SUBITEM + " TEXT, " +
                DAOAuditorias.COMENTARIO_FOTO_SUBITEM + " TEXT )";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }







}
