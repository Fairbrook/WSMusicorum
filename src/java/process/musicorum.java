/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;
import controllers.*;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import models.*;

/**
 *
 * @author kevin
 */
@WebService(serviceName = "musicorum")
public class musicorum {
    
    @WebMethod(operationName="login")
    public int login(@WebParam(name="profesor")Profesor profesor){
        ProfesorController profesorControler = new ProfesorController();
        return profesorControler.login(profesor);
    }
    
    @WebMethod(operationName="register")
    public int register(@WebParam(name="profesor")Profesor profesor){
        ProfesorController profesorController = new ProfesorController();
        return profesorController.create(profesor);
    }
    
    @WebMethod(operationName = "addAlumno")
    public int addAlumno(@WebParam(name="alumno")Alumno alumno){
        AlumnoController alumnoController = new AlumnoController();
        return alumnoController.create(alumno);
    }
    
    @WebMethod(operationName = "updateAlumno")
    public int updateAlumno(@WebParam(name="alumno")Alumno alumno){
        AlumnoController alumnoController = new AlumnoController();
        return alumnoController.update(alumno);
    }
    
    @WebMethod(operationName = "getAlumnos")
    public List<Alumno> getAlumnos(@WebParam(name = "IDProfesor")int id){
        AlumnoController alumnoController = new AlumnoController();
        List<Alumno> alumnosGrupos;
        GruposPorProfesorController grupos = new GruposPorProfesorController();
        List<GruposPorProfesor> listGrupos = grupos.getByProfesor(id);
        List<Alumno> alumnos = new ArrayList<>();
        
        if(listGrupos==null)return null;
        
        for(GruposPorProfesor grupo : listGrupos){
            
            alumnosGrupos = alumnoController.getByGrupo(grupo.getGrupo_id());
            if(alumnosGrupos==null)return null;
            
            for(Alumno alumno: alumnosGrupos){
                alumnos.add(alumno);
            }
        }
        return alumnos;
    }
    
    @WebMethod(operationName = "deleteAlumno")
    public int deleteAlumno(@WebParam(name="alumno")Alumno alumno){
        AlumnoController alumnoController = new AlumnoController();
        return alumnoController.delete(alumno);
    }
    
    @WebMethod(operationName = "addUnidad")
    public int addUnidad(@WebParam(name = "unidad")Unidad unidad){
        UnidadController unidadController = new UnidadController();
        return unidadController.create(unidad);
    }
    
    @WebMethod(operationName = "getUnidades")
    public List<Unidad> getUnidades(@WebParam(name = "IDProfesor")int profesor){
        EjercicioController ejericioController = new EjercicioController();
        UnidadController unidadController = new UnidadController();
        List<Ejercicio> ejercicios = ejericioController.getByProfesor(profesor);
        List<Unidad> unidades = new ArrayList<>();
        List<Unidad> result = new ArrayList<>();
        boolean flag;
        
        if(ejercicios==null)return null;
        
        for(Ejercicio ejercicio:ejercicios){
            unidades.add(unidadController.getById(ejercicio.getUnidad_id()));
        }
        for(Unidad unidad:unidades){
            flag=true;
            for(Unidad aux:result){
                if(aux.getId()==unidad.getId()){
                    flag=false;
                    break;
                }
            }
            if(flag)result.add(unidad);
        }
        return result;
    }
    
    @WebMethod
    public Unidad getUnidadById(@WebParam(name="id")int id){
        UnidadController unidadController = new UnidadController();
        return unidadController.getById(id);
    }
    
    @WebMethod(operationName = "addGrupo")
    public int addGrupo(@WebParam(name = "grupo")Grupo grupo){
        GrupoController grupoController = new GrupoController();
        return grupoController.create(grupo);
    }
    
    @WebMethod(operationName = "deleteGrupo")
    public int deleteGrupo(@WebParam(name="grupo")Grupo grupo){
        GrupoController grupoController = new GrupoController();
        return grupoController.del(grupo.getId());
    }
    
    @WebMethod(operationName = "getGruposByEscuela")
    public List<Grupo> getGruposByEscuela(@WebParam(name = "IDEscuela")int id){
        GrupoController grupoController = new GrupoController();
        return grupoController.getByEscuela(id);
    }
    
    @WebMethod(operationName = "getGruposByProfesor")
    public List<Grupo> getGrupoByProfesor(@WebParam(name = "IDProfesor")int id){
        GrupoController grupoController = new GrupoController();
        List<Grupo> grupos = new ArrayList<>();
        GruposPorProfesorController gruposPorProfesorController = new GruposPorProfesorController();
        List<GruposPorProfesor> gruposPorProfesor = gruposPorProfesorController.getByProfesor(id);
        gruposPorProfesor.forEach((grupo)->grupos.add(grupoController.getById(grupo.getGrupo_id())));
        return grupos;
    }
    
    @WebMethod(operationName = "getGrupoById")
    public Grupo getGrupoById(@WebParam(name="id")int id){
        GrupoController grupos = new GrupoController();
        return grupos.getById(id);
    }
    
    @WebMethod(operationName = "addEjercicio")
    public int addEjercicio(@WebParam(name = "ejercicio")Ejercicio ejercicio, @WebParam(name = "archivo")byte[] archivo,@WebParam(name="arreglo")String arreglo){
        ftp.SFTP.send("midi/"+ejercicio.getId()+".mid");
        EjercicioController ejercicioController = new EjercicioController();
        int result = ejercicioController.create(ejercicio);
        if(!ArchivoController.create("/opt/tomcat/midi/"+ejercicio.getId()+".mid",archivo))
            return -3;
        if(!ArchivoController.write("/opt/tomcat/json/"+ejercicio.getId()+".json", arreglo))
            return -2;
        return result;
    }
    
    public String getEjercicioFile(int id){
        return ArchivoController.read("/opt/tomcat/json/"+id+".json");
    }
    
    public Ejercicio getEjercicioById(int id){
        EjercicioController ejercicioController = new EjercicioController();
        return ejercicioController.getById(id);
    }
    
    @WebMethod(operationName = "addGruposPorProfesor")
    public int addGruposPorProfesor(@WebParam(name="grupo")Grupo grupo, @WebParam(name = "profesor")Profesor profesor){
        GruposPorProfesorController gruposPorProfesorController = new GruposPorProfesorController();
        GruposPorProfesor gruposPorProfesor = new GruposPorProfesor();
        gruposPorProfesor.setGrupo_id(grupo.getId());
        gruposPorProfesor.setProfesor_id(profesor.getId());
        return gruposPorProfesorController.create(gruposPorProfesor);
    }
    
    @WebMethod(operationName = "getEjerciciosByAlumno")
    public List<Ejercicio> getEjerciciosByAlumno(@WebParam(name="id")int id){
        EjerciciosPorAlumnoController controller = new EjerciciosPorAlumnoController();
        EjercicioController ejercicios = new EjercicioController();
        List<EjerciciosPorAlumno>ejerciciosXAlumno = controller.getByAlumno(id);
        List<Ejercicio> result = new ArrayList<>();
        List<Integer> idEjercicio = new ArrayList<>();
        ejerciciosXAlumno.forEach(x->{
            Ejercicio ejercicio = ejercicios.getById(x.getEjercicio_id());
            if(!idEjercicio.contains(x.getEjercicio_id())){
                result.add(ejercicio);
                idEjercicio.add(x.getEjercicio_id());
            }
        });
        return result;
    }
    
    @WebMethod(operationName = "getEstadisticas")
    public List<Estadistica> getEstadisticas(@WebParam(name="id_alumno")int alumno,@WebParam(name="id_ejercicio") int ejercicio){
        EjerciciosPorAlumnoController controller = new EjerciciosPorAlumnoController();
        return controller.getEstadisticas(ejercicio, alumno);
    }
    
    @WebMethod(operationName = "getEjercicioTotal")
    public int getEjercicioTotal(@WebParam(name="ejercicio")Ejercicio ejercicio, @WebParam(name="Alumno")Alumno alumno){
        EjerciciosPorAlumnoController controller = new EjerciciosPorAlumnoController();
        return controller.getTotal(ejercicio.getId(), alumno.getId());
    }
    
    @WebMethod(operationName = "getEjercicioExitos")
    public int getEjercicioExitos(@WebParam(name="ejercicio")Ejercicio ejercicio, @WebParam(name="Alumno")Alumno alumno){
        EjerciciosPorAlumnoController controller = new EjerciciosPorAlumnoController();
        return controller.getExitos(ejercicio.getId(), alumno.getId());
    }
    
    @WebMethod(operationName = "addEjerciciosPorAlumno")
    public int addEjerciciosPorAlumno(@WebParam(name="EjerciciosPorAlumno")EjerciciosPorAlumno ejercicio){
        EjerciciosPorAlumnoController ejerciciosPorAlumnoController = new EjerciciosPorAlumnoController();
        return ejerciciosPorAlumnoController.create(ejercicio);
    }
    
    @WebMethod(operationName = "getEjercicios")
    public List<Ejercicio> getEjercicios(@WebParam(name="IDProfesor")int id){
        EjercicioController ejercicioController = new EjercicioController();
        return ejercicioController.getByProfesor(id);
    }
    
    @WebMethod(operationName = "addEscuela")
    public int addEscuela(@WebParam(name="escuela")Escuela escuela){
        EscuelaController escuelaController = new EscuelaController();
        return escuelaController.create(escuela);
    }
    
    @WebMethod(operationName = "getEscuelas")
    public List<Escuela> getEscuelas(){
        EscuelaController escuelaController = new EscuelaController();
        return escuelaController.read();
    }
    
    @WebMethod(operationName="getProfesor")
    public Profesor getProfesor(@WebParam(name="id")int id){
        ProfesorController profesorController = new ProfesorController();
        return profesorController.getById(id);
    }
    
    @WebMethod(operationName = "checkUsername")
    public boolean checkUsername(@WebParam(name = "username")String username){
        ProfesorController profesorController = new ProfesorController();
        return profesorController.checkUsername(username);
    }
    
    @WebMethod(operationName = "escuelaLogin")
    public boolean escuelaLogin(@WebParam(name = "escuela")Escuela escuela){
        EscuelaController escuelaController = new EscuelaController();
        return escuelaController.login(escuela);
    }
}
