/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bus;
import Modelo.Chofer;
import Vista.VistaAgregarBus;
import Vista.VistaOpciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ControladorAgregarBus {
    VistaAgregarBus vab = new VistaAgregarBus();
    VistaOpciones vopciones = new VistaOpciones();
    
    Chofer chofer = new Chofer();
    Bus bus = new Bus();
    
    Object opcion1, opcion2 = new Object();
    
    public ControladorAgregarBus(VistaAgregarBus vab, Bus bus){
        this.vab= vab;
        this.vopciones = vopciones;
        
        this.bus = bus;
        
        this.vab.botonAgregarBus.addActionListener(new Agregar());
        this.vab.botonAtras.addActionListener(new Atras());
    }
    
    public void InicializarAgregarChofer(){}
    
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Agregar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> buses = new ArrayList<String>();
            String patente = vab.txtPatenteBus.getText();
            opcion1 = vab.comboBoxTerminalUbicacion.getSelectedItem();
            opcion2 = vab.comboBoxEstadoBus.getSelectedItem();
            buses = bus.verificarBus(patente);
            if(buses.isEmpty()){
                if(opcion1=="Melipilla"){
                    if(opcion2=="Activo"){
                        int a = bus.agregarBus(patente, "Melipilla", "Activo");
                        if(a!=1)
                            JOptionPane.showMessageDialog(vab, "El bus ya existe en el sistema");
                        else
                            JOptionPane.showMessageDialog(vab, "Bus agregado exitosamente");
                    }else if(opcion2=="Bloqueado"){
                        int a = bus.agregarBus(patente, "Melipilla", "Bloqueado");
                        if(a!=1)
                            JOptionPane.showMessageDialog(vab, "El bus ya existe en el sistema");
                        else
                            JOptionPane.showMessageDialog(vab, "Bus agregado exitosamente");
                    }
                }else if(opcion1=="San Antonio"){
                    if(opcion2=="Activo"){
                        int a = bus.agregarBus(patente, "San Antonio", "Activo");
                        if(a!=1)
                            JOptionPane.showMessageDialog(vab, "El bus ya existe en el sistema");
                        else
                            JOptionPane.showMessageDialog(vab, "Bus agregado exitosamente");
                    }else if(opcion2=="Bloqueado"){
                        int a = bus.agregarBus(patente, "San Antonio", "Bloqueado");
                        if(a!=1)
                            JOptionPane.showMessageDialog(vab, "El bus ya existe en el sistema");
                        else
                            JOptionPane.showMessageDialog(vab, "Bus agregado exitosamente");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(vab, "El bus ya existe en el sistema");
            }
        }
    }
    
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vopciones);
            vopciones.setVisible(true);
            vab.setVisible(false);
        }
    }
}
