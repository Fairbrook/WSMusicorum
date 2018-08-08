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
public class Ejercicio implements Serializable{
    private int id;
    private String nombre;
    private int archivo;
    private int unidad_id;
    private int profesor_id;

    public Ejercicio() {
        id=-1;
        nombre="";
        archivo=0;
        unidad_id=-1;
        profesor_id=-1;
    }

    public Ejercicio(int id, String nombre, int archivo, int unidad_id, int profesor_id) {
        this.id = id;
        this.nombre = nombre;
        this.archivo = archivo;
        this.unidad_id = unidad_id;
        this.profesor_id = profesor_id;
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

    public int getArchivo() {
        return archivo;
    }

    public void setArchivo(int archivo) {
        this.archivo = archivo;
    }

    public int getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(int unidad_id) {
        this.unidad_id = unidad_id;
    }

    public int getProfesor_id() {
        return profesor_id;
    }

    public void setProfesor_id(int profesor_id) {
        this.profesor_id = profesor_id;
    }
    
    
    
}
