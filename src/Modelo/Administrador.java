/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* validador */
/* https://stackoverflow.com/questions/23905985/validation-input-to-be-string-only-and-numbers-only-java */

package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Darto
 */
public class Administrador extends Persona{
    String IDAdministrador;
    String password;
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs;
    
    public Administrador(){
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
        IDAdministrador = "";
        password = "";
    }

    public String getIDAdministrador() {
        return IDAdministrador;
    }

    public void setIDAdministrador(String IDAdministrador) {
        this.IDAdministrador = IDAdministrador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    public boolean validarRut(String rut) {
 
        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
        int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
 
        char dv = rut.charAt(rut.length() - 1);
 
        int m = 0, s = 1;
        for (; rutAux != 0; rutAux /= 10) {
            s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
        }
        if (dv == (char) (s != 0 ? s + 47 : 75)) {
            validacion = true;
        }
 
        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
    public String verificarAdmin(String usuario, String password){
        Administrador adm = null;
        Connection DB = conexion.conectar();
        PreparedStatement pst;
        try {
            pst = DB.prepareStatement("SELECT * FROM administrador WHERE run='"+usuario+"' AND password='"+password+"'");
            rs = pst.executeQuery();
            if(rs.next()){
               return rs.getString(3);
            }else{
                return "";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public ArrayList<String> verificarUsuario(String usuario, String password){
        Administrador administrador = null;
        ArrayList<String> vusuario = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        /*if(!usuario.matches("[0-9]+")){
            return vusuario;
        }
        else{*/
            try{
                PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM administrador WHERE id_admin='"+usuario+"' and password='"+password+"'");
                rs = ps.executeQuery();
                int a = 0;
                while(rs.next()) {
                    if(usuario.equals(rs.getString("id_admin")) && password.equals(rs.getString("password"))){
                        vusuario.add(rs.getString("nombre"));
                        vusuario.add(rs.getString("id_admin"));
                        vusuario.add(rs.getString("password"));
                        return vusuario;
                    }
                }
                return vusuario;
            }catch(Exception e){
                return vusuario;
            }
        //}
    }
    public String vacacionesSolicitudes(String run){
        Connection accesoDB = conexion.conectar();
        try {
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM solicitud WHERE turno_chofer_id_chofer='"+run+"' AND tipo_solicitud=3");
            rs = ps.executeQuery();
            if(rs.next()){
                return (rs.getTimestamp(5)+";"+rs.getTimestamp(6));
                
            }else{
                return "";
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
