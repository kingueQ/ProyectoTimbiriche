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
    private int numJugadores;
    private char[][] tablero;
    private int totalCuadros;

    public Tablero() {
    }

    public Tablero(int numJugadores) {
        this.numJugadores=numJugadores;
        if(numJugadores==2){
            this.tamanio=10;
        }
        if(numJugadores==3){
            this.tamanio=20;
        }
        if(numJugadores==4){
            this.tamanio=40;
        }
        
        
    }
    
    public void crearTablero(int tamanio){
        this.tablero=new char[2*tamanio-1][2*tamanio-1];
        for(int i=0;i<tamanio*2-1;i++){
            for(int j=0;j<tamanio*2-1;j++){
                if(i%2==0&&j%2==0){
                    tablero[i][j]='.';
                }else{
                    tablero[i][j]=' ';
                }
            }
        }
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public int getTotalCuadros() {
        return totalCuadros;
    }

    public void setTotalCuadros(int totalCuadros) {
        this.totalCuadros = totalCuadros;
    }
    
    
}
