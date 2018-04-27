/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

import Lógica.Mundo;


public class EstadosAdapter implements IEstados{
    private Mundo mundo;
    
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

   
}
