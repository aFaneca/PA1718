/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;


import Estados.AguardaLeituraDeInfo;
import Lógica.Acao;
import Lógica.Ações.*;
import Lógica.Carta;
import Lógica.Constantes;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Eventos.OleoQuente;
import Lógica.Eventos.Reuniao;
import Lógica.Inimigo;
import Lógica.Inimigos.*;
import Lógica.Mundo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author António Faneca
 */




public class JogoView extends JFrame implements Observer{
    Mundo m;
    JLabel label_dia, label_forcaDaMuralha, label_nivelDosSuprimentos, label_moralDoPovo, label_localDosSoldados, label_nivelDosSuprimentosFurtados, label_nrCatapultas;
    JMenuBar menuBar_menu;
    JMenu menu_jogo, menu_sobre;
    JMenuItem menuItem_sair, menuItem_sobre, menuItem_guardar;
    Icon icon_guardar, icon_carregar, icon_sair, icon_dia1, icon_dia2, icon_dia3, icon_suprimentos0, icon_suprimentos1, icon_suprimentos2, icon_suprimentos3, icon_suprimentos4;
    Icon icon_smileMuitoFeliz, icon_smileBemFeliz, icon_smileFeliz, icon_smileTriste, icon_smileZangado, icon_catapulta1, icon_catapulta2, icon_catapulta3;
    Icon icon_soldadoTroia, icon_tunel1, icon_tunel2, icon_fortaleza, icon_seguranca0, icon_seguranca1, icon_seguranca2, icon_seguranca3, icon_seguranca4, icon_mochila0, icon_mochila1, icon_mochila2;
    JPanel painelEsquerda, painelDireita, painelTopo, painelBase, painelCentro;
    JLabel img_cartaAtual, img_carta0, img_carta1, img_carta2, img_carta3, img_carta4, img_carta5, img_carta6, img_carta7;
    JPanel desenhoDosProgressosInimigos, painelCentroBaixo, painelFimDoDia, painelFimDoJogo;
    JPanel painelInfo, painelAcoes, painelAcoes2, painelSoldadosEmLinhasInimigas, painelSoldadosSeguros, painelRodarDado, painelAcao_motivarTropas, painelAcao_motivarTropas2, painelAcao_movimentarSoldados, painelAcao_movimentarSoldados2;
    JPanel painelEventos, painelDRMS, painelAvancoInimigo, painelAcao_repararMuralha, painelAcao_repararMuralha2, painelAcao_sabotagem, painelAcao_sabotagem2, painelAcao_raid, painelAcao_raid2;
    CardLayout cl;
    JButton botao_Continuar, botao_RodarDado_SoldadosEmLinhasInimigas, botao_Continuar_SoldadosSeguros, botao_Continuar_repararMuralha, botao_Continuar_sabotagem;
    JButton botao_AtaqueDeAguaFervente, botao_AtaqueDeArqueiros, botao_AtaqueDeCloseCombat, botao_MotivarTropas, botao_MovimentarSoldadosNoTunel, botao_Raid, botao_RepararMuralha, botao_Sabotagem;
    JButton botao_NaoRealizarMaisAcoes, botao_Continuar_RodarDado, botao_Continuar_Eventos, botao_Continuar_DRMS, botao_Continuar_AvancoInimigo, botao_RodarDado_repararMuralha, botao_RodarDado_sabotagem, botao_Continuar_raid, botao_RodarDado_raid, botao_Continuar_motivarTropas, botao_RodarDado_motivarTropas, botao_RodarDado_motivarTropasBonus;
    JButton botao_Continuar_movimentarSoldados, botao_Movimentar_movimentarSoldadosFree, botao_Movimentar_movimentarSoldadosFast;
    JLabel label_mensagemInicial, label_acoesDisponiveis;
    JLabel label_Mensagem, label_MensagemFimDoDia, label_MensagemFimDoJogo;
    JButton botao_Ataque_Torres, botao_Ataque_Escadas, botao_Ataque_Arietes, botao_Ataque_Nenhum, botao_Continuar_Ataques, botao_Continuar_FimDoDia, botao_Voltar_FimDoJogo;
    JPanel painelAtaques, painelAtaques2, painelAtaquesResultados;
    Inimigo inimigoAtacado;
    String tipoDeAtaque;
    
    public JogoView(Mundo m){
        this.setTitle("9 Cards Siege - Jogo");
        this.setResizable(true); // Permite que a janela seja redimensionada
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(2048,600));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(false);
        this.setLayout(new BorderLayout());
        this.m = m;
        this.setVisible(false);
        
        
        // Inicialização dos ícones
        icon_guardar = new ImageIcon("imagens/icon_guardar.png");
        icon_carregar = new ImageIcon("images/icon_carregar.png");
        icon_sair = new ImageIcon("imagens/icon_fechar.png");
        icon_dia1 = new ImageIcon("imagens/icon_dia1.png");
        icon_dia2 = new ImageIcon("imagens/icon_dia2.png");
        icon_dia3 = new ImageIcon("imagens/icon_dia3.png");
        icon_seguranca0 = new ImageIcon("imagens/icon_seguranca0.png");
        icon_seguranca1 = new ImageIcon("imagens/icon_seguranca1.png");
        icon_seguranca2 = new ImageIcon("imagens/icon_seguranca2.png");
        icon_seguranca3 = new ImageIcon("imagens/icon_seguranca3.png");
        icon_seguranca4 = new ImageIcon("imagens/icon_seguranca4.png");
        icon_suprimentos0 = new ImageIcon("imagens/icon_suprimentos0.png");
        icon_suprimentos1 = new ImageIcon("imagens/icon_suprimentos1.png");
        icon_suprimentos2 = new ImageIcon("imagens/icon_suprimentos2.png");
        icon_suprimentos3 = new ImageIcon("imagens/icon_suprimentos3.png");
        icon_suprimentos4 = new ImageIcon("imagens/icon_suprimentos4.png");
        icon_smileMuitoFeliz = new ImageIcon("imagens/icon_smileMuitoFeliz.png");
        icon_smileBemFeliz = new ImageIcon("imagens/icon_smileFeliz+.png");
        icon_smileFeliz = new ImageIcon("imagens/icon_smileFeliz.png");
        icon_smileTriste = new ImageIcon("imagens/icon_smileTriste.png");
        icon_smileZangado = new ImageIcon("imagens/icon_smileZangado.png");
        icon_soldadoTroia = new ImageIcon("imagens/soldadoTroia.png");
        icon_tunel1 = new ImageIcon("imagens/icon_tunel1.png");
        icon_tunel2 = new ImageIcon("imagens/icon_tunel2.png");
        icon_fortaleza = new ImageIcon("imagens/icon_castelo.png");
        icon_mochila0 = new ImageIcon("imagens/icon_mochila0.png");
        icon_mochila1 = new ImageIcon("imagens/icon_mochila1.png");
        icon_mochila2 = new ImageIcon("imagens/icon_mochila2.png");
        img_carta0  = new JLabel(new ImageIcon("imagens/cartas/0.png"));
        img_carta1 = new JLabel(new ImageIcon("imagens/cartas/1.JPG"));
        img_carta2 = new JLabel(new ImageIcon("imagens/cartas/2.JPG"));
        img_carta3 = new JLabel(new ImageIcon("imagens/cartas/3.JPG"));
        img_carta4 = new JLabel(new ImageIcon("imagens/cartas/4.JPG"));
        img_carta5 = new JLabel(new ImageIcon("imagens/cartas/5.JPG"));
        img_carta6 = new JLabel(new ImageIcon("imagens/cartas/6.JPG"));
        img_carta7 = new JLabel(new ImageIcon("imagens/cartas/7.JPG"));
        icon_catapulta1 = new ImageIcon("imagens/catapultav3-1.png");
        icon_catapulta2 = new ImageIcon("imagens/catapultav3-2.png");
        icon_catapulta3 = new ImageIcon("imagens/catapultav3-3.png");
        
        // Inicialização dos Botões das Ações
        botao_AtaqueDeAguaFervente = new JButton("Ataque de Água Fervente");
        botao_AtaqueDeArqueiros = new JButton("Ataque de Arqueiros");
        botao_AtaqueDeCloseCombat = new JButton("Ataque De Close Combat");
        botao_MotivarTropas = new JButton("Motivar as Tropas");
        botao_MovimentarSoldadosNoTunel = new JButton("Movimentar Soldados no Túnel");
        botao_Raid = new JButton("Raid");
        botao_RepararMuralha = new JButton("Reparar Muralha");
        botao_Sabotagem = new JButton("Sabotagem");
        botao_NaoRealizarMaisAcoes = new JButton("Não Fazer Nada");
        botao_Continuar_movimentarSoldados = new JButton("Continuar >>");
        botao_Movimentar_movimentarSoldadosFast = new JButton("Movimentar Soldados (FAST) >>");
        botao_Movimentar_movimentarSoldadosFree = new JButton("Movimentar Soldados (FREE) >>");

        // Inicialização dos jButtons
        botao_Continuar_RodarDado = new JButton("Virar Carta >>");
        botao_Continuar_Eventos = new JButton("Continuar >>");
        botao_Continuar_DRMS = new JButton("Continuar >>");
        botao_Continuar_AvancoInimigo = new JButton("Continuar >>");
        botao_RodarDado_repararMuralha = new JButton("Rodar Dado >>");
        botao_Continuar_repararMuralha = new JButton("Continuar >>");
        botao_RodarDado_sabotagem = new JButton("Rodar Dado >>");
        botao_Continuar_sabotagem = new JButton("Continuar >>");
        botao_RodarDado_raid = new JButton("Rodar Dado >>");
        botao_Continuar_raid = new JButton("Continuar >>");
        botao_RodarDado_motivarTropas = new JButton("Rodar Dado >>");
        botao_Continuar_motivarTropas = new JButton("Continuar >>");
        botao_RodarDado_motivarTropasBonus = new JButton("Rodar Dado (COM BONUS) >>");
        botao_Ataque_Arietes = new JButton("Atacar Aríetes");
        botao_Ataque_Torres = new JButton("Atacar Torres");
        botao_Ataque_Escadas = new JButton("Atacar Escadas");
        botao_Ataque_Nenhum = new JButton("Não Atacar");
        botao_Continuar_Ataques = new JButton("Continuar >>");
        botao_Continuar_FimDoDia = new JButton("Continuar >>");
        botao_Voltar_FimDoJogo = new JButton("Voltar ao Menu Inicial >>");

// Inicialização das jLabels
        label_dia = new JLabel();
        label_forcaDaMuralha = new JLabel();
        label_localDosSoldados = new JLabel();
        label_moralDoPovo = new JLabel();
        label_nivelDosSuprimentos = new JLabel();
        label_nivelDosSuprimentosFurtados = new JLabel();
        label_mensagemInicial = new JLabel("", SwingConstants.CENTER);
        label_acoesDisponiveis = new JLabel("", SwingConstants.CENTER);
        label_nrCatapultas = new JLabel("", SwingConstants.CENTER);
        
        configuraMenu();
                
        painelEsquerda = new JPanel();
        configuraPainelEsquerda();
        painelDireita = new JPanel();
        configuraPainelDireita();
        painelTopo = new JPanel();
        configuraPainelTopo();
        painelBase = new JPanel();
        configuraPainelBase();
        desenhoDosProgressosInimigos = new DesenhoDosProgressosInimigos(this, m);
        painelCentro = new JPanel();
        painelCentroBaixo = new JPanel();
        painelInfo = new JPanel();
        painelAcoes = new JPanel();
        painelAcoes2 = new JPanel();
        painelSoldadosSeguros = new JPanel();
        painelSoldadosEmLinhasInimigas = new JPanel();
        painelRodarDado = new JPanel();
        painelEventos = new JPanel();
        painelDRMS = new JPanel();
        painelAvancoInimigo = new JPanel();
        painelAcao_repararMuralha = new JPanel();
        painelAcao_repararMuralha2 = new JPanel();
        painelAcao_sabotagem = new JPanel();
        painelAcao_sabotagem2 = new JPanel();
        painelAcao_raid = new JPanel();
        painelAcao_raid2 = new JPanel();
        painelAcao_motivarTropas = new JPanel();
        painelAcao_motivarTropas2 = new JPanel();
        painelAcao_movimentarSoldados = new JPanel();
        painelAcao_movimentarSoldados2 = new JPanel();
        painelAtaques = new JPanel();
        painelAtaques2 = new JPanel();
        painelAtaquesResultados = new JPanel();
        painelFimDoDia = new JPanel();
        painelFimDoJogo = new JPanel();
        cl = new CardLayout();
        configuraPainelCentro();
        
        
        add(painelEsquerda, BorderLayout.WEST);
        add(painelDireita, BorderLayout.EAST);
        //add(painelTopo, BorderLayout.NORTH);
        add(painelBase, BorderLayout.SOUTH);
        add(painelCentro, BorderLayout.CENTER);

    }

    
    public void configuraMenu(){
        // CONFIGURAÇÃO DO MENU
        /*
            JMenuBar -> menuBar_menu
            JMenu -> menu_jogo
                    => JMenuItem -> Guardar Jogo (menuItem_XXX)
                                 -> Carregar Jogo (menuItem_XXX)
                                 -> Sair (menuItem_sair)
                  -> menu_sobre
                    => JMenuItem -> Sobre (menuItem_sobre)
            
        */
        
        
        menuBar_menu = new JMenuBar();
        menu_jogo = new JMenu("Jogo");
        menu_jogo.setMnemonic(KeyEvent.VK_J);
        menu_sobre = new JMenu("Sobre");
        menu_sobre.setMnemonic(KeyEvent.VK_A);
        menuItem_sobre = new JMenuItem("Sobre");
        menuItem_sobre.setMnemonic(KeyEvent.VK_O);
        menuItem_sobre.setToolTipText("Sobre a Aplicação");
       
        
        
        
        menuItem_sair = new JMenuItem("Sair", icon_sair);
        menuItem_sair.setMnemonic(KeyEvent.VK_S);
        menuItem_sair.setToolTipText("Sair da Aplicação");
        
        menuItem_guardar = new JMenuItem("Guardar Jogo");
        menuItem_guardar.setMnemonic(KeyEvent.VK_G);
        menuItem_sair.setToolTipText("Guardar o estado atual do jogo");
        
        
        menu_jogo.add(menuItem_guardar);
        menu_jogo.add(menuItem_sair);
        
        menu_sobre.add(menuItem_sobre);
        
        menuBar_menu.add(menu_jogo);
        menuBar_menu.add(menu_sobre);
        
        setJMenuBar(menuBar_menu); 
        // Fim da configuração do Menu
    }
    
    
    public JLabel getLabel_dia() {
        return label_dia;
    }

    public JLabel getLabel_forcaDaMuralha() {
        return label_forcaDaMuralha;
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
       System.out.println("DIA UPDATE 0: " + getM().getDia());
        //m = (Mundo) arg;
        System.out.println("DIA UPDATE: " + getM().getDia());
        configuraDia();
        configuraMoralDoPovo();
        configuraNivelDosSuprimentos();
        configuraForcaDaMuralha();
        configuraLocalDosSoldados();
        configuraCartaAtual();
        configuraSuprimentosFurtados();
        configuraNrCatapultas();
        desenhoDosProgressosInimigos.repaint();
        configuraPainelRodarDado();
        configuraPainelEventos();
        configuraPainelDRMS();
        configuraPainelAvancoInimigo();
        configuraPainelAcoes();
        configuraPainelAcao_repararMuralha();
        configuraPainelAcao_repararMuralha2();
        configuraPainelAcao_sabotagem();
        configuraPainelAcao_sabotagem2();
        configuraPainelAcao_raid();
        configuraPainelAcao_raid2();
        configuraPainelAcao_motivarTropas();
        configuraPainelAcao_motivarTropas2();
        configuraPainelAcao_movimentarSoldados();
        configuraPainelAcao_movimentarSoldados2();
        configuraPainelAtaques();
        configuraPainelAtaques2();
        configuraPainelFimDoDia();
        configuraPainelFimDoJogo();
        
        if(m.getEstado() instanceof AguardaLeituraDeInfo)
            trocarPainel("painelInfo");
    }

    public void mostraPopup(String mensagemDeErro){

        JOptionPane.showMessageDialog(this, mensagemDeErro, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    
    private void configuraPainelTopo(){
        painelTopo.setBackground(Color.decode("#2c3e50"));
    }
    
    private void configuraPainelEsquerda() {
        painelEsquerda.setLayout(new BoxLayout(painelEsquerda,BoxLayout.Y_AXIS)); // BoxLayout - Apenas um elemento por linha (Y_AXIS)
        painelEsquerda.setBackground(Color.decode("#2c3e50"));
        painelEsquerda.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));  // Adiciona bordas ao redor do jPanel
        
        configuraCartaAtual();
    }

    private void configuraPainelDireita() {
        painelDireita.setLayout(new BoxLayout(painelDireita,BoxLayout.Y_AXIS)); // BoxLayout - Apenas um elemento por linha (Y_AXIS)
        painelDireita.setBackground(Color.decode("#2c3e50"));        
        painelDireita.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));  // Adiciona bordas ao redor do jPanel

        configuraDia();
        configuraMoralDoPovo();
        configuraNivelDosSuprimentos();
        configuraForcaDaMuralha();
        configuraLocalDosSoldados();
        configuraSuprimentosFurtados();
        configuraNrCatapultas();

        // Adiciona elementos ao painel
        
        painelDireita.add( Box.createVerticalGlue() ); // Para centrar os elementos verticalmente
        painelDireita.add(label_dia);
        painelDireita.add(label_nrCatapultas);
        //painelDireita.add(new JSeparator(SwingConstants.HORIZONTAL)); // Adiciona um separador horizontal
        painelDireita.add(label_forcaDaMuralha);
        painelDireita.add(label_nivelDosSuprimentos);
        painelDireita.add(label_moralDoPovo);
        painelDireita.add(label_localDosSoldados);
        
        painelDireita.add(label_nivelDosSuprimentosFurtados);
        painelDireita.add( Box.createVerticalGlue() ); // Para centrar os elementos verticalmente
    }

  
    private void configuraCartaAtual(){
        painelEsquerda.removeAll(); // Remover a imagem anterior
        
        JLabel label_NomeDaCarta = new JLabel();
        JLabel label_NomeDoEvento = new JLabel();
        // Descobrir qual a carta atual
        Carta cartaAtual = m.getCartaAtual();
        JLabel img_CartaAtual = img_carta0;
        
        if(cartaAtual == null){
            img_CartaAtual = img_carta0;
        }else if(cartaAtual.getNr() == 1){
            img_CartaAtual = img_carta1;
        }else if(cartaAtual.getNr() == 2){
            img_CartaAtual = img_carta2;
        }else if(cartaAtual.getNr() == 3){
            img_CartaAtual = img_carta3;
        }else if(cartaAtual.getNr() == 4){
            img_CartaAtual = img_carta4;
        }else if(cartaAtual.getNr() == 5){
            img_CartaAtual = img_carta5;
        }else if(cartaAtual.getNr() == 6){
            img_CartaAtual = img_carta6;
        }else if(cartaAtual.getNr() == 7){
            img_CartaAtual = img_carta7;
        }
        label_NomeDaCarta.setForeground(Color.white);
        label_NomeDaCarta.setFont(new Font("Serif", Font.CENTER_BASELINE, 30));
        if(cartaAtual != null)
            label_NomeDaCarta.setText("" + cartaAtual);
        
        if(cartaAtual != null)
            label_NomeDoEvento.setText("Evento: " + m.eventoAtual(cartaAtual));
        
        label_NomeDoEvento.setForeground(Color.white);
        label_NomeDoEvento.setFont(new Font("Serif", Font.CENTER_BASELINE, 30));
        
        
        // Adiciona elementos ao painel
        
        painelEsquerda.add( Box.createVerticalGlue() ); // Para centrar os elementos verticalmente
        painelEsquerda.add(label_NomeDaCarta);
        painelEsquerda.add(img_CartaAtual);
        painelEsquerda.add(label_NomeDoEvento);
        painelEsquerda.add( Box.createVerticalGlue() ); // Para centrar os elementos verticalmente
        
        //painelEsquerda.repaint();
        painelEsquerda.revalidate();
        //painelCentroBaixo.revalidate();
    }
    
    private void configuraPainelBase() {
        painelBase.setBackground(Color.decode("#2c3e50"));
    }

    private void configuraPainelCentro() {
        painelCentro.setLayout(new BorderLayout());
        painelCentro.add(desenhoDosProgressosInimigos, BorderLayout.CENTER);
        desenhoDosProgressosInimigos.setBackground(Color.decode("#34495e"));
        painelCentro.setBackground(Color.decode("#34495e"));
        configuraPainelCentroBaixo();
        painelCentro.add(painelCentroBaixo, BorderLayout.SOUTH);

    }

    private void configuraPainelCentroBaixo(){

        painelCentroBaixo.setLayout(cl);
        
        /* 
            Que diferentes Paineis vão estar no CardLayour?
                - Painel com texto e um botão para avançar - painelInfo
                - Painel que verifica a posição dos soldados antes de virar carta (SOLDADOS NO CASTELO) - painelSoldadosSeguros
                - Painel que verifica a posição dos soldados antes de virar carta (SOLDADOS EM LINHAS INIMIGAS) - painelSoldadosEmLinhasInimigas
                - Painel com o resultado da rodagem do dado - painelRodaDado
                - Painel com as ações disponíveis (para escolha) - painelAcoes
                - Painel com opções das ações de ataque
                - Painel com opções da ação de Movimentar Soldados no Túnel
                - ...
        */
    
        
        configuraPainelInfo();
        configuraPainelSoldadosSeguros();
        configuraPainelSoldadosEmLinhasInimigas();
        configuraPainelAcoes();
        configuraPainelRodarDado();
        configuraPainelEventos();
        configuraPainelDRMS();
        configuraPainelAvancoInimigo();
        configuraPainelAcao_repararMuralha();
        configuraPainelAcao_repararMuralha2();
        configuraPainelAcao_sabotagem();
        configuraPainelAcao_sabotagem2();
        configuraPainelAcao_raid();
        configuraPainelAcao_raid2();
        configuraPainelAcao_motivarTropas();
        configuraPainelAcao_motivarTropas2();
        configuraPainelAcao_movimentarSoldados();
        configuraPainelAcao_movimentarSoldados2();
        configuraPainelAtaques();
        configuraPainelAtaques2();
        configuraPainelFimDoDia();
        configuraPainelFimDoJogo();
        
        painelCentroBaixo.add(painelInfo, "painelInfo");
        painelCentroBaixo.add(painelAcoes, "painelAcoes");
        painelCentroBaixo.add(painelSoldadosSeguros, "painelSoldadosSeguros");
        painelCentroBaixo.add(painelSoldadosEmLinhasInimigas, "painelSoldadosEmLinhasInimigas");
        painelCentroBaixo.add(painelRodarDado, "painelRodarDado");
        painelCentroBaixo.add(painelEventos, "painelEventos");
        painelCentroBaixo.add(painelDRMS, "painelDRMS");
        painelCentroBaixo.add(painelAvancoInimigo, "painelAvancoInimigo");
        painelCentroBaixo.add(painelAcao_repararMuralha, "painelAcao_repararMuralha");
        painelCentroBaixo.add(painelAcao_repararMuralha2, "painelAcao_repararMuralha2");
        painelCentroBaixo.add(painelAcao_sabotagem, "painelAcao_sabotagem");
        painelCentroBaixo.add(painelAcao_sabotagem2, "painelAcao_sabotagem2");
        painelCentroBaixo.add(painelAcao_raid, "painelAcao_raid");
        painelCentroBaixo.add(painelAcao_raid2, "painelAcao_raid2");
        painelCentroBaixo.add(painelAcao_motivarTropas, "painelAcao_motivarTropas");
        painelCentroBaixo.add(painelAcao_motivarTropas2, "painelAcao_motivarTropas2");
        painelCentroBaixo.add(painelAcao_movimentarSoldados, "painelAcao_movimentarSoldados");
        painelCentroBaixo.add(painelAcao_movimentarSoldados2, "painelAcao_movimentarSoldados2");
        painelCentroBaixo.add(painelAtaques, "painelAtaques");
        painelCentroBaixo.add(painelAtaquesResultados, "painelAtaquesResultados");
        painelCentroBaixo.add(painelFimDoDia, "painelFimDoDia");
        painelCentroBaixo.add(painelFimDoJogo, "painelFimDoJogo");
        painelCentroBaixo.revalidate();
    }
    
    private void configuraPainelFimDoJogo(){
        String msg = "<html>";
        /*
            Este painel é composto por:
                - 1 bloco de texto informativo em cima - label_Mensagem
                - 1 botão em baixo que permite avançar para a próxima secção do jogo - botao_Continuar
        
        */
        painelFimDoJogo.removeAll();
        painelFimDoJogo.setLayout(new BorderLayout());
        painelFimDoJogo.setBackground(Color.decode("#405972"));
        painelFimDoJogo.setPreferredSize(new Dimension(200,300));
        
        if(m.getMotivoFimDoJogo() != null){
            msg += "Game Over :/<br />O jogo chegou ao fim pelo seguinte motivo:<br />" + m.getMotivoFimDoJogo();
        }
        else{
            msg += "Parabéns! Venceu o jogo!!!";
        }
        
        
        
        msg += "</html>";
       
        
        label_MensagemFimDoJogo = new JLabel("", SwingConstants.CENTER);
        label_MensagemFimDoJogo.setText(msg);
        label_MensagemFimDoJogo.setFont(new Font("Serif", Font.PLAIN, 30));
        label_MensagemFimDoJogo.setForeground(Color.white);
        
        //botao_Continuar_FimDoDia = new JButton("Continuar >>");
        botao_Voltar_FimDoJogo.setBorderPainted(false);
        botao_Voltar_FimDoJogo.setFocusPainted(false);
        botao_Voltar_FimDoJogo.setForeground(Color.white);
        botao_Voltar_FimDoJogo.setPreferredSize(new Dimension(120,80));
        botao_Voltar_FimDoJogo.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Voltar_FimDoJogo.setBackground(Color.decode("#104919"));
        painelFimDoJogo.add(botao_Voltar_FimDoJogo, BorderLayout.AFTER_LAST_LINE);
        painelFimDoJogo.add(label_MensagemFimDoJogo, BorderLayout.CENTER);
    }
    
    private void configuraPainelFimDoDia(){
        String msg = "<html>";
        /*
            Este painel é composto por:
                - 1 bloco de texto informativo em cima - label_Mensagem
                - 1 botão em baixo que permite avançar para a próxima secção do jogo - botao_Continuar
        
        */
        painelFimDoDia.removeAll();
        painelFimDoDia.setLayout(new BorderLayout());
        painelFimDoDia.setBackground(Color.decode("#405972"));
        painelFimDoDia.setPreferredSize(new Dimension(200,300));
        
        msg += "Parabéns! Chegou ao fim do dia " + m.getDia() + ". Continua em segurança... Por agora!";
        msg += "<br />Os seus suprimentos foram reduzidos em 1 unidade.";
        if(m.soldadosNoTunel()){
           // COMO TUDO ESTÁ CONFIGURADO PARA QUE A POSIÇÃO DOS SOLDADOS NUNCA SEJA < 0, ISTO GARANTE QUE ELES VOLTAM PARA A POSIÇÃO 0, INDEPENDENTEMENTE DA POSIÇÃO EM QUE ESTÃO ATUALMENTE
            msg += "<br />Como tinha soldados no túnel, os mesmos viajaram de volta para o castelo.";
        }
        // SOLDADOS EM TERRITÓRIO INIMIGO, SÃO CAPTURADOS
        if(m.soldadosEmLinhasInimigas()){
            msg += "<br />Como tinha soldados em linhas inimigas, os mesmos foram capturados.";
        }        
        
        msg += "</html>";
       
        
        label_MensagemFimDoDia = new JLabel("", SwingConstants.CENTER);
        label_MensagemFimDoDia.setText(msg);
        label_MensagemFimDoDia.setFont(new Font("Serif", Font.PLAIN, 30));
        label_MensagemFimDoDia.setForeground(Color.white);
        
        //botao_Continuar_FimDoDia = new JButton("Continuar >>");
        botao_Continuar_FimDoDia.setBorderPainted(false);
        botao_Continuar_FimDoDia.setFocusPainted(false);
        botao_Continuar_FimDoDia.setForeground(Color.white);
        botao_Continuar_FimDoDia.setPreferredSize(new Dimension(120,80));
        botao_Continuar_FimDoDia.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_FimDoDia.setBackground(Color.decode("#104919"));
        painelFimDoDia.add(botao_Continuar_FimDoDia, BorderLayout.AFTER_LAST_LINE);
        painelFimDoDia.add(label_MensagemFimDoDia, BorderLayout.CENTER);
    }
    
    private void configuraPainelAtaques(){
        List<JButton> botoesDisponiveis;
        List<Inimigo> inimigosDisponiveis = new ArrayList<>();
        Acao acaoDeAtaque = null;
        /*
            Este painel vai mostrar a seguinte informação:
                - Mensagem inicial a introduzir a escolha de inimigos
                - Nr. de APAs Disponíveis
                - Lista de Inimigos - JRadioButton (dentro do seu próprio painel interno) - painelAtaques2
        */
        painelAtaques.removeAll();
        painelAtaques.setLayout(new BorderLayout());
        painelAtaques.setBackground(Color.decode("#405972"));
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("<html><center>Por favor, selecione uma das facções abaixo para atacar!</center></html>");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        
        
        
        // PAINEL DE ATAQUES 2 - LISTA DAS AÇÕES DISPONÍVEIS
        painelAtaques2.setBackground(new Color(0,0,0,0)); // TRANSPARENTE (r, g, b, a <- opacidade)
        painelAtaques2.removeAll();
        // Descobrir a lista de ações permitidas pelo Evento Atual
            // Descobrir qual a carta atual
        Carta cartaAtual = m.getCartaAtual();
        if(cartaAtual != null){
            
            // Nr. de Ações Disponíveis
//            label_acoesDisponiveis.setText("Ações Disponíveis: " + m.eventoAtual(cartaAtual).getAPA() + " para o evento " + m.eventoAtual(cartaAtual));
//            label_acoesDisponiveis.setFont(new Font("Serif", Font.PLAIN, 30));
//            
            
            Evento eventoAtual = m.eventoAtual(cartaAtual);
            
            if(tipoDeAtaque == "AtaqueDeAguaFervente"){
                for(Acao acao : eventoAtual.getAcoesPermitidas()){
                    if(acao instanceof AtaqueDeAguaFervente){
                        acaoDeAtaque = acao;
                        inimigosDisponiveis.addAll(acaoDeAtaque.getPotenciaisAlvos()); 
                    }
                }
            }
            else if(tipoDeAtaque == "AtaqueDeArqueiros"){
                for(Acao acao : eventoAtual.getAcoesPermitidas()){
                    if(acao instanceof AtaqueDeArqueiros){
                        acaoDeAtaque = acao;
                        inimigosDisponiveis.addAll(acaoDeAtaque.getPotenciaisAlvos()); 
                    }
                }
            }
            else if(tipoDeAtaque == "AtaqueDeCloseCombat"){
                for(Acao acao : eventoAtual.getAcoesPermitidas()){
                    if(acao instanceof AtaqueDeCloseCombat){
                        acaoDeAtaque = acao;
                        inimigosDisponiveis.addAll(acaoDeAtaque.getPotenciaisAlvos()); 
                    }
                }
            }

        }
   
        // Inicializar a lista dos botões disponíveis com a lista recebida de uma função
        // que vai cruzar os botões recebidos com a lista de ações disponíveis e fazer a filtração
        botoesDisponiveis = new ArrayList<>(getBotoesDeAtaqueDisponiveis(inimigosDisponiveis));

        for(JButton botao : botoesDisponiveis){
             botao.setVerticalTextPosition(SwingConstants.TOP);
             botao.setHorizontalTextPosition(SwingConstants.CENTER);
             painelAtaques2.add(botao);
        }

        painelAtaques2.revalidate();
        painelAtaques2.repaint();
        // Fazer as adições ao painel principal
        painelAtaques.add(label_Msg, BorderLayout.NORTH);
//        painelAtaques.add(label_acoesDisponiveis, BorderLayout.CENTER);
        painelAtaques.add(painelAtaques2, BorderLayout.SOUTH);
        
        
        painelAtaques.revalidate();
        painelAtaques.repaint();
    }
    
    private void configuraPainelAtaques2(){
        painelAtaquesResultados.removeAll();
        painelAtaquesResultados.setLayout(new BorderLayout());
        painelAtaquesResultados.setBackground(Color.decode("#405972"));
        painelAtaquesResultados.setPreferredSize(new Dimension(200,300));


        String msg = "O resultado do dado foi " + m.getUltimoResultadoDoDado() + ".";
        
        
        if(inimigoAtacado != null){
            
            if(tipoDeAtaque == "AtaqueDeAguaFervente"){
                if(m.getUltimoResultadoDoDado() > inimigoAtacado.getForca())
                    msg += "O seu ataque teve sucesso e o inimigo recuou uma posição!";

                else if(m.getUltimoResultadoDoDado() == 1)
                    msg += "Má Sorte! O seu ataque falhou e com isso a população perde 1 unidade de moral!";
                else
                    msg += "Má Sorte! O seu ataque não teve sucesso!";
            }
            else if(tipoDeAtaque == "AtaqueDeArqueiros"){
                if(m.getUltimoResultadoDoDado() > inimigoAtacado.getForca())
                    msg += "O seu ataque teve sucesso e o inimigo recuou uma posição!";
                else
                    msg += "Má Sorte! O seu ataque não teve sucesso!";
            }
            if(tipoDeAtaque == "AtaqueDeCloseCombat"){
                if(m.getUltimoResultadoDoDado() > 4)
                    msg += "O seu ataque teve sucesso e o inimigo recuou uma posição!";

                else if(m.getUltimoResultadoDoDado() == 1)
                    msg += "Má Sorte! O seu ataque falhou e com isso a população perde 1 unidade de moral!";
                else
                    msg += "Má Sorte! O seu ataque não teve sucesso!";
            }
        }
       
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(msg);

        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_Ataques.setText("<< Voltar às Ações");
        botao_Continuar_Ataques.setBorderPainted(false);
        botao_Continuar_Ataques.setFocusPainted(false);
        botao_Continuar_Ataques.setForeground(Color.white);
        botao_Continuar_Ataques.setPreferredSize(new Dimension(120,80));
        botao_Continuar_Ataques.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_Ataques.setBackground(Color.decode("#104919"));
        painelAtaquesResultados.add(botao_Continuar_Ataques, BorderLayout.AFTER_LAST_LINE);
        painelAtaquesResultados.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_movimentarSoldados(){
        painelAcao_movimentarSoldados.removeAll();
        painelAcao_movimentarSoldados.setLayout(new BorderLayout());
        painelAcao_movimentarSoldados.setBackground(Color.decode("#405972"));
        painelAcao_movimentarSoldados.setPreferredSize(new Dimension(200,300));
        
        //
        // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            if(m.getCartaAtual() != null){
                Carta cartaVirada = m.getCartaAtual();
                Evento eventoAtual = m.eventoAtual(cartaVirada);
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof MovimentarSoldadosNoTunel){ // SE ESSA DRM AFETA A AÇÃO "RepararMuralha"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
            }
       String msg = "<html>Movimento Free: mover 1 unidade (Custo: 0 APA)"  
           + "<br />Movimento Fast: mover diretamente para o final do Túnel (Custo: 2 APA)</html>";
            
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(msg);
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Movimentar_movimentarSoldadosFree.setText("<html><center>Movimentar (FREE) <br />>></center></html>");
        botao_Movimentar_movimentarSoldadosFree.setBorderPainted(false);
        botao_Movimentar_movimentarSoldadosFree.setFocusPainted(false);
        botao_Movimentar_movimentarSoldadosFree.setForeground(Color.white);
        botao_Movimentar_movimentarSoldadosFree.setPreferredSize(new Dimension(200,80));
        botao_Movimentar_movimentarSoldadosFree.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Movimentar_movimentarSoldadosFree.setBackground(Color.decode("#104919"));
        
        botao_Movimentar_movimentarSoldadosFast.setText("<html><center>Movimentar (FAST)<br />>> </center></html>");
        botao_Movimentar_movimentarSoldadosFast.setBorderPainted(false);
        botao_Movimentar_movimentarSoldadosFast.setFocusPainted(false);
        botao_Movimentar_movimentarSoldadosFast.setForeground(Color.white);
        botao_Movimentar_movimentarSoldadosFast.setPreferredSize(new Dimension(200,80));
        botao_Movimentar_movimentarSoldadosFast.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Movimentar_movimentarSoldadosFast.setBackground(Color.decode("#104919"));
        
        painelAcao_movimentarSoldados.add(botao_Movimentar_movimentarSoldadosFree, BorderLayout.EAST);
        painelAcao_movimentarSoldados.add(botao_Movimentar_movimentarSoldadosFast, BorderLayout.WEST);
        painelAcao_movimentarSoldados.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_movimentarSoldados2(){
        painelAcao_movimentarSoldados2.removeAll();
        painelAcao_movimentarSoldados2.setLayout(new BorderLayout());
        painelAcao_movimentarSoldados2.setBackground(Color.decode("#405972"));
        painelAcao_movimentarSoldados2.setPreferredSize(new Dimension(200,300));
        

        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("Os seus soldados estão agora na posição " + m.getPosDosSoldados() + ".");

        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_movimentarSoldados.setText("<< Voltar às Ações");
        botao_Continuar_movimentarSoldados.setBorderPainted(false);
        botao_Continuar_movimentarSoldados.setFocusPainted(false);
        botao_Continuar_movimentarSoldados.setForeground(Color.white);
        botao_Continuar_movimentarSoldados.setPreferredSize(new Dimension(120,80));
        botao_Continuar_movimentarSoldados.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_movimentarSoldados.setBackground(Color.decode("#104919"));
        painelAcao_movimentarSoldados2.add(botao_Continuar_movimentarSoldados, BorderLayout.AFTER_LAST_LINE);
        painelAcao_movimentarSoldados2.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_motivarTropas(){
        painelAcao_motivarTropas.removeAll();
        painelAcao_motivarTropas.setLayout(new BorderLayout());
        painelAcao_motivarTropas.setBackground(Color.decode("#405972"));
        painelAcao_motivarTropas.setPreferredSize(new Dimension(200,300));
        
        //
        // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            if(m.getCartaAtual() != null){
                Carta cartaVirada = m.getCartaAtual();
                Evento eventoAtual = m.eventoAtual(cartaVirada);
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof MotivarTropas){ // SE ESSA DRM AFETA A AÇÃO "RepararMuralha"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
            }
       String msg = "<html>Para poder aumentar a moral do povo (+1), deverá conseguir um resultado >= a " + Constantes.MOTIVAR_TROPAS_MINIMO.getValor() + " (+" + var + " DRM)."  
           + "<br />Pode sacrificar os seus suprimentos (-1), recebendo em troca +1 no resultado do dado.</html>";
            
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(msg);
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_RodarDado_motivarTropas.setText("<html><center>Rodar Dado<br /> >></center></html>");
        botao_RodarDado_motivarTropas.setBorderPainted(false);
        botao_RodarDado_motivarTropas.setFocusPainted(false);
        botao_RodarDado_motivarTropas.setForeground(Color.white);
        botao_RodarDado_motivarTropas.setPreferredSize(new Dimension(200,80));
        botao_RodarDado_motivarTropas.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_motivarTropas.setBackground(Color.decode("#104919"));
        
        botao_RodarDado_motivarTropasBonus.setText("<html><center>Rodar Dado<br />(com Bonus) <br />>> </center></html>");
        botao_RodarDado_motivarTropasBonus.setBorderPainted(false);
        botao_RodarDado_motivarTropasBonus.setFocusPainted(false);
        botao_RodarDado_motivarTropasBonus.setForeground(Color.white);
        botao_RodarDado_motivarTropasBonus.setPreferredSize(new Dimension(200,80));
        botao_RodarDado_motivarTropasBonus.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_motivarTropasBonus.setBackground(Color.decode("#104919"));
        
        painelAcao_motivarTropas.add(botao_RodarDado_motivarTropasBonus, BorderLayout.EAST);
        painelAcao_motivarTropas.add(botao_RodarDado_motivarTropas, BorderLayout.WEST);
        painelAcao_motivarTropas.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_motivarTropas2(){
        painelAcao_motivarTropas2.removeAll();
        painelAcao_motivarTropas2.setLayout(new BorderLayout());
        painelAcao_motivarTropas2.setBackground(Color.decode("#405972"));
        painelAcao_motivarTropas2.setPreferredSize(new Dimension(200,300));
        

        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);

        if(m.getUltimoResultadoDoDado()  >= Constantes.MOTIVAR_TROPAS_MINIMO.getValor()){ 
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Conseguiu aumentar a moral do povo (+1)!");
        }else
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Não conseguiu aumentar a moral do povo.");

        
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_motivarTropas.setText("<< Voltar às Ações");
        botao_Continuar_motivarTropas.setBorderPainted(false);
        botao_Continuar_motivarTropas.setFocusPainted(false);
        botao_Continuar_motivarTropas.setForeground(Color.white);
        botao_Continuar_motivarTropas.setPreferredSize(new Dimension(120,80));
        botao_Continuar_motivarTropas.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_motivarTropas.setBackground(Color.decode("#104919"));
        painelAcao_motivarTropas2.add(botao_Continuar_motivarTropas, BorderLayout.AFTER_LAST_LINE);
        painelAcao_motivarTropas2.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_raid(){
        painelAcao_raid.removeAll();
        painelAcao_raid.setLayout(new BorderLayout());
        painelAcao_raid.setBackground(Color.decode("#405972"));
        painelAcao_raid.setPreferredSize(new Dimension(200,300));
        
        //
        // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            if(m.getCartaAtual() != null){
                Carta cartaVirada = m.getCartaAtual();
                Evento eventoAtual = m.eventoAtual(cartaVirada);
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof Raid){ // SE ESSA DRM AFETA A AÇÃO "RepararMuralha"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
            }
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("<html>Para fazer raid, deverá conseguir um resultado >= 3.<br/> Se obtiver o resultado 6, conseguirá arrecadar 2 unidades de Suprimentos. Se for 1, as tropas serão capturadas." + " (+" + var + " DRM).</html>");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_RodarDado_raid.setText("Rodar Dado >>");
        botao_RodarDado_raid.setBorderPainted(false);
        botao_RodarDado_raid.setFocusPainted(false);
        botao_RodarDado_raid.setForeground(Color.white);
        botao_RodarDado_raid.setPreferredSize(new Dimension(120,80));
        botao_RodarDado_raid.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_raid.setBackground(Color.decode("#104919"));
        painelAcao_raid.add(botao_RodarDado_raid, BorderLayout.AFTER_LAST_LINE);
        painelAcao_raid.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_raid2(){
        painelAcao_raid2.removeAll();
        painelAcao_raid2.setLayout(new BorderLayout());
        painelAcao_raid2.setBackground(Color.decode("#405972"));
        painelAcao_raid2.setPreferredSize(new Dimension(200,300));
        

        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        
       
        if(m.getUltimoResultadoDoDado()  >= Constantes.RAID_MINIMO_SUCESSO1.getValor()){ // DEFAULT: 3,4,5,6
            if(m.getUltimoResultadoDoDado()  >= Constantes.RAID_MINIMO_SUCESSO2.getValor()){ // DEFAULT: 6 <= RAID COM SUCESSO DE 2 SUPRIMENTOS
                label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Conseguiu furtar 2 unidades de suprimentos!");
            }else{  // DEFAULT: 3,4,5 <= RAID COM SUCESSO DE 1 SUPRIMENTO
                label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Conseguiu furtar 1 unidade de suprimentos!");
            }
        }else{ // DEFAULT: 1 <= SOLDADOS CAPTURADOS
            label_Msg.setText("<html>Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". <br />Os seus soldados não conseguiram efetuar o Raid e foram capturados :/</html>");
        }

        
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_raid.setText("<< Voltar às Ações");
        botao_Continuar_raid.setBorderPainted(false);
        botao_Continuar_raid.setFocusPainted(false);
        botao_Continuar_raid.setForeground(Color.white);
        botao_Continuar_raid.setPreferredSize(new Dimension(120,80));
        botao_Continuar_raid.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_raid.setBackground(Color.decode("#104919"));
        painelAcao_raid2.add(botao_Continuar_raid, BorderLayout.AFTER_LAST_LINE);
        painelAcao_raid2.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_sabotagem(){
        painelAcao_sabotagem.removeAll();
        painelAcao_sabotagem.setLayout(new BorderLayout());
        painelAcao_sabotagem.setBackground(Color.decode("#405972"));
        painelAcao_sabotagem.setPreferredSize(new Dimension(200,300));
        
        //
        // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            if(m.getCartaAtual() != null){
                Carta cartaVirada = m.getCartaAtual();
                Evento eventoAtual = m.eventoAtual(cartaVirada);
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof Sabotagem){ // SE ESSA DRM AFETA A AÇÃO "RepararMuralha"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
            }
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("Para fazer sabotagem, deverá conseguir um resultado >= 5. Se o resultado for 1, as tropas serão capturadas." + " (+" + var + " DRM).");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_RodarDado_sabotagem.setText("Rodar Dado >>");
        botao_RodarDado_sabotagem.setBorderPainted(false);
        botao_RodarDado_sabotagem.setFocusPainted(false);
        botao_RodarDado_sabotagem.setForeground(Color.white);
        botao_RodarDado_sabotagem.setPreferredSize(new Dimension(120,80));
        botao_RodarDado_sabotagem.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_sabotagem.setBackground(Color.decode("#104919"));
        painelAcao_sabotagem.add(botao_RodarDado_sabotagem, BorderLayout.AFTER_LAST_LINE);
        painelAcao_sabotagem.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_sabotagem2(){
        painelAcao_sabotagem2.removeAll();
        painelAcao_sabotagem2.setLayout(new BorderLayout());
        painelAcao_sabotagem2.setBackground(Color.decode("#405972"));
        painelAcao_sabotagem2.setPreferredSize(new Dimension(200,300));
        

        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        
        if(m.getUltimoResultadoDoDado() >= Constantes.SABOTAGEM_MINIMO_SUCESSO.getValor())
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Danificou 1 catapulta inimiga!");
        else if(m.getUltimoResultadoDoDado() <= Constantes.SABOTAGEM_MAXIMO_INSUCESSO.getValor())
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Enquanto tentavam sabotar as catapultas inimigas, as suas tropas foram capturadas pelo inimigo :/");
        else
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". Pouca sorte, não conseguiu efetuar sabotagem com sucesso :/");
        
        
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_sabotagem.setText("<< Voltar às Ações");
        botao_Continuar_sabotagem.setBorderPainted(false);
        botao_Continuar_sabotagem.setFocusPainted(false);
        botao_Continuar_sabotagem.setForeground(Color.white);
        botao_Continuar_sabotagem.setPreferredSize(new Dimension(120,80));
        botao_Continuar_sabotagem.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_sabotagem.setBackground(Color.decode("#104919"));
        painelAcao_sabotagem2.add(botao_Continuar_sabotagem, BorderLayout.AFTER_LAST_LINE);
        painelAcao_sabotagem2.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_repararMuralha2(){
        painelAcao_repararMuralha2.removeAll();
        painelAcao_repararMuralha2.setLayout(new BorderLayout());
        painelAcao_repararMuralha2.setBackground(Color.decode("#405972"));
        painelAcao_repararMuralha2.setPreferredSize(new Dimension(200,300));
        
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        
        if(m.getUltimoResultadoDoDado() >= Constantes.REPARAR_MURALHA_MINIMO.getValor())
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". A força da sua muralha foi aumentada (+1).");
        else
            label_Msg.setText("Rodou o dado e o resultado foi " + m.getUltimoResultadoDoDado() + ". A força da sua muralha não foi aumentada.");
        
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_Continuar_repararMuralha.setText("<< Voltar às Ações");
        botao_Continuar_repararMuralha.setBorderPainted(false);
        botao_Continuar_repararMuralha.setFocusPainted(false);
        botao_Continuar_repararMuralha.setForeground(Color.white);
        botao_Continuar_repararMuralha.setPreferredSize(new Dimension(120,80));
        botao_Continuar_repararMuralha.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_repararMuralha.setBackground(Color.decode("#104919"));
        painelAcao_repararMuralha2.add(botao_Continuar_repararMuralha, BorderLayout.AFTER_LAST_LINE);
        painelAcao_repararMuralha2.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAcao_repararMuralha(){
        painelAcao_repararMuralha.removeAll();
        painelAcao_repararMuralha.setLayout(new BorderLayout());
        painelAcao_repararMuralha.setBackground(Color.decode("#405972"));
        painelAcao_repararMuralha.setPreferredSize(new Dimension(200,300));
        
        //
        // VERIFICAR SE O RESULTADO SERÁ INFLUENCIADO POR ALGUMA DRM
            boolean temDRMS = false; // SE O EVENTO ATUAL TEM ALGUM DRM QUE AFETE ESTA AÇÃO
            int var  = 0; // SE TEM DRM, QUAL A VARIÂNCIA DA ALTERAÇÃO (SE NÃO TEM -> = 0)
            if(m.getCartaAtual() != null){
                Carta cartaVirada = m.getCartaAtual();
                Evento eventoAtual = m.eventoAtual(cartaVirada);
                for(DRM drm : eventoAtual.getDrms()){
                    if(drm.getAcao() instanceof RepararMuralha){ // SE ESSA DRM AFETA A AÇÃO "RepararMuralha"
                        temDRMS = true;
                        var += drm.getVar();
                    }
                }
            }
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("Para aumentar a força da muralha (+1), deve conseguir >= " + Constantes.REPARAR_MURALHA_MINIMO.getValor() + " (+" + var + " DRM) no resultado do dado.");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        botao_RodarDado_repararMuralha.setText("Rodar Dado >>");
        botao_RodarDado_repararMuralha.setBorderPainted(false);
        botao_RodarDado_repararMuralha.setFocusPainted(false);
        botao_RodarDado_repararMuralha.setForeground(Color.white);
        botao_RodarDado_repararMuralha.setPreferredSize(new Dimension(120,80));
        botao_RodarDado_repararMuralha.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_repararMuralha.setBackground(Color.decode("#104919"));
        painelAcao_repararMuralha.add(botao_RodarDado_repararMuralha, BorderLayout.AFTER_LAST_LINE);
        painelAcao_repararMuralha.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelAvancoInimigo(){
        painelAvancoInimigo.removeAll();
 
        painelAvancoInimigo.setLayout(new BorderLayout());
        painelAvancoInimigo.setBackground(Color.decode("#405972"));
        painelAvancoInimigo.setPreferredSize(new Dimension(200,300));
        
        // Descobrir quais os DRMS associados a este evento e fazer o seu display
        String msg = "<html>";
        if(m.getCartaAtual() != null){
            Carta cartaVirada = m.getCartaAtual();
            Evento eventoAtual = m.eventoAtual(cartaVirada);
            if(eventoAtual.getInimigosDoEvento().isEmpty()){ // Nenhuma unidade inimiga se deve mover quando existem ataques de trebuchets (não necessita de filtração, porque nesses casos, a lista já se encontra vazia de qualquer forma
                msg += ("De acordo com esta carta, nenhum inimigo avançará nenhuma posição neste turno.");
            }else{
            for(Inimigo i : eventoAtual.getInimigosDoEvento()){
                msg += ("O inimigo " + i + " encontra-se agora na localização " + i.getLocal() + ".<br/>");
            }
        }
        
            msg += "</html>";
        }

        //
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(msg);
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
      
        
        botao_Continuar_AvancoInimigo.setBorderPainted(false);
        botao_Continuar_AvancoInimigo.setFocusPainted(false);
        botao_Continuar_AvancoInimigo.setForeground(Color.white);
        botao_Continuar_AvancoInimigo.setPreferredSize(new Dimension(120,80));
        botao_Continuar_AvancoInimigo.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_AvancoInimigo.setBackground(Color.decode("#104919"));
        painelAvancoInimigo.add(botao_Continuar_AvancoInimigo, BorderLayout.AFTER_LAST_LINE);
        painelAvancoInimigo.add(label_Msg, BorderLayout.CENTER);
    }
    
    private void configuraPainelDRMS(){
        boolean afetaInimigos = false;
        painelDRMS.removeAll();
 
        painelDRMS.setLayout(new BorderLayout());
        painelDRMS.setBackground(Color.decode("#405972"));
        painelDRMS.setPreferredSize(new Dimension(200,300));
        
        // Descobrir quais os DRMS associados a este evento e fazer o seu display
        String msg = "<html>";
        if(m.getCartaAtual() != null){
            Carta cartaVirada = m.getCartaAtual();
            Evento eventoAtual = m.eventoAtual(cartaVirada);
            if(m.temDRM(eventoAtual)){ // SE O EVENTO POSSUI DRMs
                
                
                List<DRM> drms = new ArrayList<>(m.getDRMS(eventoAtual));
                for(DRM drm : drms){
                    if(drm.isValida()){
                        msg += ("Este evento concedeu-lhe a seguinte DRM: " + drm + ".<br/>");
                        afetaInimigos = true;
                    }
                        
                }      
            }else
                msg += ("Este evento não lhe concedeu qualquer DRM.");
            
            if(!afetaInimigos) msg = ("<html>Este evento não lhe concedeu qualquer DRM.");
            
            msg += "</html>";
        }

        //
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(msg);
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
      
        //botao_Continuar_DRMS = new JButton("Continuar >>");
        botao_Continuar_DRMS.setBorderPainted(false);
        botao_Continuar_DRMS.setFocusPainted(false);
        botao_Continuar_DRMS.setForeground(Color.white);
        botao_Continuar_DRMS.setPreferredSize(new Dimension(120,80));
        botao_Continuar_DRMS.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_DRMS.setBackground(Color.decode("#104919"));
        painelDRMS.add(botao_Continuar_DRMS, BorderLayout.AFTER_LAST_LINE);
        painelDRMS.add(label_Msg, BorderLayout.CENTER);
        
        painelDRMS.repaint();
        painelDRMS.revalidate();
    }
    
    private void configuraPainelEventos(){
        painelEventos.removeAll();
        painelEventos.setLayout(new BorderLayout());
        painelEventos.setBackground(Color.decode("#405972"));
        painelEventos.setPreferredSize(new Dimension(200,300));
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText(m.getMensagemParaJogador());
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
      
        //botao_Continuar_Eventos = new JButton("Continuar >>");
        botao_Continuar_Eventos.setBorderPainted(false);
        botao_Continuar_Eventos.setFocusPainted(false);
        botao_Continuar_Eventos.setForeground(Color.white);
        botao_Continuar_Eventos.setPreferredSize(new Dimension(120,80));
        botao_Continuar_Eventos.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_Eventos.setBackground(Color.decode("#104919"));
        painelEventos.add(botao_Continuar_Eventos, BorderLayout.AFTER_LAST_LINE);
        painelEventos.add(label_Msg, BorderLayout.CENTER);
    }
    private void configuraPainelRodarDado(){
        painelRodarDado.removeAll();
        painelRodarDado.setLayout(new BorderLayout());
        painelRodarDado.setBackground(Color.decode("#405972"));
        painelRodarDado.setPreferredSize(new Dimension(200,300));
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        int resultado = m.getUltimoResultadoDoDado();
        if(resultado > Constantes.SOLDADOS_EM_LINHAS_INIMIGAS_SEM_SORTE.getValor()){
            // TUDO CORREU BEM
            label_Msg.setText("O Resultado do dado foi " + m.getUltimoResultadoDoDado() + ". Os seus soldados continuam em segurança!");
        }else{
            // SOLDADOS CAPTURADOS
            label_Msg.setText("O Resultado do dado foi " + m.getUltimoResultadoDoDado() + ". Os seus soldados foram capturados!");
        }
        

        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        
        
        
        
        botao_Continuar_RodarDado.setBorderPainted(false);
        botao_Continuar_RodarDado.setFocusPainted(false);
        botao_Continuar_RodarDado.setForeground(Color.white);
        botao_Continuar_RodarDado.setPreferredSize(new Dimension(120,80));
        botao_Continuar_RodarDado.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_RodarDado.setBackground(Color.decode("#104919"));
        painelRodarDado.add(botao_Continuar_RodarDado, BorderLayout.AFTER_LAST_LINE);
        painelRodarDado.add(label_Msg, BorderLayout.CENTER);
        
    }
    private void configuraPainelSoldadosSeguros(){
        /*
            Este painel é composto por:
                - 1 bloco de texto informativo em cima - label_Mensagem
                - 1 botão em baixo que permite avançar para a próxima secção do jogo - botao_Continuar
        
        */
        painelSoldadosSeguros.setLayout(new BorderLayout());
        painelSoldadosSeguros.setBackground(Color.decode("#405972"));
        painelSoldadosSeguros.setPreferredSize(new Dimension(200,300));
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("Não tem nenhum soldado em linhas inimigas. Todos estão em segurança :)");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        
        botao_Continuar_SoldadosSeguros = new JButton("Virar Carta >>");
        botao_Continuar_SoldadosSeguros.setBorderPainted(false);
        botao_Continuar_SoldadosSeguros.setFocusPainted(false);
        botao_Continuar_SoldadosSeguros.setForeground(Color.white);
        botao_Continuar_SoldadosSeguros.setPreferredSize(new Dimension(120,80));
        botao_Continuar_SoldadosSeguros.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar_SoldadosSeguros.setBackground(Color.decode("#104919"));
        painelSoldadosSeguros.add(botao_Continuar_SoldadosSeguros, BorderLayout.AFTER_LAST_LINE);
        painelSoldadosSeguros.add(label_Msg, BorderLayout.CENTER);
    }    
    private void configuraPainelSoldadosEmLinhasInimigas(){
        /*
            Este painel é composto por:
                - 1 bloco de texto informativo em cima - label_Mensagem
                - 1 botão em baixo que permite avançar para a próxima secção do jogo - botao_Continuar
        
        */
        painelSoldadosEmLinhasInimigas.setLayout(new BorderLayout());
        painelSoldadosEmLinhasInimigas.setBackground(Color.decode("#405972"));
        painelSoldadosEmLinhasInimigas.setPreferredSize(new Dimension(200,300));
        
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("Tem soldados em linhas inimigas. Rode o dado para determinar o seu destino...");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
      
        botao_RodarDado_SoldadosEmLinhasInimigas = new JButton("Rodar o Dado >>");
        botao_RodarDado_SoldadosEmLinhasInimigas.setBorderPainted(false);
        botao_RodarDado_SoldadosEmLinhasInimigas.setFocusPainted(false);
        botao_RodarDado_SoldadosEmLinhasInimigas.setForeground(Color.white);
        botao_RodarDado_SoldadosEmLinhasInimigas.setPreferredSize(new Dimension(120,80));
        botao_RodarDado_SoldadosEmLinhasInimigas.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_RodarDado_SoldadosEmLinhasInimigas.setBackground(Color.decode("#104919"));
        painelSoldadosEmLinhasInimigas.add(botao_RodarDado_SoldadosEmLinhasInimigas, BorderLayout.AFTER_LAST_LINE);
        painelSoldadosEmLinhasInimigas.add(label_Msg, BorderLayout.CENTER);
    } 
    private void configuraPainelInfo(){

        /*
            Este painel é composto por:
                - 1 bloco de texto informativo em cima - label_Mensagem
                - 1 botão em baixo que permite avançar para a próxima secção do jogo - botao_Continuar
        
        */
        painelInfo.setLayout(new BorderLayout());
        painelInfo.setBackground(Color.decode("#405972"));
        painelInfo.setPreferredSize(new Dimension(200,300));
        
        label_Mensagem = new JLabel("", SwingConstants.CENTER);
        label_Mensagem.setText(m.getMensagemParaJogador());
        label_Mensagem.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Mensagem.setForeground(Color.white);
        
        botao_Continuar = new JButton("Continuar >>");
        botao_Continuar.setBorderPainted(false);
        botao_Continuar.setFocusPainted(false);
        botao_Continuar.setForeground(Color.white);
        botao_Continuar.setPreferredSize(new Dimension(120,80));
        botao_Continuar.setFont(new Font("Serif", Font.PLAIN, 30));
        botao_Continuar.setBackground(Color.decode("#104919"));
        painelInfo.add(botao_Continuar, BorderLayout.AFTER_LAST_LINE);
        painelInfo.add(label_Mensagem, BorderLayout.CENTER);
    }
    private void configuraPainelAcoes(){
        List<JButton> botoesDisponiveis;
        List<Acao> acoesDisponiveis = new ArrayList<>();

        /*
            Este painel vai mostrar a seguinte informação:
                - Mensagem inicial a introduzir a escolha de ações
                - Nr. de APAs Disponíveis
                - Lista de Ações - JRadioButton (dentro do seu próprio painel interno) - painelAcoes2
        */
        painelAcoes.removeAll();
        painelAcoes.setLayout(new BorderLayout());
        painelAcoes.setBackground(Color.decode("#405972"));
        JLabel label_Msg = new JLabel("", SwingConstants.CENTER);
        label_Msg.setText("<html><center>Por favor, selecione uma das ações abaixo para aplicar!</center></html>");
        label_Msg.setFont(new Font("Serif", Font.PLAIN, 30));
        label_Msg.setForeground(Color.white);
        
        
        
        // PAINEL DE ACOES 2 - LISTA DAS AÇÕES DISPONÍVEIS
        painelAcoes2.setBackground(new Color(0,0,0,0)); // TRANSPARENTE (r, g, b, a <- opacidade)
        painelAcoes2.removeAll();
        // Descobrir a lista de ações permitidas pelo Evento Atual
            // Descobrir qual a carta atual
        Carta cartaAtual = m.getCartaAtual();
        if(cartaAtual != null){
            // Nr. de Ações Disponíveis
            label_acoesDisponiveis.setText("Ações Disponíveis: " + m.eventoAtual(cartaAtual).getAPA() + " para o evento " + m.eventoAtual(cartaAtual));
            label_acoesDisponiveis.setFont(new Font("Serif", Font.PLAIN, 30));
            label_acoesDisponiveis.setForeground(Color.decode("#44ff76"));
            
            
            Evento eventoAtual = m.eventoAtual(cartaAtual);
            acoesDisponiveis.addAll(eventoAtual.getAcoesPermitidas());   
            //System.out.println("11111" + eventoAtual.getAcoesPermitidas());
        }
   
        // Inicializar a lista dos botões disponíveis com a lista recebida de uma função
        // que vai cruzar os botões recebidos com a lista de ações disponíveis e fazer a filtração
        botoesDisponiveis = new ArrayList<>(getBotoesDisponiveis(acoesDisponiveis));
        
        for(JButton botao : botoesDisponiveis){
             botao.setVerticalTextPosition(SwingConstants.TOP);
             botao.setHorizontalTextPosition(SwingConstants.CENTER);
             painelAcoes2.add(botao);
        }
        
        painelAcoes2.revalidate();
        painelAcoes2.repaint();
        // Fazer as adições ao painel principal
        painelAcoes.add(label_Msg, BorderLayout.NORTH);
        painelAcoes.add(label_acoesDisponiveis, BorderLayout.CENTER);
        painelAcoes.add(painelAcoes2, BorderLayout.SOUTH);
        
        
        painelAcoes.revalidate();
        painelAcoes.repaint();
    }
    
    
          
    // Faz o cruzamento entre os botões disponíveis e a lista recebida de inimigos disponíveis e retorna um resultado filtrado dessa lista
    List<JButton> getBotoesDeAtaqueDisponiveis(List<Inimigo> inimigos){
        List<JButton> botoesFiltrados = new ArrayList<>();
        
        for(Inimigo i : inimigos){
            if(i instanceof Escada)
                botoesFiltrados.add(botao_Ataque_Escadas);
            else if(i instanceof Torre)
                botoesFiltrados.add(botao_Ataque_Torres);
            else if(i instanceof Ariete)
                botoesFiltrados.add(botao_Ataque_Arietes);
        }
        
        botoesFiltrados.add(botao_Ataque_Nenhum);
        return botoesFiltrados;
    }
    
    
    // Faz o cruzamento entre os botões disponíveis e a lista recebida de ações disponíveis e retorna um resultado filtrado dessa lista
    List<JButton> getBotoesDisponiveis(List<Acao> acoes){
        List<JButton> botoesFiltrados = new ArrayList<>();
        
        if(m.getFortaleza().faccoesFatais(2)){ // Se estiverem 2 facções em Close Combat
            botoesFiltrados.add(botao_AtaqueDeCloseCombat);
            botoesFiltrados.add(botao_NaoRealizarMaisAcoes);
            return botoesFiltrados;
        }
        
        for(Acao a : acoes){
            if(a instanceof AtaqueDeAguaFervente)
                botoesFiltrados.add(botao_AtaqueDeAguaFervente);
            else if(a instanceof AtaqueDeArqueiros)
                botoesFiltrados.add(botao_AtaqueDeArqueiros);
            else if(a instanceof AtaqueDeCloseCombat)
                botoesFiltrados.add(botao_AtaqueDeCloseCombat);
            else if(a instanceof MotivarTropas)
                botoesFiltrados.add(botao_MotivarTropas);
            else if(a instanceof MovimentarSoldadosNoTunel)
                botoesFiltrados.add(botao_MovimentarSoldadosNoTunel);
            else if(a instanceof Raid)
                botoesFiltrados.add(botao_Raid);
            else if(a instanceof RepararMuralha)
                botoesFiltrados.add(botao_RepararMuralha);
            else if(a instanceof Sabotagem)
                botoesFiltrados.add(botao_Sabotagem);
        }
        
        botoesFiltrados.add(botao_NaoRealizarMaisAcoes);
        return botoesFiltrados;
}
    
    
    // Troca entre os vários Paineis de CardLayout
    public void trocarPainel(String painelEscolhido){
        label_Mensagem.setText(m.getMensagemParaJogador());
        cl.show(painelCentroBaixo, painelEscolhido);
        //m.notificaAlteracao();
        
    }
    
    private void configuraDia(){
        System.out.println("DIA UPDATE 0: " + getM().getDia());
        // jLabel que indica o dia atual
        label_dia.setAlignmentX(CENTER_ALIGNMENT);
        label_dia.setToolTipText("DIA: " + m.getDia());
        Icon iconeEscolhido = null;
        
        switch(m.getDia()){
            case 1: iconeEscolhido = icon_dia1;
                break;
            case 2: iconeEscolhido = icon_dia2;
                break;
            case 3: iconeEscolhido = icon_dia3;
                break;
        }
        
        
        label_dia.setIcon(iconeEscolhido);
    }
      
    private void configuraMoralDoPovo() {
        // jLabel que indica a moral da população/povo    
        label_moralDoPovo.setAlignmentX(CENTER_ALIGNMENT);
        label_moralDoPovo.setToolTipText("Moral da População: " + m.getMoralDoPovo());
        int moral = m.getMoralDoPovo();
        Icon iconeEscolhido = null;
        switch(moral){
            case 4: iconeEscolhido = icon_smileMuitoFeliz;
                break;
            case 3: iconeEscolhido = icon_smileBemFeliz;
                break;
            case 2: iconeEscolhido = icon_smileFeliz;
                break;
            case 1: iconeEscolhido = icon_smileTriste;
                break;
            case 0: iconeEscolhido = icon_smileZangado;
                break;
        }
        
        label_moralDoPovo.setIcon(iconeEscolhido);
    }
    
    private void configuraNrCatapultas(){
        // jLabel que indica o nível atual dos suprimentos (supplies)
        label_nrCatapultas.setAlignmentX(CENTER_ALIGNMENT);
        label_nrCatapultas.setToolTipText("Nr. De Catapultas: " + m.contaCatapultas());
        Icon iconeEscolhido = null;
        switch(m.contaCatapultas()){
            case 3: iconeEscolhido = icon_catapulta3;
                break;
            case 2: iconeEscolhido = icon_catapulta2;
                break;
            case 1: iconeEscolhido = icon_catapulta1;
                break;
        }
        
        label_nrCatapultas.setIcon(iconeEscolhido);
    }
    
    
    private void configuraSuprimentosFurtados(){
         // jLabel que indica o nível atual dos suprimentos (supplies)
        label_nivelDosSuprimentosFurtados.setAlignmentX(CENTER_ALIGNMENT);
        label_nivelDosSuprimentosFurtados.setToolTipText("Suprimentos Furtados: " + m.getSuprimentosFurtados());
        Icon iconeEscolhido = null;
        switch(m.getSuprimentosFurtados()){
            case 2: iconeEscolhido = icon_mochila2;
                break;
            case 1: iconeEscolhido = icon_mochila1;
                break;
            case 0: iconeEscolhido = icon_mochila0;
                break;
        }
        
        label_nivelDosSuprimentosFurtados.setIcon(iconeEscolhido);
    }
    
    private void configuraNivelDosSuprimentos() {
        // jLabel que indica o nível atual dos suprimentos (supplies)
        label_nivelDosSuprimentos.setAlignmentX(CENTER_ALIGNMENT);
        label_nivelDosSuprimentos.setToolTipText("Nível dos Suprimentos: " + m.getSuprimentos());
        Icon iconeEscolhido = null;
        switch(m.getSuprimentos()){
            case 4: iconeEscolhido = icon_suprimentos4;
                break;
            case 3: iconeEscolhido = icon_suprimentos3;
                break;
            case 2: iconeEscolhido = icon_suprimentos2;
                break;
            case 1: iconeEscolhido = icon_suprimentos1;
                break;
            case 0: iconeEscolhido = icon_suprimentos0;
                break;
        }
        
        label_nivelDosSuprimentos.setIcon(iconeEscolhido);
    }

    private void configuraForcaDaMuralha() {
        // jLabel que indica a força atual da muralha
        label_forcaDaMuralha.setAlignmentX(CENTER_ALIGNMENT);
        label_forcaDaMuralha.setToolTipText("Força da Muralha: " + m.getForcaDaMuralha());
        
        Icon iconeEscolhido = null;
        switch(m.getForcaDaMuralha()){
            case 4: iconeEscolhido = icon_seguranca4;
                break;
            case 3: iconeEscolhido = icon_seguranca3;
                break;
            case 2: iconeEscolhido = icon_seguranca2;
                break;
            case 1: iconeEscolhido = icon_seguranca1;
                break;
            case 0: iconeEscolhido = icon_seguranca0;
                break;
        }
        
        label_forcaDaMuralha.setIcon(iconeEscolhido);
    }

    private void configuraLocalDosSoldados() {
        // jLabel que indica a localização/estado dos soldados
        label_localDosSoldados.setAlignmentX(Component.CENTER_ALIGNMENT);
        label_localDosSoldados.setAlignmentY(Component.CENTER_ALIGNMENT);
        
        String local; // "Na Fortaleza" || "No Túnel" || "Em Linhas Inimigas"
        int l = m.getPosDosSoldados();
        Icon iconeEscolhido;
        
        if(l == 0){
            local = "No Castelo"; 
            iconeEscolhido = icon_fortaleza;
        }   
        else if(l < 3){
            local = "No Túnel";
            if(l == 1)
                iconeEscolhido = icon_tunel1;
            else
                iconeEscolhido = icon_tunel2;
        }      
        else{
            local ="Em Linhas Inimigas";
            iconeEscolhido = icon_soldadoTroia;
        }
            
        
        local += " (Local: " + l + ")";
        label_localDosSoldados.setToolTipText("Soldados: " + local);
        label_localDosSoldados.setIcon(iconeEscolhido);
    }
    
    public void addListener(ActionListener controlador, JButton botao){
        botao.addActionListener(controlador);	
    }
    
    
    
    // GETTERS & SETTERS

    public JButton getBotao_Continuar() {
        return botao_Continuar;
    }

    public CardLayout getCl() {
        return cl;
    }

    public JButton getBotao_RodarDado_SoldadosEmLinhasInimigas() {
        return botao_RodarDado_SoldadosEmLinhasInimigas;
    }

    public JButton getBotao_Continuar_SoldadosSeguros() {
        return botao_Continuar_SoldadosSeguros;
    }

    public JButton getBotao_Continuar_RodarDado() {
        return botao_Continuar_RodarDado;
    }

    public JButton getBotao_Continuar_Eventos() {
        return botao_Continuar_Eventos;
    }

    public JButton getBotao_Continuar_DRMS() {
        return botao_Continuar_DRMS;
    }

    public JButton getBotao_Continuar_AvancoInimigo() {
        return botao_Continuar_AvancoInimigo;
    }

    public JButton getBotao_AtaqueDeAguaFervente() {
        return botao_AtaqueDeAguaFervente;
    }

    public JButton getBotao_AtaqueDeArqueiros() {
        return botao_AtaqueDeArqueiros;
    }

    public JButton getBotao_AtaqueDeCloseCombat() {
        return botao_AtaqueDeCloseCombat;
    }

    public JButton getBotao_MotivarTropas() {
        return botao_MotivarTropas;
    }

    public JButton getBotao_MovimentarSoldadosNoTunel() {
        return botao_MovimentarSoldadosNoTunel;
    }

    public JButton getBotao_Raid() {
        return botao_Raid;
    }

    public JButton getBotao_RepararMuralha() {
        return botao_RepararMuralha;
    }

    public JButton getBotao_Sabotagem() {
        return botao_Sabotagem;
    }

    public JButton getBotao_NaoRealizarMaisAcoes() {
        return botao_NaoRealizarMaisAcoes;
    }

    public JButton getBotao_RodarDado_repararMuralha() {
        return botao_RodarDado_repararMuralha;
    }

    public JButton getBotao_Continuar_repararMuralha() {
        return botao_Continuar_repararMuralha;
    }

    public JButton getBotao_Continuar_sabotagem() {
        return botao_Continuar_sabotagem;
    }

    public JButton getBotao_RodarDado_sabotagem() {
        return botao_RodarDado_sabotagem;
    }

    public JButton getBotao_Continuar_raid() {
        return botao_Continuar_raid;
    }

    public JButton getBotao_RodarDado_raid() {
        return botao_RodarDado_raid;
    }

    public JButton getBotao_Continuar_motivarTropas() {
        return botao_Continuar_motivarTropas;
    }

    public JButton getBotao_RodarDado_motivarTropas() {
        return botao_RodarDado_motivarTropas;
    }

    public JButton getBotao_RodarDado_motivarTropasBonus() {
        return botao_RodarDado_motivarTropasBonus;
    }

    public JButton getBotao_Continuar_movimentarSoldados() {
        return botao_Continuar_movimentarSoldados;
    }

    public JButton getBotao_Movimentar_movimentarSoldadosFree() {
        return botao_Movimentar_movimentarSoldadosFree;
    }

    public JButton getBotao_Movimentar_movimentarSoldadosFast() {
        return botao_Movimentar_movimentarSoldadosFast;
    }

    public JButton getBotao_Ataque_Torres() {
        return botao_Ataque_Torres;
    }

    public JButton getBotao_Ataque_Escadas() {
        return botao_Ataque_Escadas;
    }

    public JButton getBotao_Ataque_Arietes() {
        return botao_Ataque_Arietes;
    }

    public JButton getBotao_Ataque_Nenhum() {
        return botao_Ataque_Nenhum;
    }

    public Inimigo getInimigoAtacado() {
        return inimigoAtacado;
    }

    public void setInimigoAtacado(Inimigo inimigoAtacado) {
        this.inimigoAtacado = inimigoAtacado;
    }

    public JButton getBotao_Continuar_Ataques() {
        return botao_Continuar_Ataques;
    }

    public JButton getBotao_Continuar_FimDoDia() {
        return botao_Continuar_FimDoDia;
    }

    public JButton getBotao_Voltar_FimDoJogo() {
        return botao_Voltar_FimDoJogo;
    }
    
    
    

    public String getTipoDeAtaque() {
        return tipoDeAtaque;
    }

    public void setTipoDeAtaque(String tipoDeAtaque) {
        
        this.tipoDeAtaque = tipoDeAtaque;
        configuraPainelAtaques();
    }

    public JMenuItem getMenuItem_sair() {
        return menuItem_sair;
    }

    public JMenuItem getMenuItem_sobre() {
        return menuItem_sobre;
    }

    public JMenuItem getMenuItem_guardar() {
        return menuItem_guardar;
    }

    public Mundo getM() {
        return m;
    }

    public void setM(Mundo m) {
        this.m = m;
    }
    
    
     
          

}
