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
public class AguardaInicio extends EstadosAdapter{
    
    public AguardaInicio(Mundo m){
        super(m);
    }

@Override
    public IEstados proximoEstado(){
        //this.getMundo().
        
        return new AguardaCarta(this.getMundo());
    }
    
}
