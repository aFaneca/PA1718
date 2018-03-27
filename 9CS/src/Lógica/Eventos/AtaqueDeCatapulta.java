/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Evento;
import Lógica.Inimigo;
import java.util.List;

/**
 *
 * @author me
 */
public class AtaqueDeCatapulta extends Evento{
    
    
    
    public AtaqueDeCatapulta(int numero, List<Inimigo> inim){
        super(mundo, numero, inim);
        nome = "Ataque de Catapulta";
    }
    

    protected void acao() { // PRECISO DE REVER ESTE CÓDIGO, PORQUE DEVE ESTAR MUITO INTRUSIVO <= TEM QUE RESPEITAR O ENCAPSULAMENTO
        
        if(mundo.contaCatapultas() == 3)
            mundo.alteraMuralha(-2);
        else if(mundo.contaCatapultas() == 2)
            mundo.alteraMuralha(-1);
        else if(mundo.contaCatapultas() == 1){
            if(mundo.rodaDado() > 3)
                mundo.alteraMuralha(-1);
        }
            
        
    }
    
}
