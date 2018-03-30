/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Texto;

import Estados.*;
import Lógica.*;
import Lógica.Eventos.*;
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
    Mundo m;
    
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
                
            
//            if( !(estado instanceof AguardaInicio) && !(estado instanceof AguardaTrocaRecursos))
//                System.out.print(j.getDados().toString()); //Mostro o tabuleiro ao utilizador
//            
//            if (estado instanceof AguardaAcao)
//                askForInputAcao();
//            else if (estado instanceof AguardaInicio)
//                askForInputMenuInicial();
//            else if (estado instanceof AguardaTrocaRecursos)
//                askForInputRecursos();
//            else if (estado instanceof AguardaUpgrades)
//                askForInputUpgrades();
        }
        
    }
    
    private void novoJogo(){
    //System.out.println("Nr. de Cartas: " + m.getCartas().size());
        //-m.verInfo();
            
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
       Carta cartaVirada = m.virarCarta();
       Evento eventoAtual = m.eventoAtual(cartaVirada);
       
       // ANTES DE TUDO, VERIFICA SE EXISTEM SOLDADOS NO TUNEL
       if(m.soldadosNoTunel()){
           System.out.println("Temos soldados corajosos a atravessar os túneis.");
           System.out.println("Clique no 'ENTER' para rodar o dado e determinar o seu destino.");
           sc.nextLine();
           int resultado = m.sorteDosSoldados();
           if(resultado > 1){
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
//       else if(eventoAtual instanceof CatapultaReparada)
//           EVENTO_catapultaReparada();
//       else if(eventoAtual instanceof CoberturaDaEscuridao)
//           EVENTO_coberturaDaEscuridao();
//       else if(eventoAtual instanceof Colapso)
//           EVENTO_colapso();
       else if(eventoAtual instanceof Doenca)
           EVENTO_doenca();
//       else if(eventoAtual instanceof EscudosDeFerro)
//           EVENTO_escudosDeFerro();
//       else if(eventoAtual instanceof FatigaInimiga)
//           EVENTO_fatigaInimiga();
//       else if(eventoAtual instanceof Fe)
//           EVENTO_fe();
//       else if(eventoAtual instanceof FlechasFlamejantes)
//           EVENTO_flechasFlamejantes();
//       else if(eventoAtual instanceof GuardasDistraidos)
//           EVENTO_guardasDistraidos();
//       else if(eventoAtual instanceof InimigoDeterminado)
//           EVENTO_inimigoDeterminado();
//       else if(eventoAtual instanceof MauTempo)
//           EVENTO_mauTempo();
       else if(eventoAtual instanceof MorteDeUmLider)
           EVENTO_morteDeUmLider();
//       else if(eventoAtual instanceof OleoQuente)
//           EVENTO_oleoQuente();
//       else if(eventoAtual instanceof PortaFortificada)
//           EVENTO_portaFortificada();
//       else if(eventoAtual instanceof Reuniao)
//           EVENTO_reuniao();
//       else if(eventoAtual instanceof SalvaDeFlechas)
//           EVENTO_salvaDeFlechas();
       else if(eventoAtual instanceof SuprimentosEstragados)
           EVENTO_suprimentosEstragados();
                  
   // AVANÇO DO INIMIGO
   
   // MÉTODO PARA VERIFICAR AS CONDIÇÕES QUE DETERMINAM O FIM IMEDIATO DO JOGO
   
   
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

    
    // EVENTOS
    private void EVENTO_ataqueDeCatapulta(Evento evento) {
        m.aplicarEvento(evento);
    }

    private void EVENTO_catapultaReparada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_coberturaDaEscuridao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_colapso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_doenca() {
        m.evento_Doenca();
        System.out.println("A moral e os suprimentos foram reduzidos em 1 unidade.");
    }

    private void EVENTO_escudosDeFerro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_fatigaInimiga() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_fe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_flechasFlamejantes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_guardasDistraidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_inimigoDeterminado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_mauTempo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_morteDeUmLider() {
        m.evento_MorteDeUmLider();
    }

    private void EVENTO_oleoQuente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_portaFortificada() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_reuniao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_salvaDeFlechas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void EVENTO_suprimentosEstragados() {
        m.evento_SuprimentosEstragados();
    }
    
}
