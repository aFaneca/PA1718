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
public class AguardaCarta extends EstadosAdapter{
    
    public AguardaCarta(Mundo m){
        super(m);
    }
    
    public IEstados fimDoDia(){
        Mundo m = super.getMundo();
        
        return this;
    }
    
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
    
    @Override
    public IEstados proximoEstado(){
        //this.getMundo().
        
        return new AguardaEscolhaDeAcao(this.getMundo());
    }
}
