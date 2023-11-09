/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author kingu
 */
public class Tablero {
    private int tamanio;
    private Cuadro[][] tablero;
    private int totalCuadros;

    public Tablero() {
    }

    public Tablero(int tamanio, Cuadro[][] tablero, int totalCuadros) {
        this.tamanio = tamanio;
        this.tablero = tablero;
        this.totalCuadros = totalCuadros;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Cuadro[][] getTablero() {
        return tablero;
    }

    public void setTablero(Cuadro[][] tablero) {
        this.tablero = tablero;
    }

    public int getTotalCuadros() {
        return totalCuadros;
    }

    public void setTotalCuadros(int totalCuadros) {
        this.totalCuadros = totalCuadros;
    }
    
    
}
