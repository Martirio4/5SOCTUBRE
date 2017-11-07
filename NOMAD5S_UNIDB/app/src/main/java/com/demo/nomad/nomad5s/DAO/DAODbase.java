package com.demo.nomad.nomad5s.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Auditoria;
import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.Model.Ese;
import com.demo.nomad.nomad5s.Model.Foto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by digitalhouse on 10/06/17.
 */

public class DAODbase extends DatabaseHelper {

    private Context context;

    //---COMIENZO TABLA AUDITORIAS---//
    public static final String IDAUDITORIA = "IDAUDITORIA";
    public static final String IDAREA_FK = "IDAREA";
    public static final String IDAUDITOR_FK = "IDAUDITOR";
    public static final String FECHA_AUDITORIA = "FECHA_AUDITORIA";
    public static final String IDESE_FK = "IDESE";
    public static final String PUNTAJE_FINAL = "PUNTAJE_FINAL";
    public static final String TABLE_AUDITORIAS="TABLE_AUDITORIAS";
    //---FIN TABLA AUDITORIAS---//

    //---COMIENZO TABLA CAMPANIAS---//
    public static final String IDCAMPANIA = "IDCAMPANIA";
    public static final String FECHA_FIN_CAMPANIA = "FECHA_FIN_CAMPANIA";
    public static final String FECHA_INICIO_CAMPANIA = "FECHA_INICIO_CAMPANIA";
    public static final String IDAUDITORIA_FK = "IDAUDITORIA";
    public static final String NOMBRE_CAMPANIA = "NOMBRE_CAMPANIA";
    public static final String TABLE_CAMPANIA="TABLE_CAMPANIA";
    //---FIN TABLA CAMPANIAS---//

    //---COMIENZO TABLA ESES---//
    public static final String IDESE = "IDESE";
    public static final String IDCRITERIO_FK = "IDCRITERIO";
    public static final String PUNTAJEESE = "PUNTAJEESE";
    public static final String NOMBRE_ESE = "NOMBRE_ESE";
    public static final String TABLE_ESE="TABLE_ESE";
    //---FIN TABLA ESES---//

    //---COMIENZO TABLA CRITERIO---//
    public static final String IDCRITERIO = "IDCRITERIO";
    public static final String TEXTOCRITERIO = "TEXTOCRITERIO";
    public static final String TEXTOOPCION1 = "TEXTOOPCION1";
    public static final String TEXTOOPCION2 = "TEXTOOPCION2";
    public static final String TEXTOOPCION3 = "TEXTOOPCION3";
    public static final String TEXTOOPCION4 = "TEXTOOPCION4";
    public static final String TEXTOOPCION5 = "TEXTOOPCION5";
    public static final String PUNTAJEELEGIDO = "PUNTAJEELEGIDO";
    public static final String IDFOTOCRITERIO = "IDFOTOCRITERIO";
    public static final String TABLE_CRITERIOS="TABLE_CRITERIOS";

    //---FIN TABLA CRITERIOS---//

    //---COMIENZO TABLA AREAS---//
    public static final String IDAREA = "IDAREA";
    public static final String NOMBRE_AREA = "NOMBRE_AREA";
    public static final String IDFOTO_AREA = "IDFOTO_AREA";
    public static final String MAILRESPONSABLEAREA = "MAILRESPONSABLEAREA";
    public static final String NOMBRERESPONSABLEAREA = "NOMBRERESPONSABLEAREA";
    public static final String TABLE_AREAS="TABLE_AREAS";
    //---FIN TABLA AREAS---//

    //---COMIENZO TABLA FOTOS---//
    public static final String IDFOTO = "IDFOTO";
    public static final String COMENTARIO = "COMENTARIO";
    public static final String RUTAFOTODB = "RUTAFOTODB";
    public static final String RUTAFOTOFB = "RUTAFOTOFB";
    public static final String TABLE_FOTOS="TABLE_FOTOS";
    //---FIN TABLA FOTOS---//

    //---COMIENZO TABLA USUARIOS ---//
    public static final String IDAUDITOR = "IDAUDITOR";
    public static final String NOMBRE_AUDITOR = "NOMBRE_AUDITOR";
    public static final String IDFOTO_AUDITOR = "IDFOTO_AUDITOR";
    public static final String MAILAUDITOR = "MAILAUDITOR";
    public static final String PUESTOAUDITOR = "PUESTOAUDITOR";
    public static final String CANTIDAD_AUDITORIAS_REALIZADAS="CANTIDAD_AUDITORIAS_REALIZADAS";
    public static final String TABLE_AUDITORES="TABLE_AUDITORES";
    // ---FIN TABLA USUARIOS ---//
    

    //CONTRUCTOR DAO
    public DAODbase(Context context) {
        super(context);
        this.context=context;
    }
    
    //AGREGAR CAMPANIA
    public void addCampania (Campania unaCampania){
        if(!checkIfExistCampania(unaCampania.getIdCampania())) {
            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            
            if (unaCampania.getAuditoriasCampania().size()>0){
                for (Auditoria unAudit:unaCampania.getAuditoriasCampania()
                        ) {
                    ContentValues row = new ContentValues();
                    row.put(IDCAMPANIA, unaCampania.getIdCampania());
                    row.put(NOMBRE_CAMPANIA, unaCampania.getNombreCampania());
                    row.put(FECHA_INICIO_CAMPANIA, unaCampania.getFechaInicio());
                    row.put(FECHA_FIN_CAMPANIA, unaCampania.getFechaLimite());
                    row.put(IDAUDITORIA_FK, unAudit.getIdAuditoria());
                    database.insert(TABLE_FOTOS, null, row);
                }    
            }
            else{
                ContentValues row = new ContentValues();
                row.put(IDCAMPANIA, unaCampania.getIdCampania());
                row.put(NOMBRE_CAMPANIA, unaCampania.getNombreCampania());
                row.put(FECHA_INICIO_CAMPANIA, unaCampania.getFechaInicio());
                row.put(FECHA_FIN_CAMPANIA, unaCampania.getFechaLimite());
                database.insert(TABLE_FOTOS, null, row);
            }
            database.close();
        }
    }

    //GET ALL CAMPANIAS
    public List<Campania> getAllCampanias(){
        List<Campania>listaAllCampanias=null;
        List<String>listaIdCampanias=new ArrayList<>();
        Campania laCampania = new Campania();

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CAMPANIA;

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            if (!cursor.getString(cursor.getColumnIndex(IDCAMPANIA)).equals(laCampania.getIdCampania())){
                laCampania.setIdCampania(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
                listaIdCampanias.add(cursor.getString(cursor.getColumnIndex(IDCAMPANIA)));
            }
        }
        cursor.close();
        database.close();

        for (String idCampaniaABuscar:listaIdCampanias
             ) {
            listaAllCampanias.add(getCampaniaConId(idCampaniaABuscar));
        }
        return listaAllCampanias;

    }

    //GET CAMPANIA CON ID
    public Campania getCampaniaConId(String id){

        List<String>listaAuditsCampaniaConId=new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CAMPANIA +
                " WHERE IDCAMPANIA='"+id+"'";

        Campania campaniaConId=new Campania();
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){

            if (!cursor.getString(cursor.getColumnIndex(IDCAMPANIA)).equals(campaniaConId.getIdCampania())){
                campaniaConId.setIdCampania((cursor.getString(cursor.getColumnIndex(IDCAMPANIA))));
                campaniaConId.setFechaInicio((cursor.getString(cursor.getColumnIndex(FECHA_INICIO_CAMPANIA))));
                campaniaConId.setFechaLimite((cursor.getString(cursor.getColumnIndex(FECHA_FIN_CAMPANIA))));
                campaniaConId.setNombreCampania((cursor.getString(cursor.getColumnIndex(NOMBRE_CAMPANIA))));
            }
            listaAuditsCampaniaConId.add(cursor.getString(cursor.getColumnIndex(IDAUDITORIA_FK)));
            //LEER CADA FILA DE LA TABLA RESULTADO
        }
        cursor.close();
        database.close();

        if (listaAuditsCampaniaConId.size()>0){
            for (String idaudit:listaAuditsCampaniaConId
                    ) {
                campaniaConId.agregarAuditoria(getAuditoriaConId(idaudit));
            }
        }

        if (campaniaConId.getIdCampania()==null || campaniaConId.getIdCampania().isEmpty()){
            return null;
        }
        else{
            return campaniaConId;
        }

    }


    //AGREGAR FOTO
    public void addFoto (Foto unaFoto){
        if(!checkIfExistFoto(unaFoto.getIdFoto())) {
            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();

            //COMO LA AUDITORIA LA GENERA EL MANAGER SOLO SE CARGAN LOS DATOS INICIALES
            row.put(IDFOTO, unaFoto.getIdFoto());
            row.put(COMENTARIO, unaFoto.getComentario());
            row.put(RUTAFOTODB, unaFoto.getRutaFotoDB());
            
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_FOTOS, null, row);
            database.close();
        }
    }

    //TRAER FOTO CON ID
    public Foto getFotoConId(String id){

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_FOTOS +
                " WHERE IDFOTO='"+id+"'";

        Foto fotoConId=null;
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            fotoConId =new Foto();
            //LEER CADA FILA DE LA TABLA RESULTADO
            fotoConId.setIdFoto((cursor.getString(cursor.getColumnIndex(IDFOTO))));
            fotoConId.setComentario((cursor.getString(cursor.getColumnIndex(COMENTARIO))));
            fotoConId.setRutaFotoDB((cursor.getString(cursor.getColumnIndex(RUTAFOTODB))));
            fotoConId.setRutaFotoFB((cursor.getString(cursor.getColumnIndex(RUTAFOTOFB))));
        }
        cursor.close();
        database.close();
        return fotoConId;
    }

    public void borrarFoto(String idFotoABorrar){
        SQLiteDatabase database = getWritableDatabase();

        String query = "DELETE FROM " + TABLE_FOTOS +
                " WHERE IDFOTO='"+idFotoABorrar+"'";

        database.execSQL(query);
        database.close();
    }
    public void cambiarComentarioFoto(String idFotoACambiar, String s) {
        SQLiteDatabase database = getWritableDatabase();
        String query ="UPDATE "+TABLE_FOTOS+" SET COMENTARIO='"+s+"' WHERE IDFOTO='"+idFotoACambiar+"'";
        database.execSQL(query);
        database.close();
    }

    //AGREGAR AUDITOR
    public void addAuditor (Auditor unAuditor){
        if(!checkIfExistAuditor(unAuditor.getIdAuditor())) {
            addFoto(unAuditor.getFotoAuditor());

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();

            //COMO LA AUDITORIA LA GENERA EL MANAGER SOLO SE CARGAN LOS DATOS INICIALES
            row.put(IDAUDITOR, unAuditor.getIdAuditor());
            row.put(NOMBRE_AUDITOR, unAuditor.getNombreAuditor());
            row.put(MAILAUDITOR, unAuditor.getMailUsuario());
            row.put(PUESTOAUDITOR, unAuditor.getPuesto());
            row.put(CANTIDAD_AUDITORIAS_REALIZADAS, unAuditor.getCantidadAuditoriasRealizada());
            row.put(IDFOTO_AUDITOR, unAuditor.getFotoAuditor().getIdFoto());

            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_AUDITORES, null, row);
            database.close();
        }
    }
    
    //TRAER TODOS LOS AUDITORES
    public List<Auditor> getAllAuditores(){
        List<Auditor>listaAllAuditores=new ArrayList<>();
        List<String>idAuditorAllAuditores=new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT IDAUDITOR FROM " + TABLE_AUDITORES;

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            idAuditorAllAuditores.add(cursor.getString(cursor.getColumnIndex(IDAUDITOR)));
        }
        cursor.close();
        database.close();

        if (idAuditorAllAuditores.size()>0){

            for (String idAuditorBuscado:idAuditorAllAuditores
                    ) {
                listaAllAuditores.add(getAuditorConId(idAuditorBuscado));
            }
        }
        return listaAllAuditores;

    }

    //TRAER AUDITOR CON ID
    public Auditor getAuditorConId(String id){
        
        String idFotoAuditor=null;

        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_AUDITORES +
                " WHERE IDAUDITOR='"+id+"'";

        Auditor auditorConId=null;
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            auditorConId =new Auditor();
            //LEER CADA FILA DE LA TABLA RESULTADO
            auditorConId.setIdAuditor((cursor.getString(cursor.getColumnIndex(IDAUDITOR))));
            auditorConId.setNombreAuditor((cursor.getString(cursor.getColumnIndex(NOMBRE_AUDITOR))));
            auditorConId.setPuesto((cursor.getString(cursor.getColumnIndex(PUESTOAUDITOR))));
            auditorConId.setMailUsuario((cursor.getString(cursor.getColumnIndex(MAILAUDITOR))));
            auditorConId.setCantidadAuditoriasRealizada((cursor.getInt(cursor.getColumnIndex(CANTIDAD_AUDITORIAS_REALIZADAS))));
            idFotoAuditor=cursor.getString(cursor.getColumnIndex(IDFOTO_AUDITOR));
        }
        cursor.close();
        database.close();
        
        if (idFotoAuditor!=null){
            auditorConId.setFotoAuditor(getFotoConId(idFotoAuditor)); 
        }
        
        return auditorConId;
    }


    //BORRAR AUDITOR (tb borra foto de AUDITOR)
    public void borrarAuditor(String idAuditor){
        String idFotoABorrar=null;
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT IDFOTO_AUDITOR FROM " + TABLE_AUDITORES +
                " WHERE IDAUDITOR='"+idAuditor+"'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()){
            idFotoABorrar=cursor.getString(cursor.getColumnIndex(IDFOTO_AUDITOR));
        }
        cursor.close();
        database.close();

        SQLiteDatabase database2 = getWritableDatabase();
        String query2 = "DELETE FROM " + TABLE_AUDITORES +
                " WHERE IDAUDITOR='"+idAuditor+"'";

        database2.execSQL(query2);
        database2.close();

        borrarFoto(idFotoABorrar);

    }

    //RENOMBRAR AUDITOR
    public void renameAuditor(String idAuditor, String s) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AUDITORES+" SET NOMBRE_AUDITOR='"+s+"' WHERE IDAUDITOR='"+idAuditor+"'";
        database.execSQL(query);
        database.close();
    }

    //CAMBIAR PUESTO AUDITOR
    public void cambiarPuestoAuditor(String idAuditor, String s) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AUDITORES+" SET PUESTOAUDITOR='"+s+"' WHERE IDAUDITOR='"+idAuditor+"'";
        database.execSQL(query);
        database.close();
    }

    //CAMBIAR FOTO AUDITOR
    public void cambiarFotoAuditor(String idAuditor, String idFotoNueva) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AUDITORES+" SET IDFOTO_AUDITOR='"+ idFotoNueva +"' WHERE IDAUDITOR='"+idAuditor+"'";
        database.execSQL(query);
        database.close();
    }









    //AGREGAR AREA
    public void addArea (Area unArea){
        if(!checkIfExistArea(unArea.getIdArea())) {

            addFoto(unArea.getFotoArea());

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            ContentValues row = new ContentValues();

            //COMO LA AUDITORIA LA GENERA EL MANAGER SOLO SE CARGAN LOS DATOS INICIALES
            row.put(IDAREA, unArea.getIdArea());
            row.put(NOMBRE_AREA, unArea.getNombreArea());
            row.put(NOMBRERESPONSABLEAREA, unArea.getNombreResponsableArea());
            row.put(IDFOTO_AREA, unArea.getFotoArea().getIdFoto());
            row.put(MAILRESPONSABLEAREA,unArea.getMailResponsableArea());
           

            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            database.insert(TABLE_AREAS, null, row);
            database.close();
        }
    }
    
    //TRAER TODAS LAS AREAS

    public List<Area> getAllAreas(){
        List<Area>listaAllAreas=new ArrayList<>();
       List<String>idAreasAllAreas=new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_AREAS;

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            idAreasAllAreas.add(cursor.getString(cursor.getColumnIndex(IDAREA)));
        }
        cursor.close();
        database.close();

        if (idAreasAllAreas.size()>0){
            for (String idAreaBuscadas:idAreasAllAreas
                 ) {
                listaAllAreas.add(getAreaConId(idAreaBuscadas));
            }
        }
        return listaAllAreas;
    }
    

    //TRAER AREA CON ID
    public Area getAreaConId(String id){
        String idFotoArea=null;
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_AREAS +
                " WHERE IDAREA='"+id+"'";

        Area unAreaConId=null;
        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            unAreaConId =new Area();
            //LEER CADA FILA DE LA TABLA RESULTADO
            unAreaConId.setIdArea((cursor.getString(cursor.getColumnIndex(IDAREA))));
            unAreaConId.setNombreArea((cursor.getString(cursor.getColumnIndex(NOMBRE_AREA))));
            unAreaConId.setNombreResponsableArea((cursor.getString(cursor.getColumnIndex(NOMBRERESPONSABLEAREA))));
            unAreaConId.setMailResponsableArea((cursor.getString(cursor.getColumnIndex(MAILRESPONSABLEAREA))));
            idFotoArea=cursor.getString(cursor.getColumnIndex(IDFOTO_AREA));
        }
        cursor.close();
        database.close();

        if (idFotoArea!=null){
            unAreaConId.setFotoArea(getFotoConId(idFotoArea));
        }
        return unAreaConId;
    }

    //BORRAR AREA (tb borra foto de area)
    public void borrarArea(String idArea){
        String idFotoABorrar=null;
        SQLiteDatabase database = getReadableDatabase();
        String query = "SELECT IDFOTO_AREA FROM " + TABLE_AREAS +
                " WHERE IDAREA='"+idArea+"'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()){
            idFotoABorrar=cursor.getString(cursor.getColumnIndex(IDFOTO_AREA));
        }
        cursor.close();
        database.close();

        SQLiteDatabase database2 = getWritableDatabase();
        String query2 = "DELETE FROM " + TABLE_AREAS +
                " WHERE IDAREA='"+idArea+"'";

        database2.execSQL(query2);
        database2.close();

        borrarFoto(idFotoABorrar);

    }

    //UPDATE AREA
    public void updateArea(String idArea, String nombreArea, String nombreResponsable, String mailResponsable) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AREAS+" SET MAILRESPONSABLEAREA='"+mailResponsable+"', NOMBRERESPONSABLEAREA='"+nombreResponsable+"', NOMBRE_AREA='"+nombreArea+"' WHERE IDAREA='"+idArea+"'";
        database.execSQL(query);
        database.close();
    }



    //CAMBIAR FOTO AREA
    public void cambiarFotoArea(String idArea, String idFotoNueva) {
        SQLiteDatabase database = getWritableDatabase();

        String query ="UPDATE "+TABLE_AREAS+" SET IDFOTO_AREA='"+ idFotoNueva +"' WHERE IDAREA='"+idArea+"'";
        database.execSQL(query);
        database.close();
    }


    
    
    //AGREGAR CRITERIO
    public void addCriterio (Criterio unCriterio){
        if(!checkIfExistCriterio(unCriterio.getIdCriterio())) {
            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            
            if (unCriterio.getListaFotosCriterio().size()>0){
                //para cada foto creo un renglon en la tabla
                for (Foto unafoto:unCriterio.getListaFotosCriterio()
                        ) {
                    ContentValues row = new ContentValues();
                    row.put(IDCRITERIO, unCriterio.getIdCriterio());
                    row.put(TEXTOCRITERIO, unCriterio.getTextoCriterio());
                    row.put(TEXTOOPCION1, unCriterio.getOpcion1());
                    row.put(TEXTOOPCION2, unCriterio.getOpcion2());
                    row.put(TEXTOOPCION3, unCriterio.getOpcion3());
                    row.put(TEXTOOPCION4, unCriterio.getOpcion4());
                    row.put(TEXTOOPCION5, unCriterio.getOpcion5());
                    row.put(PUNTAJEELEGIDO, unCriterio.getPuntajeElegido());
                    row.put(IDFOTOCRITERIO, unafoto.getIdFoto());
                    database.insert(TABLE_AREAS, null, row);
                }    
            }
            else{
                ContentValues row = new ContentValues();
                row.put(IDCRITERIO, unCriterio.getIdCriterio());
                row.put(TEXTOCRITERIO, unCriterio.getTextoCriterio());
                row.put(TEXTOOPCION1, unCriterio.getOpcion1());
                row.put(TEXTOOPCION2, unCriterio.getOpcion2());
                row.put(TEXTOOPCION3, unCriterio.getOpcion3());
                row.put(TEXTOOPCION4, unCriterio.getOpcion4());
                row.put(TEXTOOPCION5, unCriterio.getOpcion5());
                row.put(PUNTAJEELEGIDO, unCriterio.getPuntajeElegido());
                database.insert(TABLE_AREAS, null, row);
            }
            
            //LE DIGO A LA BD QUE CARGUE LA FILA EN LA TABLA
            
            database.close();
        }
    }

    //TRAER CRITERIO CON ID
    public Criterio getCriterioConId(String id){

        Criterio unCriterioConId=new Criterio();
        List<String>listaFotoCriterioConId= new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CRITERIOS +
                " WHERE IDCRITERIO='"+id+"'";

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            if (!cursor.getString(cursor.getColumnIndex(IDCRITERIO)).equals(unCriterioConId.getIdCriterio())){
                unCriterioConId.setIdCriterio(cursor.getString(cursor.getColumnIndex(IDCRITERIO)));
                unCriterioConId.setTextoCriterio(cursor.getString(cursor.getColumnIndex(TEXTOCRITERIO)));
                unCriterioConId.setOpcion1(cursor.getString(cursor.getColumnIndex(TEXTOOPCION1)));
                unCriterioConId.setOpcion2(cursor.getString(cursor.getColumnIndex(TEXTOOPCION2)));
                unCriterioConId.setOpcion3(cursor.getString(cursor.getColumnIndex(TEXTOOPCION3)));
                unCriterioConId.setOpcion4(cursor.getString(cursor.getColumnIndex(TEXTOOPCION4)));
                unCriterioConId.setOpcion5(cursor.getString(cursor.getColumnIndex(TEXTOOPCION5)));
                unCriterioConId.setPuntajeElegido(cursor.getInt(cursor.getColumnIndex(PUNTAJEELEGIDO)));
            }
            listaFotoCriterioConId.add(cursor.getString(cursor.getColumnIndex(IDFOTOCRITERIO)));
        }
        cursor.close();
        database.close();

        //LE CARGO Las fotos al criterio
        if (listaFotoCriterioConId.size()>0){
            for (String idFotoCriterio:listaFotoCriterioConId
                    ) {
                unCriterioConId.agregarFoto(getFotoConId(idFotoCriterio));

            }
        }

        //SI NO ENCUENTRA VALORES DEVUELVE NULL, SINO EL ESE
        if (unCriterioConId.getIdCriterio()==null||unCriterioConId.getIdCriterio().isEmpty()){
            return null;
        }
        else{
            return unCriterioConId;
        }

    }

    
    //AGREGAR ESE
    public void addEse (Ese unEse){
        if(!checkIfExistEse(unEse.getIdEse())) {

            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS
            for (Criterio unCrit:unEse.getListaCriterios()
                 ) {
                ContentValues row = new ContentValues();
                row.put(IDESE, unEse.getIdEse());
                row.put(NOMBRE_ESE, unEse.getNombreEse());
                row.put(PUNTAJEESE, unEse.getPuntajeEse());
                row.put(IDCRITERIO_FK, unCrit.getIdCriterio());
                database.insert(TABLE_AREAS, null, row);
            }
            database.close();
        }
    }
    //TRAER ESE CON ID
    public Ese getEseConId(String id){

        List<String>criteriosEseConId= new ArrayList<>();
        Ese unEseConId=new Ese();
        SQLiteDatabase database = getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_ESE +
                " WHERE IDESE='"+id+"'";

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            if (!cursor.getString(cursor.getColumnIndex(IDESE)).equals(unEseConId.getIdEse())){
                unEseConId.setIdEse(cursor.getString(cursor.getColumnIndex(IDESE)));
                unEseConId.setNombreEse(cursor.getString(cursor.getColumnIndex(NOMBRE_ESE)));
                unEseConId.setPuntajeEse(cursor.getDouble(cursor.getColumnIndex(PUNTAJEESE)));
            }
            criteriosEseConId.add(cursor.getString(cursor.getColumnIndex(IDCRITERIO_FK)));

        }
        cursor.close();
        database.close();

        //LE CARGO LOS CRITERIOS AL ESE
        if (criteriosEseConId.size()>0){
            for (String idCriterio:criteriosEseConId
                 ) {
                unEseConId.agregarCriterio(getCriterioConId(idCriterio));

            }
        }

        //SI NO ENCUENTRA VALORES DEVUELVE NULL, SINO EL ESE
        if (unEseConId.getIdEse()==null||unEseConId.getIdEse().isEmpty()){
            return null;
        }
        else{
            return unEseConId;
        }

    }



    //AGREGAR AUDITORIA

    public void addAuditoria (Auditoria unAuditoria){
        if(!checkIfExistAuditoria(unAuditoria.getIdAuditoria())) {
            SQLiteDatabase database = getWritableDatabase();
            //CREO LA FILA Y LE CARGO LOS DATOS

            for (Ese unEse:unAuditoria.getListaEses()
                 ) {
                    ContentValues row = new ContentValues();
                    row.put(IDAUDITORIA, unAuditoria.getIdAuditoria());
                    row.put(IDAREA_FK, unAuditoria.getAreaAuditada().getIdArea());
                    row.put(IDAUDITOR_FK, unAuditoria.getAuditor().getIdAuditor());
                    row.put(FECHA_AUDITORIA, unAuditoria.getFechaAuditoria());
                    row.put(IDESE_FK, unEse.getIdEse());
                    row.put(PUNTAJE_FINAL, unAuditoria.getPuntajeFinal());
                    database.insert(TABLE_AUDITORIAS, null, row);
            }
            database.close();
        }
    }



    //TRAER AUDITORIA CON ID

    public Auditoria getAuditoriaConId(String id){

        SQLiteDatabase database = getReadableDatabase();
        List<String>listaEsesAuditConId= new ArrayList<>();
        String unAreaAuditConId=null;
        String unAuditorAuditConId=null;
        Auditoria unAuditoria=new Auditoria();

        String query = "SELECT * FROM " + TABLE_AUDITORIAS +
                " WHERE IDAUDITORIA='"+id+"'";

        Cursor cursor = database.rawQuery(query, null);
        while(cursor.moveToNext()){
            if (!cursor.getString(cursor.getColumnIndex(IDAUDITORIA)).equals(unAuditoria.getIdAuditoria()))
            {
                unAuditoria.setIdAuditoria(cursor.getString(cursor.getColumnIndex(IDAUDITORIA)));
                unAuditoria.setFechaAuditoria(cursor.getString(cursor.getColumnIndex(FECHA_AUDITORIA)));
                unAuditoria.setPuntajeFinal(cursor.getDouble(cursor.getColumnIndex(PUNTAJE_FINAL)));

                unAreaAuditConId=cursor.getString(cursor.getColumnIndex(IDAREA_FK));
                unAuditorAuditConId=cursor.getString(cursor.getColumnIndex(IDAUDITOR_FK));
            }
            listaEsesAuditConId.add(cursor.getString(cursor.getColumnIndex(IDESE_FK)));

        }

        cursor.close();
        database.close();

        //LE CARGO EL AREA A LA AUDITORIA
        if (unAreaAuditConId!=null){
            unAuditoria.setAreaAuditada(getAreaConId(unAreaAuditConId));
        }
        //LE CARGO EL AUDITOR A LA AUDITORIA
        if (unAuditorAuditConId!=null){
            unAuditoria.setAuditor(getAuditorConId(unAuditorAuditConId));
        }
        //LE CARGO LOS ESE
        if (listaEsesAuditConId.size()>0){
            for (String idEse:listaEsesAuditConId
                 ) {
                unAuditoria.agregarEse(getEseConId(idEse));
            }
        }
        //SI TIENE VALORES DEVUELVE LA AUDITORIA, SINO NULL
        if (unAuditoria.getIdAuditoria()==null ||unAuditoria.getIdAuditoria().isEmpty()){
            return null;
        }
        else{
            return unAuditoria;
        }

    }

    public Boolean checkIfExistFoto(String id){
        Foto unAuditoria = getFotoConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistArea(String id){
        Area unAuditoria = getAreaConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistEse(String id){
        Ese unAuditoria = getEseConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistCriterio(String id){
        Criterio unAuditoria = getCriterioConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistAuditor(String id){
        Auditor unAuditoria = getAuditorConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistAuditoria(String id){
        Auditoria unAuditoria = getAuditoriaConId(id);
        return !(unAuditoria == null);
        
    }public Boolean checkIfExistCampania(String id){
        Campania unAuditoria = getCampaniaConId(id);
        return !(unAuditoria == null);
    }



}
