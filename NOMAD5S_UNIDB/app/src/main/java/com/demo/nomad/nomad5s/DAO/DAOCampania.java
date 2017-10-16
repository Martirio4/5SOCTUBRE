package com.demo.nomad.nomad5s.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.nomad.nomad5s.Model.Auditoria;
import com.demo.nomad.nomad5s.Model.Campania;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOCampania extends DatabaseHelper {

    private Context context;

   public static final String IDCAMPANIA = "IDCAMPANIA";
   public static final String FECHA_CAMPANIA = "FECHA_CAMPANIA";
   public static final String IDAUDITORIA = "IDAUDITORIA";
   public static final String NOMBRE_CAMPANIA = "NOMBRE_CAMPANIA";

   public static final String TABLE_CAMPANIA="TABLE_CAMPANIA";

    public DAOCampania(Context context) {
        super(context);
        this.context=context;
    }

    public void addCampania (Campania unaCampania){

        if(!checkIfExist(unaCampania.getIdCampania())) {

            SQLiteDatabase database = getWritableDatabase();

//            SI GENERE UNA CAMPANIA CON AUDITORIAS PRECARGADAS ENTRA POR ACA
            if (unaCampania.getAuditoriasCampania().size()>0) {

                for (Auditoria unAudit : unaCampania.getAuditoriasCampania()
                        ) {

                    //CREO LA FILA Y LE CARGO LOS DATOS
                    ContentValues row = new ContentValues();
                    row.put(IDCAMPANIA, unaCampania.getIdCampania());
                    row.put(FECHA_CAMPANIA, unaCampania.getFechaLimite());
                    row.put(NOMBRE_CAMPANIA, unaCampania.getNombreCampaña());
                    row.put(IDAUDITORIA, unAudit.getIdAuditoria());

                    //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
                    database.insert(TABLE_CAMPANIA, null, row);
                }
            }
//            SI GENERE UNA CAMPANIA VACIA ENTRA POR ACA
            else{
                ContentValues row = new ContentValues();
                row.put(IDCAMPANIA, unaCampania.getIdCampania());
                row.put(FECHA_CAMPANIA, unaCampania.getFechaLimite());
                row.put(NOMBRE_CAMPANIA, unaCampania.getNombreCampaña());
                database.insert(TABLE_CAMPANIA,null,row);
            }
            database.close();
        }
    }


//    public void addAuditorias(List<Auditoria> formatosList, String tipoAuditoria){
//
//        for(Auditoria unAuditoria : formatosList){
//            addAuditoria(unAuditoria, tipoAuditoria);
//        }
//    }


    public List<String> getAllCampanias(){

        List<String> allCampanias  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT IDCAMPANIA, NOMBRE_CAMPANIA FROM " + TABLE_CAMPANIA + " GROUP BY IDCAMPANIA" ;

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

//            AGREGO EL NOMBRE DE TODAS LAS CAMPAÑAS A LA LISTA
            allCampanias.add(cursor.getString(cursor.getColumnIndex(NOMBRE_CAMPANIA)));

        }
        //CERRAR
        cursor.close();
        database.close();

        return allCampanias;
    }



    public Campania getCampania(String id){
        DAOAuditorias daoAuditorias= new DAOAuditorias(context);

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CAMPANIA +
                        " WHERE IDCAMPANIA='"+id+"'";

        Cursor cursor = database.rawQuery(query, null);
        Campania unaCampania = null;
        if(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO

            unaCampania.setIdCampania(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
            unaCampania.setFechaLimite(cursor.getString(cursor.getColumnIndex(FECHA_CAMPANIA)));

            Auditoria unAuditoria = new Auditoria();
            unAuditoria=daoAuditorias.getAuditoria(cursor.getString(cursor.getColumnIndex(IDAUDITORIA)));
            unaCampania.agregarAuditoria(unAuditoria);


        }
        cursor.close();
        database.close();

        return unaCampania;
    }

    public Boolean checkIfExist(String id){
        Campania unaCampania = getCampania(id);
        return !(unaCampania == null);
    }

}
