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
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ControladorModificarChofer implements ActionListener{
    VistaModificarChofer vistaModificarChofer = new VistaModificarChofer();
    VistaOpciones vistaOpciones = new VistaOpciones();
    VistaBuscarChofer vistaBuscarChofer = new VistaBuscarChofer();
    Chofer chofer = new Chofer();
    
    Object opcion = new Object();
    
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
            ArrayList<String> choferes = new ArrayList<String>();
            choferes = chofer.verificarChofer(run);
            int a;
            if(!choferes.isEmpty()){
                String nombre = vistaModificarChofer.txtIngresarNombreModificar.getText();
                String telefono = vistaModificarChofer.txtIngresarTelefonoModificar.getText();
                String correo = vistaModificarChofer.txtIngresarCorreoModificar.getText();
                String direccion = vistaModificarChofer.txtIngresarDireccionModificar.getText();
                opcion = vistaModificarChofer.comboBoxOpcionEstado.getSelectedItem();
                //opcion = vistaModificarChofer.checkBoxActivo.isSelected();
                if(opcion=="Activo"){
                    a = chofer.modificarChofer(run, nombre, telefono, direccion, correo, true);
                    if(a==1){
                        JOptionPane.showMessageDialog(vistaModificarChofer, "Datos de chofer modificados exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(vistaModificarChofer, "Problemas al modificar datos del chofer."
                                + " Intentelo nuevamente.");
                    }
                }else if(opcion=="Bloqueado"){
                    a = chofer.modificarChofer(run, nombre, telefono, direccion, correo, false);
                    if(a==1){
                        JOptionPane.showMessageDialog(vistaModificarChofer, "Datos de chofer modificados exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(vistaModificarChofer, "Problemas al modificar datos del chofer."
                                + " Intentelo nuevamente.");
                    }
                }else{
                    JOptionPane.showMessageDialog(vistaModificarChofer, "Estamos trabajando para usted :v. Opcion"
                            + " aun en desarrollo!");
                }
            }
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
