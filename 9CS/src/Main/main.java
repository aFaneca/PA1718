/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controlador.Controlador;
import Lógica.Mundo;
import ui.GUI.JogoView;
import ui.GUI.MenuInicialView;

/**
 *
 * @author me
 */
public class main {
    
    
    public static void main(String[] args) {
         Mundo mundo = new Mundo(); // MODELO
         
         MenuInicialView menuInicial = new MenuInicialView(); // Vista
         JogoView jogoView = new JogoView(mundo); // VISTA
         Controlador controlador = new Controlador(mundo, menuInicial, jogoView); // Controlador
         
         controlador.run();
    }
}
