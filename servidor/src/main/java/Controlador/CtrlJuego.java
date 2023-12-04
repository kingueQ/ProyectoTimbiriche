/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo;

/**
 *
 * @author kingu
 */
public class CtrlJuego {
    Modelo modelo=new Modelo();
    public char[][] obtenerTablero(int jugadores){
        if(jugadores==2){
            return modelo.crearTablero(10);
        }
        if(jugadores==3){
            return modelo.crearTablero(20);
        }
        if(jugadores==4){
            return modelo.crearTablero(40);
        }
        return null;
    }
    
    
}
