/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesandfilters.Filters;

/**
 *
 * @author Abraham Quintana y Rafael Soqui
 * @param <I> Entrada
 * @param <O> Salida
 * @param <P> Pipe de salida
 */
interface Filter<I,O,P> {
    void procesar(I objeto);
}
