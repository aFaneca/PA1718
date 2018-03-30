/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.util.List;

/**
 *
 * @author me
 */
public class AtaqueDeCatapulta extends Evento{
     
    public AtaqueDeCatapulta(Mundo mundo, int numero, List<Inimigo> inim){
        super(mundo, numero, inim);
        //this.mundo = mundo;
        nome = "Ataque de Catapulta";
    }
    

    protected void acao() { // PRECISO DE REVER ESTE CÓDIGO, PORQUE DEVE ESTAR MUITO INTRUSIVO <= TEM QUE RESPEITAR O ENCAPSULAMENTO
        //System.out.println(mundo.getFortaleza());
        
        if(mundo.contaCatapultas() == 3)
            mundo.alteraMuralha(-2);
        else if(mundo.contaCatapultas() == 2)
            mundo.alteraMuralha(-1);
        else if(mundo.contaCatapultas() == 1){
            if(mundo.rodaDado() > 3)
                mundo.alteraMuralha(-1);
        }
            
       //System.out.println(mundo.getFortaleza()); 
    }
    
}
