/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Chofer extends Persona{
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs, rd;
    PreparedStatement pd, ps, pf, pg;
    String IDChofer;
    boolean estado;
    
    public Chofer(){
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
        IDChofer = "";
        estado = false;
    }

    public String getIDChofer() {
        return IDChofer;
    }

    public void setIDChofer(String IDChofer) {
        this.IDChofer = IDChofer;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRUN() {
        return RUN;
    }

    public void setRUN(String RUN) {
        this.RUN = RUN;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public ArrayList<String> choferesDisponibles(){
        ArrayList<String> choferes = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM chofer WHERE estado="+true+"");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                choferes.add(rs.getString("id_chofer"));
                choferes.add(rs.getString("nombre"));
                choferes.add(rs.getString("edad"));
                choferes.add(rs.getString("telefono"));
                choferes.add(rs.getString("correo"));
                choferes.add(rs.getString("ubicacion"));
                choferes.add(rs.getString("direccion"));
                choferes.add(Boolean.toString(rs.getBoolean("estado")));
            }
            return choferes;
        }catch(Exception e){
            return choferes;
        }
    }
    
    public Chofer modificarChofer(String run){
        Chofer chofer = null;
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM chofer WHERE run='"+run+"'");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                chofer = new Chofer();
                chofer.setRUN(rs.getString("run"));
            }
            return chofer;
        }catch(Exception e){
            return chofer;
        }
    }
    
    public Chofer verificarChofer(String run){
        Chofer chofer = null;
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM chofer WHERE run='"+run+"'");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                chofer = new Chofer();
                chofer.setRUN(rs.getString("run"));
                chofer.setEstado(rd.getBoolean("estado"));
            }
            return chofer;
        }catch(Exception e){
            return chofer;
        }
    }
    
    public Chofer agregarChofer(String ubicacion, String nombre, int edad, String run, String telefono, String direccion, String correo, boolean estado){
        Chofer chofer = null;
        Connection accesoDB = conexion.conectar();
        int rsu = 0;
            try{
                ps = accesoDB.prepareStatement("INSERT INTO chofer (id_chofer, nombre, edad, telefono, correo, ubicacion, direccion, estado) " +
                "VALUES ('"+run+"', '"+nombre+"', "+edad+", '"+telefono+"', '"+correo+"', '"+ubicacion+"', '"+direccion+"', "+true+")");
                rsu = ps.executeUpdate();
                return chofer;
            }catch(Exception e){
                return chofer;
        }
    }
    
    public ArrayList<String> buscarChofer(String run){
        ArrayList<String> choferes = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
            try{
                PreparedStatement pd = accesoDB.prepareStatement("SELECT * FROM chofer WHERE id_chofer='"+run+"'");
                rd = pd.executeQuery();
                while(rd.next()){
                    choferes.add(rd.getString("id_chofer"));
                    choferes.add(rd.getString("nombre"));
                    choferes.add(rd.getString("edad"));
                    choferes.add(rd.getString("telefono"));
                    choferes.add(rd.getString("correo"));
                    choferes.add(rd.getString("ubicacion"));
                    choferes.add(rd.getString("direccion"));
                    choferes.add(Boolean.toString(rd.getBoolean("estado")));
                }
                return choferes;
            }catch(Exception e){
                return choferes;
        }
    }
    
}
