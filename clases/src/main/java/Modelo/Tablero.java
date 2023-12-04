/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DominioDTO.TableroDTO;
import java.io.Serializable;

/**
 *
 * @author kingu
 */
public class Tablero  implements Serializable{
    private int tamanio;
    private int numJugadores;
    private char[][] tablero;
    private int totalCuadros;
    private int cuadrosActuales;

    public Tablero() {
    }

    public Tablero(int numJugadores) {
        this.numJugadores=numJugadores;
        this.cuadrosActuales=0;
        if(numJugadores==2){
            this.tamanio=10;
            this.totalCuadros=81;
        }
        if(numJugadores==3){
            this.tamanio=20;
            this.totalCuadros=361;
        }
        if(numJugadores==4){
            this.tamanio=40;
            this.totalCuadros=1521;
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
    
    public char[][] dibujarLinea(int fila, int columna){
        boolean vale=true;
        if(fila%2==1&&columna%2==1){
            vale=false;
        }
        if(this.tablero[fila][columna]==' '&&vale){
            if(fila%2==0){
                this.tablero[fila][columna]='━';
                return this.tablero;
            }else{
                this.tablero[fila][columna]='┃';
                return this.tablero;
            }
        }else{
            return null;
        }
    }
    
    public TableroDTO validarCuadro(Jugador jugador){
        boolean cambio=false;
        TableroDTO nuevo=new TableroDTO();
        for(int i=1; i<this.tamanio*2-1; i=i+2){
            for(int j=1; j<this.tamanio*2-1; j=j+2){
                if(this.tablero[i-1][j]=='━'&&this.tablero[i+1][j]=='━'&&this.tablero[i][j-1]=='┃'&&this.tablero[i][j+1]=='┃'&&this.tablero[i][j]==' '){
                    char letra=jugador.getUsuario().charAt(0);
                    this.tablero[i][j]=letra;
                    cambio=true;
                    nuevo.setCuadrosCambiados(nuevo.getCuadrosCambiados()+1);
                    this.cuadrosActuales++;
                }
            }
        }
        if(cambio){
            nuevo.setTablero(this.tablero);
            return nuevo;
        }else{
            return null;
        }
    }
}
