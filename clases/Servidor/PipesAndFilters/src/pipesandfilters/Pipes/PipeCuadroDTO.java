/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipesandfilters.Pipes;

import DominioDTO.CuadroDTO;
import pipesandfilters.Filters.FilterCuadro;

/**
 *
 * @author Abraham Quintana y Rafael Soqui
 */
public class PipeCuadroDTO implements Pipe<CuadroDTO, FilterCuadro>{

    @Override
    public void pasar(CuadroDTO objeto) {
        FilterCuadro fc = new FilterCuadro();
        fc.procesar(objeto);
    }
    
}
