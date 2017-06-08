/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    
    public ArrayList<String> ordenarViajes(ArrayList<String> a){
        ArrayList<String> b = new ArrayList<String>();
        ArrayList<String> c = new ArrayList<String>();
        int j=0;
        
        for(int i=0;i<(a.size());i++){
            if(a.get(i)=="true"){
                b.add(a.get(i));
                c.add(b.toString());
                b.clear();
            }else{
                b.add(a.get(i));
            }
        }
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(c.get(0));
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());*/
        System.out.println("viajes: "+c);
        return b;
    }
    
    public Integer asignarTurno(String run){
        String a = verificarBusDisponible();
        if(a=="null"){
            return 0;
        }else{
            ArrayList<String> b = new ArrayList<String>();
            b = verificarsViajeDisponibles();
            if(b.isEmpty()){
                return 0;
            }else{
                ArrayList<String> c = new ArrayList<String>();
                c = ordenarViajes(b);
            }
        }
        return 0;
    }
    
    public String verificarBusDisponible(){
        Turno turno = null;
        Bus busd = new Bus();
        ArrayList<String> bus = new ArrayList<String>();
        bus = busd.busesDisponibles();
        if(bus.isEmpty()){
            return "null";
        }else{
            return bus.get(0);
        }
    }
    
    public Integer verificarChoferDisponible(String run){
        Chofer chd = new Chofer();
        ArrayList<String> ch = new ArrayList<String>();
        ch = chd.choferesDisponibles();
        if(ch.isEmpty()){
            return 0;
        }else{
            
            return 1;
        }
    }
    
    public ArrayList<String> verificarsViajeDisponibles(){
        Viaje viaje = new Viaje();
        ArrayList<String> v = new ArrayList<String>();
        v = viaje.viajesDisponibles();
        return v;
    }
}
