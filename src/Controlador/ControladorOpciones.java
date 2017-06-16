/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class ControladorOpciones implements ActionListener{
    VistaOpciones vistaOpciones = new VistaOpciones();
    VistaLogin vistaLogin = new VistaLogin();
    
    VistaAgregarChofer vistaAgregarChofer = new VistaAgregarChofer();
    VistaBuscarChofer vistaBuscarChofer = new VistaBuscarChofer();
    VistaBuscarBus vbbus = new VistaBuscarBus();
    VistaChoferesDisponibles vchd = new VistaChoferesDisponibles();
    VistaBusesDisponibles vbd = new VistaBusesDisponibles();
    VistaAgregarBus vab = new VistaAgregarBus();
    VistaViajesDisponibles vvd = new VistaViajesDisponibles();
    
    //VistaAgregarBus vistaAgregarBus = new VistaAgregarBus();
    
    Administrador administrador = new Administrador();
    
    Object o = new Object();
    Chofer chofer = new Chofer();
    Bus bus = new Bus();
    Viaje viaje = new Viaje();
    
    public ControladorOpciones(VistaOpciones vistaOpciones){
        this.vistaOpciones = vistaOpciones;
        this.vistaLogin = vistaLogin;
        
        this.vistaAgregarChofer = vistaAgregarChofer;
        this.vistaBuscarChofer = vistaBuscarChofer;
        this.vbbus = vbbus;
        this.vchd = vchd;
        this.vab = vab;
        
        //this.vistaAgregarBus = vistaAgregarBus;
        
        this.vistaOpciones.comboBoxOpciones.addActionListener(this);
        this.vistaOpciones.botonCerrarSesion.addActionListener(this);
    }
    public void InicializarOpciones(){
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         o = vistaOpciones.comboBoxOpciones.getSelectedItem();
            if(ae.getSource().equals(vistaOpciones.botonCerrarSesion)){
                ControladorLogin controladorLogin = new ControladorLogin(vistaLogin, administrador);
                vistaLogin.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Buscar Chofer"){
                ControladorBuscarChofer controladorBuscarChofer = new ControladorBuscarChofer(vistaBuscarChofer, chofer);
                vistaBuscarChofer.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Modificar Chofer"){
                //
            }else if(o == "Choferes Disponibles"){
                ControladorChoferesDisponibles cchd = new ControladorChoferesDisponibles(vchd, chofer);
                vchd.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Agregar Bus"){
                ControladorAgregarBus controladorAgregarBus = new ControladorAgregarBus(vab, bus);
                vab.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Buscar Bus"){
                ControladorBuscarBus controladorBuscarBus = new ControladorBuscarBus(vbbus, bus);
                vbbus.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Modificar Bus"){
                //
            }else if(o == "Buses Disponibles"){
                ControladorBusesDisponibles controladorBusesDisponibles = new ControladorBusesDisponibles(vbd, chofer);
                vbd.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Viajes Disponibles"){
                ControladorViajesDisponibles controladorViajesDisponibles = new ControladorViajesDisponibles(vvd, viaje);
                vvd.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Agregar Chofer"){
                ControladorAgregarChofer controladorAgregarChofer = new ControladorAgregarChofer(vistaAgregarChofer, chofer);
                vistaAgregarChofer.setVisible(true);
                vistaOpciones.setVisible(false);
            } 
            
    }
    
    class seleccionOpcion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            o = vistaOpciones.comboBoxOpciones.getSelectedItem();
            if(o=="Agregar Chofer"){
                ControladorAgregarChofer controladorAgregarChofer = new ControladorAgregarChofer(vistaAgregarChofer, chofer);
                vistaAgregarChofer.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Buscar Chofer"){
                ControladorBuscarChofer controladorBuscarChofer = new ControladorBuscarChofer(vistaBuscarChofer, chofer);
                vistaBuscarChofer.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Modificar Chofer"){
                //
            }else if(o == "Choferes Disponibles"){
                ControladorChoferesDisponibles cchd = new ControladorChoferesDisponibles(vchd, chofer);
                vchd.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Agregar Bus"){
                ControladorAgregarBus controladorAgregarBus = new ControladorAgregarBus(vab, bus);
                vab.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o=="Buscar Bus"){
                ControladorBuscarBus controladorBuscarBus = new ControladorBuscarBus(vbbus, bus);
                vbbus.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Modificar Bus"){
                //
            }else if(o == "Buses Disponibles"){
                ControladorBusesDisponibles controladorBusesDisponibles = new ControladorBusesDisponibles(vbd, chofer);
                vbd.setVisible(true);
                vistaOpciones.setVisible(false);
            }else if(o == "Viajes Disponibles"){
                ControladorViajesDisponibles controladorViajesDisponibles = new ControladorViajesDisponibles(vvd, viaje);
                vvd.setVisible(true);
                vistaOpciones.setVisible(false);
            }
                
        }
    }
    
    //TENGO MIS DUDAS CON ESTE METODO.
    class cerrarSesion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorLogin controladorLogin = new ControladorLogin(vistaLogin, administrador);
            vistaLogin.setVisible(true);
            vistaOpciones.setVisible(false);
        }
    }
}
