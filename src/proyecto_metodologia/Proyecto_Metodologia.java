/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_metodologia;

import Controlador.ControladorLogin;
import Modelo.Administrador;
import Vista.VistaLogin;

/**
 *
 * @author Darto
 */

public class Proyecto_Metodologia{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Administrador administrador = new Administrador();
        VistaLogin vistaLogin = new VistaLogin();
        ControladorLogin controladorLogin = new ControladorLogin(vistaLogin, administrador);
        vistaLogin.setVisible(true);
    }
    
}
