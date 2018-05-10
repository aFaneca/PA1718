/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.Serializable;

/**
 *
 * @author me
 */
class Suprimento implements Serializable{
    private int nivel;
    private Fortaleza fortaleza;
    
    public Suprimento(Fortaleza fortaleza){
        this.fortaleza = fortaleza;
        nivel = 4;
    }

    public int getNivel() {
        return nivel;
    }
    
    public void alterarNivel(int quant){
        nivel += quant;
        if(nivel > 2) nivel = 2;
        if(nivel < 1) nivel = 1;
    }
}
