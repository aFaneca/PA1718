/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Ações;

import Lógica.Acao;
import Lógica.Fortaleza;

/**
 *
 * @author me
 */
public class AtaqueDeAguaFervente extends Acao{
    
    public AtaqueDeAguaFervente(Fortaleza fortaleza){
        super("Ataque de Àgua Fervente", fortaleza);
        setReutilizavel(false);
    }
}
