package com.demo.nomad.nomad5s.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Foto;

import java.util.ArrayList;
import java.util.List;

import static com.demo.nomad.nomad5s.DAO.DAOAuditorias.TABLE_AUDITORIAS;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOAreas extends DatabaseHelper {

    private Context context;

//    DEFINIDOS POR MANAGER
    
    public static final String IDAREA = "IDAREA";
    public static final String NOMBRE_AREA = "NOMBRE_AREA";
    public static final String IDFOTO_AREA = "IDFOTO_AREA";
    public static final String RUTA_FOTO_AREA = "RUTA_FOTO_AREA";

    public static final String TABLE_AREAS="TABLE_AREAS";

    public DAOAreas(Context context) {

        super(context);
        this.context=context;
    }

    public void addArea (Area unArea){
        if(!checkIfExist(unArea.getIdArea())) {


                SQLiteDatabase database = getWritableDatabase();
                //CREO LA FILA Y LE CARGO LOS DATOS
                ContentValues row = new ContentValues();

                //COMO LA AUDITORIA LA GENERA EL MANAGER SOLO SE CARGAN LOS DATOS INICIALESrow.put(IDAUDITORIA, unArea.getIdArea());
                row.put(IDAREA, unArea.getIdArea());
                row.put(NOMBRE_AREA, unArea.getNombreArea());
                row.put(IDFOTO_AREA, unArea.getFotoArea().getIdFoto());
                row.put(RUTA_FOTO_AREA, unArea.getFotoArea().getRutaFoto());

                //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
                database.insert(TABLE_AREAS, null, row);
                database.close();

        }
    }


//    public void addAreas(List<Area> formatosList, String tipoArea){
//
//        for(Area unArea : formatosList){
//            addArea(unArea, tipoArea);
//        }
//    }


    public Area getArea(String id){

        SQLiteDatabase database = getReadableDatabase();
        ControllerDatos controllerDatos= new ControllerDatos(context);

        String query = "SELECT * FROM " + TABLE_AREAS +
            " WHERE IDAREA='"+id+"'";
        Area unArea=null;
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.moveToNext()){
            unArea =new Area();
            //LEER CADA FILA DE LA TABLA RESULTADO
            unArea.setIdArea((cursor.getString(cursor.getColumnIndex(IDAREA))));
            unArea.setNombreArea((cursor.getString(cursor.getColumnIndex(NOMBRE_AREA))));
            Foto fotoArea= new Foto();
            fotoArea.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO_AREA))));
            fotoArea.setRutaFoto((cursor.getString(cursor.getColumnIndex(RUTA_FOTO_AREA))));
            unArea.setFotoArea(fotoArea);
        }
        cursor.close();
        database.close();
        return unArea;
    }

    public List<Area> getAllAreas(){

        List<Area> allAreas = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String select = "SELECT * FROM " + TABLE_AREAS;

        Cursor cursor = database.rawQuery(select, null);
        while(cursor.moveToNext()){

            //LEER CADA FILA DE LA TABLA RESULTADO
            Area unArea = new Area();
            unArea.setIdArea((cursor.getString(cursor.getColumnIndex(IDAREA))));
            unArea.setNombreArea((cursor.getString(cursor.getColumnIndex(NOMBRE_AREA))));
            Foto fotoArea= new Foto();
            fotoArea.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO_AREA))));
            fotoArea.setRutaFoto((cursor.getString(cursor.getColumnIndex(RUTA_FOTO_AREA))));
            unArea.setFotoArea(fotoArea);

            allAreas.add(unArea);
        }
        //CERRAR
        cursor.close();
        database.close();

        return allAreas;
    }

    public Boolean checkIfExist(String id){
        Area unArea = getArea(id);
        return !(unArea == null);
    }

    public void borrarArea(Area unArea){
        SQLiteDatabase database = getWritableDatabase();

        String query = "DELETE FROM " + TABLE_AREAS +
                " WHERE IDAREA='"+unArea.getIdArea()+"'";

        database.execSQL(query);
        database.close();

    }

    public void rename(Area unArea, String s) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AREAS+" SET NOMBRE_AREA='"+s+"' WHERE IDAREA='"+unArea.getIdArea()+"'";
        database.execSQL(query);
        database.close();
    }
}
