/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    /* para la asignacion de bus, se verifican los buses disponibles que hay en la ubicacion
    que se ingreso al agregar chofer. Por ejemplo, si la ubicacion del chofer es en Santiago
    se verifican que buses hay disponibles en el terminal de Santiago. Posterior a esto
    se le asigna el bus al chofer. */
    public String asignarBus(String ubicacion){
        String busAAsignar="";
        Connection accesoDB = conexion.conectar();
        try{
            ps = accesoDB.prepareStatement("SELECT * FROM terminal WHERE ubicacion='"+ubicacion+"' and estado="+true+"");
            rs = ps.executeQuery();
            int a = 0;
            while(rs.next()) {
                busAAsignar = (rs.getString("id_bus"));
            }
            return busAAsignar;
        }catch(Exception e){
            return busAAsignar;
        }
    }
    
    public ArrayList<String> ordenarViajes(String ubicacion){
        ArrayList<String> viajes = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            /* ---------------------------------------------------------------------------------------- */
            /* Se captura el dia, mes, año, hora, minutos y segundos actuales*/
            /* ---------------------------------------------------------------------------------------- */
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int year = calendar.get(Calendar.YEAR);
            String mes = String.valueOf(month);
            String hora = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
            String minutos = String.valueOf(calendar.get(Calendar.MINUTE));
            String segundos = String.valueOf(calendar.get(Calendar.SECOND));
            String fecha = sdf.format(calendar.getTime());
            /* ---------------------------------------------------------------------------------------- */
            
            /* ---------------------------------------------------------------------------------------- */
            /* Convertimos la fecha y hora actual en TimeStamp para compararlos con las fechas y horas de
            los viajes disponibles */
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(fecha);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            
            DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            LocalDate date = LocalDate.parse(fecha, dateFormat1);
            LocalDate newDate = date.withDayOfMonth(date.getMonth().maxLength());
            System.out.println(newDate);
            
            /* ---------------------------------------------------------------------------------------- */
            
            ps = accesoDB.prepareStatement("SELECT * FROM viaje WHERE inicio='"+ubicacion+"' and estado="+true+"");
            rs = ps.executeQuery();
            String hora_inicio ="";
            while(rs.next()) {
                hora_inicio = rs.getString("hora_inicio");
                parsedDate = dateFormat.parse(hora_inicio);
                Timestamp timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
                /* Al realizar esta comparacion, buscamos todos los viajes disponibles que hay despues de 
                la fecha y hora que se ingreso al chofer */
                if(timestamp2.after(timestamp)){
                    viajes.add(rs.getString("id_viaje"));
                }
            }
            return viajes;
        }catch(Exception e){
            return viajes;
        }
    }
    
    /* para la asignacion de viajes, se verifican los viajes disponibles de acuerdo a la ubicacion
    en la que se encuentra el chofer. Por ejemplo, si la ubicacion del chofer es en Santiago, se 
    verifican los viajes que hay en Santiago a San antonio. Una vez que se asigne, se cambia la ubicacion
    a San antonio, para que se busquen los demas viajes de San Antonio a Santiago. Este proceso se repite
    hasta que ya no quedne mas viajes o se haya cumplido el mes completo. 
    Por cada viaje asignado, se le asignara un descanso igual a 1 hora. */
    public String asignarViajes(String ubicacion){
        String viajeAAsignar="";
        ArrayList<String> viajes = new ArrayList<String>();
        Connection accesoDB = conexion.conectar();
        try{
            
            viajes = ordenarViajes(ubicacion);
            System.out.println("VIAJES DESPUES DE LA FECHA DE INGRESO DEL CHOFER: "+viajes);
            
            /* Aqui lo que se procede a hacer es que se realiza una comparacion de fecha de todos los viajes
            disponibles a partir desde su fecha y hora. Con esto se hace una asignacion de viajes en orden de
            fecha y hora. Cuando se capture el viaje mas cercano a la fecha y hora que se creo el chofer, se
            le asignara de inmediato el viaje. Cuando se asigne el viaje, este se cambiara de estado a 'false'.
            Este proceso se repite hasta completar el mes completo. */
            int c = 0;
            return viajeAAsignar;
        }catch(Exception e){
            return viajeAAsignar;
        }
    }
    public void asignarTodos(){
        String diaDelMes, fecha, inicioVacaciones, finalVacaciones, aux = null;
        String[] splitter = new String[2];
        int diasLibres, viajesDelDia, lib1,lib2,month = 0;
        int dia = 1;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Chofer c = new Chofer();
        Bus b = new Bus();
        Viaje v = new Viaje();
        Administrador admin = new Administrador();
        ArrayList <Chofer> choferes= new ArrayList<Chofer>();
        ArrayList <Bus> buses = new ArrayList<Bus>();
        ArrayList <Viaje> viajes = new ArrayList<Viaje>();
        /* Obtenemos el mes actual y le sumamos uno para obtener el mes siguiente*/
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        cal.add(Calendar.MONTH, 2);
        //System.out.println(fecha);
        /*Obtenemos todos los choferes disponibles en el ArrayList*/
        choferes = c.choferesDisponibles();
        /*Obtenemos todos los buses disponibles en el ArrayList*/
        buses = b.busesDisponibles();
        /*Obtenemos todos los viajes disponibles en el ArrayList*/
        viajes = v.viajesDisp();
        if(cal.getActualMaximum(Calendar.DAY_OF_MONTH) < 31){
            v.bloquearViajes(1);
        }
        if(admin.vacacionesSolicitudes("10108004-8").equals("")){
            inicioVacaciones="vacio";
            finalVacaciones = "vacio";
        }else{
           aux = admin.vacacionesSolicitudes("10108004-8");
           splitter = aux.split(";");
           inicioVacaciones = splitter[0];
           finalVacaciones = splitter[1];
            try {
                d = sdf.parse(inicioVacaciones);
                cal1.setTime(d);
                lib1 = cal1.get(Calendar.DAY_OF_MONTH);
                month = cal1.get(Calendar.MONTH)+1;
                d = sdf.parse(finalVacaciones);
                cal1.setTime(d);
                lib2 = cal1.get(Calendar.DAY_OF_MONTH);
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        if(cal.get(Calendar.MONTH) == month){
            
        }
        
        
        
        
        
    }
    public Integer asignarTurno(String run, String ubicacion){
        String a = asignarBus(ubicacion);
        if(a=="null"){
            return 0;
        }else{
            System.out.println("bus a asignar: "+a);
            String b = asignarViajes(ubicacion);
            return 1;
        }
    }
    
    public String verificarBusDisponible(){
        Turno turno = null;
        Bus busd = new Bus();
        ArrayList<String> bus = new ArrayList<String>();
        //bus = busd.busesDisponibles();
        if(bus.isEmpty()){
            return "null";
        }else{
            return bus.get(0);
        }
    }
    
    public Integer verificarChoferDisponible(String run){
        Chofer chd = new Chofer();
        ArrayList<String> ch = new ArrayList<String>();
        //ch = chd.choferesDisponibles();
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
