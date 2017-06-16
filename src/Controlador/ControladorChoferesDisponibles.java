/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Chofer;
import Vista.VistaChoferesDisponibles;
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
public class ControladorChoferesDisponibles implements ActionListener{
    VistaChoferesDisponibles vchd = new VistaChoferesDisponibles();
    VistaOpciones vopciones = new VistaOpciones();
    
    Chofer chofer = new Chofer();
    
    public ControladorChoferesDisponibles(VistaChoferesDisponibles vchd, Chofer chofer){
        this.vchd= vchd;
        this.chofer = chofer;
        
        this.vchd.botonBuscar.addActionListener(new buscarChoferesDisponibles());
        this.vchd.botonAtras.addActionListener(new Atras());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class buscarChoferesDisponibles implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> choferes = new ArrayList<String>();
            String filas[] = new String[100];
            //choferes = chofer.choferesDisponibles();
            if(choferes.isEmpty()){
                JOptionPane.showMessageDialog(vchd, "No hay choferes disponibles");
            }else{
                //System.out.println(productos);
            
                DefaultTableModel m;
                String titulos[] = {"Id_Chofer", "Nombre Chofer", "Edad", "Teléfono","Correo", "Ubicación", "Dirección", "Estado"};
                m = new DefaultTableModel(null, titulos);
                vchd.tablaChoferesDisponibles.setModel(m);

                int i=0, j=0;
                for(String d:choferes){
                    filas[i]=d;
                    i++;
                    if(i==8){
                        m.addRow(filas);
                        vchd.tablaChoferesDisponibles.setModel(m);
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
            vchd.setVisible(false);
        }
    }
}
