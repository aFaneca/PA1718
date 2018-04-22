/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Ações;

import Lógica.Acao;
import Lógica.Fortaleza;
import Lógica.Inimigo;

/**
 *
 * @author me
 */
public class AtaqueDeArqueiros extends Acao{
    private Inimigo inimigoEscolhido;
    
    public AtaqueDeArqueiros(Fortaleza fortaleza){
        super("Ataque de Arqueiros", fortaleza);
        setReutilizavel(true);
    }
}
