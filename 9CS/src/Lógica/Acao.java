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
public class Acao {
    private String nome;
    private boolean reutilizavel; // ESTA AÇÃO PODE SER REUTILIZADA NO MESMO TURNO?
    private Fortaleza fortaleza;
    
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
    
    @Override
    public String toString(){
        return nome;
    }
}
