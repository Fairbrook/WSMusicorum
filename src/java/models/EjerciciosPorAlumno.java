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
public class EjerciciosPorAlumno implements Serializable{
    private int id;
    private boolean exito;
    private String fecha;
    private int ejercicio_id;
    private int alumno_id;

    public EjerciciosPorAlumno() {
        id=-1;
        exito=false;
        fecha="000-00-00";
        ejercicio_id = -1;
        alumno_id = -1;
    }

    public EjerciciosPorAlumno(int id, boolean exito, String fecha, int ejercicio_id, int alumno_id) {
        this.id = id;
        this.exito = exito;
        this.fecha = fecha;
        this.ejercicio_id = ejercicio_id;
        this.alumno_id = alumno_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEjercicio_id() {
        return ejercicio_id;
    }

    public void setEjercicio_id(int ejercicio_id) {
        this.ejercicio_id = ejercicio_id;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }
    
    
}
