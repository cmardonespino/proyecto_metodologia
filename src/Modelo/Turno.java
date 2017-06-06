/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Carlos
 */
public class Turno {
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs, rd;
    PreparedStatement pd, ps, pf, pg;
    
    String fecha;
    String inicio_viaje;
    String destino_viaje;
    String descanso_inicio;
    String descanso_final;
    
    public Turno(){
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
                                    
        fecha = "";
        inicio_viaje = "";
        destino_viaje = "";
        descanso_inicio = "";
        descanso_final = "";
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getInicio_viaje() {
        return inicio_viaje;
    }

    public void setInicio_viaje(String inicio_viaje) {
        this.inicio_viaje = inicio_viaje;
    }

    public String getDestino_viaje() {
        return destino_viaje;
    }

    public void setDestino_viaje(String destino_viaje) {
        this.destino_viaje = destino_viaje;
    }

    public String getDescanso_inicio() {
        return descanso_inicio;
    }

    public void setDescanso_inicio(String descanso_inicio) {
        this.descanso_inicio = descanso_inicio;
    }

    public String getDescanso_final() {
        return descanso_final;
    }

    public void setDescanso_final(String descanso_final) {
        this.descanso_final = descanso_final;
    }
    
    public Turno asignarTurno(){
        Turno turno = null;
        return turno;
    }
    
    public Turno verificarBusDisponible(){
        Turno turno = null;
        return turno;
    }
    
    public Turno verificarChoferDisponible(String run){
        Turno t = null;
        Chofer turno = null;
        return t;
    }
    
    public Turno verificarViajeDisponible(){
        Turno turno = null;
        return turno;
    }
}
