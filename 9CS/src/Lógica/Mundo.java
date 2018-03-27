/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Lógica.Inimigos.UnidadesLentas;
import Lógica.Inimigos.Escada;
import Lógica.Inimigos.Torre;
import Lógica.Inimigos.Ariete;
import Lógica.Eventos.*;
import java.util.ArrayList;
import java.util.List;


public class Mundo {
    private Dado dado;
    private Fortaleza fortaleza;
    private List<Carta> cartas;
    private List<List<Inimigo>> inimigos;

    
    public Mundo(){
        dado = new Dado();
        fortaleza = new Fortaleza(this);
        cartas = new ArrayList<>();
        gerarCartas();
    
    }
    
    public void gerarCartas(){
        // INICIALIZAÇÃO
        inimigos = new ArrayList<>(3);
        
        
        // CARTA 1
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        cartas.add(new Carta(this,1, 
                new AtaqueDeCatapulta(3, inimigos.get(0)), 
                new AtaqueDeCatapulta(2, inimigos.get(1)), 
                new AtaqueDeCatapulta(1, inimigos.get(2))
        ));
        
        // CARTA 2
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new Torre());
        inimigos.get(1).add(new UnidadesLentas());
        //inimigos.get(2).add(null);

        cartas.add(new Carta(this,2, 
                new Doenca(2, inimigos.get(0)), 
                new GuardasDistraidos(2, inimigos.get(1)), 
                new AtaqueDeCatapulta(1, inimigos.get(2)))
        );
        
        // CARTA 3
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new Escada());
        inimigos.get(2).add(new Escada());
        inimigos.get(2).add(new Ariete());
        
        cartas.add(new Carta(this,3, 
                new SuprimentosEstragados(2, inimigos.get(0)), 
                new MauTempo(2, inimigos.get(1)), 
                new OleoQuente(2, inimigos.get(2)))
        );
        
        // CARTA 4
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new Escada());
        inimigos.get(0).add(new Torre());
        inimigos.get(1).add(new Escada());
        inimigos.get(1).add(new Ariete());
        inimigos.get(2).add(new Torre());
        
        cartas.add(new Carta(this,4, 
                new MorteDeUmLider(2, inimigos.get(0)), 
                new PortaFortificada(2, inimigos.get(1)), 
                new FlechasFlamejantes(2, inimigos.get(2)))
        );
        
        // CARTA 5
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new Ariete());
        inimigos.get(1).add(new Escada());
        inimigos.get(1).add(new Ariete());
        inimigos.get(2).add(new Escada());
        
        cartas.add(new Carta(this,5, 
                new SalvaDeFlechas(2, inimigos.get(0)), 
                new Colapso(2, inimigos.get(1)), 
                new CatapultaReparada(2, inimigos.get(2)))
        );
        
        // CARTA 6
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new UnidadesLentas());
        inimigos.get(1).add(new Escada());
        inimigos.get(2).add(new Ariete());
        inimigos.get(2).add(new Torre());
        
        cartas.add(new Carta(this,6, 
                new CoberturaDaEscuridao(2, inimigos.get(0)), 
                new FatigaInimiga(2, inimigos.get(1)), 
                new Reuniao(2, inimigos.get(2)))
        );
        
        // CARTA 7
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(new Ariete());
        inimigos.get(1).add(new Torre());
        inimigos.get(2).add(new Escada());
        inimigos.get(2).add(new Ariete());
        inimigos.get(2).add(new Torre());
        
        cartas.add(new Carta(this,7, 
                new InimigoDeterminado(2, inimigos.get(0)), 
                new EscudosDeFerro(2, inimigos.get(1)), 
                new Fe(2, inimigos.get(2)))
        );
    }
    
    
    public int rodaDado(){
        return dado.rodaDado();
    }

    public int contaCatapultas() {
        return fortaleza.getNrCatapultas();
    }
    
    public void alteraMuralha(int quant){
        fortaleza.alteraMuralha(quant);
    }

    public List<Carta> getCartas() {
        return cartas;
    }
    
    
    public void verInfo(){
        int nr = 1, nr1 = 1;
        
        for(Carta carta : cartas){
            System.out.println("## CARTA Nº " + nr++ + " ##");
            System.out.println("\tNr. de Eventos: " + carta.eventos.size());
            for(Evento evento : carta.eventos){
                System.out.println("\t\tNOME DO EVENTO: " + evento.nome);
                System.out.println("\t\tAPA: " + evento.APA);
                System.out.println("\t\tNr. de Inimigos: " + evento.inimigos.size());
                for(Inimigo inimigo : evento.inimigos){
                    System.out.println("\t\t\tINIMIGO #" + nr1++ + " - " + inimigo.nome);
                }
                System.out.println("\n\t\t\t------------------------\n\n");
                nr1 = 1;
            }
            System.out.println("\n==========================================\n");
        }
        
    }
}
