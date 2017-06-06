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
public class Viaje {
    String hora_salida;
    String inicio;
    String destino;
    Boolean estado;
    
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs,rs2, rs3; 
    PreparedStatement pd, ps, pf, pg;
    
    public Viaje(){
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
        hora_salida="";
        inicio="";
        destino="";
        estado=false;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public ArrayList<String> viajesDisponibles(){
        ArrayList<String> viajes = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM viaje WHERE estado='"+true+"'");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                viajes.add(rs.getString("id_viaje"));
                viajes.add(rs.getString("salida"));
                viajes.add(rs.getString("inicio"));
                viajes.add(rs.getString("destino"));
                viajes.add(Boolean.toString(rs.getBoolean("estado")));
            }
            return viajes;
        }catch(Exception e){
            return viajes;
        }
    }
    
}
