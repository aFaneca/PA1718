/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Estados.AguardaCarta;
import Estados.AguardaInicio;
import Estados.AguardaLeituraDeInfo;
import Estados.AguardaSelecaoDeAcao;
import Estados.DiaTerminado;
import Estados.IEstados;
import Estados.JogoTerminado;
import Lógica.CarregamentoDeJogo;
import Lógica.Carta;
import Lógica.Constantes;
import Lógica.Evento;
import Lógica.Eventos.AtaqueDeCatapulta;
import Lógica.Eventos.CatapultaReparada;
import Lógica.Eventos.CoberturaDaEscuridao;
import Lógica.Eventos.Colapso;
import Lógica.Eventos.Doenca;
import Lógica.Eventos.EscudosDeFerro;
import Lógica.Eventos.FatigaInimiga;
import Lógica.Eventos.Fe;
import Lógica.Eventos.FlechasFlamejantes;
import Lógica.Eventos.GuardasDistraidos;
import Lógica.Eventos.InimigoDeterminado;
import Lógica.Eventos.MauTempo;
import Lógica.Eventos.MorteDeUmLider;
import Lógica.Eventos.OleoQuente;
import Lógica.Eventos.PortaFortificada;
import Lógica.Eventos.Reuniao;
import Lógica.Eventos.SalvaDeFlechas;
import Lógica.Eventos.SuprimentosEstragados;
import Lógica.Mundo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import ui.GUI.JogoView;
import ui.GUI.MenuInicialView;

/**
 *
 * @author me
 */
public class Controlador implements ActionListener{ // CONTROLLER
    private Mundo m; // MODEL
    private MenuInicialView menuInicial; // VIEW
    private JogoView jogoView; // VIEW
    boolean sair;
    Evento eventoAtual;
    Carta cartaVirada;
    boolean pausa, pausa_acoes;
    boolean movimentarSoldadosFreeJaUsado;
    String msg;
    boolean modoDebug;
    public Controlador(Mundo m, MenuInicialView menuInicial, JogoView jogoView){
        this.m = m;
        this.menuInicial = menuInicial;
        this.jogoView = jogoView;
        movimentarSoldadosFreeJaUsado = false;
        modoDebug = false;
        acoesDoMenu();
        
        m.addObserver(jogoView); // Adiciona a View JogoView à lista de observers do Observable "Mundo", que é o Modelo no padrão MVC
        menuInicial.addListener(this, menuInicial.getBotao_sair());
        menuInicial.addListener(this, menuInicial.getBotao_continuarJogo());
        menuInicial.addListener(this, menuInicial.getBotao_iniciarJogo());
        jogoView.addListener(this, jogoView.getBotao_Continuar());
        jogoView.addListener(this, jogoView.getBotao_RodarDado_SoldadosEmLinhasInimigas());
        jogoView.addListener(this, jogoView.getBotao_Continuar_SoldadosSeguros());
        jogoView.addListener(this, jogoView.getBotao_Continuar_RodarDado());
        jogoView.addListener(this, jogoView.getBotao_Continuar_Eventos());
        jogoView.addListener(this, jogoView.getBotao_Continuar_DRMS());
        jogoView.addListener(this, jogoView.getBotao_Continuar_AvancoInimigo());
        
        // Adicionar listeners a todos os botões de ações
        jogoView.addListener(this, jogoView.getBotao_MotivarTropas());
        jogoView.addListener(this, jogoView.getBotao_MovimentarSoldadosNoTunel());
        jogoView.addListener(this, jogoView.getBotao_NaoRealizarMaisAcoes());
        jogoView.addListener(this, jogoView.getBotao_Raid());
        jogoView.addListener(this, jogoView.getBotao_RepararMuralha());
        jogoView.addListener(this, jogoView.getBotao_Sabotagem());
        jogoView.addListener(this, jogoView.getBotao_AtaqueDeAguaFervente());
        jogoView.addListener(this, jogoView.getBotao_AtaqueDeArqueiros());
        jogoView.addListener(this, jogoView.getBotao_AtaqueDeCloseCombat());
        
        jogoView.addListener(this, jogoView.getBotao_RodarDado_repararMuralha());
        jogoView.addListener(this, jogoView.getBotao_Continuar_repararMuralha());
        jogoView.addListener(this, jogoView.getBotao_Continuar_sabotagem());
        jogoView.addListener(this, jogoView.getBotao_RodarDado_sabotagem());
        jogoView.addListener(this, jogoView.getBotao_Continuar_raid());
        jogoView.addListener(this, jogoView.getBotao_RodarDado_raid());
        jogoView.addListener(this, jogoView.getBotao_Continuar_motivarTropas());
        jogoView.addListener(this, jogoView.getBotao_RodarDado_motivarTropas());
        jogoView.addListener(this, jogoView.getBotao_RodarDado_motivarTropasBonus());
        jogoView.addListener(this, jogoView.getBotao_Continuar_movimentarSoldados());
        jogoView.addListener(this, jogoView.getBotao_Movimentar_movimentarSoldadosFast());
        jogoView.addListener(this, jogoView.getBotao_Movimentar_movimentarSoldadosFree());
        
        jogoView.addListener(this, jogoView.getBotao_Ataque_Torres());
        jogoView.addListener(this, jogoView.getBotao_Ataque_Escadas());
        jogoView.addListener(this, jogoView.getBotao_Ataque_Arietes());
        jogoView.addListener(this, jogoView.getBotao_Ataque_Nenhum());
        jogoView.addListener(this, jogoView.getBotao_Continuar_Ataques());
        jogoView.addListener(this, jogoView.getBotao_Continuar_FimDoDia());
        jogoView.addListener(this, jogoView.getBotao_Voltar_FimDoJogo());
        pausa = pausa_acoes = false;
    }
    
    public void run() {
        while (!sair) {     
//            if(pausa) // Só executar este loop nas fases em que o jogo não está em pausa
//                continue; // Necessário, caso contrário o sistema vai simplesmente passar imediatamente para a próxima carta, sem esperar pela interação do utilizador
            IEstados estado = m.getEstado();  
            //System.out.println(estado);
            if(estado instanceof AguardaInicio){
//                m = new Mundo();

                menuInicial.setVisible(true);
                jogoView.setVisible(false);

            }
            else if(estado instanceof AguardaLeituraDeInfo){
               
                menuInicial.setVisible(false);
                jogoView.setVisible(true);

            }
                //m.novoJogo();
            else if(estado instanceof AguardaCarta){
                virarCarta();
            }

            else if(estado instanceof AguardaSelecaoDeAcao){
                //processaAcoes();
                jogoView.trocarPainel("painelAcoes");
                selecaoDeAcao();
                
                if(!modoDebug) msg = m.verificaCondicoesFataisImediatas();
                if(msg != null){
                    m.setMotivoFimDoJogo(msg);
                    m.setEstado(m.getEstado().fimDoJogo());
                }

                
            }


            else if(estado instanceof DiaTerminado){
                fimDoDia();
            }


            else if(estado instanceof JogoTerminado){
                fimDoJogo();
                //sair = true;
            }
                
       }
        
    }
    
    
    private void fimDoJogo(){
        jogoView.trocarPainel("painelFimDoJogo");
    }
    
    private void fimDoDia(){
        jogoView.trocarPainel("painelFimDoDia");
    }
    
    private void selecaoDeAcao(){
        pausa_acoes = true;
        
        while(pausa_acoes){
            try {
                Thread.sleep(1);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        /*System.out.println("A Selecionar Ação...");*/
    }
    
    private void virarCarta(){

       pausa = true;
       cartaVirada = m.virarCarta();
       eventoAtual = m.eventoAtual(cartaVirada);
       
       
       
       //m.setEstado(m.getEstado().proximoEstado());
        
       // DESCOBRE QUAL O EVENTO ATUAL E APLICA-O
       if(eventoAtual instanceof AtaqueDeCatapulta)
            EVENTO_ataqueDeCatapulta();
       else if(eventoAtual instanceof CatapultaReparada)
           EVENTO_catapultaReparada();
       else if(eventoAtual instanceof Colapso)
           EVENTO_colapso();
       else if(eventoAtual instanceof Doenca)
           EVENTO_doenca();
       else if(eventoAtual instanceof MorteDeUmLider)
           EVENTO_morteDeUmLider();
       else if(eventoAtual instanceof SuprimentosEstragados)
           EVENTO_suprimentosEstragados();
       else                                      // Se não for nenhum dos eventos acima (que englobam impactos diretos no jogo),
           jogoView.trocarPainel("painelDRMS"); //  direcionar diretamente para este painel

                      // Fica aqui parado até que pausa = false (que vai ser sinalizado no listener abaixo
       while(pausa){
           
           try {
               Thread.sleep(1);
               
               /*System.out.println("A Virar Carta...");*/
           } catch (InterruptedException ex) {
               Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
           }
}; // Necessário, caso contrário o sistema vai simplesmente passar imediatamente para a próxima carta, sem esperar pela interação do utilizador

       // MÉTODO PARA VERIFICAR AS CONDIÇÕES QUE DETERMINAM O FIM DO JOGO AO FINAL DE CADA TURNO 
       if(!modoDebug) m.setMotivoFimDoJogo(m.verificaCondicoesFatais());   
   } 
    
    
    

    private void EVENTO_ataqueDeCatapulta() {
        m.evento_AtaqueDeCatapulta(); // Aplica o evento
        m.setMensagemParaJogador("Sofreu danos na sua muralha, que agora tem força de " + m.getForcaDaMuralha() + ".");  
        jogoView.trocarPainel("painelEventos");
    }

    private void EVENTO_catapultaReparada() {
        m.evento_CatapultaReparada(); // Aplica o evento
        m.setMensagemParaJogador("Adicionada 1 Catapulta ao inimigo. Agora possui " + m.contaCatapultas() + " catapultas.");
        jogoView.trocarPainel("painelEventos");
        
    }

    private void EVENTO_colapso() {
        int resultado = m.evento_Colapso();
        
        if(resultado == 1)
            m.setMensagemParaJogador("<html>Uma vez que a Torre de Cerco ainda se encontrava na posição inicial, a mesma foi destruída permanentemente. <br/>Menos uma preocupação!</html>");
        else if(resultado == 0)
            m.setMensagemParaJogador("Uma vez que a Torre de Cerco já não se encontrava na posição inicial, nada aconteceu.");
    
        jogoView.trocarPainel("painelEventos");
    }

    private void EVENTO_doenca() {
        m.evento_Doenca();
        m.setMensagemParaJogador("A moral e os suprimentos foram reduzidos em 1 unidade. Agora tem " + m.getMoralDoPovo() + " de moral e " + m.getSuprimentos() + " de suprimentos.");
        jogoView.trocarPainel("painelEventos");
    }

    private void EVENTO_morteDeUmLider() {
        m.evento_MorteDeUmLider();
        m.setMensagemParaJogador("Que dia negro! Um dos seus líderes morreu e com isso a população perdeu 1 unidade de moral!");
        jogoView.trocarPainel("painelEventos");
    }

    private void EVENTO_suprimentosEstragados() {
        m.evento_SuprimentosEstragados();
        m.setMensagemParaJogador("Devido ao clima atípico, os seus fazendeiros reportam que parte da colheita está estragada :(");
        jogoView.trocarPainel("painelEventos");
    }
    
    // AÇÕES
    private void acao_repararMuralha(){
        m.setUltimoResultadoDoDado(m.acao_RepararMuralha(eventoAtual)); // De forma a que o ultimo resultado possa contabilizar também os DRMs adicionados posateriormente
        m.consomeAcaoAtual("RepararMuralha");
        jogoView.trocarPainel("painelAcao_repararMuralha2");
    }
    
    private void acao_sabotagem(){
        m.setUltimoResultadoDoDado(m.acao_Sabotagem(eventoAtual));
        m.consomeAcaoAtual("Sabotagem");
        jogoView.trocarPainel("painelAcao_sabotagem2");
    }
    
    private void acao_raid(){
        m.setUltimoResultadoDoDado(m.acao_Raid(eventoAtual));
        m.consomeAcaoAtual("Raid");
        jogoView.trocarPainel("painelAcao_raid2");
    }
    
    private void acao_motivarTropas(boolean usarBonus){
        m.setUltimoResultadoDoDado(m.acao_MotivarTropas(usarBonus, eventoAtual));
        m.consomeAcaoAtual("MotivarTropas");
        jogoView.trocarPainel("painelAcao_motivarTropas2");
    }
    
    private void acao_movimentarSoldados(boolean fast){
        if(fast){
            m.acao_movimentarSoldadosNoTunel(2);
            for(int i = 0; i < 2; i++)
                eventoAtual.decrementaAPA();
        }   
        else{
            movimentarSoldadosFreeJaUsado = true;
            m.acao_movimentarSoldadosNoTunel(1);
        }
            
        
        jogoView.trocarPainel("painelAcao_movimentarSoldados2");
 
    }
    
    private void acao_AtaqueArietes(){
        m.setUltimoResultadoDoDado(m.acao_AtaqueDeAguaFervente(m.getFortaleza().getAriete(), eventoAtual));
        jogoView.setInimigoAtacado(m.getFortaleza().getAriete());
        m.consomeAcaoAtual(jogoView.getTipoDeAtaque());
        jogoView.trocarPainel("painelAtaquesResultados");  
    }
    
    private void acao_AtaqueEscadas(){
        m.setUltimoResultadoDoDado(m.acao_AtaqueDeAguaFervente(m.getFortaleza().getEscada(), eventoAtual));
        jogoView.setInimigoAtacado(m.getFortaleza().getEscada());
        m.consomeAcaoAtual(jogoView.getTipoDeAtaque());
        jogoView.trocarPainel("painelAtaquesResultados"); 
    }
    
    private void acao_AtaqueTorres(){
        m.setUltimoResultadoDoDado(m.acao_AtaqueDeAguaFervente(m.getFortaleza().getTorre(), eventoAtual));
        jogoView.setInimigoAtacado(m.getFortaleza().getTorre());
        m.consomeAcaoAtual(jogoView.getTipoDeAtaque());
        jogoView.trocarPainel("painelAtaquesResultados"); 
    }
    
    private void voltarAoInicio(){
        m.voltarAoInicio();
        jogoView.setM(m);
        
    }
    
    public void acoesDoMenu(){
        
        jogoView.getMenuItem_guardar().addActionListener((ActionEvent evento) ->{
            
            JFileChooser fc = new JFileChooser("./dados");
            int returnVal = fc.showOpenDialog(menuInicial);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File ficheiro = fc.getSelectedFile();             
                try{
                    CarregamentoDeJogo.guardarJogo(ficheiro, m);
                    //m.notificaAlteracao();
                }catch(IOException ex){
                    menuInicial.mostraErro("A operação falhou: \r\n\r\n" + ex);
                }
          
            } else {
                System.out.println("Operação de Carregamento de Jogo Cancelada.");
            }
            
        });
        
        jogoView.getMenuItem_sair().addActionListener((ActionEvent evento) -> {
            System.exit(0);
        });
        
        jogoView.getMenuItem_sobre().addActionListener((ActionEvent evento) -> {
            jogoView.mostraPopup("Sobre o Jogo\nAutor: António Faneca");
        });
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        
        Object origem = e.getSource();
         if(origem == (menuInicial.getBotao_sair())){
             System.exit(0);
         }
         else if(origem == (menuInicial.getBotao_iniciarJogo())){
             m.novoJogo();
             jogoView.trocarPainel("painelInfo");
         }
         else if(origem == (menuInicial.getBotao_continuarJogo())){
             //menuInicial.mostraErro("Ainda não implementado...");
            JFileChooser fc = new JFileChooser("./dados");
            int returnVal = fc.showOpenDialog(menuInicial);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File ficheiro = fc.getSelectedFile();             
                try{
                    m = (Mundo) CarregamentoDeJogo.carregarJogo(ficheiro);
                    m.addObserver(jogoView);
                    jogoView.setM(m);
                    m.notificaAlteracao();
                    
                    System.out.println("DIA ATUAL: " + m.getDia());
                    System.out.println("MENSAGEM: " + m.getMensagemParaJogador());
                    
                    menuInicial.dispose();
                    jogoView.setVisible(true);
                    

                }catch(IOException | ClassNotFoundException ex){
                    menuInicial.mostraErro("A operação falhou: \r\n\r\n" + e);
                }
          
            } else {
                System.out.println("Operação de Carregamento de Jogo Cancelada.");
            }
         }
         else if(origem == (jogoView.getBotao_Continuar())){
             m.getEstado().verificarSoldados(jogoView);
             //jogoView.trocarPainel("painelAcoes");
         }
         else if(origem == (jogoView.getBotao_RodarDado_SoldadosEmLinhasInimigas())){
             // Continuar para a secção de Rodar Dado
             m.getEstado().testaSorteDosSoldados();
             jogoView.trocarPainel("painelRodarDado");
         }
         else if(origem == (jogoView.getBotao_Continuar_SoldadosSeguros())){
             // Continuar para a secção VirarCarta
             m.setEstado(m.getEstado().virarCarta());
         }
         else if(origem == (jogoView.getBotao_Continuar_RodarDado())){
             // Continuar para a secção VirarCarta
             m.setEstado(m.getEstado().virarCarta());
         }
         else if(origem == (jogoView.getBotao_Continuar_Eventos())){
             jogoView.trocarPainel("painelDRMS");
             
         }
         else if(origem == (jogoView.getBotao_Continuar_DRMS())){
             m.avancaInimigos(eventoAtual); // muda para o estado "aguardaSelecaoDeAcao"
             jogoView.trocarPainel("painelAvancoInimigo");
         }
         else if(origem == (jogoView.getBotao_Continuar_AvancoInimigo())){
             System.out.println(m.getEstado());
             pausa = false;

         }
         
         // LISTENERS DOS BOTÕES DE AÇÕES
         else if(origem == (jogoView.getBotao_AtaqueDeAguaFervente())){
             System.out.println("Ataque De Àgua Fervente");
             jogoView.setTipoDeAtaque("AtaqueDeAguaFervente");
             jogoView.trocarPainel("painelAtaques");
             pausa = false;

         }
         else if(origem == (jogoView.getBotao_AtaqueDeArqueiros())){
             jogoView.setTipoDeAtaque("AtaqueDeArqueiros");
             jogoView.trocarPainel("painelAtaques");
             System.out.println("Ataque De Arqueiros");
         }
         else if(origem == (jogoView.getBotao_AtaqueDeCloseCombat())){
             jogoView.setTipoDeAtaque("AtaqueDeCloseCombat");
             jogoView.trocarPainel("painelAtaques");
             System.out.println("Ataque De Close Combat");
         }
         else if(origem == (jogoView.getBotao_MotivarTropas())){
             System.out.println("Motivar Tropas");
             jogoView.trocarPainel("painelAcao_motivarTropas");
         }
         else if(origem == (jogoView.getBotao_MovimentarSoldadosNoTunel())){
             System.out.println("Movimentar Soldados No Túnel");
             jogoView.trocarPainel("painelAcao_movimentarSoldados");

         }
         else if(origem == (jogoView.getBotao_NaoRealizarMaisAcoes())){
             System.out.println("Não Realizar Mais Ações");
             m.setEstado(m.getEstado().proximoEstado());
             pausa_acoes = false;
             movimentarSoldadosFreeJaUsado = false;

         }
         else if(origem == (jogoView.getBotao_RepararMuralha())){
             System.out.println("Reparar Muralha");
             jogoView.trocarPainel("painelAcao_repararMuralha");
             
         }
         else if(origem == (jogoView.getBotao_Raid())){
             System.out.println("Raid");
             if(!m.soldadosEmLinhasInimigas())
                 jogoView.mostraPopup("Não possui soldados em linhas inimigas!");
             else
                 jogoView.trocarPainel("painelAcao_raid");

         }
         else if(origem == (jogoView.getBotao_Sabotagem())){
             System.out.println("Sabotagem");
             if(!m.soldadosEmLinhasInimigas())
                 jogoView.mostraPopup("Não possui soldados em linhas inimigas!");
             else
                 jogoView.trocarPainel("painelAcao_sabotagem");
         }
         
         else if(origem == (jogoView.getBotao_RodarDado_repararMuralha())){
             // Rodar o dado e determinar o destino da ação
             acao_repararMuralha();
         }
         else if(origem == (jogoView.getBotao_Continuar_repararMuralha())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_RodarDado_sabotagem())){
             acao_sabotagem();
         }
         else if(origem == (jogoView.getBotao_Continuar_sabotagem())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_RodarDado_raid())){
             acao_raid();
         }
         else if(origem == (jogoView.getBotao_Continuar_raid())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_RodarDado_motivarTropasBonus())){
             acao_motivarTropas(true);
         }
         else if(origem == (jogoView.getBotao_RodarDado_motivarTropas())){
             acao_motivarTropas(false);
         }
         else if(origem == (jogoView.getBotao_Continuar_motivarTropas())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_Continuar_movimentarSoldados())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_Movimentar_movimentarSoldadosFast())){
             acao_movimentarSoldados(true);
         }
         else if(origem == (jogoView.getBotao_Movimentar_movimentarSoldadosFree())){
             if(this.movimentarSoldadosFreeJaUsado)
                 jogoView.mostraPopup("Já utilizou um movimento free neste turno!");
             else
                acao_movimentarSoldados(false);
         }
         else if(origem == (jogoView.getBotao_Ataque_Torres())){
             acao_AtaqueTorres();
         }
         else if(origem == (jogoView.getBotao_Ataque_Escadas())){
             acao_AtaqueEscadas();
         }
         else if(origem == (jogoView.getBotao_Ataque_Arietes())){
             acao_AtaqueArietes();
         }
         else if(origem == (jogoView.getBotao_Ataque_Nenhum())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_Continuar_Ataques())){
             pausa_acoes = false;
         }
         else if(origem == (jogoView.getBotao_Continuar_FimDoDia())){
//             m.fimDoDia(); // Vai automaticamente determinar o próximo estado
//             System.out.println("Fdasfsad");
//             System.exit(0);
//             pausa_acoes = false;
             m.fimDoDia(); // Vai automaticamente determinar o próximo estado
             pausa_acoes = false;
             movimentarSoldadosFreeJaUsado = false;
         }
         else if(origem ==(jogoView.getBotao_Voltar_FimDoJogo())){
//             m.setEstado(new AguardaInicio(m));
//             pausa = false;
             voltarAoInicio();
//             System.out.println("A voltar ao início...");
//             jogoView.setVisible(false);
//             menuInicial.setVisible(true);
//             pausa_acoes = false;
//             pausa = false;
//             movimentarSoldadosFreeJaUsado = false;
         }
    }

    public Mundo getMundo() {
        return m;
    }

    public void setMundo(Mundo m) {
        this.m = m;
    }
    
    
    
}
