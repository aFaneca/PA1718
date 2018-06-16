/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Constantes;
import Lógica.Mundo;
import java.io.Serializable;
import javax.swing.JFrame;
import ui.GUI.JogoView;

/**
 *
 * @author me
 */
public class AguardaLeituraDeInfo extends EstadosAdapter implements Serializable{
    Mundo m;
   
    
    public AguardaLeituraDeInfo(Mundo mundo, String mensagem) {
        super(mundo);
        this.m = mundo;
    }
    
    public IEstados virarCarta(){
        
        return new AguardaCarta(m);
    }
    
    
    @Override
    public IEstados testaSorteDosSoldados(){
         int resultado = m.sorteDosSoldados();
            if(resultado > Constantes.SOLDADOS_EM_LINHAS_INIMIGAS_SEM_SORTE.getValor()){
               // TUDO CORREU BEM
           }else{
               m.soldadosCapturados();
           }
            
            return this;
    }
    
    @Override
    public IEstados verificarSoldados(JogoView view){ // Verificar se existem soldados em linhas inimigas; Se sim, apresentar botão para testar a sua sorte
        if(m.soldadosEmLinhasInimigas())
            view.trocarPainel("painelSoldadosEmLinhasInimigas");
        else
            view.trocarPainel("painelSoldadosSeguros");
        
        
        
        return this;
    }
    
    @Override
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
    

}
