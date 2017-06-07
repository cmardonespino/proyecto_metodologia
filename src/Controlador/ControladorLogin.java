/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Administrador;
import Vista.VistaLogin;
import Vista.VistaOpciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Darto
 */
public class ControladorLogin implements ActionListener{
    VistaLogin vistaLogin = new VistaLogin();
    VistaOpciones vistaOpciones = new VistaOpciones();
    
    Administrador administrador = new Administrador();
    
    public ControladorLogin(VistaLogin vistaLogin, Administrador administrador){
        this.vistaLogin = vistaLogin;
        this.vistaOpciones = vistaOpciones;
        
        this.administrador= administrador;
        
        this.vistaLogin.botonIngresarLogin.addActionListener(new Ingresar());
        this.vistaLogin.botonSalirLogin.addActionListener(new Salir());
    }
    public void InicializarLogin(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class Ingresar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            ArrayList<String> admin = new ArrayList<String>();
            String user = vistaLogin.txtIngresarUsuario.getText();
            char[] password = vistaLogin.txtIngresarPasswordLogin.getPassword();
            admin = administrador.verificarUsuario(user, String.valueOf(password));
            /*JOptionPane.showMessageDialog(vistaLogin, user);
            JOptionPane.showMessageDialog(vistaLogin, password);*/
            if(admin.isEmpty()){
                JOptionPane.showMessageDialog(vistaLogin, "Usuario o contraseña incorrecta");
            }else{
                String nombre = administrador.getNombre();
                JOptionPane.showMessageDialog(vistaLogin, "Bienvenido "+ admin.get(0));
                ControladorOpciones controladorOpciones = new ControladorOpciones(vistaOpciones);
                vistaOpciones.setVisible(true);
                vistaLogin.setVisible(false);
            }
        }
    }
    
    class Salir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            vistaLogin.setVisible(false);
            System.exit(0);
        }
    }
}
