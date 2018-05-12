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
public class GuardasDistraidos extends Evento implements Serializable{

    public GuardasDistraidos(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Guardas Distraídos";
        drms.add(new DRM(this, new Sabotagem(carta.getFortaleza()), 1));
        drms.add(new DRM(this, new MotivarTropas(carta.getFortaleza()), 1));
    }

}
