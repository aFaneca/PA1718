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
public abstract class Inimigo implements Serializable{
    protected int forca;
    protected int local; // 4 <= espaço inicial - 0 <= zona de close combat
    protected String nome;
    
    public Inimigo(){
        local = Constantes.POSICAO_INICIAL_INIMIGOS.getValor();
    }
    
    
    public int getLocal(){
        return local;
    }
    
    public void setLocal(int l){
        local = l;
        if(local < 0) local = 0;
        if(local > 3) local = 3;
    }
    
    public int getForca(){
        return forca;
    }
    
    public void alterarForca(int var){
        forca += var;
        
        if (forca < 0) forca = 0; //  A FORÇA NUNCA SERÁ MENOR QUE 0
        //if (forca > 4) forca = 4;
    }
    
    public void alterarLocal(int var){
        local += var;
        
        if(local < 0) local = 0;
        if(local > 3) local = 3;
    }
    
    @Override
    public String toString(){
        return nome;
    }
}
