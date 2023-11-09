/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo;
import Vista.Vista;


/**
 *
 * @author kingu
 */
public class CtrlVistas {
    private Vista vista;
    private Modelo modelo;

    public CtrlVistas() {
        this.vista = new Vista(this) {};
        this.modelo = new Modelo();
    }

    public void startApplication() {
        vista.showPantallaInicio();
    }

    public void validarRegistro(String nombreUsuario, String avatarSeleccionado, String colorSeleccionado) {
        // Implementar la validación y lógica aquí.
        if (/* Validación exitosa */true) {
            modelo.setUsuario(nombreUsuario);
            modelo.setAvatar(avatarSeleccionado);
            modelo.setColor(colorSeleccionado);
            vista.showPantallaMenu();
        } else {
            vista.mostrarMensajeError("Por favor complete todos los campos.");
        }
    }
    
    public void showRegistro(){
        vista.showPantallaRegistro();
    }
    
    public void showMenu(){
        vista.showPantallaMenu();
    }
    
    public void crearSala(){
        vista.showPantallaSala();
    }

    public void iniciarJuego(){
        vista.showPartida();
    }
}
