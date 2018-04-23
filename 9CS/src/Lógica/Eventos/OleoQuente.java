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
import Lógica.Mundo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class OleoQuente extends Evento {
    private List<Integer> locais;
    private List<Inimigo> inimigosAfetados;
    
    public OleoQuente(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Óleo Quente";
        
        // +2 para ataques em Espaços Circulares (local = 1)
        locais = new ArrayList<>();
        locais.add(1);
        inimigosAfetados = new ArrayList<>(carta.getInimigos(locais)); // RETORNA OS INIMIGOS QUE ESTÃO EM ALGUMA DAS POSIÇÕES INDICADAS (NESTE CASO, POSIÇÃO 1)
        
        drms.add(new DRM(this, new AtaqueDeArqueiros(carta.getFortaleza()), 2, inimigosAfetados));
        drms.add(new DRM(this, new AtaqueDeAguaFervente(carta.getFortaleza()), 2, inimigosAfetados));
        drms.add(new DRM(this, new AtaqueDeCloseCombat(carta.getFortaleza()), 2, inimigosAfetados));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
