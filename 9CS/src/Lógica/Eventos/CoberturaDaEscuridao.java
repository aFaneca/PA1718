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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author me
 */
public class CoberturaDaEscuridao extends Evento implements Serializable{

    public CoberturaDaEscuridao(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Cobertura da Escuridão";
        drms.add(new DRM(this, new Raid(carta.getFortaleza()), 1)); // +1 para Raid
        drms.add(new DRM(this, new Sabotagem(carta.getFortaleza()), 1)); // +1 para Sabotagem
        drms.get(0).setAfetaInimigos(false);
        drms.get(1).setAfetaInimigos(false);
        
    }


}
