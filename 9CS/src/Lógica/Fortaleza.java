/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Lógica.Ações.*;
import Lógica.Inimigos.Escada;
import Lógica.Inimigos.Torre;
import Lógica.Inimigos.Ariete;
import java.util.ArrayList;
import java.util.List;


public class Fortaleza {
    private Mundo mundo;
    private Muralha muralha;
    private Povo povo;
    private Suprimento suprimento;
    private List<Inimigo> inimigos;
    private Torre torre;      // INIMIGO
    private Escada escada;   // INIMIGO
    private Ariete ariete;  // INIMIGO
    private List<Inimigo> unidadesLentas;
    private List<Acao> acoes; // Lista de todas as ações existentes no jogo
    private AtaqueDeAguaFervente ataqueDeAguaFervente; // AÇÃO
    private AtaqueDeArqueiros ataqueDeArqueiros; // AÇÃO
    private AtaqueDeCloseCombat ataqueDeCloseCombat; // AÇÃO
    private MotivarTropas motivarTropas; // AÇÃO
    private MovimentarSoldadosNoTunel movimentarSoldadosNoTunel; // AÇÃO
    private Raid raid; // AÇÃO
    private RepararMuralha repararMuralha; // AÇÃO
    private Sabotagem sabotagem; // AÇÃO
    private Soldado soldados;
    private int nrCatapultas;
    
    public Fortaleza(Mundo m){
        this.mundo = m;
        muralha = new Muralha(this);
        povo = new Povo(this);
        suprimento = new Suprimento(this);
        soldados = new Soldado(this);
        torre = new Torre();
        escada = new Escada();
        ariete = new Ariete();
        inimigos = new ArrayList<>(3);
        inimigos.add(torre);
        inimigos.add(escada);
        inimigos.add(ariete);
        ataqueDeAguaFervente = new AtaqueDeAguaFervente(this);
        ataqueDeArqueiros = new AtaqueDeArqueiros(this);
        ataqueDeCloseCombat = new AtaqueDeCloseCombat(this);
        motivarTropas = new MotivarTropas(this);
        movimentarSoldadosNoTunel = new MovimentarSoldadosNoTunel(this);
        raid = new Raid(this);
        repararMuralha = new RepararMuralha(this);
        sabotagem = new Sabotagem(this);
        acoes = new ArrayList<>(8);
        acoes.add(ataqueDeAguaFervente);
        acoes.add(ataqueDeArqueiros);
        acoes.add(ataqueDeCloseCombat);
        acoes.add(motivarTropas);
        acoes.add(movimentarSoldadosNoTunel);
        acoes.add(raid);
        acoes.add(repararMuralha);
        acoes.add(sabotagem);
        nrCatapultas = 3;
    }

    
    public void alteraNrCatapultas(int var){
        nrCatapultas += var;
        
        if(nrCatapultas > 3) nrCatapultas = 3;
        if(nrCatapultas < 1) nrCatapultas = 1;
    }
    
    public void alteraMuralha(int alteracao){
        muralha.alteraForca(alteracao);
    }
    
    public void alteraSuprimentos(int alteracao){
        suprimento.alterarNivel(alteracao);
    }
    
    public void alteraPovo(int var){
        povo.alterarMoral(var);
    }
    
    public boolean soldadosNoTunel(){
        return soldados.noTunel();
    }
    
    public void alteraSuprimentosFurtados(int var){
        soldados.alteraSuprimentosFurtados(var);
    }
    
    public void soldadosCapturados(){
        // ELIMINAR REGISTO DOS SOLDADOS ATUAIS E GERAR NOVOS SOLDADOS
        soldados = new Soldado(this);
    }
    
    public int posDaTorre() {
        if(inimigos.contains(torre))
            return inimigos.get(inimigos.indexOf(torre)).local;
        return -1;
    }
    
    public void removerTorre(){
        inimigos.remove(torre);
 
    }
    
    
    
    // RETORNA UMA LISTA COM OS INIMIGOS QUE SE ENCONTRAREM NAS POSIÇÕES PASSADAS COMO ARGUMENTOS ("LOCAIS")
    public List<Inimigo> getInimigos(List<Integer> locais){
        List<Inimigo> inimigosNaPosicao = new ArrayList<>();
        
        for(Inimigo i : inimigos){  // for(int i = 0; i < inimigos.size(); i++)
            for(int local : locais){
                if(i.local == local)
                    inimigosNaPosicao.add(i);
            }
        }
        
        return inimigosNaPosicao;
    }
   
    
    public List<Inimigo> getListaDeInimigos(){
        return inimigos;
    }
    
    // EVENTOS
    public void evento_AtaqueDeCatapulta(){
        if(mundo.contaCatapultas() == 3)
            mundo.alteraMuralha(-2);
        else if(mundo.contaCatapultas() == 2)
            mundo.alteraMuralha(-1);
        else if(mundo.contaCatapultas() == 1){
            if(mundo.rodaDado() > 3)
                mundo.alteraMuralha(-1);
        }
    }
    
    public void evento_Doenca(){
        povo.alterarMoral(-1);
        suprimento.alterarNivel(-1);
    }
    
    void evento_SuprimentosEstragados() {
        suprimento.alterarNivel(-1);
    }
    
    void evento_MorteDeUmLider() {
        povo.alterarMoral(-1);
    }

    
    // GETTERS & SETTERS
    public int getNrCatapultas() {
        return nrCatapultas;
    }
    
    public Torre getTorre() {
        return torre;
    }

    public Escada getEscada() {
        return escada;
    }

    public Ariete getAriete() {
        return ariete;
    }
    
    public List<Inimigo> getUnidadesLentas(){
        unidadesLentas = new ArrayList<>(); // REINICIALIZA A LISTA
        int local = Constantes.POSICAO_INICIAL_INIMIGOS.getValor();
        
        return encontraUnidadesMaisLentas(local);
    }

    
    
    public List<Inimigo> encontraUnidadesMaisLentas(int local){
        
        
        for(Inimigo i : inimigos){
            // ENCONTRAR AS QUE SE ENCONTRAM MAIS LONGE DO CASTELO, A COMEÇAR PELA POSIÇÃO MAIS AFASTADA
            if(i.local == local){
                unidadesLentas.add(i);
            }
        }
        
        if(unidadesLentas.isEmpty()){ //
            encontraUnidadesMaisLentas(--local); // RECURSIVIDADE
        }
        
        return unidadesLentas;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }

    public AtaqueDeAguaFervente getAtaqueDeAguaFervente() {
        return ataqueDeAguaFervente;
    }

    public AtaqueDeArqueiros getAtaqueDeArqueiros() {
        return ataqueDeArqueiros;
    }

    public AtaqueDeCloseCombat getAtaqueDeCloseCombat() {
        return ataqueDeCloseCombat;
    }

    public MotivarTropas getMotivarTropas() {
        return motivarTropas;
    }

    public MovimentarSoldadosNoTunel getMovimentarSoldadosNoTunel() {
        return movimentarSoldadosNoTunel;
    }

    public Raid getRaid() {
        return raid;
    }

    public RepararMuralha getRepararMuralha() {
        return repararMuralha;
    }

    public Sabotagem getSabotagem() {
        return sabotagem;
    }
    
    public List<Inimigo> getTodosOsInimigos() {
        return inimigos;
    }

    public boolean faccoesFatais(int n) {
        // VERIFICAR SE 2 OU MAIS FACÇÕES ESTÃO NA ZONA DE CLOSE COMBAT (local = 0)
        int contador = 0;
        
        for(Inimigo i : inimigos){
            if(i.local == 0)
                contador++;
        }
        if(contador >= n) return true;
        
       return false;
    }

    public boolean forcasFatais(int n) {
        // VERIFICAR SE ALGUMA DAS FORÇAS ESTÁ A 0
        int contador = 0;
        
        if(muralha.getForca() == 0) contador++;
        if(povo.getMoral() == 0) contador++;
        if(suprimento.getNivel() == 0) contador ++;
        
        if(contador >= n)
            return true;
        
        return false;
    }

    @Override
    public String toString(){
        return "## DETALHES DA FORTALEZA ##\n" 
                + "FORÇA DA MURALHA: " + muralha.getForca()
                + "\n MORAL DO POVO: " + povo.getMoral()
                + "\n NIVEL DE SUPRIMENTOS: " + suprimento.getNivel();
    }   
}
