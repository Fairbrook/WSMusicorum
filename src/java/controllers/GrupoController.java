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
import models.Grupo;

/**
 *
 * @author kevin
 */
public class GrupoController extends BaseController{
    
    public GrupoController() {
        super("grupo");
    }
    
    public int create(Grupo grupo){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Nombre,"
                    + " id_escuela) values (?,?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            grupo.setId(this.getNextInt());
            
            stmt.setInt(1, grupo.getId());
            stmt.setString(2, grupo.getNombre());
            stmt.setInt(3, grupo.getId_escuela());
            
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
    
    public List<Grupo> read(){
        try {
            List<Grupo>result = new ArrayList<>();
            Grupo grupo;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                grupo = new Grupo();
                grupo.setId(set.getInt("ID"));
                grupo.setNombre(set.getString("Nombre"));
                grupo.setId_escuela(set.getInt("id_escuela"));
                result.add(grupo);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Grupo grupo){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Nombre = ?,"
                    + " SET id_escuela=? "
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, grupo.getNombre());
            stmt.setInt(2, grupo.getId_escuela());
            stmt.setInt(3, grupo.getId());
            return stmt.executeUpdate();
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(Grupo grupo){
        return this.del(grupo.getId());
    }
    
    public Grupo getById(int id){
        try {
            Grupo grupo = new Grupo();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                grupo.setId(id);
                grupo.setNombre(set.getString("Nombre"));
                grupo.setId_escuela(set.getInt("id_escuela"));
            }
            return grupo;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public List<Grupo> getByEscuela(int id){
        try {
            if(this.connect()==null)return null;
            Grupo grupo;
            List<Grupo> grupos = new ArrayList<>();
            String query = "select * from "+table+" where id_escuela=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            while(set.next()){
                grupo = new Grupo();
                grupo.setId(set.getInt("ID"));
                grupo.setNombre(set.getString("Nombre"));
                grupos.add(grupo);
            }
            return grupos;
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
}
