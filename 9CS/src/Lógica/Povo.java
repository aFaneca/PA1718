/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

/**
 *
 * @author me
 */
class Povo {
    private int moral;
    
    public Povo(){
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
