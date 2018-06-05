/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;


import Lógica.Carta;
import Lógica.Mundo;
import java.awt.BorderLayout;
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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import sun.java2d.SunGraphicsEnvironment;

/**
 *
 * @author me
 */




public class JogoView extends JFrame implements Observer{
    Mundo m;
    JLabel label_dia, label_forcaDaMuralha, label_nivelDosSuprimentos, label_moralDoPovo, label_localDosSoldados;
    JMenuBar menuBar_menu;
    JMenu menu_jogo, menu_sobre;
    JMenuItem menuItem_sair, menuItem_sobre;
    Icon icon_guardar, icon_carregar, icon_sair, icon_dia1, icon_dia2, icon_dia3, icon_suprimentos0, icon_suprimentos1, icon_suprimentos2, icon_suprimentos3, icon_suprimentos4;
    Icon icon_smileMuitoFeliz, icon_smileBemFeliz, icon_smileFeliz, icon_smileTriste, icon_smileZangado;
    Icon icon_soldadoTroia, icon_tunel1, icon_tunel2, icon_fortaleza, icon_seguranca0, icon_seguranca1, icon_seguranca2, icon_seguranca3, icon_seguranca4;
    JPanel painelEsquerda, painelDireita, painelTopo, painelBase, painelCentro;
    JLabel img_cartaAtual, img_carta0, img_carta1, img_carta2, img_carta3, img_carta4, img_carta5, img_carta6, img_carta7;
    JPanel desenhoDosProgressosInimigos;

    
    public JogoView(Mundo m){
        this.setTitle("9 Cards Siege - Jogo");
        this.setResizable(true); // Permite que a janela seja redimensionada
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(2048,600));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(false);
        this.setLayout(new BorderLayout());
        this.m = m;
        
        
        
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
        img_carta0  = new JLabel(new ImageIcon("imagens/cartas/0.png"));
        img_carta1 = new JLabel(new ImageIcon("imagens/cartas/1.JPG"));
        img_carta2 = new JLabel(new ImageIcon("imagens/cartas/2.JPG"));
        img_carta3 = new JLabel(new ImageIcon("imagens/cartas/3.JPG"));
        img_carta4 = new JLabel(new ImageIcon("imagens/cartas/4.JPG"));
        img_carta5 = new JLabel(new ImageIcon("imagens/cartas/5.JPG"));
        img_carta6 = new JLabel(new ImageIcon("imagens/cartas/6.JPG"));
        img_carta7 = new JLabel(new ImageIcon("imagens/cartas/7.JPG"));
        
        
        // Inicialização das jLabels
        label_dia = new JLabel();
        label_forcaDaMuralha = new JLabel();
        label_localDosSoldados = new JLabel();
        label_moralDoPovo = new JLabel();
        label_nivelDosSuprimentos = new JLabel();
        
        configuraMenu();
                
        painelEsquerda = new JPanel();
        configuraPainelEsquerda();
        painelDireita = new JPanel();
        configuraPainelDireita();
        painelTopo = new JPanel();
        configuraPainelTopo();
        painelBase = new JPanel();
        configuraPainelBase();
        desenhoDosProgressosInimigos = new DesenhoDosProgressosInimigos(m);
        painelCentro = new JPanel();
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
        menuItem_sobre.addActionListener((ActionEvent evento) -> {
            mostraPopup("Sobre o Jogo\nAutores: Amadeus Alves e António Faneca");
        });
        
        
        
        menuItem_sair = new JMenuItem("Sair", icon_sair);
        menuItem_sair.setMnemonic(KeyEvent.VK_S);
        menuItem_sair.setToolTipText("Sair da Aplicação");
        menuItem_sair.addActionListener((ActionEvent evento) -> {
            System.exit(0);
        });
        
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
        configuraDia();
        configuraMoralDoPovo();
        configuraNivelDosSuprimentos();
        configuraForcaDaMuralha();
        configuraLocalDosSoldados();
        configuraCartaAtual();
        desenhoDosProgressosInimigos.repaint();
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

        // Adiciona elementos ao painel
        
        painelDireita.add( Box.createVerticalGlue() ); // Para centrar os elementos verticalmente
        painelDireita.add(label_dia);
        //painelDireita.add(new JSeparator(SwingConstants.HORIZONTAL)); // Adiciona um separador horizontal
        painelDireita.add(label_forcaDaMuralha);
        painelDireita.add(label_nivelDosSuprimentos);
        painelDireita.add(label_moralDoPovo);
        painelDireita.add(label_localDosSoldados);
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
    }
    
    private void configuraPainelBase() {
        painelBase.setBackground(Color.decode("#2c3e50"));
    }

    private void configuraPainelCentro() {
        painelCentro.setLayout(new BorderLayout());
        painelCentro.add(desenhoDosProgressosInimigos, BorderLayout.CENTER);
        desenhoDosProgressosInimigos.setBackground(Color.decode("#34495e"));
        painelCentro.setBackground(Color.decode("#34495e"));

    }

    
    
    private void configuraDia(){
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

}
