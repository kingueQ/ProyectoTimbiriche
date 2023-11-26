/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DominioDTO;

/**
 *
 * @author kingu
 */
public class TableroDTO {
    private int cuadrosCambiados=0;
    private char[][] tablero;

    public int getCuadrosCambiados() {
        return cuadrosCambiados;
    }

    public void setCuadrosCambiados(int cuadrosCambiados) {
        this.cuadrosCambiados = cuadrosCambiados;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }
    
    
}
