/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.Controlador;
import Lógica.Mundo;
import ui.GUI.menuInicialView;

/**
 *
 * @author me
 */
public class main {
    
    
    public static void main(String[] args) {
         Mundo mundo = new Mundo(); // MODELO
         
         menuInicialView menuInicial = new menuInicialView(); // Vista
         Controlador controlador = new Controlador(mundo, menuInicial); // Controlador
         
         controlador.run();
         //menuInicial.setVisible(true);
    }
}
