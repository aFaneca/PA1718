/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Ações.AtaqueDeAguaFervente;
import Lógica.Ações.AtaqueDeArqueiros;
import Lógica.Ações.AtaqueDeCloseCombat;
import Lógica.Carta;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Inimigos.Ariete;
import Lógica.Mundo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class Reuniao extends Evento implements Serializable{
    private List<Inimigo> inimigosAfetados;
    private List<Integer> locais;
    
    public Reuniao(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Reunião";
        
        // +1 para ataques em Close Combat (local = 0) ou Espaços Circulares (local = 1)
        locais = new ArrayList<>();
        locais.add(0); locais.add(1);
        inimigosAfetados = new ArrayList<>(carta.getInimigos(locais));

        drms.add(new DRM(this, new AtaqueDeArqueiros(carta.getFortaleza()), 1, inimigosAfetados));
        drms.add(new DRM(this, new AtaqueDeAguaFervente(carta.getFortaleza()), 1, inimigosAfetados));
        drms.add(new DRM(this, new AtaqueDeCloseCombat(carta.getFortaleza()), 1, inimigosAfetados));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
