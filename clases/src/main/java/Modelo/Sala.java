/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kingu
 */
public class Sala {
    private String codigo;
    private List<Jugador> jugadores;
    private int numJugadores;
    private int disponibilidad;
    private boolean publica;

    public Sala() {
        jugadores=new ArrayList<>();
    }

    public Sala(String codigo, List<Jugador> jugadores, int numJugadores, int disponibilidad, boolean publica) {
        this.codigo = codigo;
        this.jugadores = jugadores;
        this.numJugadores = numJugadores;
        this.disponibilidad = disponibilidad;
        this.publica=publica;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }
    
    public void añadirJugador(Jugador jugador){
        this.jugadores.add(jugador);
    }
    
    public void eliminarJugador(Jugador jugador){
        this.jugadores.remove(jugador);
    }
    
    public boolean jugadorExiste(String nombreUsuario) {
        for (Jugador jugador : jugadores) {
            if (jugador.getUsuario().equals(nombreUsuario)) {
                return true; // El jugador ya está en la sala
            }
        }
        return false; // El jugador no está en la sala
    }

    public void agregarJugador(Jugador jugador) {
        if (!jugadorExiste(jugador.getUsuario())) {
            jugadores.add(jugador);
        }
    }
}
