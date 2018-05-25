/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Ações.AtaqueDeArqueiros;
import Lógica.DRM;
import Lógica.Evento;
import Lógica.Eventos.*;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;

/**
 *
 * @author me
 */
public class AguardaCarta extends EstadosAdapter implements Serializable{
    Mundo m;
    public AguardaCarta(Mundo m){
        super(m);
        this.m = m;
    }
    

    
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
    
    
    
    @Override
    public IEstados avancaInimigos(Evento evento){
        for(Inimigo i : evento.getInimigosDoEvento()){
            i.setLocal(i.getLocal() - 1);
        }
        
        // O ESTADO SEGUINTE É AguardaSelecaoDeAcao
        return new AguardaSelecaoDeAcao(this.getMundo());
    }
    

    @Override
    public IEstados evento_AtaqueDeCatapulta(){
        m.getFortaleza().evento_AtaqueDeCatapulta();
        
        return this;
    }
    
    @Override
    public IEstados evento_Doenca(){
        m.getFortaleza().evento_Doenca();
        
        return this;
    }
    
    @Override
    public IEstados evento_SuprimentosEstragados(){
        m.getFortaleza().evento_SuprimentosEstragados();
        return this;
    }
    
    
    @Override
    public IEstados evento_MorteDeUmLider(){
        m.getFortaleza().evento_MorteDeUmLider();
        
        return this;
    }
    
    @Override
    public IEstados evento_CatapultaReparada(){
        m.alteraNrCatapultas(+1);
        
        return this;
    }
    
    @Override
    public IEstados proximoEstado(){
        
        
        return new AguardaSelecaoDeAcao(this.getMundo());
    }
}
