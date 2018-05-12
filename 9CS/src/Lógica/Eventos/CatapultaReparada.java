/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Ações.RepararMuralha;
import Lógica.Carta;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Fortaleza;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author me
 */
public class CatapultaReparada extends Evento implements Serializable{

    public CatapultaReparada(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Catapulta Reparada";
        drms.add(new DRM(this, new RepararMuralha(carta.getFortaleza()), 1)); // +1 para ações de reparação da muralha
    }
   
}
