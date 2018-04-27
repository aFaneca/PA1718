/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Lógica.DRM;

/**
 *
 * @author me
 */
public abstract class Evento {
    protected String nome;
    protected int APA; // Action Poins Allowance
    //protected static Mundo mundo;
    protected Carta carta;
    protected List<Inimigo> inimigos;
    protected List<DRM> drms; // Dice Result Modifications
    protected List<Acao> acoesPermitidas; // LISTA DE AÇOES PERMITIDAS; POR DEFAULT, TODAS SÃO PERMITIDAS
   
    public Evento(Carta c, int var, List<Inimigo> inim){
        APA = var;
        this.carta = c;
        this.inimigos = new ArrayList<>(inim);
        this.drms = new ArrayList<>();
        this.acoesPermitidas = new ArrayList<>(carta.getAcoes());
    }
    
    /*
    protected abstract boolean condicao();
*/
    protected abstract void acao();
    
    
    
    public boolean temRestricoesDeAcoes(){
        if(acoesPermitidas.isEmpty())
            return false;
        return true;
    }
    public List<Acao> getAcoesPermitidas(){
        return acoesPermitidas;
    }
    
    
    
    
    public int getAPA() {return APA;}

    public void setAPA(int APA) {this.APA = APA;}
    
    protected boolean temDRM(){
        if(drms.isEmpty())
            return false;
        return true;
    }

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
    
    
    @Override
    public String toString(){
        return nome;
    }

    public List<Inimigo> getInimigos() {
        return carta.getTodosOsInimigos();
    }
    
    public List<Inimigo> getInimigosDoEvento() {
        return inimigos;
    }
}
