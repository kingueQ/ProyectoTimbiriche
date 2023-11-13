package Vista;

import Controlador.CtrlVistas;
import SocketServidor.SocketServidor;

public class Principal {

    public static void main(String[] args) {
        // Iniciar el SocketServidor en un hilo separado
        new Thread(() -> {
            SocketServidor servidor = new SocketServidor();
            servidor.iniciarServidor();
        }).start();

        // Iniciar la aplicación desde el controlador de vistas
        CtrlVistas controlador = new CtrlVistas();
        controlador.startApplication();
    }
}
