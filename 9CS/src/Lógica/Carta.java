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
public class Carta {
    protected List<Evento> eventos;
    protected Mundo mundo;
    protected int nr;
    protected Fortaleza fortaleza;
    
    public Carta(Mundo m, int numero){
        this.mundo = m;
        fortaleza = m.getFortaleza();
        nr = numero;
    }

    public void adicionaEventos(List<Evento> ev){
        eventos = new ArrayList<>(ev);        
    }
    
    
    public Fortaleza getFortaleza(){
        
        return fortaleza;
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
    
    
    @Override
    public String toString(){
        
        return "Carta nr." + nr;
    }

    Evento getEventoAtual() {
        return this.eventos.get(mundo.getDia() - 1);
    }
}
