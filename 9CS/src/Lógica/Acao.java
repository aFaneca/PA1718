/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public abstract class Acao implements Serializable{
    private String nome;
    private boolean reutilizavel; // ESTA AÇÃO PODE SER REUTILIZADA NO MESMO TURNO?
    protected Fortaleza fortaleza;
    protected List<Inimigo> potenciaisAlvos; // PARA OS ATAQUES DE ARQUEIROS, AGUA FERVENTE E CLOSE COMBAT
    
    public Acao(String nome, Fortaleza fortaleza){
        this.nome = nome;
        this.fortaleza = fortaleza;
    }

    public boolean isReutilizavel() {
        return reutilizavel;
    }

    public void setReutilizavel(boolean reutilizavel) {
        this.reutilizavel = reutilizavel;
    }
    
    public List<Inimigo> getPotenciaisAlvos(){
        return null;
    }
    
    @Override
    public String toString(){
        return nome;
    }
}
