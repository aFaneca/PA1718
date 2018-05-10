/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Carta;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author me
 */
public class Doenca extends Evento implements Serializable{

    public Doenca(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Doença";
        
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
