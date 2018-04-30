/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

/**
 *
 * @author me
 */
public enum Constantes {
    
    SOLDADOS_NO_TUNEL_SEM_SORTE(1), // OS SOLDADOS NO TÚNEL SERÃO CAPTURADOS SE O DADO NÃO RETORNAR UM VALOR SUPERIOR AO DEFINIDO AQUI
    POSICAO_INICIAL_INIMIGOS(3), // POSIÇÃO INICIAL DOS INIMIGOS (DEFAULT: A 3 CASAS DE DISTANCIA DA FORTALEZA
    REPARAR_MURALHA_MINIMO(5), // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO
    MOTIVAR_TROPAS_MINIMO(5); // RESULTADO MÍNIMO OBTIDO NO DADO PARA QUE A AÇÃO TENHA SUCESSO
    
    
    private final int valor;

        Constantes(final int novoValor) {
            valor = novoValor;
        }

        public int getValor() { return valor; }
}
