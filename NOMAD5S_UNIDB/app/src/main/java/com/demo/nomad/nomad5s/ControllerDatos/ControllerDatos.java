package com.demo.nomad.nomad5s.ControllerDatos;
import android.content.Context;

import com.demo.nomad.nomad5s.DAO.DAODbase;
import com.demo.nomad.nomad5s.Model.Area;
import com.demo.nomad.nomad5s.Model.Auditor;
import com.demo.nomad.nomad5s.Model.Campania;
import com.demo.nomad.nomad5s.Model.Criterio;
import com.demo.nomad.nomad5s.Model.Ese;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by elmar on 13/8/2017.
 */

public class ControllerDatos {

    private Context context;
    private DAODbase daoDB;
    private DatabaseReference mDatabase;


    public ControllerDatos(Context context) {
        this.context = context;

        daoDB =new DAODbase(context);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public List<String>traerSeiri(){
        List<String> unaLista= new ArrayList<>();
        unaLista.add("1S 1");
        unaLista.add("1S 2");
        unaLista.add("1S 3");
        unaLista.add("1S 4");

        return  unaLista;
    }
    public List<String>traerSeiton(){
        List<String> unaLista= new ArrayList<>();
        unaLista.add("2S 1");
        unaLista.add("2S 2");
        unaLista.add("2S 3");
        unaLista.add("2S 4");

        return  unaLista;
    }

    public List<String>traerSeiso(){
        List<String> unaLista= new ArrayList<>();
        unaLista.add("3S 1");
        unaLista.add("3S 2");
        unaLista.add("3S 3");
        unaLista.add("3S 4");

        return  unaLista;
    }

    public List<String>traerSeiketsu(){
        List<String> unaLista= new ArrayList<>();
        unaLista.add("4S 1");
        unaLista.add("4S 2");
        unaLista.add("4S 3");
        unaLista.add("4S 4");

        return  unaLista;
    }

    public List<String>traerShitsuke(){
        List<String> unaLista= new ArrayList<>();
        unaLista.add("5S 1");
        unaLista.add("5S 2");
        unaLista.add("5S 3");
        unaLista.add("5S 4");

        return  unaLista;
    }

/*
    public List<SubItem>traerSubItems(){
        List<SubItem>unaLista=new ArrayList<>();
        
        SubItem subItem1=new SubItem();
        subItem1.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem1.setId("1S 1");
        subItem1.setaQuePertenece("Seiri / Sort");
        subItem1.setEnunciado(context.getString(R.string.enunciado11));
        subItem1.setPunto1(context.getString(R.string.punto111));
        subItem1.setPunto2(context.getString(R.string.punto112));
        subItem1.setPunto3(context.getString(R.string.punto113));
        subItem1.setPunto4(context.getString(R.string.punto114));
        subItem1.setPunto5(context.getString(R.string.punto115));
        unaLista.add(subItem1);

        SubItem subItem12=new SubItem();
        subItem12.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem12.setId("1S 2");
        subItem12.setaQuePertenece("Seiri / Sort");
        subItem12.setEnunciado(context.getString(R.string.enunciado12));
        subItem12.setPunto1(context.getString(R.string.punto121));
        subItem12.setPunto2(context.getString(R.string.punto122));
        subItem12.setPunto3(context.getString(R.string.punto123));
        subItem12.setPunto4(context.getString(R.string.punto124));
        subItem12.setPunto5(context.getString(R.string.punto125));
        unaLista.add(subItem12);

        SubItem subItem13=new SubItem();
        subItem13.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem13.setId("1S 3");
        subItem13.setaQuePertenece("Seiri / Sort");
        subItem13.setEnunciado(context.getString(R.string.enunciado13));
        subItem13.setPunto1(context.getString(R.string.punto131));
        subItem13.setPunto2(context.getString(R.string.punto132));
        subItem13.setPunto3(context.getString(R.string.punto133));
        subItem13.setPunto4(context.getString(R.string.punto134));
        subItem13.setPunto5(context.getString(R.string.punto135));
        unaLista.add(subItem13);

        SubItem subItem14=new SubItem();
        subItem14.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem14.setId("1S 4");
        subItem14.setaQuePertenece("Seiri / Sort");
        subItem14.setEnunciado(context.getString(R.string.enunciado14));
        subItem14.setPunto1(context.getString(R.string.punto141));
        subItem14.setPunto2(context.getString(R.string.punto142));
        subItem14.setPunto3(context.getString(R.string.punto143));
        subItem14.setPunto4(context.getString(R.string.punto144));
        subItem14.setPunto5(context.getString(R.string.punto145));
        unaLista.add(subItem14);

        SubItem subItem21=new SubItem();
        subItem21.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem21.setId("2S 1");
        subItem21.setaQuePertenece("Seiton / Set in order");
        subItem21.setEnunciado(context.getString(R.string.enunciado21));
        subItem21.setPunto1(context.getString(R.string.punto211));
        subItem21.setPunto2(context.getString(R.string.punto212));
        subItem21.setPunto3(context.getString(R.string.punto213));
        subItem21.setPunto4(context.getString(R.string.punto214));
        subItem21.setPunto5(context.getString(R.string.punto215));
        unaLista.add(subItem21);

        SubItem subItem22=new SubItem();
        subItem22.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem22.setId("2S 2");
        subItem22.setaQuePertenece("Seiton / Set in order");
        subItem22.setEnunciado(context.getString(R.string.enunciado22));
        subItem22.setPunto1(context.getString(R.string.punto221));
        subItem22.setPunto2(context.getString(R.string.punto222));
        subItem22.setPunto3(context.getString(R.string.punto223));
        subItem22.setPunto4(context.getString(R.string.punto224));
        subItem22.setPunto5(context.getString(R.string.punto225));
        unaLista.add(subItem22);

        SubItem subItem23=new SubItem();
        subItem23.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem23.setId("2S 3");
        subItem23.setaQuePertenece("Seiton / Set in order");
        subItem23.setEnunciado(context.getString(R.string.enunciado23));
        subItem23.setPunto1(context.getString(R.string.punto231));
        subItem23.setPunto2(context.getString(R.string.punto232));
        subItem23.setPunto3(context.getString(R.string.punto233));
        subItem23.setPunto4(context.getString(R.string.punto234));
        subItem23.setPunto5(context.getString(R.string.punto235));
        unaLista.add(subItem23);

        SubItem subItem24=new SubItem();
        subItem24.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem24.setId("2S 4");
        subItem24.setaQuePertenece("Seiton / Set in order");
        subItem24.setEnunciado(context.getString(R.string.enunciado24));
        subItem24.setPunto1(context.getString(R.string.punto241));
        subItem24.setPunto2(context.getString(R.string.punto242));
        subItem24.setPunto3(context.getString(R.string.punto243));
        subItem24.setPunto4(context.getString(R.string.punto244));
        subItem24.setPunto5(context.getString(R.string.punto245));
        unaLista.add(subItem24);

        SubItem subItem31=new SubItem();
        subItem31.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem31.setId("3S 1");
        subItem31.setaQuePertenece("Seiso / Shine");
        subItem31.setEnunciado(context.getString(R.string.enunciado31));
        subItem31.setPunto1(context.getString(R.string.punto311));
        subItem31.setPunto2(context.getString(R.string.punto312));
        subItem31.setPunto3(context.getString(R.string.punto313));
        subItem31.setPunto4(context.getString(R.string.punto314));
        subItem31.setPunto5(context.getString(R.string.punto315));
        unaLista.add(subItem31);

        SubItem subItem32=new SubItem();
        subItem32.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem32.setId("3S 2");
        subItem32.setaQuePertenece("Seiso / Shine");
        subItem32.setEnunciado(context.getString(R.string.enunciado32));
        subItem32.setPunto1(context.getString(R.string.punto321));
        subItem32.setPunto2(context.getString(R.string.punto322));
        subItem32.setPunto3(context.getString(R.string.punto323));
        subItem32.setPunto4(context.getString(R.string.punto324));
        subItem32.setPunto5(context.getString(R.string.punto325));
        unaLista.add(subItem32);

        SubItem subItem33=new SubItem();
        subItem33.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem33.setId("3S 3");
        subItem33.setaQuePertenece("Seiso / Shine");
        subItem33.setEnunciado(context.getString(R.string.enunciado33));
        subItem33.setPunto1(context.getString(R.string.punto331));
        subItem33.setPunto2(context.getString(R.string.punto332));
        subItem33.setPunto3(context.getString(R.string.punto333));
        subItem33.setPunto4(context.getString(R.string.punto334));
        subItem33.setPunto5(context.getString(R.string.punto335));
        unaLista.add(subItem33);

        SubItem subItem34=new SubItem();
        subItem34.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem34.setId("3S 4");
        subItem34.setaQuePertenece("Seiso / Shine");
        subItem34.setEnunciado(context.getString(R.string.enunciado34));
        subItem34.setPunto1(context.getString(R.string.punto341));
        subItem34.setPunto2(context.getString(R.string.punto342));
        subItem34.setPunto3(context.getString(R.string.punto343));
        subItem34.setPunto4(context.getString(R.string.punto344));
        subItem34.setPunto5(context.getString(R.string.punto345));
        unaLista.add(subItem34);


        SubItem subItem41=new SubItem();
        subItem41.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem41.setId("4S 1");
        subItem41.setaQuePertenece("Seiketsu / Standarize");
        subItem41.setEnunciado(context.getString(R.string.enunciado41));
        subItem41.setPunto1(context.getString(R.string.punto411));
        subItem41.setPunto2(context.getString(R.string.punto412));
        subItem41.setPunto3(context.getString(R.string.punto413));
        subItem41.setPunto4(context.getString(R.string.punto414));
        subItem41.setPunto5(context.getString(R.string.punto415));
        unaLista.add(subItem41);


        SubItem subItem42=new SubItem();
        subItem42.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem42.setId("4S 2");
        subItem42.setaQuePertenece("Seiketsu / Standarize");
        subItem42.setEnunciado(context.getString(R.string.enunciado42));
        subItem42.setPunto1(context.getString(R.string.punto421));
        subItem42.setPunto2(context.getString(R.string.punto422));
        subItem42.setPunto3(context.getString(R.string.punto423));
        subItem42.setPunto4(context.getString(R.string.punto424));
        subItem42.setPunto5(context.getString(R.string.punto425));
        unaLista.add(subItem42);

        SubItem subItem43=new SubItem();
        subItem43.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem43.setId("4S 3");
        subItem43.setaQuePertenece("Seiketsu / Standarize");
        subItem43.setEnunciado(context.getString(R.string.enunciado43));
        subItem43.setPunto1(context.getString(R.string.punto431));
        subItem43.setPunto2(context.getString(R.string.punto432));
        subItem43.setPunto3(context.getString(R.string.punto433));
        subItem43.setPunto4(context.getString(R.string.punto434));
        subItem43.setPunto5(context.getString(R.string.punto435));
        unaLista.add(subItem43);

        SubItem subItem44=new SubItem();
        subItem44.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem44.setId("4S 4");
        subItem44.setaQuePertenece("Seiketsu / Standarize");
        subItem44.setEnunciado(context.getString(R.string.enunciado44));
        subItem44.setPunto1(context.getString(R.string.punto441));
        subItem44.setPunto2(context.getString(R.string.punto442));
        subItem44.setPunto3(context.getString(R.string.punto443));
        subItem44.setPunto4(context.getString(R.string.punto444));
        subItem44.setPunto5(context.getString(R.string.punto445));
        unaLista.add(subItem44);

        SubItem subItem51=new SubItem();
        subItem51.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem51.setId("5S 1");
        subItem51.setaQuePertenece("Shitsuke / Sustain");
        subItem51.setEnunciado(context.getString(R.string.enunciado51));
        subItem51.setPunto1(context.getString(R.string.punto511));
        subItem51.setPunto2(context.getString(R.string.punto512));
        subItem51.setPunto3(context.getString(R.string.punto513));
        subItem51.setPunto4(context.getString(R.string.punto514));
        subItem51.setPunto5(context.getString(R.string.punto515));
        unaLista.add(subItem51);

        SubItem subItem52=new SubItem();
        subItem52.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem52.setId("5S 2");
        subItem52.setaQuePertenece("Shitsuke / Sustain");
        subItem52.setEnunciado(context.getString(R.string.enunciado52));
        subItem52.setPunto1(context.getString(R.string.punto521));
        subItem52.setPunto2(context.getString(R.string.punto522));
        subItem52.setPunto3(context.getString(R.string.punto523));
        subItem52.setPunto4(context.getString(R.string.punto524));
        subItem52.setPunto5(context.getString(R.string.punto525));
        unaLista.add(subItem52);

        SubItem subItem53=new SubItem();
        subItem53.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem53.setId("5S 3");
        subItem53.setaQuePertenece("Shitsuke / Sustain");
        subItem53.setEnunciado(context.getString(R.string.enunciado53));
        subItem53.setPunto1(context.getString(R.string.punto531));
        subItem53.setPunto2(context.getString(R.string.punto532));
        subItem53.setPunto3(context.getString(R.string.punto533));
        subItem53.setPunto4(context.getString(R.string.punto534));
        subItem53.setPunto5(context.getString(R.string.punto535));
        unaLista.add(subItem53);

        SubItem subItem54=new SubItem();
        subItem54.setAuditoria(ActivityAuditoria.idAuditoria);
        subItem54.setId("5S 4");
        subItem54.setaQuePertenece("Shitsuke / Sustain");
        subItem54.setEnunciado(context.getString(R.string.enunciado54));
        subItem54.setPunto1(context.getString(R.string.punto541));
        subItem54.setPunto2(context.getString(R.string.punto542));
        subItem54.setPunto3(context.getString(R.string.punto543));
        subItem54.setPunto4(context.getString(R.string.punto544));
        subItem54.setPunto5(context.getString(R.string.punto545));
        unaLista.add(subItem54);

        return unaLista;
    }
    */

    public List<String>traerTitulos(){
        List<String>unaLista= new ArrayList<>();
        unaLista.add("Seiri 1");
        unaLista.add("Seiri 2");
        unaLista.add("Seiri 3");
        unaLista.add("Seiri 4");
        unaLista.add("Seiton 1");
        unaLista.add("Seiton 2");
        unaLista.add("Seiton 3");
        unaLista.add("Seiton 4");
        unaLista.add("Seiso 1");
        unaLista.add("Seiso 2");
        unaLista.add("Seiso 3");
        unaLista.add("Seiso 4");
        unaLista.add("Seiketsu 1");
        unaLista.add("Seiketsu 2");
        unaLista.add("Seiketsu 3");
        unaLista.add("Seiketsu 4");
        unaLista.add("Shitsuke 1");
        unaLista.add("Shitsuke 2");
        unaLista.add("Shitsuke 3");
        unaLista.add("Shitsuke 4");
        return unaLista;
    }

    public List<String> traerListaViewPager(){
        List<String>unaLista=new ArrayList<>();
        unaLista.add("My Audits");
        unaLista.add("Ranking");
        return unaLista;
    }
/*
    public List<Area> traerAreas() {

        //chequear en firebase las areas

        DAOAreas daoAreas= new DAOAreas(context);
        return daoAreas.getAllAreas();
    }

    public Area traerUnArea(String idArea) {
        DAOFotos daoFotos= new DAOFotos(context);
        DAOAreas daoAreas = new DAOAreas(context);
        Area unArea=daoAreas.getArea(idArea);
        Foto unaFoto = daoFotos.getFoto(idArea);
    }
    */


    public void guardarArea(Area unArea){
        //GUARDAR EN LA DB
        daoDB.addArea(unArea);
        //GUARDAR EN FIREBASE
        mDatabase.child("AREAS").child(unArea.getIdArea()).setValue(unArea);
    }


    public List<Area> traerListaAreas() {
        return daoDB.getAllAreas();
    }

    public void eliminarArea(String idArea){
//        BORRAR DE LA DB
        daoDB.borrarArea(idArea);
//        BORRAR DE FIREBASE
        mDatabase.child("AREAS").child(idArea).removeValue();

//

    }




    public List<Auditor> traerListaAuditores() {
        return daoDB.getAllAuditores();
    }

    public void guardarAuditor(Auditor unAuditor) {
        //GUARDAR EN LA DB
        daoDB.addAuditor(unAuditor);
        //GUARDAR EN FIREBASE

    }
    public void eliminarAuditor(String idAuditor){
//        BORRAR DE LA DB
        daoDB.borrarAuditor(idAuditor);
//        BORRAR DE FIREBASE


//

    }



    public List<Campania> traerListaCampanias() {
        return daoDB.getAllCampanias();
    }

    public Area traerArea(String string) {
        return daoDB.getAreaConId(string);
    }

    public void actualizarAuditor(Auditor unAuditor) {
        //ACTUALIZAR DATOS EN LA DB
        daoDB.renameAuditor(unAuditor.getIdAuditor(), unAuditor.getNombreAuditor());
        daoDB.cambiarPuestoAuditor(unAuditor.getIdAuditor(),unAuditor.getPuesto());
        //ACTUALIZAR DATOS EN FIREBASE
    }

    public void actualizarArea(Area unArea) {
        daoDB.updateArea(unArea.getIdArea(), unArea.getNombreArea(),unArea.getNombreResponsableArea(), unArea.getMailResponsableArea());
    }

    public List<Ese> traerlistaEses() {
        //pedir lista a firebase

        List<Ese>listaEses= new ArrayList<>();

        Ese ese1=new Ese();
        Ese ese2=new Ese();
        Ese ese3=new Ese();
        Ese ese4=new Ese();
        Ese ese5=new Ese();

        ese1.setIdEse("1S Seiri");
        ese2.setIdEse("2S Seiton");
        ese3.setIdEse("3S Seiso");
        ese4.setIdEse("4S Seiketsu");
        ese5.setIdEse("5S Shitsuke");

        ese1.setListaCriterios(traerCriteriosParaEse(ese1));
        listaEses.add(ese1);

        ese2.setListaCriterios(traerCriteriosParaEse(ese2));
        listaEses.add(ese2);

        ese3.setListaCriterios(traerCriteriosParaEse(ese3));
        listaEses.add(ese3);

        ese4.setListaCriterios(traerCriteriosParaEse(ese4));
        listaEses.add(ese4);

        ese5.setListaCriterios(traerCriteriosParaEse(ese5));
        listaEses.add(ese5);

        return listaEses;
    }

    public List<Criterio> traerCriteriosParaEse(Ese ese) {
        List<Criterio>listaCriterios;



        return new ArrayList<>();
    }

    public void guardarCriterio(Criterio unCrit) {
        daoDB.addCriterio(unCrit);
    }

    /*
    public void AgregarCriterioAEse(String idEse, String idCriterio) {
        daoDB.addCriterioAEse(idEse, idCriterio);}
    */
}
