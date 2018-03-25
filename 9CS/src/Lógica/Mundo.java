/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class Mundo {
    Dado dado;
    Fortaleza fortaleza;
    List<Carta> cartas;
    
    
    public Mundo(){
        dado = new Dado();
        fortaleza = new Fortaleza(this);
        cartas = new ArrayList<>();
    }
    
    
    public int rodaDado(){
        return dado.rodaDado();
    }
}
