/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Mundo;
import java.io.Serializable;

/**
 *
 * @author me
 */
public class DiaTerminado extends EstadosAdapter implements Serializable{

    Mundo m;
    public DiaTerminado(Mundo mundo) {
        super(mundo);
        this.m = mundo;
    }
    
    @Override
    public IEstados aplicarAcoes(){
        
        // SOLDADOS NO TÚNEL, VOLTAM AUTOMATICAMENTE PARA O CASTELO
        if(m.soldadosNoTunel()){
            m.alteraPosSoldados(-3); // COMO TUDO ESTÁ CONFIGURADO PARA QUE A POSIÇÃO DOS SOLDADOS NUNCA SEJA < 0, ISTO GARANTE QUE ELES VOLTAM PARA A POSIÇÃO 0, INDEPENDENTEMENTE DA POSIÇÃO EM QUE ESTÃO ATUALMENTE
        }
        
        
        // SOLDADOS EM TERRITÓRIO INIMIGO, SÃO CAPTURADOS
        if(m.soldadosEmLinhasInimigas()){
            m.soldadosCapturados();
        }
        
        // REDUZIR SUPPLIES EM 1 UNIDADE
        m.alteraSuprimentos(-1);
        
        return this.proximoEstado();
    }
    
    
    @Override
    public IEstados proximoEstado(){
        if(m.getDia() == 3)
            return new JogoTerminado(this.getMundo());
        Mundo m  = super.getMundo();
        
        m.setDia(m.getDia() + 1);
        
        return new AguardaCarta(this.getMundo());
    }
    
    @Override
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
}