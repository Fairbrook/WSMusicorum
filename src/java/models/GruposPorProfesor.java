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
public class GruposPorProfesor implements Serializable{
    private int id;
    private int profesor_id;
    private int grupo_id;

    public GruposPorProfesor() {
        id = -1;
        profesor_id = -1;
        grupo_id = -1;
        
    }

    public GruposPorProfesor(int id, int profesor_id, int grupo_id) {
        this.id = id;
        this.profesor_id = profesor_id;
        this.grupo_id = grupo_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfesor_id() {
        return profesor_id;
    }

    public void setProfesor_id(int profesor_id) {
        this.profesor_id = profesor_id;
    }

    public int getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }
    
    
}
