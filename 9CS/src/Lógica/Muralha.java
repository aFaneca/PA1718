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
class Muralha {
    int forca;
    
    public Muralha(){
        forca = 4;
    }
    
    
    public void alteraForca(int quant){
        forca += quant;
        if(forca > 4) forca = 4;
        if(forca < 0) forca = 0;
            
    }
}
