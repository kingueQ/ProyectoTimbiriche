/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DominioDTO.TableroDTO;
import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.Sala;
import Modelo.Tablero;
import SocketServidor.SocketServidor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kingu
 */
public class CtrlServidor {
    private List<String> nombresUsuarios = new ArrayList<>();
    private List<Sala> salas = new ArrayList<>();
    Modelo modelo;
    
    SocketServidor servidor;
    
    public CtrlServidor(){
        modelo=new Modelo();
    }
    
    public CtrlServidor(SocketServidor servidor){
        this.servidor=servidor;
    }
    
    public boolean agregarJugador(Jugador jugador){
        return this.servidor.agregarJugador(jugador);
    }
    
    public Sala unirseSala(String codigo, Jugador jugador){
        return this.servidor.unirseSala(codigo, jugador);
    }
    
    public Sala unirsePublica(Jugador jugador){
        return this.servidor.unirsePublica(jugador);
    }
    
    public void crearSala(Sala sala){
        this.servidor.crearSala(sala);
    }
    
    public void eliminarJugadorSala(Sala sala, Jugador jugador){
        this.servidor.eliminarJugadorSala(sala, jugador);
    }
    
    public Sala listo(Sala sala, Jugador jugador){
        return this.servidor.listo(sala, jugador);
    }
    
    public Tablero validarLinea(int fila, int columna, Tablero tablero){
        return this.servidor.validarLinea(fila, columna, tablero);
    }
    
    public TableroDTO verificarCuadro(Jugador jugador, Tablero tablero){
        return this.servidor.verificarCuadro(jugador, tablero);
    }
    
    public Sala crearPublica(){
        return modelo.crearSalaPublica();
    }
    
    public Sala crearPrivada(){
        return modelo.crearSalaPrivada();
    }
    
    
}
