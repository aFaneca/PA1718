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
public class AguardaInicio extends EstadosAdapter implements Serializable{
    
    public AguardaInicio(Mundo m){
        super(m);
    }

@Override
    public IEstados mostraInfo(String mensagem){
        //this.getMundo().
        
        return new AguardaLeituraDeInfo(this.getMundo(), mensagem);
    }
    
}
