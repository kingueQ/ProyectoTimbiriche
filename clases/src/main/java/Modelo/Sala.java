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
    private int listos;

    public Sala() {
        jugadores = new ArrayList<>();
        numJugadores = 0;
    }

    public Sala(String codigo, List<Jugador> jugadores, int numJugadores, int disponibilidad, boolean publica) {
        this.codigo = codigo;
        this.jugadores = jugadores;
        this.numJugadores = numJugadores;
        this.disponibilidad = disponibilidad;
        this.publica = publica;
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

    public boolean añadirJugador(Jugador jugador) {
        if (this.disponibilidad > 0) {
            this.jugadores.add(jugador);
            this.disponibilidad--;
            this.numJugadores++;
            return true;
        } else {
            return false;
        }
    }

    public void eliminarJugador(Jugador jugador) {
        if (this.disponibilidad < 4) {
            this.jugadores.remove(jugador);
            this.disponibilidad++;
            this.numJugadores--;
            this.listo(null);
        }
    }

    public void listo(Jugador jugador) {
        listos = 0;
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugador!=null&&jugadores.get(i).equals(jugador)) {
                if (jugadores.get(i).isListo()) {
                    jugadores.get(i).setListo(false);
                } else {
                    jugadores.get(i).setListo(true);
                }
            }
            if (jugadores.get(i).isListo()) {
                listos++;
            }
        }
    }

    public int getListos() {
        return listos;
    }

    public void setListos(int listos) {
        this.listos = listos;
    }
}
