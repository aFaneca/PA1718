/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Mundo;

/**
 *
 * @author me
 */
public class AguardaSelecaoDeAcao extends EstadosAdapter{
    
    public AguardaSelecaoDeAcao(Mundo m){
        super(m);
        
    }
    
    @Override
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }

    public IEstados proximoEstado(){
        
        if(mundo.getCartasViradas() % mundo.getCartas().size() == 0) // SE JÁ FORAM VIRADAS AS 7 CARTAS
            return new DiaTerminado(this.getMundo());
        else
            return new AguardaCarta(mundo);
    }
    
}
