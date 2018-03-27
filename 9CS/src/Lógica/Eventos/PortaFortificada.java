/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica.Eventos;

import Lógica.Evento;
import Lógica.Inimigo;
import java.util.List;

/**
 *
 * @author me
 */
public class PortaFortificada extends Evento{

    public PortaFortificada(int numero, List<Inimigo> inim){
        super(mundo, numero, inim);
        nome = "Porta Fortificada";
    }
    
}
