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
class Muralha implements Serializable{
    private int forca;
    private Fortaleza fortaleza;
    
    public Muralha(Fortaleza fortaleza){
        forca = 4;
        this.fortaleza = fortaleza;
    }
    
    
    public void alteraForca(int quant){
        forca += quant;
        if(forca > 4) forca = 4;
        if(forca < 0) forca = 0;
    }

    public int getForca() {
        return forca;
    }
 
}
