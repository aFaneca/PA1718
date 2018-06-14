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
public class JogoTerminado extends EstadosAdapter implements Serializable{
    
    public JogoTerminado(Mundo m){
        super(m);
    }
    
    public IEstados voltarAoInicio(){
        return new AguardaInicio(super.getMundo());
    }
}
