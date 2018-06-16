/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Evento;
import Lógica.Inimigo;
import ui.GUI.JogoView;

/**
 *
 * @author me
 */
public interface IEstados {
    IEstados proximoEstado();
    IEstados fimDoJogo();
    IEstados fimDoDia();
    
    // AÇÕES
    IEstados acao_AtaqueDeArqueiros(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual);
    IEstados acao_AtaqueDeAguaFervente(int resultadoDoDado, int var, Inimigo inimigoEscolhido, Evento eventoAtual, boolean temDRMS);
    IEstados acao_AtaqueDeCloseCombat(int resultadoDoDado, Inimigo inimigoEscolhido);
    IEstados acao_RepararMuralha(int resultadoDoDado);
    IEstados acao_MotivarTropas(int resultadoDoDado, boolean usarBonus);
    IEstados acao_Raid(int resultadoDoDado);
    IEstados acao_Sabotagem(int resultadoDoDado);
    IEstados acao_movimentarSoldadosNoTunel(int resposta, boolean aVoltarAoCastelo);
    
    // EVENTOS
    IEstados evento_AtaqueDeCatapulta();
    IEstados evento_Doenca();
    IEstados evento_SuprimentosEstragados();
    IEstados evento_MorteDeUmLider();
    IEstados evento_CatapultaReparada();
    IEstados evento_Colapso();
    
    
    IEstados avancaInimigos(Evento evento);
    IEstados aplicarAcoes(); // Aplica as ações de final de dia (reduzir supplies, etc)
    
    public IEstados virarCarta();
    public IEstados mostraInfo(String mensagem);
    public IEstados verificarSoldados(JogoView view);
    public IEstados testaSorteDosSoldados();
    public IEstados voltarAoInicio();

}
