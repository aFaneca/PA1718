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
public class Torre extends Inimigo implements Serializable{
    
    
    public Torre(){
        forca = 4;
        nome = "Torres de Cerco";
    }
}
