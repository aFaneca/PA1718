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
public class AtaqueDeCloseCombat extends Acao{
    
    public AtaqueDeCloseCombat(Fortaleza fortaleza){
        super("Ataque de Close Combat", fortaleza);
        setReutilizavel(true);
    }
}
