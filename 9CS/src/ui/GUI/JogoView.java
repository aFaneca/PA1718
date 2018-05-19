/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;


import Lógica.Mundo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author me
 */
public class JogoView extends JFrame implements Observer{
    //Mundo m;
    JLabel label_dia, label_forcaDaMuralha;
    JMenuBar menuBar_menu;
    JMenu menu_jogo, menu_sobre;
    JMenuItem menuItem_sair, menuItem_sobre;
    Icon icon_guardar = new ImageIcon("imagens/icon_guardar.png");
    Icon icon_carregar = new ImageIcon("images/icon_carregar.png");
    Icon icon_sair = new ImageIcon("imagens/icon_fechar.png");
    
    public JogoView(){
        this.setTitle("9 Cards Siege - Menu Inicial");
        this.setResizable(false); // Não permite que a janela seja redimensionada
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //painel = new JPanel(); // Cria o painel principal desta view
        //setLayout(new BorderLayout(5,5)); // Gap vertical e horizontal: 5
        setLayout(new FlowLayout());
        
        
        
        label_forcaDaMuralha = new JLabel();
        label_dia = new JLabel();
        
        
        
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
        //add(menuBar_menu);
        //add(label_forcaDaMuralha);
        //add(label_dia);
        this.setSize(new Dimension(800,600));
    }

    public JLabel getLabel_dia() {
        return label_dia;
    }

    public JLabel getLabel_forcaDaMuralha() {
        return label_forcaDaMuralha;
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
        Mundo m = (Mundo) arg;
        
        label_forcaDaMuralha.setText("Força da Muralha: " + m.getForcaDaMuralha());
        label_dia.setText("Dia: " + m.getDia());
        
        //this.setSize(new Dimension(800,600));
        
    }

    public void mostraPopup(String mensagemDeErro){

        JOptionPane.showMessageDialog(this, mensagemDeErro, "Mensagem do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
}
