/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author me
 */
public abstract class Evento {
    protected String nome;
    protected int APA; // Action Poins Allowance
    protected static Mundo mundo;
    protected List<Inimigo> inimigos;
    
    
    public Evento(Mundo m, int var, List<Inimigo> inim){
        APA = var;
        this.mundo = m;
        this.inimigos = new ArrayList<>(inim);
    }
   
    /*
    protected abstract boolean condicao();
*/
    protected abstract void acao();
    
    
    public int getAPA() {return APA;}

    public void setAPA(int APA) {this.APA = APA;}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nome);
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
        final Evento other = (Evento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
}
