/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CtrlVistas;
import Modelo.Jugador;
import Modelo.Sala;
import SocketCliente.SocketCliente;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kingu
 */
public class Vista {
    private CtrlVistas controlador;
    private JFrame frame;
    private JPanel panel;

    public Vista(CtrlVistas controller) {
        this.controlador = controller;
        this.frame = new JFrame("Juego de Timbiriche");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(400, 300);

        this.panel = new JPanel();
        this.frame.add(panel);
    }

    public void showPantallaInicio() {
        // Implementar la pantalla de inicio aquí.
        FrmInicio inicio=new FrmInicio();
        inicio.setVisible(true);
        // Usar ActionListener para el botón "Ingresar" y "Salir".
    }

    public void showPantallaRegistro(SocketCliente socketCliente) {
        // Implementar la pantalla de registro aquí.
        FrmRegistro registro=new FrmRegistro(socketCliente);
        registro.setVisible(true);
        // Usar ActionListener para el botón "Ingresar".
    }

    public void showPantallaMenu(SocketCliente socketCliente) {
        // Implementar la pantalla de menú aquí.
        FrmMenu menu=new FrmMenu(socketCliente);
        menu.setVisible(true);
        // Usar ActionListener para las opciones del menú.
    }

    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(frame, mensaje);
    }
    
    public void showPantallaSala(Sala sala, SocketCliente socketCliente){
        FrmSala pantallaSala=new FrmSala(sala, socketCliente);
        pantallaSala.setVisible(true);
    }
    
    public void showPartida(List<Jugador> jugadores, SocketCliente socketCliente){
        FrmPartida partida=new FrmPartida(jugadores, socketCliente);
        partida.setVisible(true);
    }
}
