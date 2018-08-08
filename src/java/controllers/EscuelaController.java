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
import models.Escuela;

/**
 *
 * @author kevin
 */
public class EscuelaController extends BaseController{
    
    public EscuelaController() {
        super("escuela");
    }
    
    public int create(Escuela escuela){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Nombre,"
                    + " password) values (?,?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            escuela.setId(this.getNextInt());
            
            stmt.setInt(1, escuela.getId());
            stmt.setString(2, escuela.getNombre());
            stmt.setString(3, escuela.getPassword());
            
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return escuela.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<Escuela> read(){
        try {
            List<Escuela>result = new ArrayList<>();
            Escuela escuela;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                escuela = new Escuela();
                escuela.setId(set.getInt("ID"));
                escuela.setNombre(set.getString("Nombre"));
                result.add(escuela);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Escuela escuela){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Nombre = ?,"
                    + " SET password =?"
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, escuela.getNombre());
            stmt.setString(2, escuela.getPassword());
            stmt.setInt(3, escuela.getId());
            return stmt.executeUpdate();
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(Escuela escuela){
        return this.del(escuela.getId());
    }
    
    public Escuela getById(int id){
        try {
            Escuela escuela = new Escuela();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                escuela.setId(id);
                escuela.setNombre(set.getString("Nombre"));
            }
            return escuela;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public boolean login(Escuela escuela){
        try {
            if(this.connect()==null)return false;
            String query = "select * from "
                    +table
                    +" where id=? and password=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, escuela.getId());
            stmt.setString(2, escuela.getPassword());
            ResultSet set = stmt.executeQuery();
            return set.first();
        }catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return false;
    }
}
