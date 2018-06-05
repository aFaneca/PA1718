/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.GUI;

import Lógica.Mundo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author me
 */
public class DesenhoDosProgressosInimigos extends JPanel{
    
    Mundo m;
    int uDistancia = 50; // Unidades de Distância
    int diametro = 70; // Diâmetro dos círculos
    int comp_CloseCombat_X = 650;
    int comp_CloseCombat_Y = 20;
    int comp_CloseCombat_Comp = 170;
    int comp_CloseCombat_Alt = 80;
    
    int comp_Escadas_X = comp_CloseCombat_X - uDistancia;
    int comp_Escadas_Y = comp_CloseCombat_Y + comp_CloseCombat_Alt  + uDistancia;
    
    int comp_Arietes_X = (comp_CloseCombat_X) + comp_CloseCombat_Comp / 2; // POSIÇÃO CENTRAL
    int comp_Arietes_Y = comp_Escadas_Y + (int) Math.round(Math.sqrt(uDistancia));
        
    int comp_Torres_X = comp_CloseCombat_X + comp_CloseCombat_Comp + uDistancia;
    int comp_Torres_Y = comp_Escadas_Y;
    
    int posEscadas, posArietes, posTorres;
    
    public DesenhoDosProgressosInimigos(Mundo m){
        this.m = m;
        
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        g.setColor(Color.red);
        g.setFont(new Font("Batang", Font.PLAIN, 20)); 
        g.drawRect(comp_CloseCombat_X, comp_CloseCombat_Y, comp_CloseCombat_Comp, comp_CloseCombat_Alt);
        g.setColor(Color.white);
        //g.drawString("Close Combat", (comp_CloseCombat_Comp + comp_CloseCombat_X)/2, (comp_CloseCombat_Y) + comp_CloseCombat_Comp/2);
        
       
        g.drawLine(comp_CloseCombat_X, comp_CloseCombat_Y + comp_CloseCombat_Alt, comp_Escadas_X, comp_Escadas_Y); // LINHA PARA A ESQUERDA
        g.drawLine(comp_Arietes_X, comp_CloseCombat_Y + comp_CloseCombat_Alt, comp_Arietes_X, comp_Arietes_Y); // LINHA PARA O CENTRO
        g.drawLine(comp_CloseCombat_X + comp_CloseCombat_Comp, comp_CloseCombat_Y + comp_CloseCombat_Alt, comp_Torres_X, comp_Torres_Y); // LINHA PARA A DIREITA
        
        
        g.setColor(Color.BLACK);
        // LOCAL 1
        g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y, diametro, diametro);  
        g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y, diametro, diametro);
        g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y, diametro, diametro);
        
        g.drawLine(comp_Escadas_X - 15, comp_Escadas_Y + diametro, comp_Escadas_X - 15, comp_Escadas_Y + diametro + uDistancia); 
        g.drawLine(comp_Arietes_X, comp_Arietes_Y + diametro, comp_Arietes_X, comp_Arietes_Y + diametro + uDistancia); 
        g.drawLine(comp_Torres_X + 10, comp_Torres_Y + diametro, comp_Torres_X + 10, comp_Torres_Y + diametro + uDistancia); 
        
        // LOCAL 2
        g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro + uDistancia, diametro, diametro);
        g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro + uDistancia, diametro, diametro);
        g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro + uDistancia, diametro, diametro);
        
        g.drawLine(comp_Escadas_X - 15, comp_Escadas_Y + diametro * 2 + uDistancia, comp_Escadas_X - 15, comp_Escadas_Y + 2 * diametro + 2 * uDistancia); 
        g.drawLine(comp_Arietes_X, comp_Arietes_Y + diametro * 2 + uDistancia, comp_Arietes_X, comp_Arietes_Y + 2 * diametro + 2 * uDistancia); 
        g.drawLine(comp_Torres_X + 10, comp_Torres_Y + diametro * 2 + uDistancia, comp_Torres_X + 10, comp_Torres_Y + 2 * diametro + 2 * uDistancia); 
        
        // LOCAL 3
        g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro * 2 + uDistancia * 2, diametro, diametro);
        g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro * 2 + uDistancia * 2, diametro, diametro);
        g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro * 2 + uDistancia * 2, diametro, diametro);
        
        g.drawLine(comp_Escadas_X - 15, comp_Escadas_Y + 3 * diametro  + uDistancia * 2, comp_Escadas_X - 15, comp_Escadas_Y + 3 * diametro + 3 * uDistancia); 
        g.drawLine(comp_Arietes_X, comp_Arietes_Y + 3 * diametro  + uDistancia * 2, comp_Arietes_X, comp_Arietes_Y + 3 * diametro + 3 * uDistancia); 
        g.drawLine(comp_Torres_X + 10, comp_Torres_Y + 3 * diametro + uDistancia * 2, comp_Torres_X + 10, comp_Torres_Y + 3 * diametro + 3 * uDistancia); 
        
        // LOCAL 4
        g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro * 3 + uDistancia * 3, diametro, diametro);
        g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro * 3 + uDistancia * 3, diametro, diametro);
        g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro * 3 + uDistancia * 3, diametro, diametro);
    
    
        
        
        posTorres = m.getPosTorre();
        posEscadas = m.getPosEscadas();
        posArietes = m.getPosArietes();

        g.setColor(Color.decode("#10681E"));
        // FILL do elemento que contém a posição onde está cada inimigo
        switch(posEscadas){
            case 1: g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y, diametro, diametro);  
                break;
            case 2: g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro + uDistancia, diametro, diametro);
                break;
            case 3: g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro * 2 + uDistancia * 2, diametro, diametro);
                break;
            case 4: g.fillOval(comp_Escadas_X - uDistancia, comp_Escadas_Y + diametro * 3 + uDistancia * 3, diametro, diametro);
                break;
        }
        
        switch(posArietes){
            case 1: g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y, diametro, diametro);
                break;
            case 2: g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro + uDistancia, diametro, diametro);
                break;
            case 3: g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro * 2 + uDistancia * 2, diametro, diametro);
                break;
            case 4: g.fillOval(comp_Arietes_X - 35, comp_Arietes_Y  + diametro * 3 + uDistancia * 3, diametro, diametro);
                break;
        }
        
        switch(posTorres){
            case 1: g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y, diametro, diametro);
                break;
            case 2: g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro + uDistancia, diametro, diametro);
                break;
            case 3: g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro * 2 + uDistancia * 2, diametro, diametro);
                break;
            case 4: g.fillOval(comp_Torres_X - uDistancia / 2, comp_Torres_Y  + diametro * 3 + uDistancia * 3, diametro, diametro);
                break;
        }
        
        
        g.setColor(Color.white);
        // Rótulos de todos os componentes
        g.drawString("Close Combat", comp_CloseCombat_X + 20, comp_CloseCombat_Y + comp_CloseCombat_Alt/2 + 2);
        g.drawString("1", comp_Escadas_X - 20, comp_Escadas_Y + diametro/2 + 10);
        g.drawString("1", comp_Arietes_X - 5, comp_Arietes_Y + diametro/2 + 6);
        g.drawString("1", comp_Torres_X + 5, comp_Torres_Y + diametro/2 + 8);
        g.drawString("2", comp_Escadas_X - 20, comp_Escadas_Y +  diametro/2 + 10 + diametro + uDistancia);
        g.drawString("2", comp_Arietes_X - 5, comp_Arietes_Y + diametro/2 + 6 + diametro + uDistancia);
        g.drawString("2", comp_Torres_X + 5, comp_Torres_Y + diametro/2 + 8 + diametro + uDistancia);
        g.drawString("3", comp_Escadas_X - 20, comp_Escadas_Y +  diametro/2 + 10 + diametro * 2 + uDistancia * 2);
        g.drawString("3", comp_Arietes_X - 5, comp_Arietes_Y + diametro/2 + 6 + diametro * 2 + uDistancia * 2);
        g.drawString("3", comp_Torres_X + 5, comp_Torres_Y + diametro/2 + 8 + 2 * diametro + 2 * uDistancia);
        g.drawString("4", comp_Escadas_X - 20, comp_Escadas_Y +  diametro/2 + 10 + diametro * 3 + uDistancia * 3);
        g.drawString("4", comp_Arietes_X - 5, comp_Arietes_Y + diametro/2 + 6 + diametro * 3 + uDistancia * 3);
        g.drawString("4", comp_Torres_X + 5, comp_Torres_Y + diametro/2 + 8 + 3 * diametro + 3 * uDistancia);
    
    }
}