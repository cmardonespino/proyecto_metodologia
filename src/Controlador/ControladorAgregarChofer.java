/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Chofer;
import Vista.VistaAgregarChofer;
import Vista.VistaOpciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class ControladorAgregarChofer implements ActionListener{
    VistaAgregarChofer vistaAgregarChofer = new VistaAgregarChofer();
    VistaOpciones vistaOpciones = new VistaOpciones();
    Chofer chofer = new Chofer();
    
    Object opcion = new Object();
    
    public ControladorAgregarChofer(VistaAgregarChofer vistaAgregarChofer, Chofer chofer){
        this.vistaAgregarChofer= vistaAgregarChofer;
        this.vistaOpciones = vistaOpciones;
        
        this.chofer = chofer;
        
        this.vistaAgregarChofer.botonAgregarChofer.addActionListener(new Agregar());
        this.vistaAgregarChofer.botonAtrasMenuOpciones.addActionListener(new Atras());
    }
    public void InicializarAgregarChofer(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Agregar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String run = vistaAgregarChofer.txtIngresarRUN.getText();
            chofer = chofer.verificarChofer(run);
            Chofer agregarChofer = null;
            if(chofer==null){
                String nombre = vistaAgregarChofer.txtIngresarNombre.getText();
                int edad = Integer.parseInt(vistaAgregarChofer.txtIngresarEdad.getText());
                String telefono = vistaAgregarChofer.txtIngresarTelefono.getText();
                String correo = vistaAgregarChofer.txtIngresarCorreo.getText();
                String direccion = vistaAgregarChofer.txtIngresarDireccion.getText();
                opcion = vistaAgregarChofer.comboBoxUbicacion.getSelectedItem();
                JOptionPane.showMessageDialog(vistaAgregarChofer, opcion);
                if(opcion=="Santiago"){
                    JOptionPane.showMessageDialog(vistaAgregarChofer, "Chofer agregado exitosamente");
                    agregarChofer = chofer.agregarChofer("Santiago", nombre, edad, run, telefono, direccion, correo, true);
                }else if(opcion=="San Antonio"){
                    agregarChofer = chofer.agregarChofer("San Antonio", nombre, edad, run, telefono, direccion, correo, true);
                }
                JOptionPane.showMessageDialog(vistaAgregarChofer, "Chofer agregado exitosamente");
            }else{
                JOptionPane.showMessageDialog(vistaAgregarChofer, "El chofer con run "+chofer.getRUN()+" ya existe en el sistema");
            }
        }
    }
    
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vistaOpciones);
            vistaOpciones.setVisible(true);
            vistaAgregarChofer.setVisible(false);
        }
    }
}
