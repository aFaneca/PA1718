/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Mundo;
import java.io.Serializable;

/**
 *
 * @author me
 */
public class AguardaCarta extends EstadosAdapter implements Serializable{

    public AguardaCarta(Mundo m){
        super(m);
    }
    

    
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
    
    @Override
    public IEstados proximoEstado(){
        
        
        return new AguardaSelecaoDeAcao(this.getMundo());
    }
}
