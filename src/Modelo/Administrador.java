/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    
    public Administrador verificarUsuario(String usuario, String password){
        Administrador administrador = null;
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM administrador WHERE user='"+usuario+"' and password='"+password+"'");
            rs = ps.executeQuery();
            int a = 0;
            if(rs.next()) {
                administrador = new Administrador();
                administrador.setNombre(rs.getString("nombre"));
                administrador.setIDAdministrador(rs.getString("usuario"));
                administrador.setPassword(rs.getString("password"));
            }
            if(usuario.equals(rs.getString("usuario")) && password.equals(rs.getString("password"))){
                return administrador;
            }
            return null;
        }catch(Exception e){
            return administrador;
        }
    }
    
}
