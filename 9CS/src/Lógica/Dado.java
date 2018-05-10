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

    public Dado(){
        random = new Random();
    }
    
    
    public int rodaDado(){
        int min = 1;
        int max = 6;
        
        return random.nextInt(max) + min;
    }
}
