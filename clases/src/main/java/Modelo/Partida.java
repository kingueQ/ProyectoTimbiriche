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
public class Partida {
    private int jugadores;
    private Sala datos;
    private Tablero tablero;
    private int cuadrosLlenos;

    public Partida() {
    }

    public Partida(int jugadores, Sala datos, Tablero tablero, int cuadrosLlenos) {
        this.jugadores = jugadores;
        this.datos = datos;
        this.tablero = tablero;
        this.cuadrosLlenos = cuadrosLlenos;
    }

    public int getJugadores() {
        return jugadores;
    }

    public void setJugadores(int jugadores) {
        this.jugadores = jugadores;
    }

    public Sala getDatos() {
        return datos;
    }

    public void setDatos(Sala datos) {
        this.datos = datos;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public int getCuadrosLlenos() {
        return cuadrosLlenos;
    }

    public void setCuadrosLlenos(int cuadrosLlenos) {
        this.cuadrosLlenos = cuadrosLlenos;
    }
    
    
}
