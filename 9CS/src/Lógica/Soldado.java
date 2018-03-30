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
class Soldado {
    private int local; // 0 => no castelo | 1 => entrada do túnel | 2 => saida do túnel | 3 => linhas inimigas
    private boolean capturado;
    
    public Soldado(){
        local = 0;
        capturado = false;
    }
    
    
    public boolean noTunel(){
        if(this.local == 1 || this.local == 2)
            return true;
        return false;
    }
}
