package com.demo.nomad.nomad5s.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.demo.nomad.nomad5s.ControllerDatos.ControllerDatos;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditoria;
import com.demo.nomad.nomad5s.Model.Foto;
import com.demo.nomad.nomad5s.Model.SubItem;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAOAuditorias extends DatabaseHelper {

    private Context context;

//    DEFINIDOS POR MANAGER
    public static final String IDAUDITORIA = "IDAUDITORIA";
    public static final String IDCAMPANIA = "IDCAMPANIA";
    public static final String DEADLINE = "DEADLINE";
    public static final String IDAREA = "IDAREA";
    public static final String NOMBRE_AREA = "NOMBRE_AREA";
    public static final String IDFOTO_AREA = "IDFOTO_AREA";
    public static final String RUTA_FOTO_AREA = "RUTA_FOTO_AREA";
    public static final String AUDITOR = "AUDITOR";

//    DEFINIDOS POR AUDITOR
    public static final String FECHA_AUDITORIA = "FECHA_AUDITORIA";
    public static final String IDSUBITEM = "IDSUBITEM";
    public static final String PUNTAJE_SUBITEM = "PUNTAJE_SUBITEM";
    public static final String IDFOTO_SUBITEM = "IDFOTO_SUBITEM";
    public static final String RUTA_FOTO_SUBITEM = "RUTA_FOTO_SUBITEM";
    public static final String COMENTARIO_FOTO_SUBITEM = "COMENTARIO_FOTO_SUBITEM";

//    CALCULADO POR LA APP
    public static final String PUNTAJE_FINAL = "PUNTAJE_FINAL";

    public static final String TABLE_AUDITORIAS="TABLE_AUDITORIAS";

    public DAOAuditorias(Context context) {

        super(context);
        this.context=context;
    }

    public void addAuditoria (Auditoria unAuditoria){
        if(!checkIfExist(unAuditoria.getIdAuditoria())) {
            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();

            //COMO LA AUDITORIA LA GENERA EL MANAGER SOLO SE CARGAN LOS DATOS INICIALES
            row.put(IDAUDITORIA, unAuditoria.getIdAuditoria());
            row.put(IDCAMPANIA, unAuditoria.getIdCampania());
            row.put(IDAREA, unAuditoria.getAreaAuditada().getIdArea());
            row.put(NOMBRE_AREA, unAuditoria.getAreaAuditada().getNombreArea());
            row.put(IDFOTO_AREA, unAuditoria.getAreaAuditada().getFotoArea().getIdFoto());
            row.put(RUTA_FOTO_AREA, unAuditoria.getAreaAuditada().getFotoArea().getRutaFoto());
            row.put(AUDITOR, unAuditoria.getAuditor().getIdAuditor());

            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_AUDITORIAS, null, row);
            database.close();
        }
    }


//    public void addAuditorias(List<Auditoria> formatosList, String tipoAuditoria){
//
//        for(Auditoria unAuditoria : formatosList){
//            addAuditoria(unAuditoria, tipoAuditoria);
//        }
//    }


    public Auditoria getAuditoria(String id){

        SQLiteDatabase database = getReadableDatabase();
        Auditoria unAuditoria = null;
        ControllerDatos controllerDatos= new ControllerDatos(context);
        unAuditoria = new Auditoria();

        for (String elsub:controllerDatos.traerTitulos())
        {
                String query = "SELECT * FROM " + TABLE_AUDITORIAS +
                    " WHERE IDAUDITORIA=" + id+" AND IDSUBITEM=" + elsub;

                Cursor cursor = database.rawQuery(query, null);
                SubItem unSubitem= new SubItem();
                if(cursor.moveToNext()){
                    //LEER CADA FILA DE LA TABLA RESULTADO
                    unAuditoria.setIdAuditoria(cursor.getString(cursor.getColumnIndex(IDAUDITORIA)));
                    unAuditoria.setIdCampania(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
                    unAuditoria.setDeadLine(cursor.getString(cursor.getColumnIndex(DEADLINE)));
                   unAuditoria.setAuditor(cursor.getString(cursor.getColumnIndex(AUDITOR)));
                    unAuditoria.setFechaAuditoria(cursor.getString(cursor.getColumnIndex(FECHA_AUDITORIA)));
                    unAuditoria.setPuntajeFinal(cursor.getDouble(cursor.getColumnIndex(PUNTAJE_FINAL)));
    ///ATENCION////ATENCION//////traigo el area y le modifico los parametros, se guarda ok??

                    Area unArea= new Area();
                    unArea.setIdArea((cursor.getString(cursor.getColumnIndex(IDAREA))));
                    unArea.setNombreArea((cursor.getString(cursor.getColumnIndex(NOMBRE_AREA))));
                    Foto fotoArea= new Foto();
                    fotoArea.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO_AREA))));
                    fotoArea.setRutaFoto((cursor.getString(cursor.getColumnIndex(RUTA_FOTO_AREA))));
                    unArea.setFotoArea(fotoArea);

                    unAuditoria.setAreaAuditada(unArea);


                    unSubitem.setIdSubitem((cursor.getString(cursor.getColumnIndex(IDSUBITEM))));
                    unSubitem.setPuntuacion((cursor.getInt(cursor.getColumnIndex(PUNTAJE_SUBITEM))));

                    //CADA LINEA ES UNA FOTO DISTINTA, CADA VEZ Q CAMBIO LA LINEA GENERO UNA FOTO NUEVA
                    Foto unaFoto=new Foto();
                    unaFoto.setIdFoto(cursor.getString(cursor.getColumnIndex(IDFOTO_SUBITEM)));
                    unaFoto.setRutaFoto(cursor.getString(cursor.getColumnIndex(RUTA_FOTO_SUBITEM)));
                    unaFoto.setComentario(cursor.getString(cursor.getColumnIndex(COMENTARIO_FOTO_SUBITEM)));

                    unSubitem.agregarFoto(unaFoto);
                }
                unAuditoria.agregarSubitem(unSubitem);
                cursor.close();
        }
        database.close();
        return unAuditoria;
    }

    public Boolean checkIfExist(String id){
        Auditoria unAuditoria = getAuditoria(id);
        return !(unAuditoria == null);
    }

}
