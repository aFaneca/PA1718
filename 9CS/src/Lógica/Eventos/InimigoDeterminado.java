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
import Lógica.Inimigos.*;
import Lógica.Mundo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class InimigoDeterminado extends Evento{
    List<Inimigo> inimigosAfetados;
    
    public InimigoDeterminado(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Inimigo Determinado";
        
        // -1 para ataques contra Ariete
        inimigosAfetados = new ArrayList<>();
        inimigosAfetados.add(new Ariete());
        drms.add(new DRM(new AtaqueDeArqueiros(carta.getFortaleza()), -1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeAguaFervente(carta.getFortaleza()), -1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeCloseCombat(carta.getFortaleza()), -1, inimigosAfetados));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
