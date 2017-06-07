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
import java.util.ArrayList;
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
            ArrayList<String> choferes = new ArrayList<String>();
            String run = vistaAgregarChofer.txtIngresarRUN.getText();
            choferes = chofer.verificarChofer(run);
            if(choferes.isEmpty()){
                String nombre = vistaAgregarChofer.txtIngresarNombre.getText();
                int edad = Integer.parseInt(vistaAgregarChofer.txtIngresarEdad.getText());
                String telefono = vistaAgregarChofer.txtIngresarTelefono.getText();
                String correo = vistaAgregarChofer.txtIngresarCorreo.getText();
                String direccion = vistaAgregarChofer.txtIngresarDireccion.getText();
                opcion = vistaAgregarChofer.comboBoxUbicacion.getSelectedItem();
                if(opcion=="Santiago"){
                    int a = chofer.agregarChofer("Santiago", nombre, edad, run, telefono, direccion, correo, true);
                    if(a!=1)
                        JOptionPane.showMessageDialog(vistaAgregarChofer, "El chofer ya existe en el sistema");
                    else
                        JOptionPane.showMessageDialog(vistaAgregarChofer, "Chofer agregado exitosamente");
                }else if(opcion=="San Antonio"){
                    int a = chofer.agregarChofer("San Antonio", nombre, edad, run, telefono, direccion, correo, true);
                    if(a!=1)
                        JOptionPane.showMessageDialog(vistaAgregarChofer, "El chofer ya existe en el sistema");
                    else
                        JOptionPane.showMessageDialog(vistaAgregarChofer, "Chofer agregado exitosamente");
                }
            }else{
                JOptionPane.showMessageDialog(vistaAgregarChofer, "El chofer ya existe en el sistema");
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
