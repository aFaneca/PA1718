/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Ações.Raid;
import Lógica.Ações.Sabotagem;
import Lógica.Carta;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author me
 */
public class MauTempo extends Evento implements Serializable{

    public MauTempo(Carta carta, int numero, List<Inimigo> inim){
        super(carta, numero, inim);
        nome = "Mau Tempo";
        

        // SÓ SÃO PERMITIDAS AÇÕES DE SABOTAGEM E RAID NESTA RONDA
        acoesPermitidas = new ArrayList<>();
        acoesPermitidas.add(carta.getAcaoDaLista(carta.getRaid()));
        acoesPermitidas.add(carta.getAcaoDaLista(carta.getSabotagem()));
    }

}
