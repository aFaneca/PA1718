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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors 
 * Amadeus Alves
 * António Faneca
 */
public class textoView {
    private Scanner sc;
    private int valorLido;
    boolean sair; 
    private String motivoFimDoJogo;
    Mundo m;
    Evento eventoAtual;
    Carta cartaVirada;
    
    public textoView(){
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
            
            else if(estado instanceof DiaTerminado)
                fimDoDia();
            
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
                case 2: ui_clearScreen(); continuarJogo();
                    break;
                case 3: System.exit(0);return;

                default: ui_clearScreen(); 
                    break;
                }
            }else{ // SENÃO, PEDIR OUTRO INPUT
                ui_clearScreen();
                sc.next(); 
        }

         
    }
    
    private void fimDoDia(){
        
        System.out.println("O dia " + (m.getDia()) + " chegou ao fim.");
    /*
        As cartas devem ser rebaralhadas para o próximo dia
        Reduzir os supplies em 1 unidade
        Mover soldados no túnel de volta para o castelo
        Soldados em território inimigo são capturados
    */

        
        // SOLDADOS NO TÚNEL, VOLTAM AUTOMATICAMENTE PARA O CASTELO
        if(m.soldadosNoTunel()){
            System.out.println("Os seus soldados estão de volta à fortaleza.");
//            m.alteraPosSoldados(-3); // COMO TUDO ESTÁ CONFIGURADO PARA QUE A POSIÇÃO DOS SOLDADOS NUNCA SEJA < 0, ISTO GARANTE QUE ELES VOLTAM PARA A POSIÇÃO 0, INDEPENDENTEMENTE DA POSIÇÃO EM QUE ESTÃO ATUALMENTE
        }
            
        // SOLDADOS EM TERRITÓRIO INIMIGO, SÃO CAPTURADOS
        if(m.soldadosEmLinhasInimigas()){
            System.out.println("Como os seus soldados em linhas inimigas não voltaram para o castelo antes do anoitecer, foram capturados :/");
//            m.soldadosCapturados();
        }
        
        // REDUZIR SUPPLIES EM 1 UNIDADE
        System.out.println("Com o chegar do fim do dia, os suprimentos armazenados na fortaleza foram reduzidos em 1 unidade.");
        
        m.fimDoDia();
        
        boolean valido = false;
        int resposta = 0;
        String nomeDoFicheiro;
            do{
                System.out.println("[1] Continuar para o novo dia\n[2] Sair e Guardar");
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
        
            
            if(resposta == 1){
                // REBARALHAR CARTAS
                ui_PressioneNoEnterPara("rebaralhar as cartas e começar um novo dia");
                m.baralharCartas();

                m.setEstado(m.getEstado().proximoEstado());
            }else if(resposta == 2){
                System.out.print("Escolha o nome para o ficheiro de backup: ");
                nomeDoFicheiro = sc.next();
                
                guardarJogo(nomeDoFicheiro);
            }

        if(m.getDia() == 3)
            motivoFimDoJogo = "Chegou ao fim do terceiro dia de cerco e as tropas inimigas levantam a bandeira branca. Parabéns pela vitória!";
    }

    private void guardarJogo(String nomeDoFicheiro){
    
        
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro));
            os.writeObject(m);
            os.close();
        
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a guardar ficheiro...");
            Logger.getLogger(textoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Erro a guardar ficheiro...");
            Logger.getLogger(textoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Jogo guardado no ficheiro " + nomeDoFicheiro + ". Esperamos voltar a vê-lo em breve!");
        System.exit(0);
    }
    
    private void continuarJogo(){
        String nomeDoFicheiro;
        System.out.print("Insira o nome do ficheiro de recuperação: ");
        nomeDoFicheiro = sc.next();
        boolean valido = false;
        
        while(!valido){
            try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
            m = (Mundo)is.readObject();
            valido = true;
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro de recuperação...");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Erro a abrir ficheiro de recuperação...");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
                System.out.println("Erro a recuperar dados do jogo...");
                Logger.getLogger(textoView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            // Recomeçar o jogo
            ui_clearScreen();
            run();     
        }   
    }
    
    private void virarCarta(){
       cartaVirada = m.virarCarta();
       eventoAtual = m.eventoAtual(cartaVirada);
       
       // ANTES DE TUDO, VERIFICA SE EXISTEM SOLDADOS NO TUNEL
       if(m.soldadosEmLinhasInimigas()){
           System.out.println("Temos soldados corajosos em linhas inimigas.");
           ui_PressioneNoEnterPara("rodar o dado e determinar o seu destino.");
           
           int resultado = m.sorteDosSoldados();
           if(resultado > Constantes.SOLDADOS_EM_LINHAS_INIMIGAS_SEM_SORTE.getValor()){
               System.out.println("A sorte está do seu lado! O resultado do dado foi " + resultado + " e os soldados continuam indetetados em linhas inimigas.");
           }else{
               System.out.println("A sorte não está do seu lado! O resultado do dado foi " + resultado + " e os soldados foram capturados pelos inimigos");
               m.soldadosCapturados();
           }
       }else
           System.out.println("Não tem soldados em linhas inimigas. Todos estão em segurança dentro do Castelo.");
       
       
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
        textoView main = new textoView();
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
        m.avancaInimigos(evento);
        if(evento.getInimigosDoEvento().isEmpty()){ // Nenhuma unidade inimiga se deve mover quando existem ataques de trebuchets (não necessita de filtração, porque nesses casos, a lista já se encontra vazia de qualquer forma
            System.out.println("De acordo com esta carta, nenhum inimigo avançará nenhuma posição neste turno.");
        }else{
            
            for(Inimigo i : evento.getInimigosDoEvento()){
                System.out.println("O inimigo " + i + " encontra-se agora na localização " + i.getLocal() + ".");
            }
        }
        
        
    }
    
    // EVENTOS
    private void EVENTO_ataqueDeCatapulta(Evento evento) {
        //m.aplicarEvento(evento);
        m.evento_AtaqueDeCatapulta();
        System.out.println("Sofreu danos na sua muralha. Agora a mesma tem força = " + m.getForcaDaMuralha() + ".");
    }

    private void EVENTO_catapultaReparada() {
        m.evento_CatapultaReparada();
        System.out.println("Adicionada 1 Catapulta ao inimigo. Agora possui " + m.contaCatapultas() + " catapultas.");
        
        
    }

    private void EVENTO_coberturaDaEscuridao() {
        return;
    }

    private void EVENTO_colapso() {

        int resultado = m.evento_Colapso();
        
        if(resultado == 1)
            System.out.println("Uma vez que a Torre de Cerco ainda se encontrava na posição inicial, a mesma foi destruída permanentemente. Menos uma preocupação!");
        else if(resultado == 0)
            System.out.println("Uma vez que a Torre de Cerco já não se encontrava na posição inicial, nada aconteceu.");
    }

    private void EVENTO_doenca() {
        m.evento_Doenca();
        System.out.println("A moral e os suprimentos foram reduzidos em 1 unidade. Agora tem " + m.getMoralDoPovo() + " de moral e " + m.getSuprimentos() + " de suprimentos.");
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
        motivoFimDoJogo = m.verificaCondicoesFataisImediatas();
    }

    
    private void processaAcoes(){
        Acao acaoEscolhida = null;
        boolean continuar = true;
        
        
        while(continuar){
            System.out.println(" -- AÇÕES DISPONÍVEIS: " + eventoAtual.getAPA());
            continuar = selecionarAcao(); // Este método vai tratar da seleção e aplicação da ação escolhida e retorna falso quando o jogador não desejar obter mais ações ou já as tiver esgotado
        }
        if(!(m.getEstado() instanceof JogoTerminado)){
            m.setEstado(m.getEstado().proximoEstado());
            //m.setEstado(new AguardaCarta(m));
        }
        
        
            
    }
    private boolean selecionarAcao() {
        List<Integer> nrsNaoPermitidos = new ArrayList<>();
        List<Acao> acoesPermitidas = eventoAtual.getAcoesPermitidas();
        Acao acao, acaoEscolhida = null;
        Inimigo inimigoEscolhido = null;
        boolean valido = false;
        int resposta = -1;
        int i = 0;
        
        
        
        do{
            if(m.getEstado() instanceof JogoTerminado)
                return false;
            //ui_clearScreen();
            System.out.println("Escolha uma ação: ");
            i = 0;
            nrsNaoPermitidos.clear();

            for(Acao a : acoesPermitidas){
                
                // QUANDO NÃO É UMA AÇÃO DE 'RAID' NEM DE 'SABOTAGEM' OU É UMA DAS DUAS, MAS ESTÃO SOLDADOS EM TERRITÓRIO INIMIGO
                if( (!(a instanceof Raid) && !(a instanceof Sabotagem) ) || ( ((a instanceof Raid) || (a instanceof Sabotagem)) && (m.soldadosEmLinhasInimigas()))){
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
            // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
            for(DRM drm : eventoAtual.getDrms()){
                if(drm.getAcao() instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                    temDRMS = true;
                    var += drm.getVar();
                }
            }
            
            
            System.out.println("Para poder aumentar a força da sua muralha em 1 unidade, deve conseguir um resultado >= a " + Constantes.REPARAR_MURALHA_MINIMO.getValor() + " (+" + var + " DRM).");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_RepararMuralha(eventoAtual);
            
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            if(resultadoDaAcao >= Constantes.REPARAR_MURALHA_MINIMO.getValor())
                System.out.println("A força da muralha foi aumentada em 1 unidade.");
            else
                System.out.println("Não conseguiu reparar a sua muralha :/");
            
        }
        // AÇÃO: MOTIVAR TROPAS
        else if(acaoEscolhida instanceof MotivarTropas){
            
            
            // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
            for(DRM drm : eventoAtual.getDrms()){
                if(drm.getAcao() instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                    temDRMS = true;
                    var += drm.getVar();
                }
            }
            
            System.out.println("Para poder motivar as suas tropas e aumentar a moral do seu povo em 1 unidade, deverá conseguir um resultado >= a " + Constantes.MOTIVAR_TROPAS_MINIMO.getValor() + " (+" + var + " DRM).");
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
            int resultadoDaAcao = m.acao_MotivarTropas(usarBonus, eventoAtual);
            
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            if(resultadoDaAcao >= Constantes.MOTIVAR_TROPAS_MINIMO.getValor())
                System.out.println("A moral do seu Povo foi aumentada em 1 unidade.");
            else
                System.out.println("Não conseguiu aumentar a moral do povo :/");
        }
        // AÇÃO: RAID DE SUPRIMENTOS
        else if(acaoEscolhida instanceof Raid){
            // NESTA FASE, JÁ SABEMOS QUE EXISTEM SOLDADOS NO TÚNEL 
            
            // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
            for(DRM drm : eventoAtual.getDrms()){
                if(drm.getAcao() instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                    temDRMS = true;
                    var += drm.getVar();
                }
            }
            
            System.out.println("Para fazer raid de suprimentos em linhas inimigas, deverá conseguir um resultado >= 3. Se obtiver o resultado 6, consegiurá arrecadar 2 unidades de Suprimentos. Se o resultado for 1, as suas tropas serão capturadas." + " (+" + var + " DRM).");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação.");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_Raid(eventoAtual);
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
            
            // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
            for(DRM drm : eventoAtual.getDrms()){
                if(drm.getAcao() instanceof Sabotagem){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                    temDRMS = true;
                    var += drm.getVar();
                }
            }
            
            System.out.println("Para fazer sabotagem em linhas inimigas, deverá conseguir um resultado >= 5. Se o resultado for 1, as suas tropas serão capturadas." + " (+" + var + " DRM).");
            ui_PressioneNoEnterPara("rodar o dado e desvendar o destino desta ação.");
            
            // APLICAR AÇÃO
            int resultadoDaAcao = m.acao_Sabotagem(eventoAtual);
            System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
            
            if(resultadoDaAcao >= Constantes.SABOTAGEM_MINIMO_SUCESSO.getValor()){ // DEFAULT: 5, 6 <= -1 Catapulta dos Inimigos
                System.out.println("Conseguiu danificar com sucesso 1 catapulta inimiga. A mesma encontra-se agora inoperacional!");
            
            }else if(resultadoDaAcao <= Constantes.SABOTAGEM_MAXIMO_INSUCESSO.getValor()){ // DEFAULT: 1 <= Soldados são capturados
                System.out.println("Pouca sorte! Enquanto tentavam sabotar as catapultas inimigas, as suas tropas foram capturadas pelo inimigo :/");
            }
        }
        
        // AÇÃO: Movimentar Soldados no Túnel
        else if(acaoEscolhida instanceof MovimentarSoldadosNoTunel){
            valido = false;
            do{
                System.out.println("[1] Movimento FREE (Não Gasta Action Points)\n[2] Movimento FAST (Chegar diretamente ao fim do túnel; Custo: 2 APAs");
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
            ui_PressioneNoEnterPara("movimentar os seus soldados no túnel.");
            
            // APLICAR AÇÃO
           
           if(resposta == 1){
               eventoAtual.setAPA(eventoAtual.getAPA() + 1); // INCREMENTAR O NR. DE APAs PARA COMPENSAR A DECREMENTAÇÃO QUE É FEITA ABAIXO, UMA VEZ QUE ESTE MOVIMENTO É FREE
               System.out.println("Os seus soldados movimentaram-se 1 unidade no túnel. ");
           }else if(resposta == 2){
               System.out.println("Os seus soldados movimentaram-se automaticamente até ao fim do túnel. ");
           }
           System.out.println("Estão agora na posição " + m.acao_movimentarSoldadosNoTunel(resposta) + ".");
            
        }
        // AÇÃO: Ataque de Àgua Fervente
        else if(acaoEscolhida instanceof AtaqueDeAguaFervente){
            valido = false;
            do{
                int cnt = 1;
                for(Inimigo inimigo : acaoEscolhida.getPotenciaisAlvos()){
                    System.out.println("[" + cnt + "] Atacar " + inimigo + " (Local: A " + inimigo.getLocal() + " Unidades de Distância)");
                    
                    cnt++;
                }
                
                System.out.println("[" + cnt + "] Voltar Atrás");
                
                System.out.print("> ");
                if(sc.hasNextInt()){
                    resposta = sc.nextInt();
                    sc.nextLine(); //  Para limpar o buffer do resto da linha
                    if(resposta > 0 && resposta <= acaoEscolhida.getPotenciaisAlvos().size() + 1){
                        valido = true;
                    }
                }else
                    sc.next();

                //sc.next();
        } while(!valido);
            
            if(resposta == acaoEscolhida.getPotenciaisAlvos().size() + 1){ // SE A OPÇÃO ESCOLHIDA É A DE VOLTAR ATRÁS
                 eventoAtual.setAPA(eventoAtual.getAPA() + 1); // INCREMENTAR O NR. DE APAs PARA COMPENSAR A DECREMENTAÇÃO QUE É FEITA ABAIXO, UMA VEZ QUE NÃO FOI FEITO MOVIMENTO NENHUM
            }
            else{
                // DETERMINAR A FACÇÃO INIMIGA QUE FOI ESCOLHIDA
                inimigoEscolhido = m.getListaDeInimigos().get(m.getListaDeInimigos().indexOf(acaoEscolhida.getPotenciaisAlvos().get(resposta - 1)));
                
                // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
                boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
                int var  = 1; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
                
                
                System.out.println("O inimigo " + inimigoEscolhido + " tem nesta momento força = " + inimigoEscolhido.getForca() + ".");
                System.out.println("Para conseguir atacar com sucesso o inimigo, deve rodar o dado e obter um resultado superior a " + inimigoEscolhido.getForca() + " (+" + var + " DRM).");
                ui_PressioneNoEnterPara("tentar a sua sorte e realizar o ataque.");

                // APLICAR AÇÃO
                int resultadoDaAcao = m.acao_AtaqueDeAguaFervente(inimigoEscolhido, eventoAtual);
                
                
                
                System.out.println("O resultado do dado foi " + (resultadoDaAcao - 1) + " (+1 DRM).");
                if(resultadoDaAcao > inimigoEscolhido.getForca())
                    System.out.println("Parabéns! O seu ataque teve sucesso e o inimigo recuou uma posição.\n"
                            + "O inimigo " + inimigoEscolhido + " está agora na posição " + inimigoEscolhido.getLocal() + ".");
                else
                    System.out.println("Má sorte! O seu ataque não teve sucesso :/");
            }
            
        }
        
        
        // AÇÃO: Ataque de Arqueiros
        else if(acaoEscolhida instanceof AtaqueDeArqueiros){
            valido = false;
            do{
                int cnt = 1;
                for(Inimigo inimigo : acaoEscolhida.getPotenciaisAlvos()){
                    System.out.println("[" + cnt + "] Atacar " + inimigo + " (Local: A " + inimigo.getLocal() + " Unidades de Distância)");
                    
                    cnt++;
                }
                
                System.out.println("[" + cnt + "] Voltar Atrás");
                
                System.out.print("> ");
                if(sc.hasNextInt()){
                    resposta = sc.nextInt();
                    sc.nextLine(); //  Para limpar o buffer do resto da linha
                    if(resposta > 0 && resposta <= acaoEscolhida.getPotenciaisAlvos().size() + 1){
                        valido = true;
                    }
                }else
                    sc.next();

                //sc.next();
        } while(!valido);
            
            if(resposta == acaoEscolhida.getPotenciaisAlvos().size() + 1){ // SE A OPÇÃO ESCOLHIDA É A DE VOLTAR ATRÁS
                 eventoAtual.setAPA(eventoAtual.getAPA() + 1); // INCREMENTAR O NR. DE APAs PARA COMPENSAR A DECREMENTAÇÃO QUE É FEITA ABAIXO, UMA VEZ QUE NÃO FOI FEITO MOVIMENTO NENHUM
            }
            else{
                // DETERMINAR A FACÇÃO INIMIGA QUE FOI ESCOLHIDA
                inimigoEscolhido = m.getListaDeInimigos().get(m.getListaDeInimigos().indexOf(acaoEscolhida.getPotenciaisAlvos().get(resposta - 1)));
                // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
                boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
                int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
                
                
                
                System.out.println("O inimigo " + inimigoEscolhido + " tem nesta momento força = " + inimigoEscolhido.getForca() + ".");
                System.out.println("Para conseguir atacar com sucesso o inimigo, deve rodar o dado e obter um resultado superior a " + inimigoEscolhido.getForca() + " (+" + var + " DRM).");
                ui_PressioneNoEnterPara("tentar a sua sorte e realizar o ataque.");

                // APLICAR AÇÃO
                int resultadoDaAcao = m.acao_AtaqueDeArqueiros(inimigoEscolhido, eventoAtual);
                System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
                if(resultadoDaAcao > inimigoEscolhido.getForca())
                    System.out.println("Parabéns! O seu ataque teve sucesso e o inimigo recuou uma posição.\n"
                            + "O inimigo " + inimigoEscolhido + " está agora na posição " + inimigoEscolhido.getLocal() + ".");
                else
                    System.out.println("Má sorte! O seu ataque não teve sucesso :/");
            }
            
        }
        
        // AÇÃO: Ataque de Close Combat
        else if(acaoEscolhida instanceof AtaqueDeCloseCombat){
            valido = false;
            do{
                int cnt = 1;
                
                
                for(Inimigo inimigo : acaoEscolhida.getPotenciaisAlvos()){
                    System.out.println("[" + cnt + "] Atacar " + inimigo + " (Local: A " + inimigo.getLocal() + " Unidades de Distância)");
                    
                    cnt++;
                }
                
                System.out.println("[" + cnt + "] Voltar Atrás");
                
                System.out.print("> ");
                if(sc.hasNextInt()){
                    resposta = sc.nextInt();
                    sc.nextLine(); //  Para limpar o buffer do resto da linha
                    if(resposta > 0 && resposta <= acaoEscolhida.getPotenciaisAlvos().size() + 1){
                        valido = true;
                    }
                }else
                    sc.next();

                //sc.next();
        } while(!valido);
            
            if(resposta == acaoEscolhida.getPotenciaisAlvos().size() + 1){ // SE A OPÇÃO ESCOLHIDA É A DE VOLTAR ATRÁS
                 eventoAtual.setAPA(eventoAtual.getAPA() + 1); // INCREMENTAR O NR. DE APAs PARA COMPENSAR A DECREMENTAÇÃO QUE É FEITA ABAIXO, UMA VEZ QUE NÃO FOI FEITO MOVIMENTO NENHUM
            }
            else{
                // DETERMINAR A FACÇÃO INIMIGA QUE FOI ESCOLHIDA
                inimigoEscolhido = m.getListaDeInimigos().get(m.getListaDeInimigos().indexOf(acaoEscolhida.getPotenciaisAlvos().get(resposta - 1)));
                System.out.println("O inimigo " + inimigoEscolhido + " tem nesta momento força = 4.");
                System.out.println("Para conseguir atacar com sucesso o inimigo, deve rodar o dado e obter um resultado superior a 4.");
                ui_PressioneNoEnterPara("tentar a sua sorte e realizar o ataque.");

                // APLICAR AÇÃO
                int resultadoDaAcao = m.acao_AtaqueDeCloseCombat(inimigoEscolhido);
                System.out.println("O resultado do dado foi " + resultadoDaAcao + ".");
                if(resultadoDaAcao > 4)
                    System.out.println("Parabéns! O seu ataque teve sucesso e o inimigo recuou uma posição.\n"
                            + "O inimigo " + inimigoEscolhido + " está agora na posição " + inimigoEscolhido.getLocal() + ".");
                else if(resultadoDaAcao == 1)
                    System.out.println("Má sorte! O seu ataque não teve sucesso e a moral do povo foi reduzida em 1 unidade :/");
                else{
                    System.out.println("Má sorte! O seu ataque não teve sucesso e o inimigo conseguiu penetrar a muralha. FUJAM TODOOS!");
                    motivoFimDoJogo = "As muralhas caíram e a fortaleza foi invadida.";
                }
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
