/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.io.Serializable;

/**
 *
 * @author me
 */
public enum Constantes implements Serializable{
    
    SOLDADOS_EM_LINHAS_INIMIGAS_SEM_SORTE(1),        // OS SOLDADOS NO TÚNEL SERÃO CAPTURADOS SE O DADO NÃO RETORNAR UM VALOR SUPERIOR AO DEFINIDO AQUI
    POSICAO_INICIAL_INIMIGOS(1),          // POSIÇÃO INICIAL DOS INIMIGOS (DEFAULT: A 4 CASAS DE DISTANCIA DA FORTALEZA
    REPARAR_MURALHA_MINIMO(5),           // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO
    MOTIVAR_TROPAS_MINIMO(5),           // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO
    RAID_MINIMO_SUCESSO1(3),           // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO MÍNIMO
    RAID_MINIMO_SUCESSO2(6),          // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO MÁXIMO
    RAID_MAXIMO_INSUCESSO(1),        // RESULTADO MÁXIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA INSUCESSO
    SABOTAGEM_MINIMO_SUCESSO(5),    // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÁO TENHA SUCESSO
    SABOTAGEM_MAXIMO_INSUCESSO(1); // RESULTADO MÁXIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA INSUCESSO
    
    
    private final int valor;

        Constantes(final int novoValor) {
            valor = novoValor;
        }

        public int getValor() { return valor; }
}
