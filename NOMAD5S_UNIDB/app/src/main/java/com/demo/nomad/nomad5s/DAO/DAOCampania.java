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
   public static final String FECHA_FIN_CAMPANIA = "FECHA_FIN_CAMPANIA";
   public static final String FECHA_INICIO_CAMPANIA = "FECHA_INICIO_CAMPANIA";
   public static final String CANTIDAD_AUDITORIAS_PROGRAMADAS = "CANTIDAD_AUDITORIAS_PROGRAMADAS";
   public static final String CANTIDAD_AUDITORIAS_TERMINADAS = "CANTIDAD_AUDITORIAS_TERMINADAS";
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
                    row.put(FECHA_FIN_CAMPANIA, unaCampania.getFechaLimite());
                    row.put(FECHA_INICIO_CAMPANIA, unaCampania.getFechaInicio());
                    row.put(CANTIDAD_AUDITORIAS_PROGRAMADAS, unaCampania.getCantidadAuditoriasProgramadas());
                    row.put(CANTIDAD_AUDITORIAS_TERMINADAS, unaCampania.getCantidadAuditoriasTerminadas());
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
                row.put(FECHA_FIN_CAMPANIA, unaCampania.getFechaLimite());
                row.put(NOMBRE_CAMPANIA, unaCampania.getNombreCampaña());
                row.put(FECHA_INICIO_CAMPANIA, unaCampania.getFechaInicio());
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


    public List<Campania> getAllCampanias(){

        List<Campania> allCampanias  = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_CAMPANIA;

        Cursor cursor = database.rawQuery(select, null);
        Campania laCampania = new Campania();
        while(cursor.moveToNext()){
            if (cursor.getString(cursor.getColumnIndex(IDCAMPANIA)).equals(laCampania.getIdCampania())){

            }
            else{
                laCampania= getCampania(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
                allCampanias.add(laCampania);
            }
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
        while (cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO

            unaCampania.setIdCampania(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
            unaCampania.setFechaLimite(cursor.getString(cursor.getColumnIndex(FECHA_FIN_CAMPANIA)));
            unaCampania.setFechaInicio(cursor.getString(cursor.getColumnIndex(FECHA_INICIO_CAMPANIA)));
            unaCampania.setCantidadAuditoriasProgramadas(cursor.getInt(cursor.getColumnIndex(CANTIDAD_AUDITORIAS_PROGRAMADAS)));
            unaCampania.setCantidadAuditoriasTerminadas(cursor.getInt(cursor.getColumnIndex(CANTIDAD_AUDITORIAS_TERMINADAS)));

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
