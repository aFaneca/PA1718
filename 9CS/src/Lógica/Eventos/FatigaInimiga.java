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
public class FatigaInimiga extends Evento{

    public FatigaInimiga(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Fatiga Inimiga";
        drms.add(new DRM(new RepararMuralha(carta.getFortaleza()), +1));
        drms.add(new DRM(new Raid(carta.getFortaleza()), +1));
        drms.add(new DRM(new Sabotagem(carta.getFortaleza()), +1));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
