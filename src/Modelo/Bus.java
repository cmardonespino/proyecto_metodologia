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
public class Bus {
    ConexionBasedeDatos conexion; //INSTANCIAMOS LA VARIABLE PARA EL OBJETO CONEXION
    public ResultSet rs,rs2, rs3; 
    PreparedStatement pd, ps, pf, pg;
    String patente;
    boolean estado;
    
    public Bus(){
        patente = "";
        estado = false;
        conexion = new ConexionBasedeDatos(); // GUARDAMOS EL OBJETO DE LA CLASE CONEXION EN ESTA VARIABLE
                                    //PARA LUEGO LLAMAR A LOS METODOS DE ESTA CLASE. EN ESTE CASO
                                    //LLAMAREMOS MAS ABAJO "CONECTAR"
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public ArrayList<String> busesDisponibles(){
        ArrayList<String> buses = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM terminal WHERE estado='"+true+"'");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                buses.add(rs.getString("id_bus"));
                buses.add(rs.getString("ubicacion"));
                buses.add(Boolean.toString(rs.getBoolean("estado")));
            }
            return buses;
        }catch(Exception e){
            return buses;
        }
    }
    
    public ArrayList<String> buscarBus(String patente){
        Bus bus = null;
        ArrayList<String> p = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            PreparedStatement ps = accesoDB.prepareStatement("SELECT * FROM terminal WHERE id_bus='"+patente+"'");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                p.add(rs.getString("id_bus"));
                p.add(rs.getString("ubicacion"));
                p.add(Boolean.toString(rs.getBoolean("estado")));
            }
            return p;
        }catch(Exception e){
            return p;
        }
    }
    
    public Integer agregarBus(String patente, String ubicacion, String estado){
        Connection accesoDB = conexion.conectar();
        try{
            int a = 0;
            if(estado=="true")
                pg = accesoDB.prepareStatement("INSERT INTO terminal (id_bus, ubicacion, estado) VALUES ("+patente+", '"+ubicacion+"', '"+true+"')");
            else if(estado=="false")
                pg = accesoDB.prepareStatement("INSERT INTO terminal (id_bus, ubicacion, estado) VALUES ("+patente+", '"+ubicacion+"', '"+false+"')");
            a = pg.executeUpdate();
            return 1;
        }catch(Exception e){
            return 0;
        }
    }
    
}
