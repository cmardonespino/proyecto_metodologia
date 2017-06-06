/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Chofer;
import Vista.VistaBuscarChofer;
import Vista.VistaModificarChofer;
import Vista.VistaOpciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos
 */
public class ControladorModificarChofer implements ActionListener{
    VistaModificarChofer vistaModificarChofer = new VistaModificarChofer();
    VistaOpciones vistaOpciones = new VistaOpciones();
    VistaBuscarChofer vistaBuscarChofer = new VistaBuscarChofer();
    Chofer chofer = new Chofer();
    
    Object o = new Object();
    
    String run;
    
    public ControladorModificarChofer(VistaModificarChofer vistaModificarChofer, Chofer chofer, String run){
        this.vistaModificarChofer= vistaModificarChofer;
        this.chofer = chofer;
        this.run = run;
        
        this.vistaModificarChofer.botonModificarChofer.addActionListener(new Modificar());
        this.vistaModificarChofer.botonAtrasAOpciones.addActionListener(new Atras());
    }
    
    public void InicializarModificarChofer(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Modificar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            
        }
    }
    
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vistaOpciones);
            vistaBuscarChofer.setVisible(true);
            vistaModificarChofer.setVisible(false);
        }
    }
}
