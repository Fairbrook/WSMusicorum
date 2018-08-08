/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import database.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Profesor;

/**
 *
 * @author kevin
 */
public class ProfesorController extends BaseController{
    
    public ProfesorController() {
        super("profesor");
    }
    
    public int create(Profesor profesor){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    + " (ID,"
                    + " Username,"
                    + " Nombre,"
                    + " ApPaterno,"
                    + " ApMaterno,"
                    + " Password, "
                    + " id_escuela) values (?,?,?,?,?,?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            
            this.getConnection().setAutoCommit(false);
            if(!checkUsername(profesor.getUsername()))return -2;
            profesor.setId(this.getNextInt());
            
            stmt.setInt(1, profesor.getId());
            stmt.setString(2, profesor.getUsername());
            stmt.setString(3, profesor.getNombre());
            stmt.setString(4, profesor.getApPaterno());
            stmt.setString(5, profesor.getApMaterno());
            stmt.setString(6, profesor.getPassword());
            stmt.setInt(7, profesor.getId_escuela());
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return profesor.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }catch (SQLException ex) {ex.printStackTrace();}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<Profesor> read(){
        try {
            List<Profesor>result = new ArrayList<>();
            Profesor profesor;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                profesor = new Profesor();
                profesor.setId(set.getInt("ID"));
                profesor.setUsername(set.getString("username"));
                profesor.setNombre(set.getString("Nombre"));
                profesor.setApPaterno(set.getString("ApPaterno"));
                profesor.setApMaterno(set.getString("ApMaterno"));
                profesor.setId_escuela(set.getInt("id_escuela"));
                result.add(profesor);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Profesor profesor){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    + " SET Nombre = ?, "
                    + "Username=?, "
                    + "ApPaterno = ?, "
                    + "ApMaterno = ?, "
                    + "Password = ?, "
                    + "id_escuela = ? "
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, profesor.getNombre());
            stmt.setString(2, profesor.getUsername());
            stmt.setString(3, profesor.getApPaterno());
            stmt.setString(4, profesor.getApMaterno());
            stmt.setString(5, profesor.getPassword());
            stmt.setInt(6, profesor.getId());
            stmt.setInt(7, profesor.getId_escuela());
            return stmt.executeUpdate();
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
    public int delete(Profesor profesor){
        return this.del(profesor.getId());
    }
    
    public Profesor getById(int id){
        try {
            Profesor profesor = new Profesor();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                profesor.setId(id);
                profesor.setNombre(set.getString("Nombre"));
                profesor.setApPaterno(set.getString("ApPaterno"));
                profesor.setApMaterno(set.getString("ApMaterno"));
                profesor.setUsername(set.getString("username"));
                profesor.setId_escuela(set.getInt("id_escuela"));
            }
            return profesor;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int login(Profesor profesor){
        try {
            if(this.connect()==null)return -1;
            String query = "select * from "
                    +table
                    +" where username=? and password=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, profesor.getUsername());
            stmt.setString(2, profesor.getPassword());
            ResultSet set = stmt.executeQuery();
            if(set.first()){
                return set.getInt("ID");
            }
            else {
                return -1;
            }
        } catch (SQLException ex) {
            return -1;
        }
    }
    
    public boolean checkUsername(String username){
        try {
            if(this.getConnection()==null)
                this.connect();
            String query="select username from "
                    +table
                    +" where username=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            ResultSet set = stmt.executeQuery();
            return !set.first();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName())
                    .log(Level.SEVERE,null,ex);
            return false;
        }
    }
}
