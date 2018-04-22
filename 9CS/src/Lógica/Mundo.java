/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Estados.AguardaInicio;
import Estados.IEstados;
import Lógica.Inimigos.UnidadesLentas;
import Lógica.Inimigos.Escada;
import Lógica.Inimigos.Torre;
import Lógica.Inimigos.Ariete;
import Lógica.Eventos.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Mundo {
    private Dado dado;
    private Fortaleza fortaleza;
    private List<Carta> cartas;
    private List<List<Inimigo>> inimigos;
    private List<Evento> eventos;
    private IEstados estadoAtual; 
    private static int cartasViradas;
    private int dia;
    
    
    public Mundo(){
        estadoAtual = new AguardaInicio(this);
        dado = new Dado();
        fortaleza = new Fortaleza(this);
        cartas = new ArrayList<>();
        eventos = new ArrayList<>();
        gerarCartas();
        cartasViradas = 0;
        dia = 3;
    
    }
    
    public void gerarCartas(){
        // INICIALIZAÇÃO
        inimigos = new ArrayList<>(3);
        
        
        // CARTA 1
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        cartas.add(new Carta(this, 1));
        eventos.clear();
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 3, inimigos.get(0)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 2, inimigos.get(1)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 1, inimigos.get(2)));
        cartas.get(0).adicionaEventos(eventos);
//        cartas.add(new Carta(this,1, 
//                new AtaqueDeCatapulta(fortaleza, 3, inimigos.get(0)), 
//                new AtaqueDeCatapulta(this, 2, inimigos.get(1)), 
//                new AtaqueDeCatapulta(this, 1, inimigos.get(2))
//        ));
        
        // CARTA 2
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getTorre());
        inimigos.get(1).add(fortaleza.getUnidadesLentas());
        cartas.add(new Carta(this, 2));
        eventos.clear();
        eventos.add(new Doenca(cartas.get(1), 2, inimigos.get(0)));
        eventos.add(new GuardasDistraidos(cartas.get(1), 2, inimigos.get(1)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(1), 1, inimigos.get(2)));
        cartas.get(1).adicionaEventos(eventos);

//        cartas.add(new Carta(this,2, 
//                new Doenca(this, 2, inimigos.get(0)), 
//                new GuardasDistraidos(this, 2, inimigos.get(1)), 
//                new AtaqueDeCatapulta(this, 1, inimigos.get(2)))
//        );
        
        // CARTA 3
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getEscada());
        inimigos.get(2).add(fortaleza.getEscada());
        inimigos.get(2).add(fortaleza.getAriete());
        cartas.add(new Carta(this, 3));
        eventos.clear();
        eventos.add(new SuprimentosEstragados(cartas.get(2), 2, inimigos.get(0)));
        eventos.add(new MauTempo(cartas.get(2), 2, inimigos.get(1)));
        eventos.add(new OleoQuente(cartas.get(2), 2, inimigos.get(2)));
        cartas.get(2).adicionaEventos(eventos);
                
                
//        cartas.add(new Carta(this,3, 
//                new SuprimentosEstragados(this, 2, inimigos.get(0)), 
//                new MauTempo(this, 2, inimigos.get(1)), 
//                new OleoQuente(this, 2, inimigos.get(2)))
//        );
        
        // CARTA 4
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getEscada());
        inimigos.get(0).add(fortaleza.getTorre());
        inimigos.get(1).add(fortaleza.getEscada());
        inimigos.get(1).add(fortaleza.getAriete());
        inimigos.get(2).add(fortaleza.getTorre());
        cartas.add(new Carta(this, 4));
        eventos.clear();
        eventos.add(new MorteDeUmLider(cartas.get(3), 2, inimigos.get(0)));
        eventos.add(new PortaFortificada(cartas.get(3), 2, inimigos.get(1)));
        eventos.add(new FlechasFlamejantes(cartas.get(3), 2, inimigos.get(2)));
        cartas.get(3).adicionaEventos(eventos);
        
//        cartas.add(new Carta(this,4, 
//                new MorteDeUmLider(this, 2, inimigos.get(0)), 
//                new PortaFortificada(this, 2, inimigos.get(1)), 
//                new FlechasFlamejantes(this, 2, inimigos.get(2)))
//        );
        
        // CARTA 5
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getAriete());
        inimigos.get(1).add(fortaleza.getEscada());
        inimigos.get(1).add(fortaleza.getAriete());
        inimigos.get(2).add(fortaleza.getEscada());
        cartas.add(new Carta(this, 5));
        eventos.clear();
        eventos.add(new SalvaDeFlechas(cartas.get(4), 2, inimigos.get(0)));
        eventos.add(new Colapso(cartas.get(4), 2, inimigos.get(1)));
        eventos.add(new CatapultaReparada(cartas.get(4), 2, inimigos.get(2)));
        cartas.get(4).adicionaEventos(eventos);
        
        
//        cartas.add(new Carta(this,5, 
//                new SalvaDeFlechas(this, 2, inimigos.get(0)), 
//                new Colapso(this, 2, inimigos.get(1)), 
//                new CatapultaReparada(this, 2, inimigos.get(2)))
//        );
        
        // CARTA 6
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getUnidadesLentas());
        inimigos.get(1).add(fortaleza.getEscada());
        inimigos.get(2).add(fortaleza.getAriete());
        inimigos.get(2).add(fortaleza.getTorre());
        cartas.add(new Carta(this, 6));
        eventos.clear();
        eventos.add(new CoberturaDaEscuridao(cartas.get(5), 2, inimigos.get(0)));
        eventos.add(new FatigaInimiga(cartas.get(5), 2, inimigos.get(1)));
        eventos.add(new Reuniao(cartas.get(5), 2, inimigos.get(2)));
        cartas.get(5).adicionaEventos(eventos);
        
        
//        cartas.add(new Carta(this,6, 
//                new CoberturaDaEscuridao(this, 2, inimigos.get(0)), 
//                new FatigaInimiga(this, 2, inimigos.get(1)), 
//                new Reuniao(this, 2, inimigos.get(2)))
//        );
        
        // CARTA 7
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getAriete());
        inimigos.get(1).add(fortaleza.getTorre());
        inimigos.get(2).add(fortaleza.getEscada());
        inimigos.get(2).add(fortaleza.getAriete());
        inimigos.get(2).add(fortaleza.getTorre());
        cartas.add(new Carta(this, 7));
        eventos.clear();
        eventos.add(new InimigoDeterminado(cartas.get(6), 2, inimigos.get(0)));
        eventos.add(new EscudosDeFerro(cartas.get(6), 2, inimigos.get(1)));
        eventos.add(new Fe(cartas.get(6), 1, inimigos.get(2)));
        cartas.get(6).adicionaEventos(eventos);
        
        
//        cartas.add(new Carta(this,7, 
//                new InimigoDeterminado(this, 2, inimigos.get(0)), 
//                new EscudosDeFerro(this, 2, inimigos.get(1)), 
//                new Fe(this, 2, inimigos.get(2)))
//        );
    }
    
    
    public void baralharCartas(){
        Collections.shuffle(cartas);
    }
    
    
    public void novoJogo(){
        baralharCartas();
        setEstado(estadoAtual.proximoEstado());
    }
    
    public Carta virarCarta(){
       if(cartasViradas > 6){
           cartasViradas = 0;
          // setEstado(estadoAtual.fimDoDia());
          setEstado(new AguardaInicio(this)); // TEMPORÁRIO PARA EFEITO DE TESTES
       }
       
        return cartas.get(cartasViradas++);
    }
    
    
    public Evento eventoAtual(Carta carta){
        return carta.getEventoAtual();
    }
    
//    public void aplicarEvento(Evento evento){
//        evento.acao();
//    }
    
    public int rodaDado(){
        return dado.rodaDado();
    }

    public int contaCatapultas() {
        return fortaleza.getNrCatapultas();
    }
    
    public void alteraMuralha(int quant){
        fortaleza.alteraMuralha(quant);
    }

    public boolean soldadosNoTunel(){

        return fortaleza.soldadosNoTunel();
    }
    
    public int sorteDosSoldados(){
        return dado.rodaDado();
    }
    
    public void soldadosCapturados(){
        fortaleza.soldadosCapturados();
    }
    
    public boolean temDRM(Evento evento){
        return evento.temDRM();
    }
    
    
    public void evento_AtaqueDeCatapulta(){
        fortaleza.evento_AtaqueDeCatapulta();
    }
    
    public void evento_Doenca(){
        // REDUZIR MORAL E SUPRIMENTOS
        fortaleza.evento_Doenca();
    }
    
    public void evento_SuprimentosEstragados() {
        fortaleza.evento_SuprimentosEstragados();
    }
    
    public void evento_MorteDeUmLider() {
        fortaleza.evento_MorteDeUmLider();
    }
    
    public void evento_CatapultaReparada(){
        fortaleza.alteraNrCatapultas(1);
    }
    
    public void removerTorre(){
        fortaleza.removerTorre();
    }
    public List<DRM> getDRMS(Evento evento){
        return evento.drms;
    }
    
    public List<Carta> getCartas() {
        return cartas;
    }
    
    public IEstados getEstado(){
        return this.estadoAtual;
    }
    
    public void setEstado(IEstados estado){
        this.estadoAtual = estado;
    }
    
    public int getDia(){
        return this.dia;
    }
    
    public void setDia(int d){
        this.dia = d;
    }
    
    public Fortaleza getFortaleza(){
        return fortaleza;
    }
    
    public int getPosTorre(){ // OBTÉM A POSIÇÃO DA TORRE DE CERCO
        return fortaleza.posDaTorre();
    }
    public void verInfo(){
        int nr = 1, nr1 = 1;
        
        for(Carta carta : cartas){
            System.out.println("## CARTA Nº " + nr++ + " ##");
            System.out.println("\tNr. de Eventos: " + carta.eventos.size());
            for(Evento evento : carta.eventos){
                System.out.println("\t\tNOME DO EVENTO: " + evento);
                System.out.println("\t\tAPA: " + evento.APA);
                System.out.println("\t\tNr. de Inimigos: " + evento.inimigos.size());
                for(Inimigo inimigo : evento.inimigos){
                    System.out.println("\t\t\tINIMIGO #" + nr1++ + " - " + inimigo);
                }
                System.out.println("\n\t\t\t------------------------\n\n");
                nr1 = 1;
            }
            System.out.println("\n==========================================\n");
        }
        
    }

    


}
