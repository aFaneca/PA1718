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
public class DiaTerminado extends EstadosAdapter{
    static int contagemDeDias = 1;

    public DiaTerminado(Mundo mundo) {
        super(mundo);
    }
    
    @Override
    public IEstados proximoEstado(){
        if(contagemDeDias++ == 3)
            return new JogoTerminado(this.getMundo());
        Mundo m  = super.getMundo();
        
        m.setDia(m.getDia() + 1);
        
        return new AguardaCarta(this.getMundo());
    }
}