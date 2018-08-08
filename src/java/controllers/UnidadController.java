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
import models.Unidad;

/**
 *
 * @author kevin
 */
public class UnidadController extends BaseController{

    public UnidadController() {
        super("unidad");
    }

    public int create(Unidad unidad){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Nombre) values (?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            unidad.setId(this.getNextInt());
            
            stmt.setInt(1, unidad.getId());
            stmt.setString(2, unidad.getNombre());
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return unidad.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<Unidad> read(){
        try {
            List<Unidad>result = new ArrayList<>();
            Unidad unidad;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                unidad = new Unidad();
                unidad.setId(set.getInt("ID"));
                unidad.setNombre(set.getString("Nombre"));
                result.add(unidad);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Unidad unidad){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Nombre = ? "
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, unidad.getNombre());
            stmt.setInt(2, unidad.getId());
            return stmt.executeUpdate();
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(Unidad unidad){
        return this.del(unidad.getId());
    }
    
    public Unidad getById(int id){
        try {
            Unidad unidad = new Unidad();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                unidad.setId(id);
                unidad.setNombre(set.getString("Nombre"));
            }
            return unidad;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
}
