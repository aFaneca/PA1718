/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.ArrayList;
import java.util.List;


class Fortaleza {
    Mundo mundo;
    Muralha muralha;
    Povo povo;
    Suprimento suprimento;
    List<Inimigo> inimigos;
    Torre torre;
    Escada escada;
    Ariete ariete;
    Soldado soldados;
    int nrCatapultas;
    
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
    
    
}
