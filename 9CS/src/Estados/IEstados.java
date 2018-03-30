/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estados;

/**
 *
 * @author me
 */
public interface IEstados {
    IEstados proximoEstado();
    IEstados fimDoDia();
}
