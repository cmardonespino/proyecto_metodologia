/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Darto
 */
public class Persona {
    String nombre;
    int edad;
    /* HAY QUE DEJAR FECHA DE NACIMIENTO COMO DATE 
    String fech_nacim;
     */
    String direccion;
    String RUN;
    String telefono;
    String correo;
    
    public Persona(){
        nombre = "";
        edad = 0;
        /* HAY QUE DEJAR FECHA DE NACIMIENTO COMO DATE 
        fech_nacim = "";
         */
        RUN = "";
        telefono = "";
        correo = "";
        direccion = "";
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
    
}
