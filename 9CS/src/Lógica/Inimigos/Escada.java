/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Inimigos;

import Lógica.Inimigo;
import java.io.Serializable;

/**
 *
 * @author me
 */
public class Escada extends Inimigo implements Serializable{
    
    
    public Escada(){
        forca = 2;
        nome = "Escadas";
    }
}
