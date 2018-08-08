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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class BaseController extends Connection{
    protected String table;
    
    public BaseController(String table) {
        super("musicorum", "kevin", "123");
        this.table = table;
    }
    
    public int getNextInt(){
        try {
            String query = "select max(id) as 'last' from "+table;
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            ResultSet set= stmt.executeQuery();
            if(set.first())
                return set.getInt("last")+1;
        } 
        catch (SQLException ex) {}
        return -1;
    }
    
    public ResultSet byId(int id){
        try {
            if(this.connect()==null)return null;
            String query = "select * from "+table+" where id=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set= stmt.executeQuery();
            return set;
        }
        catch (SQLException ex) {}
        return null;
    }
    
    public ResultSet getAll(){
        try {
            if(this.connect()==null)return null;
            String query = "select * from "+table;
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            ResultSet set= stmt.executeQuery();
            return set;
        }catch (SQLException ex) {}
        return null;
    }
    
    public int del(int id){
        try {
            if(this.connect()==null)return -1;
            String query = "delete from "+table+" where id=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        }
        catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return 0;
    }
    
}
