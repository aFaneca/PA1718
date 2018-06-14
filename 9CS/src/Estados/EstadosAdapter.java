/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Evento;
import Lógica.Inimigo;
import Lógica.Mundo;
import java.io.Serializable;
import ui.GUI.JogoView;


public class EstadosAdapter implements IEstados, Serializable{
    protected Mundo mundo;
    
    public EstadosAdapter(Mundo mundo){
        this.mundo = mundo;
    }

    public Mundo getMundo() {
        return mundo;
    }

    public void setMundo(Mundo mundo) {
        this.mundo = mundo;
    }

    @Override
    public IEstados proximoEstado() {return this;}

    @Override
    public IEstados fimDoDia() {return this;}
    
    @Override
    public IEstados fimDoJogo() {return this;}

    @Override
    public IEstados acao_AtaqueDeArqueiros(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual) {
        return this;
    }

    @Override
    public IEstados acao_AtaqueDeAguaFervente(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual, boolean temDRMS) {
        return this;
    }

    @Override
    public IEstados acao_AtaqueDeCloseCombat(int resultadoDoDado, Inimigo inimigoEscolhido) {
        return this;
    }

   @Override
   public IEstados evento_AtaqueDeCatapulta(){
       return this;
   }
   
   @Override
   public IEstados evento_Doenca(){
       return this;
   }

    @Override
    public IEstados evento_SuprimentosEstragados() {
        return this;
    }

    @Override
    public IEstados avancaInimigos(Evento evento) {
        return this;
    }

    @Override
    public IEstados acao_RepararMuralha(int resultadoDoDado) {
        return this;
    }

    @Override
    public IEstados acao_MotivarTropas(int resultadoDoDado, boolean usarBonus) {
        return this;
    }

    @Override
    public IEstados acao_Raid(int resultadoDoDado) {
        return this;
    }

    @Override
    public IEstados acao_Sabotagem(int resultadoDoDado) {
        return this;
    }

    @Override
    public IEstados acao_movimentarSoldadosNoTunel(int resposta, boolean aVoltarAoCastelo) {
        return this;
    }

    @Override
    public IEstados evento_MorteDeUmLider() {
        return this;
    }

    @Override
    public IEstados evento_CatapultaReparada() {
        return this;
    }

    @Override
    public IEstados evento_Colapso() {
        return this;
    }

    @Override
    public IEstados aplicarAcoes() {
        return this;
    }
    
    @Override
    public IEstados virarCarta(){
        return this;
    }
    
    public IEstados mostraInfo(String mensagem){

        return this;
    }

    public IEstados verificarSoldados(JogoView view){
        return this;
    }
    
     public IEstados testaSorteDosSoldados(){
         return this;
     }

    @Override
    public IEstados voltarAoInicio() {
        return this;
    }

}
