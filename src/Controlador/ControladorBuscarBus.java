/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Bus;
import Vista.VistaBuscarBus;
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
public class ControladorBuscarBus implements ActionListener{
    VistaOpciones vopciones = new VistaOpciones();
    VistaBuscarBus vbbus = new VistaBuscarBus();
    
    Bus bus = new Bus();
    
    public ControladorBuscarBus(VistaBuscarBus vbbus, Bus bus){
        this.vbbus= vbbus;
        this.vopciones = vopciones;
        
        this.bus = bus;
        
        this.vbbus.botonBuscarBus.addActionListener(new Buscar());
        this.vbbus.botonAtras.addActionListener(new Atras());
    }
    
    public void InicializarBuscarBus(){}

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Buscar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String filas[] = new String[100];
            ArrayList<String> buses = new ArrayList<String>();
            
            String patente = vbbus.txtBuscarBus.getText();
            buses = bus.buscarBus(patente);
            
            if(buses.isEmpty()){
                JOptionPane.showMessageDialog(vbbus, "No existe en el sistema el bus con la patente ingresada. "
                        + "Por favor, intentelo nuevamente.");
            }else{
                System.out.println(buses);
            
                DefaultTableModel m;
                String titulos[] = {"Patente", "Ubicación", "Estado"};
                m = new DefaultTableModel(null, titulos);
                vbbus.tablaBuscarBus.setModel(m);

                int i=0, j=0;
                for(String d:buses){
                    filas[i]=d;
                    i++;
                }
                m.addRow(filas);
                vbbus.tablaBuscarBus.setModel(m);
            }
        }
    }
    
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vopciones);
            vopciones.setVisible(true);
            vbbus.setVisible(false);
        }
    }
}
