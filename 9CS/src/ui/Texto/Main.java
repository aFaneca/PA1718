/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Texto;

import Estados.*;
import Lógica.*;
import Lógica.Ações.*;
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
                processaAcoes();

            
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
            
        ui_mostraMenu();
 
        if(sc.hasNextInt()){  // SE TIVER LIDO UM INTEIRO
            int n = sc.nextInt();
            sc.nextLine(); // PARA LIMPAR O BUFFER DO RESTO DA LINHA
            switch(n){
                case 1: ui_clearScreen(); m.novoJogo();
                    break;
                case 2:
                    break;
                case 3: return;

                default: ui_clearScreen(); 
                    break;
                }
            }else{ // SENÃO, PEDIR OUTRO INPUT
                ui_clearScreen();
                sc.next(); 
        }

        //ui_CliqueNoEnterPara("continuar");
        //ui_clearScreen();
         
    }
        

   private void virarCarta(){
       cartaVirada = m.virarCarta();
       eventoAtual = m.eventoAtual(cartaVirada);
       
       // ANTES DE TUDO, VERIFICA SE EXISTEM SOLDADOS NO TUNEL
       if(m.soldadosNoTunel()){
           System.out.println("Temos soldados corajosos a atravessar os túneis.");
           ui_PressioneNoEnterPara("rodar o dado e determinar o seu destino.");
           
           int resultado = m.sorteDosSoldados();
           if(resultado > Constantes.SOLDADOS_NO_TUNEL_SEM_SORTE.getValor()){
               System.out.println("A sorte está do seu lado! O resultado do dado foi " + resultado + " e os soldados continuam indetetados no Túnel.");
           }else{
               System.out.println("A sorte não está do seu lado! O resultado do dado foi " + resultado + " e os soldados foram capturados pelos inimigos");
               m.soldadosCapturados();
           }
       }else
           System.out.println("Não tem soldados no túnel. Todos estão em segurança dentro do Castelo.");
       
       
       ui_PressioneNoEnterPara("virar uma carta.");
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
  

    private void mostraDRMS(Evento evento){
        if(m.temDRM(evento)){ // SE O EVENTO POSSUI DRMs
            List<DRM> drms = new ArrayList<>(m.getDRMS(evento));
            for(DRM drm : drms){
                if(drm.isValida())
                    System.out.println("Este evento concedeu-lhe a seguinte DRM: " + drm + ".");
            }
                
        }else
            System.out.println("Este evento não lhe concedeu qualquer DRM.");
    }
    
    private void avancaInimigos(Evento evento){ // AVANÇA OS INIMIGOS ASSOCIADOS A ESTE EVENTO
        if(evento.getInimigosDoEvento().isEmpty()){ // Nenhuma unidade inimiga se deve mover quando existem ataques de trebuchets (não necessita de filtração, porque nesses casos, a lista já se encontra vazia de qualquer forma
            System.out.println("De acordo com esta carta, nenhum inimigo avançará nenhuma posição neste turno.");
        }else{
            
            for(Inimigo i : evento.getInimigosDoEvento()){
                System.out.println("O inimigo " + i + " encontra-se agora na localização " + i.getLocal() + ".");
            }
        }
        m.avancaInimigos(evento);
        
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

    
    private void processaAcoes(){
        Acao acaoEscolhida = null;
        boolean continuar = true;
        
        
        while(continuar){
            System.out.println(" -- AÇÕES DISPONÍVEIS: " + eventoAtual.getAPA());
            continuar = selecionarAcao(); // Este método vai tratar da seleção e aplicação da ação escolhida e retorna falso quando o jogador não desejar obter mais ações ou já as tiver esgotado
        }

        m.setEstado(new AguardaCarta(m));
    }
    private boolean selecionarAcao() {
        List<Integer> nrsNaoPermitidos = new ArrayList<>();
        List<Acao> acoesPermitidas = eventoAtual.getAcoesPermitidas();
        Acao acao, acaoEscolhida = null;
        boolean valido = false;
        int resposta = -1;
        int i = 0;
        do{
            //ui_clearScreen();
            System.out.println("Escolha uma ação: ");
            i = 0;
            nrsNaoPermitidos.clear();

            for(Acao a : acoesPermitidas){
                
                if( (!(a instanceof Raid) || m.soldadosNoTunel()) && ( !(a instanceof Sabotagem) || m.soldadosNoTunel() )  ){ // SE NÃO ESTIVEREM SOLDADOS NO TÚNEL, NÃO MOSTRAR A OPÇÃO DE RAID, UMA VEZ QUE NÃO SE APLICA
                    System.out.println("[" + i + "] " + a);
                }else{
                    nrsNaoPermitidos.add(i); 
                }   
                  i++;
            }
            System.out.println("[" + i + "] " + "Não quero realizar mais nenhuma ação.");
            System.out.print("> ");
            if(sc.hasNextInt()){
                resposta = sc.nextInt();
                sc.nextLine(); //  Para limpar o buffer do resto da linha
                if(!nrsNaoPermitidos.contains(resposta) && resposta >= 0 && resposta <= i){
                    valido = true;
                }
            }else
                sc.next();
            
            //sc.next();
        } while(!valido);
        
        if(resposta == i) // SE O UTILIZADOR NÃO DESEJAR REALIZAR NENHUMA AÇÃO 
            return false;
        
        for(Acao a : acoesPermitidas){
            if(resposta == acoesPermitidas.indexOf(a))
                acaoEscolhida = a;
        }
        System.out.println("Escolheu a ação " + acaoEscolhida + "."); 
        
        // AÇÃO: REPARAR MURALHA
        if(acaoEscolhida instanceof RepararMuralha){
            System.out.println("Para poder aumentar a força da sua muralha em 1 unidade, deve conseguir um resultado >= a " + Constantes.REPARAR_MURALHA_MINIMO.getValor() + ".");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_RepararMuralha();
            
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            if(resultadoDaAcao >= Constantes.REPARAR_MURALHA_MINIMO.getValor())
                System.out.println("A força da muralha foi aumentada em 1 unidade.");
            else
                System.out.println("Não conseguiu reparar a sua muralha :/");
            
        }
        // AÇÃO: MOTIVAR TROPAS
        else if(acaoEscolhida instanceof MotivarTropas){
            System.out.println("Para poder motivar as suas tropas e aumentar a moral do seu povo em 1 unidade, deverá conseguir um resultado >= a " + Constantes.MOTIVAR_TROPAS_MINIMO.getValor() + ".");
            System.out.println("Deseja sacrificar os seus suprimentos em 1 unidade, recebendo em troca +1 no resultado do dado?");
            
            valido = false;
            do{
                System.out.println("[1] Sim, dar 1 unidade de suprimentos ao povo\n[2] Não, tentar a minha sorte");
                System.out.print("> ");
                if(sc.hasNextInt()){
                    resposta = sc.nextInt();
                    sc.nextLine(); //  Para limpar o buffer do resto da linha
                    if(resposta == 1 || resposta == 2){
                        valido = true;
                    }
                }else
                    sc.next();

                //sc.next();
        } while(!valido);
            
            boolean usarBonus = (resposta == 1) ? true : false; // SE A RESPOSTA FOR 1, usarBonus = true, senão, usarBonus = false
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação.");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_MotivarTropas(usarBonus);
            
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            if(resultadoDaAcao >= Constantes.MOTIVAR_TROPAS_MINIMO.getValor())
                System.out.println("A moral do seu Povo foi aumentada em 1 unidade.");
            else
                System.out.println("Não conseguiu aumentar a moral do povo :/");
        }
        // AÇÃO: RAID DE SUPRIMENTOS
        else if(acaoEscolhida instanceof Raid){
            // NESTA FASE, JÁ SABEMOS QUE EXISTEM SOLDADOS NO TÚNEL 
            System.out.println("Para fazer raid de suprimentos em linhas inimigas, deverá conseguir um resultado >= 3. Se obtiver o resultado 6, consegiurá arrecadar 2 unidades de Suprimentos. Se o resultado for 1, as suas tropas serão capturadas.");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação.");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_Raid();
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            if(resultadoDaAcao >= Constantes.RAID_MINIMO_SUCESSO1.getValor()){ // DEFAULT: 3,4,5,6
                if(resultadoDaAcao >= Constantes.RAID_MINIMO_SUCESSO2.getValor()){ // DEFAULT: 6 <= RAID COM SUCESSO DE 2 SUPRIMENTOS
                    System.out.println("Os seus soldados efetuaram o ataque com bastante sucesso e conseguiram furtar 2 unidades de suprimentos!");
                }else{  // DEFAULT: 3,4,5 <= RAID COM SUCESSO DE 1 SUPRIMENTO
                    System.out.println("Os seus soldados efetuaram o ataque com sucesso e conseguiram furtar 1 unidade de suprimentos!");
                }
            }else{ // DEFAULT: 1 <= SOLDADOS CAPTURADOS
                System.out.println("Pouca sorte! Os seus soldados não conseguiram efetuar o Raid e foram capturados :/");
            }
        }
        // AÇÃO: SABOTAGEM
        else if(acaoEscolhida instanceof Sabotagem){
            // NESTA FASE, JÁ SABEMOS QUE EXISTEM SOLDADOS NO TÚNEL 
            System.out.println("Para fazer sabotagem em linhas inimigas, deverá conseguir um resultado >= 5. Se o resultado for 1, as suas tropas serão capturadas.");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação.");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_Sabotagem();
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            
            if(resultadoDaAcao >= Constantes.SABOTAGEM_MINIMO_SUCESSO.getValor()){ // DEFAULT: 5, 6 <= -1 Catapulta dos Inimigos
                System.out.println("Conseguiu danificar com sucesso 1 catapulta inimiga. A mesma encontra-se agora inoperacional!");
            
            }else if(resultadoDaAcao <= Constantes.SABOTAGEM_MAXIMO_INSUCESSO.getValor()){ // DEFAULT: 1 <= Soldados são capturados
                System.out.println("Pouca sorte! Enquanto tentavam sabotar as catapultas inimigas, as suas tropas foram capturadas pelo inimigo :/");
            }
        }
        
        if(!acaoEscolhida.isReutilizavel()) // SE A AÇÃO ATUAL NÃO FOR REUTILIZÁVEL
            eventoAtual.removerAcao(acaoEscolhida); // REMOVER A AÇÃO DO LEQUE DE AÇÕES DISPONÍVEIS
            
            eventoAtual.setAPA(eventoAtual.getAPA() - 1); // RETIRAR DO NR. DE APA'S DISPONÍVEIS O QUE FOI UTILIZADOO AGORA
            if(eventoAtual.getAPA() < 1)
                return false;
            else
                return true;

    }
    
    private void ui_PressioneNoEnterPara(String texto){
        System.out.println("Pressione 'Enter' para " + texto + "...");
        sc.nextLine();
        
        
    }
          
    public static void ui_clearScreen() {  
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

    private void ui_mostraMenu() {
        System.out.println("## 9 CARDS SIEGE ##\n"
                + "[1] NOVO JOGO\n"
                + "[2] CONTINUAR JOGO\n"
                + "[3] SAIR");
        
        System.out.print("> ");
    }
}
