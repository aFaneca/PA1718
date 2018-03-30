/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Lógica.Inimigos.Escada;
import Lógica.Inimigos.Torre;
import Lógica.Inimigos.Ariete;
import java.util.ArrayList;
import java.util.List;


class Fortaleza {
    private Mundo mundo;
    private Muralha muralha;
    private Povo povo;
    private Suprimento suprimento;
    private List<Inimigo> inimigos;
    private Torre torre;
    private Escada escada;
    private Ariete ariete;
    private Soldado soldados;
    private int nrCatapultas;
    
    public Fortaleza(Mundo m){
        this.mundo = m;
        muralha = new Muralha();
        povo = new Povo();
        suprimento = new Suprimento();
        soldados = new Soldado();
        inimigos = new ArrayList<>(3);
        inimigos.add(torre);
        inimigos.add(escada);
        inimigos.add(ariete);
        nrCatapultas = 3;
    }

    public int getNrCatapultas() {
        return nrCatapultas;
    }
    
    public void alteraMuralha(int alteracao){
        muralha.alteraForca(alteracao);
    }
    
    public boolean soldadosNoTunel(){
        return soldados.noTunel();
    }
    
    
    public void soldadosCapturados(){
        // ELIMINAR REGISTO DOS SOLDADOS ATUAIS E GERAR NOVOS SOLDADOS
        soldados = new Soldado();
    }
    
    public void evento_Doenca(){
        povo.alterarMoral(-1);
        suprimento.alterarNivel(-1);
    }
    
    void evento_SuprimentosEstragados() {
        suprimento.alterarNivel(-1);
    }
    
    void evento_MorteDeUmLider() {
        povo.alterarMoral(-1);
    }
    
    
    @Override
    public String toString(){
        return "## DETALHES DA FORTALEZA ##\n" 
                + "FORÇA DA MURALHA: " + muralha.getForca()
                + "\n MORAL DO POVO: " + povo.getMoral()
                + "\n NIVEL DE SUPRIMENTOS: " + suprimento.getNivel();
    }

    

    
}
