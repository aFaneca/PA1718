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
abstract class Inimigo {
    int forca;
    int local; // 4 <= espaço inicial - 0 <= zona de close combat
    
    
    public Inimigo(){
        local = 4;
    }
}
