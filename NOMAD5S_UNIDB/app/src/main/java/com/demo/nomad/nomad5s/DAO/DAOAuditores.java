package com.demo.nomad.nomad5s.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Foto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOAuditores extends DatabaseHelper {

    private Context context;

//    DEFINIDOS POR MANAGER
    //agregar posicion o algun dato del auditor
    


    public DAOAuditores(Context context) {

        super(context);
        this.context=context;
    }

    public void addAuditor (Auditor unAuditor){
        if(!checkIfExist(unAuditor.getIdAuditor())) {


                SQLiteDatabase database = getWritableDatabase();
                //CREO LA FILA Y LE CARGO LOS DATOS
                ContentValues row = new ContentValues();

                row.put(IDAUDITOR, unAuditor.getIdAuditor());
                row.put(NOMBRE_AUDITOR, unAuditor.getNombreAuditor());
                row.put(IDFOTO_AUDITOR, unAuditor.getFotoAuditor().getIdFoto());
                row.put(MAILAUDITOR, unAuditor.getFotoAuditor().getRutaFotoDB());
                row.put(CANTIDAD_AUDITORIAS_REALIZADAS, unAuditor.getCantidadAuditoriasRealizada());


                //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
                database.insert(TABLE_AUDITORES, null, row);
                database.close();

        }
    }


//    public void addAuditors(List<Auditor> formatosList, String tipoAuditor){
//
//        for(Auditor unAuditor : formatosList){
//            addAuditor(unAuditor, tipoAuditor);
//        }
//    }


    public Auditor getAuditor(String id){

        SQLiteDatabase database = getReadableDatabase();
        ControllerDatos controllerDatos= new ControllerDatos(context);

        String query = "SELECT * FROM " + TABLE_AUDITORES +
            " WHERE IDAUDITOR='"+id+"'";
        Auditor unAuditor=null;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToNext()){
            unAuditor =new Auditor();
            //LEER CADA FILA DE LA TABLA RESULTADO
            unAuditor.setIdAuditor((cursor.getString(cursor.getColumnIndex(IDAUDITOR))));
            unAuditor.setNombreAuditor((cursor.getString(cursor.getColumnIndex(NOMBRE_AUDITOR))));
            unAuditor.setCantidadAuditoriasRealizada((cursor.getInt(cursor.getColumnIndex(CANTIDAD_AUDITORIAS_REALIZADAS))));
            Foto fotoAuditor= new Foto();
            fotoAuditor.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO_AUDITOR))));
            fotoAuditor.setRutaFotoDB((cursor.getString(cursor.getColumnIndex(MAILAUDITOR))));
            unAuditor.setFotoAuditor(fotoAuditor);
        }
        cursor.close();
        database.close();
        return unAuditor;
    }

    public List<Auditor> getAllAuditors(){

        List<Auditor> allAuditors = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_AUDITORES;

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Auditor unAuditor =new Auditor();
            //LEER CADA FILA DE LA TABLA RESULTADO
            unAuditor.setIdAuditor((cursor.getString(cursor.getColumnIndex(IDAUDITOR))));
            unAuditor.setNombreAuditor((cursor.getString(cursor.getColumnIndex(NOMBRE_AUDITOR))));
            unAuditor.setCantidadAuditoriasRealizada((cursor.getInt(cursor.getColumnIndex(CANTIDAD_AUDITORIAS_REALIZADAS))));
            Foto fotoAuditor= new Foto();
            fotoAuditor.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO_AUDITOR))));
            fotoAuditor.setRutaFotoDB((cursor.getString(cursor.getColumnIndex(MAILAUDITOR))));
            unAuditor.setFotoAuditor(fotoAuditor);

            allAuditors.add(unAuditor);
        }
        //CERRAR
        cursor.close();
        database.close();

        return allAuditors;
    }

    public Boolean checkIfExist(String id){
        Auditor unAuditor = getAuditor(id);
        return !(unAuditor == null);
    }

    public void borrarAuditor(Auditor unAuditor){
        SQLiteDatabase database = getWritableDatabase();

        String query = "DELETE FROM " + TABLE_AUDITORES +
                " WHERE IDAUDITOR='"+unAuditor.getIdAuditor()+"'";

        database.execSQL(query);
        database.close();

    }

    public void rename(Auditor unAuditor, String s) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AUDITORES+" SET NOMBRE_AUDITOR='"+s+"' WHERE IDAUDITOR='"+unAuditor.getIdAuditor()+"'";
        database.execSQL(query);
        database.close();
    }
}
