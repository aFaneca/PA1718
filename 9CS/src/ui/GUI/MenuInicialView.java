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
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author António Faneca
 */
public class MenuInicialView extends JFrame{
    JPanel painelInferior;
    JButton botao_iniciarJogo;
    JButton botao_sair;
    JButton botao_continuarJogo;
    JLabel logotipo;
    Icon icon_abrirFicheiro = new ImageIcon("imagens/icon_abrirFicheiro.png");
    Icon icon_adicionar = new ImageIcon("imagens/icon_adicionar.png");
    Icon icon_fechar = new ImageIcon("imagens/icon_fechar.png");
    
    public MenuInicialView(){
        this.setTitle("9 Cards Siege - Menu Inicial");
        this.setResizable(false); // Não permite que a janela seja redimensionada
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //painel = new JPanel(); // Cria o painel principal desta view
        setLayout(new BorderLayout(5,5)); // Gap vertical e horizontal: 5
        this.setVisible(false);
        
        // CONFIGURAÇÃO DA IMAGEM DE BACKGROUND
	JLabel background=new JLabel(new ImageIcon("imagens/logo.png"));
	add(background);
	//
        background.setLayout(new FlowLayout());
        
        botao_iniciarJogo = new JButton("Iniciar Jogo", icon_adicionar);
        botao_iniciarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_iniciarJogo.setPreferredSize(new Dimension(200,50));
        //botao_iniciarJogo.addActionListener(this);
        
        
        botao_continuarJogo = new JButton("Continuar Jogo", icon_abrirFicheiro);
        botao_continuarJogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_continuarJogo.setPreferredSize(new Dimension(200,50));
        //botao_continuarJogo.addActionListener(this);
        
        botao_sair = new JButton("Sair", icon_fechar);
        
        botao_sair.setFocusPainted(false);
        botao_sair.setBackground(new java.awt.Color(240,81,78));
        botao_sair.setForeground(java.awt.Color.WHITE);
        botao_sair.setFont(new Font("Tahoma", Font.BOLD, 12));
        
        
        //botao_sair.setBackground(java.awt.Color.LIGHT_GRAY);
        botao_sair.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao_sair.setPreferredSize(new Dimension(150,50));
        //botao_sair.addActionListener(this);
        
        background.add(botao_iniciarJogo);
        background.add(botao_continuarJogo);
        background.add(botao_sair);
        

        pack();


    }

    public JButton getBotao_iniciarJogo() {
        return botao_iniciarJogo;
    }

    public JButton getBotao_sair() {
        return botao_sair;
    }

    public JButton getBotao_continuarJogo() {
        return botao_continuarJogo;
    }
    
    
    
    
    public void addListener(ActionListener controlador, JButton botao){
        botao.addActionListener(controlador);	//need instance of controller before can add it as a listener
    }
    
    
    public void mostraErro(String mensagemDeErro){
        JOptionPane.showMessageDialog(this, mensagemDeErro, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
   
    
    
}
