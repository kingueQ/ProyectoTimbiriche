/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.Sala;
import SocketCliente.SocketCliente;
import Vista.Vista;
import java.util.List;

/**
 *
 * @author kingu
 */
public class CtrlVistas {

    private Vista vista;
    private Modelo modelo;

    public CtrlVistas() {
        this.vista = new Vista(this) {
        };
        this.modelo = new Modelo();
    }

    public void startApplication(CtrlServidor ctrlServidor) {
        vista.showPantallaInicio(ctrlServidor);
    }

    public void showRegistro(SocketCliente socketCliente, CtrlServidor ctrlServidor) {
        vista.showPantallaRegistro(socketCliente, ctrlServidor);
    }

    public void showMenu(SocketCliente socketCliente, CtrlServidor ctrlServidor) {
        vista.showPantallaMenu(socketCliente, ctrlServidor);
    }

    public Sala crearSalaPublica() {
        return modelo.crearSalaPublica();
    }

    public void iniciarJuego(List<Jugador> jugadores, SocketCliente socketCliente, CtrlServidor ctrlServidor) {
        vista.showPartida(jugadores, socketCliente, ctrlServidor);
    }

    public Sala crearSalaPrivada() {
        return modelo.crearSalaPrivada();
    }

    public void mostrarSala(Sala sala, SocketCliente socketCliente, CtrlServidor ctrlServidor) {
        vista.showPantallaSala(sala, socketCliente, ctrlServidor);
    }

}
