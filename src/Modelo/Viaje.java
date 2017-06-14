/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Viaje {
    Time hora_inicio, hora_destino;
    String inicio, destino;
    int dia;
    Boolean estado;
    
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs,rs2, rs3; 
    PreparedStatement pd, ps, pf, pg;
    
    public Viaje(){
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
        
        inicio="";
        destino="";
        estado=false;
        dia=0;
    }

    public Viaje(Time hora_inicio, Time hora_destino, String inicio, String destino, int dia, Boolean estado) {
        this.hora_inicio = hora_inicio;
        this.hora_destino = hora_destino;
        this.inicio = inicio;
        this.destino = destino;
        this.dia = dia;
        this.estado = estado;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_destino() {
        return hora_destino;
    }

    public void setHora_destino(Time hora_destino) {
        this.hora_destino = hora_destino;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM viaje WHERE estado="+true+"");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                viajes.add(rs.getString("id_viaje"));
                viajes.add(rs.getString("hora_inicio"));
                viajes.add(rs.getString("hora_destino"));
                viajes.add(rs.getString("inicio"));
                viajes.add(rs.getString("destino"));
                viajes.add(Boolean.toString(rs.getBoolean("estado")));
            }
            return viajes;
        }catch(Exception e){
            return viajes;
        }
    }
    public ArrayList<Viaje> viajesDisp(){
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM viaje WHERE estado="+true+"");
            rs = ps.executeQuery();
            while(rs.next()) {
               viajes.add(new Viaje(rs.getTime("hora_inicio"), rs.getTime("hora_destino"), rs.getString("punto_inicio"), rs.getString("punto_final"), rs.getInt(dia), rs.getInt("estado")!=0));
            }
            return viajes;
        }catch(Exception e){
            return viajes;
        }
    }
    
}
