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
public class Profesor implements Serializable{
    private int id;
    private String username;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String password;
    private int id_escuela;

    public Profesor() {
        id = -1;
        username="";
        nombre="";
        apPaterno="";
        apMaterno="";
        password="";
        id_escuela=-1;
    }

    public Profesor(int id, String username, String nombre, String apPaterno, String apMaterno, String password, int id_escuela) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.password = password;
        this.id_escuela = id_escuela;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_escuela() {
        return id_escuela;
    }

    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
    }
    
    
}
