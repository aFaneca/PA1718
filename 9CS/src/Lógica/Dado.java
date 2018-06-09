/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.Serializable;
import java.util.Random;
/**
 *
 * @author me
 */
class Dado implements Serializable{
    Random random;
    int ultimoResultado;
    Mundo m;
    public Dado(Mundo m){
        random = new Random();
        this.m = m;
    }
    
    
    public int rodaDado(){
        int min = 1;
        int max = 6;
        
        setUltimoResultado(random.nextInt(max) + min);

        return ultimoResultado;
    }
    
    
    public int getUltimoResultado(){
        return ultimoResultado;
    }
    
    public void setUltimoResultado(int valor){
        ultimoResultado = valor;
        m.notificaAlteracao();
    }
}
