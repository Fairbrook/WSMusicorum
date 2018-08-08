/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author kevin
 */
public class Connection {
    private java.sql.Connection connection;
    protected String database;
    protected String user;
    protected String password;
    

    public Connection() {
        this.database="";
        this.user="root";
        this.password="";
    }

    public Connection(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
    }
    
    
    
    protected java.sql.Connection connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"+this.database,
                    this.user,
                    this.password
                    
            );
            return this.connection;
        }catch( ClassNotFoundException | SQLException ex){
           Logger.getLogger(Connection.class.getName())
                    .log(Level.SEVERE,null,ex);
           return null;
        }
    }

    public java.sql.Connection getConnection(){
        return this.connection;
    }
}
