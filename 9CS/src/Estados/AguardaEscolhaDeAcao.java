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
public class AguardaEscolhaDeAcao extends EstadosAdapter{
    private boolean continuar = true;
    public AguardaEscolhaDeAcao(Mundo mundo) {
        super(mundo);
    }
    
    @Override
    public IEstados proximoEstado(){
        //this.getMundo().
        
        if(continuar)
            return this;
        
        return new AguardaCarta(this.getMundo());
    }
}
