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
public class AtaqueDeArqueiros extends Acao implements Serializable{
    private Inimigo inimigoEscolhido;
    
    
    public AtaqueDeArqueiros(Fortaleza fortaleza){
        super("Ataque de Arqueiros", fortaleza);
        setReutilizavel(true);
    }
    
    @Override
    public List<Inimigo> getPotenciaisAlvos(){
        List<Integer> locais;
        locais = new ArrayList<>();
        locais.add(0); locais.add(1); locais.add(2); locais.add(3); // TODOS OS INIMIGOS EM TODAS AS POSIÇÕES
        potenciaisAlvos = new ArrayList<>(fortaleza.getInimigos(locais));
        
        return potenciaisAlvos;
    }
}
