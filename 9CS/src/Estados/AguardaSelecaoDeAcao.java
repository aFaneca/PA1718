/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Constantes;
import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;

/**
 *
 * @author me
 */ 
public class AguardaSelecaoDeAcao extends EstadosAdapter implements Serializable{
    Mundo m;
    
    public AguardaSelecaoDeAcao(Mundo m){
        super(m);
        this.m = m;
    }
    
    @Override
    public IEstados fimDoJogo(){
        return new JogoTerminado(this.getMundo());
    }
    
    @Override
    public IEstados acao_AtaqueDeArqueiros(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual){


        if(resultadoDoDado > inimigoEscolhido.getForca()){ // O ataque teve sucesso
            //Recuar a posição do inimigo em 1 unidade
            inimigoEscolhido.alterarLocal(+1);
        }
        
        //estadoAtual = estadoAtual.proximoEstado();
        
        return this;
    }
    
    @Override
    public IEstados acao_AtaqueDeAguaFervente(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual, boolean temDRMS){
        if(resultadoDoDado > inimigoEscolhido.getForca()){ // O ataque teve sucesso
            //Recuar a posição do inimigo em 1 unidade
            inimigoEscolhido.alterarLocal(+1);
        }
        else if(resultadoDoDado == 1 && !temDRMS)
            // REDUZIR MORAL EM -1
            m.alteraPovo(-1);

        
        return this;
    }
    
    @Override
    public IEstados acao_AtaqueDeCloseCombat(int resultadoDoDado, Inimigo inimigoEscolhido){
        if(resultadoDoDado > 4){ // O ataque teve sucesso
            //Recuar a posição do inimigo em 1 unidade
            inimigoEscolhido.alterarLocal(+1);
        }else if(resultadoDoDado == 1){
            // Reduz a moral do povo em 1 unidade
            m.alteraPovo(-1);
        }else{
            // Perde o Jogo
            return this.fimDoJogo();
        }
        
        return this;
    }

    @Override
    public IEstados acao_RepararMuralha(int resultadoDoDado){
        if(resultadoDoDado >= Constantes.REPARAR_MURALHA_MINIMO.getValor())
            m.alteraMuralha(+1);
        
        return this;
    }
    
    @Override
    public IEstados acao_MotivarTropas(int resultadoDoDado, boolean usarBonus){
        
        if(usarBonus){
            m.alteraSuprimentos(-1);
        }
        
        if(resultadoDoDado >= Constantes.MOTIVAR_TROPAS_MINIMO.getValor())
            m.alteraPovo(+1);
        
        
        return this;
    }
    
    @Override
    public IEstados acao_Raid(int resultadoDoDado){
        
        if(resultadoDoDado >= Constantes.RAID_MINIMO_SUCESSO1.getValor()){ // DEFAULT: 3,4,5,6
            if(resultadoDoDado >= Constantes.RAID_MINIMO_SUCESSO2.getValor()){ // DEFAULT: 6 <= RAID COM SUCESSO DE 2 SUPRIMENTOS
                m.alteraSuprimentos(+2);
            }else{  // DEFAULT: 3,4,5 <= RAID COM SUCESSO DE 1 SUPRIMENTO
                m.alteraSuprimentosFurtados(+1);
            }
        }else{ // DEFAULT: 1 <= SOLDADOS CAPTURADOS
            m.soldadosCapturados();
        }
        
        return this;
    }
    
    @Override
    public IEstados acao_Sabotagem(int resultadoDoDado){
        if(resultadoDoDado >= Constantes.SABOTAGEM_MINIMO_SUCESSO.getValor()){ // DEFAULT: 5, 6 <= -1 Catapulta dos Inimigos
            m.alteraNrCatapultas(-1);
            
        }else if(resultadoDoDado <= Constantes.SABOTAGEM_MAXIMO_INSUCESSO.getValor()){ // DEFAULT: 1 <= Soldados são capturados
            m.soldadosCapturados();
        }
        
        return this;
    }
    
    
    @Override
    public IEstados acao_movimentarSoldadosNoTunel(int resposta, boolean aVoltarAoCastelo){
        int movimento = 0;
        if(resposta == 1){ //Movimentar 1 posição no túnel
            if(aVoltarAoCastelo)
                movimento = -1;
            else
                movimento = +1;
        }else if(resposta == 2){
            if(aVoltarAoCastelo)
                movimento = -2;
            else
                movimento = +2;
        }
        
        m.alteraPosSoldados(movimento);
        
        return this;
    }
    
    public IEstados proximoEstado(){
        
        if(mundo.getCartasViradas() % mundo.getCartas().size() == 0) // SE JÁ FORAM VIRADAS AS 7 CARTAS
            return new DiaTerminado(this.getMundo());
        else
            return new AguardaCarta(mundo);
    }
    
}
