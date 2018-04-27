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
public abstract class Inimigo {
    protected int forca;
    protected int local; // 4 <= espaço inicial - 0 <= zona de close combat
    protected String nome;
    
    public Inimigo(){
        local = 3;
    }
    
    
    public int getLocal(){
        return local;
    }
    
    public void setLocal(int l){
        local = l;
        if(local < 0) local = 0;
        if(local > 3) local = 3;
    }
    
    @Override
    public String toString(){
        return nome;
    }
}
