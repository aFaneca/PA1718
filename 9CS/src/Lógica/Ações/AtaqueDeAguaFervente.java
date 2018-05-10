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
public class AtaqueDeAguaFervente extends Acao implements Serializable{
    
    public AtaqueDeAguaFervente(Fortaleza fortaleza){
        super("Ataque de Àgua Fervente", fortaleza);
        setReutilizavel(false);
    }
    
    @Override
    public List<Inimigo> getPotenciaisAlvos(){
        List<Integer> locais;
        locais = new ArrayList<>();
        locais.add(1); // TODOS OS INIMIGOS EM ESPAÇOS CIRCULARES (local = 1)
        potenciaisAlvos = new ArrayList<>(fortaleza.getInimigos(locais));
        
        return potenciaisAlvos;
    }
    
}
