/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Ações;

import Lógica.Acao;
import Lógica.Fortaleza;
import Lógica.Inimigo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class AtaqueDeCloseCombat extends Acao implements Serializable{
    
    public AtaqueDeCloseCombat(Fortaleza fortaleza){
        super("Ataque de Close Combat", fortaleza);
        setReutilizavel(true);
    }

    @Override
    public List<Inimigo> getPotenciaisAlvos(){
        List<Integer> locais;
        locais = new ArrayList<>();
        locais.add(0); // TODOS OS INIMIGOS EM ESPAÇO DE CLOSE COMBAT (local = 0)
        potenciaisAlvos = new ArrayList<>(fortaleza.getInimigos(locais));
        
        return potenciaisAlvos;
    }

}
