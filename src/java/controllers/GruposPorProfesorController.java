/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.GruposPorProfesor;

/**
 *
 * @author kevin
 */
public class GruposPorProfesorController extends BaseController{
    
    public GruposPorProfesorController() {
        super("gruposporprofesor");
    }
    
    public int create(GruposPorProfesor grupo){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Profesor_id,"
                    + " Grupo_id) values (?,?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            grupo.setId(this.getNextInt());
            
            stmt.setInt(1, grupo.getId());
            stmt.setInt(2, grupo.getProfesor_id());
            stmt.setInt(3, grupo.getGrupo_id());
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return grupo.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<GruposPorProfesor> read(){
        try {
            List<GruposPorProfesor>result = new ArrayList<>();
            GruposPorProfesor grupo;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                grupo = new GruposPorProfesor();
                grupo.setId(set.getInt("ID"));
                grupo.setProfesor_id(set.getInt("Profesor_id"));
                grupo.setGrupo_id(set.getInt("Grupo_id"));
                result.add(grupo);
            }
            return result;
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(GruposPorProfesor grupo){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Profesor_id = ?,"
                    + "Grupo_id = ? "
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, grupo.getProfesor_id());
            stmt.setInt(2, grupo.getGrupo_id());
            stmt.setInt(3, grupo.getId());
            return stmt.executeUpdate();
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(GruposPorProfesor grupo){
        return this.del(grupo.getId());
    }
    
    public GruposPorProfesor getById(int id){
        try {
            GruposPorProfesor grupo = new GruposPorProfesor();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                grupo.setId(id);
                grupo.setProfesor_id(set.getInt("Profesor_id"));
                grupo.setGrupo_id(set.getInt("Grupo_id"));
            }
            return grupo;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    
    public List<GruposPorProfesor> getByProfesor(int id){
        try {
            if(this.connect()==null)return null;
            List<GruposPorProfesor>result = new ArrayList<>();
            String query = "select * from "+table+" where profesor_id=?";
            PreparedStatement stm = this.getConnection().prepareStatement(query);
            stm.setInt(1, id);
            GruposPorProfesor grupo;
            ResultSet set = stm.executeQuery();
            while(set.next()){
                grupo = new GruposPorProfesor();
                grupo.setId(set.getInt("ID"));
                grupo.setProfesor_id(set.getInt("Profesor_id"));
                grupo.setGrupo_id(set.getInt("Grupo_id"));
                result.add(grupo);
            }
            return result;
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
}
