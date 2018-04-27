/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Texto;

import Estados.*;
import Lógica.*;
import Lógica.Eventos.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @authors 
 * Amadeus Alves
 * António Faneca
 */
public class Main {
    private Scanner sc;
    private int valorLido;
    boolean sair; 
    private String motivoFimDoJogo;
    Mundo m;
    Evento eventoAtual;
    Carta cartaVirada;
    
    public Main(){
        sc = new Scanner(System.in);
        sair = false;
        m = new Mundo();
    }
    
    private void run() {
        while (!sair) {      
            IEstados estado = m.getEstado();  
            
            
            if(estado instanceof AguardaInicio){
                novoJogo();

            }
                //m.novoJogo();
            else if(estado instanceof AguardaCarta){
                virarCarta();
            }
            
            else if(estado instanceof AguardaSelecaoDeAcao)
                selecionarAcao();
            
            else if(estado instanceof JogoTerminado){
                fimDoJogo();
                sair = true;
            }
                
        }
        
    }
    
    private void novoJogo(){
//        System.out.println(":: UNIDADES MAIS LENTAS ::");
//        List<Inimigo> inimigos = new ArrayList<>(m.getFortaleza().getUnidadesLentas());
//        
//        for(Inimigo i : inimigos){
//            System.out.println("> " + i + " - Local: " + i.getLocal());
//        }

//        System.out.println("Nr. de Cartas: " + m.getCartas().size());
//        m.verInfo();
//        sc.nextInt();
            
        mostraMenu();
 
        if(sc.hasNextInt()){  // SE TIVER LIDO UM INTEIRO
            switch(sc.nextInt()){
                case 1: clearScreen(); m.novoJogo();
                    break;
                case 2:
                    break;
                case 3: return;

                default: clearScreen(); 
                    break;
                }
            }else{ // SENÃO, PEDIR OUTRO INPUT
                clearScreen();
                sc.next(); 
        }

        System.out.println(">>> Clique no 'Enter' para continuar...");
        sc.nextLine();
        clearScreen();
        System.out.println(">>> Clique no 'Enter' para continuar...");
        sc.nextLine();  
    }
        

   private void virarCarta(){
       cartaVirada = m.virarCarta();
       eventoAtual = m.eventoAtual(cartaVirada);
       
       // ANTES DE TUDO, VERIFICA SE EXISTEM SOLDADOS NO TUNEL
       if(m.soldadosNoTunel()){
           System.out.println("Temos soldados corajosos a atravessar os túneis.");
           System.out.println("Clique no 'ENTER' para rodar o dado e determinar o seu destino.");
           sc.nextLine();
           int resultado = m.sorteDosSoldados();
           if(resultado > Constantes.SOLDADOS_NO_TUNEL_SEM_SORTE.getValor()){
               System.out.println("A sorte está do seu lado! O resultado do dado foi " + resultado + " e os soldados continuam indetetados no Túnel.");
           }else{
               System.out.println("A sorte não está do seu lado! O resultado do dado foi " + resultado + " e os soldados foram capturados pelos inimigos");
               m.soldadosCapturados();
           }
       }else
           System.out.println("Não tem soldados no túnel. Todos estão em segurança dentro do Castelo.");
       
       
       // VIRA A CARTA NO TOPO DO BARALHO
       System.out.println("Encontrou a carta " + cartaVirada + ".");
       System.out.println("Enfrentará agora o Evento " + eventoAtual + ".");
       
       // DESCOBRE QUAL O EVENTO ATUAL E APLICA-O
       if(eventoAtual instanceof AtaqueDeCatapulta)
           EVENTO_ataqueDeCatapulta(eventoAtual);
       else if(eventoAtual instanceof CatapultaReparada)
           EVENTO_catapultaReparada();
       else if(eventoAtual instanceof CoberturaDaEscuridao)
           EVENTO_coberturaDaEscuridao();
       else if(eventoAtual instanceof Colapso)
           EVENTO_colapso();
       else if(eventoAtual instanceof Doenca)
           EVENTO_doenca();
       else if(eventoAtual instanceof EscudosDeFerro)
           EVENTO_escudosDeFerro();
       else if(eventoAtual instanceof FatigaInimiga)
           EVENTO_fatigaInimiga();
       else if(eventoAtual instanceof Fe)
           EVENTO_fe();
       else if(eventoAtual instanceof FlechasFlamejantes)
           EVENTO_flechasFlamejantes();
       else if(eventoAtual instanceof GuardasDistraidos)
           EVENTO_guardasDistraidos();
       else if(eventoAtual instanceof InimigoDeterminado)
           EVENTO_inimigoDeterminado();
       else if(eventoAtual instanceof MauTempo)
           EVENTO_mauTempo();
       else if(eventoAtual instanceof MorteDeUmLider)
           EVENTO_morteDeUmLider();
       else if(eventoAtual instanceof OleoQuente)
           EVENTO_oleoQuente();
       else if(eventoAtual instanceof PortaFortificada)
           EVENTO_portaFortificada();
      else if(eventoAtual instanceof Reuniao)
           EVENTO_reuniao();
       else if(eventoAtual instanceof SalvaDeFlechas)
           EVENTO_salvaDeFlechas();
       else if(eventoAtual instanceof SuprimentosEstragados)
           EVENTO_suprimentosEstragados();
       
       // MOSTRA OS DRMS ASSOCIADOS AO EVENTO
       mostraDRMS(eventoAtual);
       
       // AVANÇO DO INIMIGO
       avancaInimigos(eventoAtual);
       verificaCondicoesFataisImediatas(); /* FALTA FAZER COM QUE ESTAS CONDIÇÕES SEJAM VERIFICADAS IMEDIATAMENTE */

       // MÉTODO PARA VERIFICAR AS CONDIÇÕES QUE DETERMINAM O FIM DO JOGO AO FINAL DE CADA TURNO 
       verificaCondicoesFatais();   
   } 
   
   
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
     
    }
        
    public static void clearScreen() {  
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "        \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
        System.out.flush();  
    }

    private void mostraMenu() {
        System.out.println("## 9 CARDS SIEGE ##\n"
                + "[1] NOVO JOGO\n"
                + "[2] CONTINUAR JOGO\n"
                + "[3] SAIR");
        
        System.out.print("> ");
    }

    private void mostraDRMS(Evento evento){
        if(m.temDRM(evento)){ // SE O EVENTO POSSUI DRMs
            List<DRM> drms = new ArrayList<>(m.getDRMS(evento));
            for(DRM drm : drms){
                if(drm.isValida())
                    System.out.println("Este evento concedeu-lhe a seguinte DRM: " + drm + ".");
            }
                
        }
    }
    
    private void avancaInimigos(Evento evento){ // AVANÇA OS INIMIGOS ASSOCIADOS A ESTE EVENTO
        if(evento.getInimigosDoEvento().isEmpty()){ // Nenhuma unidade inimiga se deve mover quando existem ataques de trebuchets (não necessita de filtração, porque nesses casos, a lista já se encontra vazia de qualquer forma
            System.out.println("De acordo com esta carta, nenhum inimigo avançará nenhuma posição neste turno.");
        }else{
            m.avancaInimigos(evento);
            for(Inimigo i : evento.getInimigosDoEvento()){
                System.out.println("O inimigo " + i + " encontra-se agora na localização " + i.getLocal() + ".");
            }
        }
        
    }
    
    // EVENTOS
    private void EVENTO_ataqueDeCatapulta(Evento evento) {
        //m.aplicarEvento(evento);
        m.evento_AtaqueDeCatapulta();
    }

    private void EVENTO_catapultaReparada() {
        m.evento_CatapultaReparada();
        System.out.println("Adicionada 1 Catapulta à sua defesa. Agora possui " + m.contaCatapultas() + " catapultas.");
        
        
    }

    private void EVENTO_coberturaDaEscuridao() {
        return;
    }

    private void EVENTO_colapso() {
        // A TORRE DE CERCO É REMOVIDA DO JOGO SE ESTIVER NA POSIÇÃO INICIAL
        if(m.getPosTorre() == Constantes.POSICAO_INICIAL_INIMIGOS.getValor()){ // SE A POSIÇÃO DA TORRE FOR A INICIAL
            m.removerTorre();
            System.out.println("Uma vez que a Torre de Cerco ainda se encontrava na posição inicial, a mesma foi destruída permanentemente. Menos uma preocupação!");
        }else
            System.out.println("Uma vez que a Torre de Cerco já não se encontrava na posição inicial, nada aconteceu.");
    }

    private void EVENTO_doenca() {
        m.evento_Doenca();
        System.out.println("A moral e os suprimentos foram reduzidos em 1 unidade.");
    }

    private void EVENTO_escudosDeFerro() {
        return;
    }

    private void EVENTO_fatigaInimiga() {
        return;
    }

    private void EVENTO_fe() {
        return;
    }

    private void EVENTO_flechasFlamejantes() {
        return;
    }

    private void EVENTO_guardasDistraidos() {
        return;
    }

    private void EVENTO_inimigoDeterminado() {
        return;
    }

    private void EVENTO_mauTempo() {
        return;
    }

    private void EVENTO_morteDeUmLider() {
        m.evento_MorteDeUmLider();
    }

    private void EVENTO_oleoQuente() {
        return;
    }

    private void EVENTO_portaFortificada() {
        return;
    }

    private void EVENTO_reuniao() {
        return;
    }

    private void EVENTO_salvaDeFlechas() {
        return;
    }

    private void EVENTO_suprimentosEstragados() {
        m.evento_SuprimentosEstragados();
    }

    private void fimDoJogo() {
        System.out.println(":: GAME OVER ::");
        System.out.println("O jogo chegou ao fim pelo seguinte motivo: " + motivoFimDoJogo);
    }

    private void verificaCondicoesFatais() {
        motivoFimDoJogo = m.verificaCondicoesFatais();
    }
    
    private void verificaCondicoesFataisImediatas() {
        motivoFimDoJogo = m.verificaCondicoesFatais();
    }

    private void selecionarAcao() {
        List<Acao> acoesPermitidas = eventoAtual.getAcoesPermitidas();
        Acao acao;
        
        do{
            System.out.println("Escolha uma ação: ");
            int i = 0;

            for(Acao a : acoesPermitidas){
                System.out.println("[" + i + "] " + a);
                i++;
            }
            System.out.println("> ");
            sc.next();
        } while(!sc.hasNextInt());
        
        int resposta = sc.nextInt();
        
        switch(resposta){
            case 0: acao = new // Ataque de Agua Fervente
                break;
            case 1: // Ataque de Arqueiros
                break;
            case 2: // Ataque de Close Combat
                break;
            case 3: // Motivar as Tropas
                break;
            case 4: // Movimentar Soldados No Túnel
                break;
            case 5: // Raid
                break;
            case 6: // Reparar Muralhas
                break;
            case 7: // Sabotagem
                break;
        }
    }
}
