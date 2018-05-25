/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Estados.AguardaInicio;
import Estados.AguardaSelecaoDeAcao;
import Estados.IEstados;
import Estados.JogoTerminado;
import Lógica.Ações.AtaqueDeAguaFervente;
import Lógica.Ações.AtaqueDeArqueiros;
import Lógica.Ações.MotivarTropas;
import Lógica.Ações.Raid;
import Lógica.Ações.RepararMuralha;
import Lógica.Ações.Sabotagem;
import Lógica.Inimigos.Escada;
import Lógica.Inimigos.Torre;
import Lógica.Inimigos.Ariete;
import Lógica.Eventos.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Mundo extends Observable implements Serializable{
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
        cartas.add(new Carta(this, 1));
        eventos.clear();
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 3, inimigos.get(0)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 2, inimigos.get(1)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(0), 1, inimigos.get(2)));
        cartas.get(0).adicionaEventos(eventos);

        
        // CARTA 2
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).add(fortaleza.getTorre());
        inimigos.get(1).addAll(fortaleza.getUnidadesLentas());
        cartas.add(new Carta(this, 2));
        eventos.clear();
        eventos.add(new Doenca(cartas.get(1), 2, inimigos.get(0)));
        eventos.add(new GuardasDistraidos(cartas.get(1), 2, inimigos.get(1)));
        eventos.add(new AtaqueDeCatapulta(cartas.get(1), 1, inimigos.get(2)));
        cartas.get(1).adicionaEventos(eventos);


        
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
        eventos.add(new FlechasFlamejantes(cartas.get(3), 3, inimigos.get(2)));
        cartas.get(3).adicionaEventos(eventos);
        

        
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
        eventos.add(new SalvaDeFlechas(cartas.get(4), 3, inimigos.get(0)));
        eventos.add(new Colapso(cartas.get(4), 2, inimigos.get(1)));
        eventos.add(new CatapultaReparada(cartas.get(4), 2, inimigos.get(2)));
        cartas.get(4).adicionaEventos(eventos);
        
       
        
        // CARTA 6
        inimigos.clear();
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.add(new ArrayList<>());
        inimigos.get(0).addAll(fortaleza.getUnidadesLentas());
        inimigos.get(1).add(fortaleza.getEscada());
        inimigos.get(2).add(fortaleza.getAriete());
        inimigos.get(2).add(fortaleza.getTorre());
        cartas.add(new Carta(this, 6));
        eventos.clear();
        eventos.add(new CoberturaDaEscuridao(cartas.get(5), 3, inimigos.get(0)));
        eventos.add(new FatigaInimiga(cartas.get(5), 3, inimigos.get(1)));
        eventos.add(new Reuniao(cartas.get(5), 3, inimigos.get(2)));
        cartas.get(5).adicionaEventos(eventos);
        
        

        
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
        eventos.add(new Fe(cartas.get(6), 3, inimigos.get(2)));
        cartas.get(6).adicionaEventos(eventos);
        
        
    }
    
    
    public void baralharCartas(){
        Collections.shuffle(cartas);
    }
    
    
    public void novoJogo(){
        baralharCartas();
        setEstado(estadoAtual.proximoEstado());
    }
    
    public Carta virarCarta(){
       if(cartasViradas > 6)
           cartasViradas = 0;
//          // setEstado(estadoAtual.fimDoDia());
//          setEstado(new AguardaInicio(this)); // TEMPORÁRIO PARA EFEITO DE TESTES
//       }
       
        return cartas.get(cartasViradas++);
    }
    
    
    public Evento eventoAtual(Carta carta){
        return carta.getEventoAtual();
    }
    
    public int rodaDado(){
        return dado.rodaDado();
    }

    public int contaCatapultas() {
        return fortaleza.getNrCatapultas();
    }
    
    public void alteraMuralha(int quant){
        fortaleza.alteraMuralha(quant);
        setChanged();
        notifyObservers();
    }
    
    public void alteraSuprimentos(int quant){
        fortaleza.alteraSuprimentos(quant);
        setChanged();
        notifyObservers();
    }
    
    public void alteraPosSoldados(int var){
        fortaleza.alteraPosSoldados(var);
        setChanged();
        notifyObservers();
    }
    
    public void alteraPovo(int var){
        fortaleza.alteraPovo(var);
        setChanged();
        notifyObservers();
    }
    public boolean soldadosNoTunel(){

        return fortaleza.soldadosNoTunel();
    }
    
    public boolean soldadosEmLinhasInimigas(){
        return fortaleza.soldadosEmLinhasInimigas();
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
    
    public void avancaInimigos(Evento evento){
        
        setEstado(estadoAtual.avancaInimigos(evento));
        
    }
    
    public int acao_RepararMuralha(Evento eventoAtual){
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.drms){
            if(drm.acao instanceof RepararMuralha){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                temDRMS = true;
                var += drm.var;
            }
        }
        
        
        int resultadoDoDado = rodaDado() + var;
        
        // Aplica a ação
        estadoAtual.acao_RepararMuralha(resultadoDoDado);
        
        
        return resultadoDoDado;
    }
    
    public int acao_MotivarTropas(boolean usarBonus, Evento eventoAtual){
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.drms){
            if(drm.acao instanceof MotivarTropas){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                temDRMS = true;
                var += drm.var;
            }
        }
        
        int resultadoDoDado = rodaDado() + var;
        
        if(usarBonus)
            resultadoDoDado++; // +1 para o resultado do dado
        
        // Aplica a ação
        estadoAtual.acao_MotivarTropas(resultadoDoDado, usarBonus);
        
        return resultadoDoDado;
    }
    
    public int acao_Raid(Evento eventoAtual){
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.drms){
            if(drm.acao instanceof Raid){ // SE ESSA DRM AFETA A AÇÃO "RAID"
                temDRMS = true;
                var += drm.var;
            }
        }
        
        int resultadoDoDado = rodaDado() + var;
        
        // Aplica a ação
        estadoAtual.acao_Raid(resultadoDoDado);

        
        return resultadoDoDado;
    }
    
    public int acao_Sabotagem(Evento eventoAtual){
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.drms){
            if(drm.acao instanceof Sabotagem){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                temDRMS = true;
                var += drm.var;
            }
        }

        int resultadoDoDado = rodaDado() + var;
        
        // Aplica a ação
        estadoAtual.acao_Sabotagem(resultadoDoDado);
        

        return resultadoDoDado;
    }
    
    public int acao_movimentarSoldadosNoTunel(int resposta){ // resposta == 1 => FREE | resposta == 2 => FAST
        
        // Se os soldados carregarem supplies, o movimento deles deve ser de volta para a fortaleza. Senão, movimento contrário
        boolean aVoltarAoCastelo = (fortaleza.getSuprimentosFurtados() != 0) ? true : false;
        
        // Aplica Ação
        estadoAtual.acao_movimentarSoldadosNoTunel(resposta, aVoltarAoCastelo);

        return fortaleza.getPosicaoSoldados();
    }
    
    public int acao_AtaqueDeArqueiros(Inimigo inimigoEscolhido, Evento eventoAtual) {
        
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.getDrms()){
            if(drm.getAcao() instanceof AtaqueDeArqueiros){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                temDRMS = true;
                var += drm.getVar();
            }
        }
        
        int resultadoDoDado = rodaDado() + var;
        
        // Aplica ação
        estadoAtual.acao_AtaqueDeArqueiros(resultadoDoDado, var, inimigoEscolhido, eventoAtual);
        
        return resultadoDoDado;
    }
    
    public int acao_AtaqueDeAguaFervente(Inimigo inimigoEscolhido, Evento eventoAtual) {
        boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
        int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
        
        for(DRM drm : eventoAtual.drms){
            if(drm.acao instanceof AtaqueDeAguaFervente){ // SE ESSA DRM AFETA A AÇÃO "ATAQUE DE AGUA FERVENTE"
                temDRMS = true;
                var += drm.var;
            }
        }
        
        int resultadoDoDado = rodaDado() + 1 + var; // +1 DRM
        
        // Aplica ação
        estadoAtual.acao_AtaqueDeAguaFervente(resultadoDoDado, var, inimigoEscolhido, eventoAtual, temDRMS);
        
       
        
        return resultadoDoDado;
    }
     
    public int acao_AtaqueDeCloseCombat(Inimigo inimigoEscolhido) {
        //estadoAtual = estadoAtual.proximoEstado();
        int resultadoDoDado = rodaDado();
        
        
        // Aplica ação
        setEstado(estadoAtual.acao_AtaqueDeCloseCombat(resultadoDoDado, inimigoEscolhido));
        
        
        
        return resultadoDoDado;
    }
    
    
    public void evento_AtaqueDeCatapulta(){
        
        estadoAtual.evento_AtaqueDeCatapulta();
    }
    
    public void evento_Doenca(){
        // REDUZIR MORAL E SUPRIMENTOS
        estadoAtual.evento_Doenca();
        
    }
    
    public void evento_SuprimentosEstragados() {
        estadoAtual.evento_SuprimentosEstragados();
        
    }
    
    public void evento_MorteDeUmLider() {
        estadoAtual.evento_MorteDeUmLider();
        
    }
    
    public void evento_CatapultaReparada(){
        fortaleza.alteraNrCatapultas(1);
        
    }
    
    public int evento_Colapso(){
        // A TORRE DE CERCO É REMOVIDA DO JOGO SE ESTIVER NA POSIÇÃO INICIAL
        if(getPosTorre() == Constantes.POSICAO_INICIAL_INIMIGOS.getValor()){ // SE A POSIÇÃO DA TORRE FOR A INICIAL
            removerTorre();
            return 1;
            
        }else
            return 0;
            
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
        
        if(dia > 3) dia = 3;
        if(dia < 1) dia = 1;
        
        setChanged();
        notifyObservers();
    }
    
    public Fortaleza getFortaleza(){
        return fortaleza;
    }
    
    public int getCartasViradas(){
        return cartasViradas;
    }
    
    public int getPosTorre(){ // OBTÉM A POSIÇÃO DA TORRE DE CERCO
        return fortaleza.posDaTorre();
    }
    
    public List<Inimigo> getListaDeInimigos(){
        return fortaleza.getListaDeInimigos();
    }
    
    public int getForcaDaMuralha(){
        return fortaleza.getForcaDaMuralha();
    }
    
    public int getMoralDoPovo(){
        return fortaleza.getMoralDoPovo();
    }
    
    public int getSuprimentos(){
        return fortaleza.getSuprimentos();
    }
    
    public int getPosDosSoldados(){
        return fortaleza.getPosicaoSoldados();
    }
    
    public void verInfo(){
        int nr = 1, nr1 = 1;
        
        for(Carta carta : cartas){
            System.out.println("## CARTA Nº " + nr++ + " ##");
            System.out.println("\tNr. de Eventos: " + carta.eventos.size());
            for(Evento evento : carta.eventos){
                System.out.println("\t\tNOME DO EVENTO: " + evento);
                System.out.println("\t\tAPA: " + evento.APA);
                System.out.println("\t\tTem Restrições de Ações: " + evento.temRestricoesDeAcoes());
                if(evento.temRestricoesDeAcoes())
                    System.out.println("\t\tAções Permitidas: " + evento.acoesPermitidas);
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

    public String verificaCondicoesFatais() {
        /* CONDIÇÕES PARA FIM DO JOGO NO FINAL DO TURNO
            - 2 facções estiverem na zona de close combat
            - 1 das Forças (muralha, moral ou supplies) estiver a 0
        */
        boolean faccoesFatais = fortaleza.faccoesFatais(2); // 2 facções estão na zona de close combat?
        boolean forcasFatais = fortaleza.forcasFatais(1); // 1 das forças está a 0?
        
        if(faccoesFatais){
            setEstado(estadoAtual.fimDoJogo());
            return "2 ou mais facções entraram na zona de Close Combat";
        }
            
        else if(forcasFatais){
            setEstado(estadoAtual.fimDoJogo());
            return "1 das forças da fortaleza chegou a zero.";
        }
         
        return null;
    }

    public String verificaCondicoesFataisImediatas(){
        /* CONDIÇÕES PARA FIM IMEDIATO DO JOGO
            - Uma 3a facção avançar para zona de close combat
            - Uma 2a força fica a 0
        */
        boolean faccoesFatais = fortaleza.faccoesFatais(2); // 2 facções estão na zona de close combat?
        boolean forcasFatais = fortaleza.forcasFatais(1); // 1 das forças está a 0?
        
        if(faccoesFatais){
            setEstado(estadoAtual.fimDoJogo());
            return "3 ou mais facções entraram na zona de Close Combat, pelo que o jogador perdeu automaticamente o jogo.";
        }
            
        else if(forcasFatais){
            setEstado(estadoAtual.fimDoJogo());
            return "2 das forças da fortaleza chegou a zero, pelo que o jogador perdeu automaticamente o jogo.";
        }
        
        
        return null;
    }

    public void alteraSuprimentosFurtados(int var) {
        fortaleza.alteraSuprimentosFurtados(var);
    }

    public void alteraNrCatapultas(int var) {
        fortaleza.alteraNrCatapultas(var);
    }

    
    public void fimDoDia(){
        estadoAtual.aplicarAcoes();
    }
}
