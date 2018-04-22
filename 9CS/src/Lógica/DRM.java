/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author me
 */
public class DRM { 
    // DICE RESULT MOFICATION
    protected int var; // variação no resultado
    protected Acao acao; // ação afetada pela variação
    protected List<Inimigo> inimigosAfetados; // SE A LISTA SE ENCONTRAR VAZIA, É SINAL QUE AFETA TODOS OS INIMIGOS (OU NENHUM, CASO SEJA UMA DRM NÃO DIRECIONADA A ATAQUE)
    
    public DRM(Acao acao, int var){
        this.var = var;
        this.acao = acao;
        inimigosAfetados = new ArrayList<>();
    }
    
    public DRM(Acao acao, int var, List<Inimigo> inimigos){
        this(acao, var); // CHAMA O CONSTRUTOR DE CIMA
        inimigosAfetados.addAll(inimigos);
    }
    
    
    public boolean temRestricoes(){
        if(inimigosAfetados.isEmpty())
            return false;
        return true;
    }
    
    @Override
    public String toString(){
        String str;
        char sinal = (var > 0) ? '+' : ' '; // EXPRESSÃO TERNÁRIA
        str = "" + sinal + var + " para ação de " + acao;
        
        if(temRestricoes()){
            str += " sobre unidades do tipo " + inimigosAfetados.get(0);
            if(inimigosAfetados.size() > 1){
                for(int i = 1; i < inimigosAfetados.size() - 1; i++){
                    str += ", " + inimigosAfetados.get(i);
                }
                str += " e " + inimigosAfetados.get(inimigosAfetados.size() - 1);
            }
        }
        
        //return "+" + var + " para ação de " + acao;
        return str;
    }
}
