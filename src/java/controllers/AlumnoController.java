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
import java.util.UUID;
import models.Alumno;
/**
 *
 * @author kevin
 */
public class AlumnoController extends BaseController{
    
    public AlumnoController() {
        super("alumno");
    }
    
    public int create(Alumno alumno){
        try {
            if(this.connect()==null)return -1;
            String query = "insert into "
                    +table
                    +" (ID,"
                    + " Nombre,"
                    + " ApPaterno,"
                    + " ApMaterno,"
                    + " Anotaciones,"
                    + " Codigo,"
                    + " Grupo_id) values (?,?,?,?,?,?,?)";
            
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            String codigo = generateString();
            this.getConnection().setAutoCommit(false);
            alumno.setId(this.getNextInt());
            while(!checkCodigo(codigo))codigo = generateString();
            stmt.setInt(1, alumno.getId());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getApPaterno());
            stmt.setString(4, alumno.getApMaterno());
            stmt.setString(5, alumno.getAnotaciones());
            stmt.setString(6, codigo);
            stmt.setInt(7, alumno.getGrupo_id());
            
            if(stmt.executeUpdate()==1){
                this.getConnection().commit();
                return alumno.getId();
            }else{
                this.getConnection().rollback();
                return -1;
            }
        }
        catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public List<Alumno> read(){
        try {
            List<Alumno>result = new ArrayList<>();
            Alumno alumno;
            ResultSet set = this.getAll();
            if(set == null)return null;
            while(set.next()){
                alumno = new Alumno();
                alumno.setId(set.getInt("ID"));
                alumno.setNombre(set.getString("Nombre"));
                alumno.setApPaterno(set.getString("ApPaterno"));
                alumno.setApMaterno(set.getString("ApMaterno"));
                alumno.setAnotaciones(set.getString("Anotaciones"));
                alumno.setCodigo(set.getString("Codigo"));
                alumno.setGrupo_id(set.getInt("Grupo_id"));
                result.add(alumno);
            }
            return result;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public int update(Alumno alumno){
        try {
            if(this.connect()==null)return -1;
            String query = "UPDATE "+ table
                    +" SET Nombre = ?, "
                    + "ApPaterno = ?, "
                    + "ApMaterno = ?, "
                    + "Anotaciones = ?, "
                    + "Grupo_id = ? "
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApPaterno());
            stmt.setString(3, alumno.getApMaterno());
            stmt.setString(4, alumno.getAnotaciones());
            stmt.setInt(5, alumno.getGrupo_id());
            stmt.setInt(6, alumno.getId());
            return stmt.executeUpdate();
        }
        catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return -1;
    }
    
    public int delete(Alumno alumno){
        return this.del(alumno.getId());
    }
    
    public Alumno getById(int id){
        try {
            Alumno alumno = new Alumno();
            ResultSet set = this.byId(id);
            if(set == null)return null;
            if(set.first()){
                alumno.setId(id);
                alumno.setNombre(set.getString("Nombre"));
                alumno.setApPaterno(set.getString("ApPaterno"));
                alumno.setApMaterno(set.getString("ApMaterno"));
                alumno.setCodigo(set.getString("Codigo"));
                alumno.setAnotaciones(set.getString("Anotaciones"));
                alumno.setGrupo_id(set.getInt("Grupo_id"));
            }
            return alumno;
        } catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public List<Alumno> getByGrupo(int id){
        try {
            if(this.connect()==null)return null;
            List<Alumno>result = new ArrayList<>();
            String query = "select * from "+table+" where Grupo_id=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            Alumno alumno;
            ResultSet set = stmt.executeQuery();
            if(set==null)return new ArrayList<>();
            while(set.next()){
                alumno = new Alumno();
                alumno.setId(set.getInt("ID"));
                alumno.setNombre(set.getString("Nombre"));
                alumno.setApPaterno(set.getString("ApPaterno"));
                alumno.setApMaterno(set.getString("ApMaterno"));
                alumno.setAnotaciones(set.getString("Anotaciones"));
                alumno.setCodigo(set.getString("Codigo"));
                alumno.setGrupo_id(set.getInt("Grupo_id"));
                result.add(alumno);
            }
            return result;
        }
        catch (SQLException ex) {}
        finally {try{this.getConnection().close();}catch(SQLException ex){}}
        return null;
    }
    
    public boolean checkCodigo(String codigo){
        try {
            String query="select codigo from "
                    +table
                    +" where codigo=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setString(1, codigo);
            ResultSet set = stmt.executeQuery();
            return !set.first();
        }
        catch (SQLException ex) {}
        return false;
    }
    
    public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 7);
    }
}
