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
    private IEstados estadoAtual; 
    private static int cartasViradas;
    private int dia;
    
    public Mundo(){
        estadoAtual = new AguardaInicio(this);
        dado = new Dado();
        fortaleza = new Fortaleza(this);
        cartas = new ArrayList<>();
        gerarCartas();
        cartasViradas = 0;
        dia = 1;
    
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
                new AtaqueDeCatapulta(this, 3, inimigos.get(0)), 
                new AtaqueDeCatapulta(this, 2, inimigos.get(1)), 
                new AtaqueDeCatapulta(this, 1, inimigos.get(2))
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
                new Doenca(this, 2, inimigos.get(0)), 
                new GuardasDistraidos(this, 2, inimigos.get(1)), 
                new AtaqueDeCatapulta(this, 1, inimigos.get(2)))
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
                new SuprimentosEstragados(this, 2, inimigos.get(0)), 
                new MauTempo(this, 2, inimigos.get(1)), 
                new OleoQuente(this, 2, inimigos.get(2)))
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
                new MorteDeUmLider(this, 2, inimigos.get(0)), 
                new PortaFortificada(this, 2, inimigos.get(1)), 
                new FlechasFlamejantes(this, 2, inimigos.get(2)))
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
                new SalvaDeFlechas(this, 2, inimigos.get(0)), 
                new Colapso(this, 2, inimigos.get(1)), 
                new CatapultaReparada(this, 2, inimigos.get(2)))
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
                new CoberturaDaEscuridao(this, 2, inimigos.get(0)), 
                new FatigaInimiga(this, 2, inimigos.get(1)), 
                new Reuniao(this, 2, inimigos.get(2)))
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
                new InimigoDeterminado(this, 2, inimigos.get(0)), 
                new EscudosDeFerro(this, 2, inimigos.get(1)), 
                new Fe(this, 2, inimigos.get(2)))
        );
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
    
    public void aplicarEvento(Evento evento){
        evento.acao();
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

    public boolean soldadosNoTunel(){
        return fortaleza.soldadosNoTunel();
    }
    
    public int sorteDosSoldados(){
        return dado.rodaDado();
    }
    
    public void soldadosCapturados(){
        fortaleza.soldadosCapturados();
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
