/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.EjerciciosPorAlumno;
import models.Estadistica;

/**
 *
 * @author kevin
 */
public class EjerciciosPorAlumnoController extends BaseController {

    public EjerciciosPorAlumnoController() {
        super("ejerciciosporalumno");
    }

    public int create(EjerciciosPorAlumno ejercicio) {
        try {
            if (this.connect() == null) {
                return -1;
            }
            String query = "insert into "
                    + table
                    + " (ID,"
                    + " Exito,"
                    + " Fecha,"
                    + " Ejercicio_id,"
                    + " Alumno_id) values (?,?,NOW(),?,?)";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);

            this.getConnection().setAutoCommit(false);
            ejercicio.setId(this.getNextInt());

            stmt.setInt(1, ejercicio.getId());
            stmt.setBoolean(2, ejercicio.isExito());
            //stmt.setDate(3, Date.valueOf(ejercicio.getFecha()));
            stmt.setInt(3, ejercicio.getEjercicio_id());
            stmt.setInt(4, ejercicio.getAlumno_id());
            if (stmt.executeUpdate() == 1) {
                this.getConnection().commit();
                return ejercicio.getId();
            } else {
                this.getConnection().rollback();
                return -1;
            }
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return -1;
    }

    public List<EjerciciosPorAlumno> read() {
        try {
            List<EjerciciosPorAlumno> result = new ArrayList<>();
            EjerciciosPorAlumno ejercicio;
            ResultSet set = this.getAll();
            if (set == null) {
                return null;
            }
            while (set.next()) {
                ejercicio = new EjerciciosPorAlumno();
                ejercicio.setId(set.getInt("ID"));
                DateFormat df = new SimpleDateFormat("yyy-MM-dd");
                ejercicio.setFecha(df.format(set.getDate("Fecha")));
                ejercicio.setExito(set.getBoolean("Exito"));
                ejercicio.setEjercicio_id(set.getInt("Ejercicio_id"));
                ejercicio.setAlumno_id(set.getInt("Alumno_id"));
                result.add(ejercicio);
            }
            return result;
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public int update(EjerciciosPorAlumno ejercicio) {
        try {
            if (this.connect() == null) {
                return -1;
            }
            String query = "UPDATE " + table
                    + "SET Exito = ?, "
                    + "Fecha = ?, "
                    + "Ejercicio_id = ?, "
                    + "Alumno_id=?"
                    + "WHERE ID=?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setBoolean(1, ejercicio.isExito());
            stmt.setDate(2, Date.valueOf(ejercicio.getFecha()));
            stmt.setInt(3, ejercicio.getEjercicio_id());
            stmt.setInt(4, ejercicio.getAlumno_id());
            stmt.setInt(5, ejercicio.getId());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return 0;
    }

    public int delete(EjerciciosPorAlumno ejercicio) {
        return this.del(ejercicio.getId());
    }

    public EjerciciosPorAlumno getById(int id) {
        try {
            EjerciciosPorAlumno ejercicio = new EjerciciosPorAlumno();
            ResultSet set = this.byId(id);
            if (set == null) {
                return null;
            }
            if (set.first()) {
                ejercicio.setId(id);
                ejercicio.setExito(set.getBoolean("Exito"));
                DateFormat df = new SimpleDateFormat("yyy-MM-dd");
                ejercicio.setFecha(df.format(set.getDate("Fecha")));
                ejercicio.setEjercicio_id(set.getInt("Ejercicio_id"));
                ejercicio.setAlumno_id(set.getInt("Alumno_id"));
            }
            return ejercicio;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public List<EjerciciosPorAlumno> getByAlumno(int id) {
        try {
            if (this.connect() == null) {
                return null;
            }
            List<EjerciciosPorAlumno> result = new ArrayList<>();
            EjerciciosPorAlumno ejercicio;
            String query = "SELECT * FROM " + table
                    + " WHERE Alumno_id = ? ";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set == null) {
                return null;
            }
            while (set.next()) {
                ejercicio = new EjerciciosPorAlumno();
                ejercicio.setId(set.getInt("ID"));
                DateFormat df = new SimpleDateFormat("yyy-MM-dd");
                ejercicio.setFecha(df.format(set.getDate("Fecha")));
                ejercicio.setExito(set.getBoolean("Exito"));
                ejercicio.setEjercicio_id(set.getInt("Ejercicio_id"));
                ejercicio.setAlumno_id(set.getInt("Alumno_id"));
                result.add(ejercicio);
            }
            return result;
        } catch (SQLException ex) {ex.printStackTrace();
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public Integer getTotal(int ejercicio, int alumno) {
        try {
            if (this.connect() == null) {
                return -1;
            }
            String query = "SELECT count(*) as num FROM " + table
                    + " WHERE Alumno_id = ? "
                    + "AND Ejercicio_id = ?";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, alumno);
            stmt.setInt(2, ejercicio);
            ResultSet set = stmt.executeQuery();            
            if (set == null) {
                return -1;
            }
            if (set.first()) {
                return set.getInt("num");
            }
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return 0;
    }
    
    public Integer getExitos(int ejercicio, int alumno) {
        try {
            if (this.connect() == null) {
                return -1;
            }
            String query = "SELECT count(*) as num FROM " + table
                    + " WHERE Alumno_id = ? "
                    + "AND Ejercicio_id = ? "
                    + "AND Exito=1";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, alumno);
            stmt.setInt(2, ejercicio);
            ResultSet set = stmt.executeQuery();            
            if (set == null) {
                return -1;
            }
            if (set.first()) {
                return set.getInt("num");
            }
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return 0;
    }
    
    public List<Estadistica> getEstadisticas(int ejercicio, int alumno){
        try {
            if (this.connect() == null) {
                return null;
            }
            String query = "SELECT count(ID) AS Num,"
                    + " Exito,"
                    + " Fecha FROM "
                    + table
                    + " WHERE Ejercicio_id = ?"
                    + " AND Alumno_id = ?"
                    + " GROUP BY Fecha, Exito";
            PreparedStatement stmt = this.getConnection().prepareStatement(query);
            stmt.setInt(1, ejercicio);
            stmt.setInt(2, alumno);
            ResultSet set = stmt.executeQuery();
            Estadistica estadistica;
            List<Estadistica> estadisticas = new ArrayList<>();
            if(set==null)return null;
            while(set.next()){
                estadistica = new Estadistica();
                estadistica.setExito(set.getBoolean("Exito"));
                DateFormat df = new SimpleDateFormat("yyy-MM-dd");
                estadistica.setFecha(df.format(set.getDate("Fecha")));
                estadistica.setNum(set.getInt("Num"));
                estadisticas.add(estadistica);
            }
            return estadisticas;
        } catch (SQLException ex) {
        } finally {
            try {
                this.getConnection().close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

}
