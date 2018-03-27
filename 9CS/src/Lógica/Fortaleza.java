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
    
}
