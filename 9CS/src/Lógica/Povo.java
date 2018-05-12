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
class Povo implements Serializable{
    private int moral;
    private Fortaleza fortaleza;
    
    public Povo(Fortaleza fortaleza){
        this.fortaleza = fortaleza;
        moral = 4;
    }

    public int getMoral() {
        return moral;
    }
    
    public void alterarMoral(int quant){
        moral += quant;
        if(moral > 4) moral = 4;
        if(moral < 0) moral = 0;
    }
}
