package com.example.labsoftware1.personamaterial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.UUID;

/**
 * Created by Lab Software 1 on 20/05/2017.
 */

public class Persona {
    private String uuid;
    private String urlfoto;
    private String idfoto;
    private String cedula;
    private String nombre;
    private String apellido;

    public Persona(){

    }

    public Persona(String urlfoto, String cedula, String nombre, String apellido, String idfoto) {
        this.uuid= UUID.randomUUID().toString();
        this.urlfoto = urlfoto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idfoto=idfoto;
    }

    public Persona(String uuid, String urlfoto, String idfoto, String cedula, String nombre, String apellido) {
        this.uuid = uuid;
        this.urlfoto = urlfoto;
        this.idfoto = idfoto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(String idfoto) {
        this.idfoto = idfoto;
    }



    public void guardar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "INSERT INTO Personas values('"
                +this.getUuid()+"','"
                +this.getUrlfoto()+"','"
                +this.getCedula()+"','"
                +this.getNombre()+"','"
                +this.getApellido()+"','"
                +this.getIdfoto()+"')";

        db.execSQL(sql);

        //insert forma 2

      /*  ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("foto",this.getFoto());
        nuevoRegistro.put("cedula",this.getCedula());
        nuevoRegistro.put("nombre",this.getNombre());
        nuevoRegistro.put("apellido",this.getApellido());
        nuevoRegistro.put("sexo",this.getSexo());
        nuevoRegistro.put("pasatiempo",this.getPasatiempo());

        db.insert("Personas",null,nuevoRegistro);
*/
        //cerrar conexion
        db.close();

    }

    public void eliminar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null);
        db = aux.getWritableDatabase();

        sql = "DELETE FROM Personas where cedula='"+this.getCedula()+"'";
        db.execSQL(sql);
        db.close();

    }

    public void modificar(Context contexto){
        //declarar las variables
        SQLiteDatabase db;
        String sql;

        //Abrir la conexion de base datos en modo escritura
        PersonasSQLiteOpenHelper  aux = new PersonasSQLiteOpenHelper(contexto,"DBPersonas",null);
        db = aux.getWritableDatabase();

        //insertar forma 1
        sql = "UPDATE Personas SET nombre='"+this.getNombre()+"', apellido='"+this.getApellido()+"' where cedula ='"+this.getCedula()+"'";

        db.execSQL(sql);

        //cerrar conexion
        db.close();

    }



}