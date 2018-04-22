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
import Lógica.Inimigos.*;
import Lógica.Mundo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class SalvaDeFlechas extends Evento{
    List<Inimigo> inimigosAfetados;
    
    public SalvaDeFlechas(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Salva de Flechas";
        
        // +1 para todos os ataques
        inimigosAfetados = new ArrayList<>();
        inimigosAfetados.add(new Torre());
        inimigosAfetados.add(new Escada());
        inimigosAfetados.add(new Ariete());
        drms.add(new DRM(new AtaqueDeArqueiros(carta.getFortaleza()), 1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeAguaFervente(carta.getFortaleza()), 1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeCloseCombat(carta.getFortaleza()), 1, inimigosAfetados));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
