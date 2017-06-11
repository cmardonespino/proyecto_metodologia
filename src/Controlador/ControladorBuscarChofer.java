/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* https://stackoverflow.com/questions/7375827/how-to-print-text-to-a-text-area */

package Controlador;

import Modelo.Chofer;
import Vista.VistaBuscarChofer;
import Vista.VistaModificarChofer;
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
public class ControladorBuscarChofer implements ActionListener{
    VistaBuscarChofer vistaBuscarChofer = new VistaBuscarChofer();
    VistaOpciones vistaOpciones = new VistaOpciones();
    VistaModificarChofer vistaModificarChofer = new VistaModificarChofer();
    
    Chofer chofer = new Chofer();
    Chofer modificarChofer = new Chofer();
    
    public ControladorBuscarChofer(VistaBuscarChofer vistaBuscarChofer, Chofer chofer){
        this.vistaBuscarChofer= vistaBuscarChofer;
        this.chofer = chofer;
        
        this.vistaBuscarChofer.botonBuscar.addActionListener(new buscarChofer());
        this.vistaBuscarChofer.botonModificarChofer.addActionListener(new VentanaModificar());
        this.vistaBuscarChofer.botonAtras.addActionListener(new Atras());
    }
    public void InicializarBuscarChofer(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class VentanaModificar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> choferes = new ArrayList<String>();
            String run = vistaBuscarChofer.txtIngresarRUN.getText();
            /* llamamos al metodo verificarChofer para recuperar todos los datos del chofer */
            choferes = chofer.verificarChofer(run);
            if(!run.equals("")){
                /* mostramos en la vista de modificarChofer los datos actuales que tiene el chofer registrados */
                /* en el sistema */
                vistaModificarChofer.txtIngresarNombreModificar.setText(choferes.get(1));
                vistaModificarChofer.txtIngresarTelefonoModificar.setText(choferes.get(2));
                vistaModificarChofer.txtIngresarDireccionModificar.setText(choferes.get(3));
                vistaModificarChofer.txtIngresarCorreoModificar.setText(choferes.get(4));
                if(choferes.get(5)=="true"){
                   JOptionPane.showMessageDialog(vistaModificarChofer, "Chofer se encuentra Activo");
                }else if(choferes.get(5)=="false"){
                   JOptionPane.showMessageDialog(vistaModificarChofer, "Chofer se encuentra Bloqueado");
                }
                /*if(choferes.get(5)=="true"){
                    vistaModificarChofer.checkBoxActivo.setSelected(true);
                }else if(choferes.get(5)=="false"){
                    vistaModificarChofer.checkBoxBloqueado.setSelected(true);
                }else{
                    vistaModificarChofer.checkBoxBloqueoTemporal.setSelected(true);
                }*/
                ControladorModificarChofer controladorModificarChofer = new ControladorModificarChofer(vistaModificarChofer, chofer, run);
                vistaModificarChofer.setVisible(true);
                vistaBuscarChofer.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(vistaOpciones, "Ingrese un RUT por favor");
            }
        }
    }
    
    class buscarChofer implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String run = vistaBuscarChofer.txtIngresarRUN.getText();
            ArrayList<String> choferes = new ArrayList<String>();
            String filas[] = new String[100];
            choferes = chofer.buscarChofer(run);
            if(choferes.isEmpty()){
                JOptionPane.showMessageDialog(vistaBuscarChofer, "Chofer no encontrado");
            }else{
                JOptionPane.showMessageDialog(vistaOpciones, "Chofer encontrado");
                DefaultTableModel m;
                String titulos[] = {"Id_Chofer", "Nombre Chofer", "Edad", "Teléfono","Correo", "Ubicación", "Dirección", "Estado"};
                m = new DefaultTableModel(null, titulos);
                vistaBuscarChofer.tablMostrarDatos.setModel(m);

                int i=0, j=0;
                for(String d:choferes){
                    filas[i]=d;
                    i++;
                    if(i==8){
                        m.addRow(filas);
                        vistaBuscarChofer.tablMostrarDatos.setModel(m);
                        i=0;
                    }
                }
            }
        }
    }
    
    class Atras implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ControladorOpciones controladorOpciones = new ControladorOpciones(vistaOpciones);
            vistaOpciones.setVisible(true);
            vistaBuscarChofer.setVisible(false);
        }
    }
}
