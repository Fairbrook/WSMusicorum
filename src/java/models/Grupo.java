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
public class Grupo implements Serializable{
    private int id;
    private String nombre;
    private int id_escuela;

    public Grupo() {
        id = -1;
        nombre = "";
        id_escuela=-1;
    }

    public Grupo(int id, String nombre, int id_escuela) {
        this.id = id;
        this.nombre = nombre;
        this.id_escuela = id_escuela;
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

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
