/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Carta;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.util.List;

/**
 *
 * @author me
 */
public class AtaqueDeCatapulta extends Evento{
     
//    public AtaqueDeCatapulta(Carta carta, int numero){
//        super(carta, numero);
//        //this.mundo = mundo;
//        nome = "Ataque de Catapulta";
//    }    
    
    public AtaqueDeCatapulta(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        //this.mundo = mundo;
        nome = "Ataque de Catapulta";
    }


    protected void acao() { 
        //System.out.println(mundo.getFortaleza());
        
//        if(mundo.contaCatapultas() == 3)
//            mundo.alteraMuralha(-2);
//        else if(mundo.contaCatapultas() == 2)
//            mundo.alteraMuralha(-1);
//        else if(mundo.contaCatapultas() == 1){
//            if(mundo.rodaDado() > 3)
//                mundo.alteraMuralha(-1);
//        }
            
       //System.out.println(mundo.getFortaleza()); 
    }
}
