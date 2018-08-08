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
import models.Ejercicio;

/**
 *
 * @author kevin
 */
public class EjercicioController extends BaseController{
    
    public EjercicioController() {
        super("ejercicio");
    }
    
    public int create(Ejercicio ejercicio){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Nombre,"
                    + " Archivo,"
                    + " Unidad_id,"
                    + " Profesor_id) values (?,?,?,?,?)";
            
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            ejercicio.setId(this.getNextInt());
            
            stmt.setInt(1, ejercicio.getId());
            stmt.setString(2, ejercicio.getNombre());
            stmt.setInt(3, ejercicio.getId());
            stmt.setInt(4, ejercicio.getUnidad_id());
            stmt.setInt(5, ejercicio.getProfesor_id());
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return ejercicio.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }
        catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<Ejercicio> read(){
        try {
            List<Ejercicio>result = new ArrayList<>();
            Ejercicio ejercicio;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                ejercicio = new Ejercicio();
                ejercicio.setId(set.getInt("ID"));
                ejercicio.setNombre(set.getString("Nombre"));
                ejercicio.setArchivo(set.getInt("Archivo"));
                ejercicio.setUnidad_id(set.getInt("Unidad_id"));
                ejercicio.setProfesor_id(set.getInt("Porfesor_id"));
                result.add(ejercicio);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Ejercicio ejercicio){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Nombre = ?, "
                    + "Archivo = ?, "
                    + "Unidad_id = ?, "
                    + "Profesor_id=?"
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, ejercicio.getNombre());
            stmt.setInt(2,ejercicio.getArchivo());
            stmt.setInt(3, ejercicio.getUnidad_id());
            stmt.setInt(4, ejercicio.getProfesor_id());
            stmt.setInt(5, ejercicio.getId());
            return stmt.executeUpdate();
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(Ejercicio ejercicio){
        return this.del(ejercicio.getId());
    }
    
    public Ejercicio getById(int id){
        try {
            Ejercicio ejercicio = new Ejercicio();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                ejercicio.setId(id);
                ejercicio.setNombre(set.getString("Nombre"));
                ejercicio.setArchivo(set.getInt("Archivo"));
                ejercicio.setUnidad_id(set.getInt("Unidad_id"));
                ejercicio.setProfesor_id(set.getInt("Profesor_id"));
            }
            return ejercicio;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public List<Ejercicio> getByProfesor(int id){
        try {
            if(this.connect()==null)return new ArrayList<>();
            String query = "select * from "+table+" where Profesor_id=?";
            List<Ejercicio>result = new ArrayList<>();
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            Ejercicio ejercicio;
            ResultSet set = stmt.executeQuery();
            while(set.next()){
                ejercicio = new Ejercicio();
                ejercicio.setId(set.getInt("ID"));
                ejercicio.setNombre(set.getString("Nombre"));
                ejercicio.setArchivo(set.getInt("Archivo"));
                ejercicio.setUnidad_id(set.getInt("Unidad_id"));
                ejercicio.setProfesor_id(set.getInt("Profesor_id"));
                result.add(ejercicio);
            }
            return result;
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
}
