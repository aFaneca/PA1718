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
    private Fortaleza fortaleza;
    private int suprimentosFurtados;
    
    public Soldado(Fortaleza fortaleza){
        this.fortaleza = fortaleza;
        local = 0;
        capturado = false;
        suprimentosFurtados = 0;
    }
    
    
    public boolean noTunel(){
        if(this.local == 1 || this.local == 2)
            return true;
        return false;
    }
    
    public void alteraSuprimentosFurtados(int var){
        suprimentosFurtados += var;
        
        if(suprimentosFurtados > 2) suprimentosFurtados = 2;
        if(suprimentosFurtados < 0) suprimentosFurtados = 0;
    }
    
    
    public int getLocal(){
        
        return local;
    }
    
    
    public int getSuprimentosFurtados(){
        return suprimentosFurtados;
    }
    
    
    public void alteraPosicao (int var){
        local += var;
        
        if(local < 0) local = 0;
        if(local > 3) local = 3;
    }
}
