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
abstract class Carta {
    List<Evento> eventos;
    List<Inimigo> inimigos;
    Mundo mundo;
    int nr;
    
    public Carta(Mundo m, int numero, List<Inimigo> inim, Evento e1, Evento e2, Evento e3){
        this.mundo = m;
        eventos = new ArrayList<>(3);
        this.inimigos = new ArrayList<>(inim);
        nr = numero;
        eventos.add(e1);
        eventos.add(e2);
        eventos.add(e3);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.nr;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.nr != other.nr) {
            return false;
        }
        return true;
    }
    
    
    
}
