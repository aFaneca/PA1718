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
public class EscudosDeFerro extends Evento{

    /*  -1 em Ataques a Torres de Cerco
        Assim, aplica-se às seguintes ações:
            => Ataque de Arqueiros
            => Ataque de Àgua Fervente
            => Ataque de Close Combat
                                            */
    
    List<Inimigo> inimigosAfetados;
    
    public EscudosDeFerro(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Escudos de Ferro";
        inimigosAfetados = new ArrayList<>();
        inimigosAfetados.add(new Torre());
        drms.add(new DRM(new AtaqueDeArqueiros(carta.getFortaleza()), -1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeAguaFervente(carta.getFortaleza()), -1, inimigosAfetados));
        drms.add(new DRM(new AtaqueDeCloseCombat(carta.getFortaleza()), -1, inimigosAfetados));
    }

    @Override
    protected void acao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
