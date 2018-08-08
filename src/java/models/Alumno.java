/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author kevin
 */
public class Alumno implements Serializable{
    private int id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String anotaciones;
    private String codigo;
    private int grupo_id;

    public Alumno() {
        id=-1;
        nombre="";
        apPaterno="";
        apMaterno="";
        anotaciones="";
        codigo="";
        grupo_id=-1;
    }

    public Alumno(int id, String nombre, String apPaterno, String apMaterno, String anotaciones, String codigo, int grupo_id) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.anotaciones = anotaciones;
        this.codigo = codigo;
        this.grupo_id = grupo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }
    
    
}
