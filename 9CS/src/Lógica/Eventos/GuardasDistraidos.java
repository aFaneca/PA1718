/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.util.List;

/**
 *
 * @author me
 */
public class GuardasDistraidos extends Evento {

    public GuardasDistraidos(Mundo mundo, int numero, List<Inimigo> inim){
        super(mundo, numero, inim);
        nome = "Guardas Distraídos";
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
