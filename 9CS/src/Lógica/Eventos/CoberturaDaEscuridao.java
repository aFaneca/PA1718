/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Ações.*;
import Lógica.Carta;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.util.List;

/**
 *
 * @author me
 */
public class CoberturaDaEscuridao extends Evento{

    public CoberturaDaEscuridao(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Cobertura da Escuridão";
        drms.add(new DRM(this, new Raid(carta.getFortaleza()), 1)); // +1 para Raid
        drms.add(new DRM(this, new Sabotagem(carta.getFortaleza()), 1)); // +1 para Sabotagem
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
