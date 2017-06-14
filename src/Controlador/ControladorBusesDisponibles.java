/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bus;
import Modelo.Chofer;
import Vista.VistaBusesDisponibles;
import Vista.VistaOpciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ControladorBusesDisponibles {
    VistaBusesDisponibles vbd = new VistaBusesDisponibles();
    VistaOpciones vopciones = new VistaOpciones();
    
    Chofer chofer = new Chofer();
    Bus bus = new Bus();
    
    public ControladorBusesDisponibles(VistaBusesDisponibles vbd, Chofer chofer){
        this.vbd= vbd;
        this.chofer = chofer;
        this.bus = bus;
        
        this.vbd.botonBuscar.addActionListener(new Buscar());
        this.vbd.botonAtras.addActionListener(new Atras());
    }
    
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Buscar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> buses = new ArrayList<String>();
            String filas[] = new String[100];
            //buses = bus.busesDisponibles();
            if(buses.isEmpty()){
                JOptionPane.showMessageDialog(vbd, "No hay buses disponibles");
            }else{
            
                DefaultTableModel m;
                String titulos[] = {"Patente", "Ubicación", "Estado"};
                m = new DefaultTableModel(null, titulos);
                vbd.tablaBusesDisponibles.setModel(m);

                int i=0, j=0;
                for(String d:buses){
                    filas[i]=d;
                    i++;
                    if(i==3){
                        m.addRow(filas);
                        vbd.tablaBusesDisponibles.setModel(m);
                        i=0;
                    }
                }
            }
        }
    }
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vopciones);
            vopciones.setVisible(true);
            vbd.setVisible(false);
        }
    }
}
