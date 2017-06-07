/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Viaje;
import Vista.VistaOpciones;
import Vista.VistaViajesDisponibles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos
 */
public class ControladorViajesDisponibles {
    VistaViajesDisponibles vvd = new VistaViajesDisponibles();
    VistaOpciones vopciones = new VistaOpciones();
    
    Viaje viaje = new Viaje();
    
    public ControladorViajesDisponibles(VistaViajesDisponibles vvd, Viaje viaje){
        this.vvd= vvd;
        this.viaje = viaje;
        
        this.vvd.botonBuscarViajesDisponibles.addActionListener(new Buscar());
        this.vvd.botonAtras.addActionListener(new Atras());
    }
    
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Buscar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> viajes = new ArrayList<String>();
            String filas[] = new String[100];
            viajes = viaje.viajesDisponibles();
            if(viajes.isEmpty()){
                JOptionPane.showMessageDialog(vvd, "No hay viajes disponibles");
            }else{
                DefaultTableModel m;
                String titulos[] = {"ID Viaje", "Hora inicio", "Hora destino", "Inicio", "Destino", "Estado"};
                m = new DefaultTableModel(null, titulos);
                vvd.tablaMostrarViajesDisponibles.setModel(m);

                int i=0, j=0;
                for(String d:viajes){
                    filas[i]=d;
                    i++;
                    if(i==6){
                        m.addRow(filas);
                        vvd.tablaMostrarViajesDisponibles.setModel(m);
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
            vvd.setVisible(false);
        }
    }
}
