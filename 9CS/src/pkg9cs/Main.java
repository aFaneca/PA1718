/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9cs;

import Lógica.Mundo;

/**
 *
 * @authors 
 * Amadeus Alves
 * António Faneca
 */
public class Main {

    private void run() {
        m = new Mundo();
        System.out.println("Nr. de Cartas: " + m.getCartas().size());
        
        m.verInfo();
        
    }

    Mundo m;
    
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    
}
