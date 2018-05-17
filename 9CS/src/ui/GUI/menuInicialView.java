/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;



import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author me
 */
public class menuInicialView extends JFrame implements ActionListener{
    JPanel painelInferior;
    JButton botao_iniciarJogo;
    JButton botao_sair;
    JButton botao_continuarJogo;
    BufferedImage wPic;
    JLabel logotipo;
    Icon icon_abrirFicheiro = new ImageIcon("imagens/icon_abrirFicheiro.png");
    Icon icon_adicionar = new ImageIcon("imagens/icon_adicionar.png");
    Icon icon_fechar = new ImageIcon("imagens/icon_fechar.png");
    
    public menuInicialView(){
        this.setTitle("9 Cards Siege - Menu Inicial");
        this.setResizable(false); // Não permite que a janela seja redimensionada
        //painel = new JPanel(); // Cria o painel principal desta view
        setLayout(new BorderLayout(5,5)); // Gap vertical e horizontal: 5
        
        // CONFIGURAÇÃO DA IMAGEM DE BACKGROUND
	JLabel background=new JLabel(new ImageIcon("imagens/logo.png"));
	add(background);
	//
        background.setLayout(new FlowLayout());
        
        botao_iniciarJogo = new JButton("Iniciar Jogo", icon_adicionar);
        botao_iniciarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_iniciarJogo.setPreferredSize(new Dimension(200,50));
        botao_iniciarJogo.addActionListener(this);
        
        
        botao_continuarJogo = new JButton("Continuar Jogo", icon_abrirFicheiro);
        botao_continuarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_continuarJogo.setPreferredSize(new Dimension(200,50));
        botao_continuarJogo.addActionListener(this);
        
        botao_sair = new JButton("Sair", icon_fechar);
        
        botao_sair.setFocusPainted(false);
        botao_sair.setBackground(new java.awt.Color(240,81,78));
        botao_sair.setForeground(java.awt.Color.WHITE);
        botao_sair.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        
        //botao_sair.setBackground(java.awt.Color.LIGHT_GRAY);
        botao_sair.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_sair.setPreferredSize(new Dimension(150,50));
        botao_sair.addActionListener(this);
        
        background.add(botao_iniciarJogo);
        background.add(botao_continuarJogo);
        background.add(botao_sair);
        
        //
        // Configuração do painel inferior (com os botões do menu)
//        painelInferior = new JPanel();
//        painelInferior.setLayout(new BoxLayout(painelInferior, BoxLayout.X_AXIS));
//        
//        botao_iniciarJogo = new JButton("Iniciar Jogo");
//        botao_iniciarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
//        botao_iniciarJogo.addActionListener(this);
//        
//        botao_sair = new JButton("Iniciar Jogo");
//        botao_sair.setAlignmentX(Component.CENTER_ALIGNMENT);
//        botao_sair.addActionListener(this);
//        
//        
//        
//        
//        painelInferior.add(botao_iniciarJogo);
//        painelInferior.add(botao_sair);
//        add("South", painelInferior);
        
        
       // setVisible(true);
        pack();
        
//        add("North", new JButton("North"));
//        add("South", new JButton("South"));
//        add("East", new JButton("East"));
//        add("West", new JButton("West"));
//        add("Center", new JButton("Center"));
//        
        
//        painel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        //painel.setBackground(Color.cyan);
//        //this.setContentPane(painel);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ao clicar no botão X na GUI, a app fecha
//        this.setSize(500, 300); // Tamanho da janela
//        
//        // Logotipo no topo
//        ImageIcon logotipo = new ImageIcon("imagens/logo.png");
//        JLabel logo = new JLabel("", logotipo, JLabel.CENTER);
//        logo.setBounds(0,0,400,142);
//        Image logoImg = logotipo.getImage();
//        Image logoResized = logoImg.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon logoFinal = new ImageIcon(logoResized);
//        logo.setIcon(logoFinal);
//        
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.insets = new Insets(0,10,10,0);
//        painel.add(logo, gbc);
//        
//        painel.add(Box.createRigidArea(new Dimension(0,10))); // Adiciona espaçamento entre elementos
//        
//        botao_iniciarJogo = new JButton("Iniciar Jogo");
//        botao_iniciarJogo.addActionListener(this);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.insets = new Insets(10,10,0,0);
//        painel.add(botao_iniciarJogo, gbc);
//        
//        painel.add(Box.createRigidArea(new Dimension(0,10))); // Adiciona espaçamento entre elementos
//         
//        botao_sair = new JButton("Sair");
//        botao_sair.addActionListener(this);
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        gbc.insets = new Insets(10,10,0,0);
//        painel.add(botao_sair, gbc);
//        
//
//        this.add(painel); // Adicionar o painel ao frame
//        
//       
//        //this.pack();

    }
    
    
    
    
    
    
    
    void mostraErro(String mensagemDeErro){
        JOptionPane.showMessageDialog(this, mensagemDeErro);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object origem = e.getSource();
         if(origem == (botao_sair)){
             System.exit(0);
         }
         else if(origem == (botao_iniciarJogo)){
             
         }
    }
    
    
}
