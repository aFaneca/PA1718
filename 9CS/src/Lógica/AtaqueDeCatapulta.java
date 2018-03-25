/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

/**
 *
 * @author me
 */
public class AtaqueDeCatapulta extends Evento{
    
    
    
    public AtaqueDeCatapulta(int numero){
        super(mundo, numero);
        nome = "Ataque de Catapulta";
    }
    
    @Override
    protected boolean condicao() {
        return true; // NÃO EXISTEM CONDIÇÕES PARA A AÇÃO ACONTECER
    }

    @Override
    protected void acao() { // PRECISO DE REVER ESTE CÓDIGO, PORQUE DEVE ESTAR MUITO INTRUSIVO <= TEM QUE RESPEITAR O ENCAPSULAMENTO
        
        if(mundo.fortaleza.nrCatapultas == 3)
            mundo.fortaleza.muralha.forca -= 2;
        else if(mundo.fortaleza.nrCatapultas == 2)
            mundo.fortaleza.muralha.forca--;
        else if(mundo.fortaleza.nrCatapultas == 1){
            if(mundo.dado.rodaDado() > 3)
                mundo.fortaleza.muralha.forca--;
        }
            
        
    }
    
}
