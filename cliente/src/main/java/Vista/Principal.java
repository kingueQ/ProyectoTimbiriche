package Vista;

import Controlador.CtrlServidor;
import Controlador.CtrlVistas;
import SocketServidor.SocketServidor;

public class Principal {

    public static void main(String[] args) {
        // Declarar la variable servidor fuera del lambda
        SocketServidor servidor = new SocketServidor();

        // Iniciar el SocketServidor en un hilo separado
        new Thread(() -> {
            servidor.iniciarServidor();
        }).start();

        // Iniciar la aplicación desde el controlador de vistas
        CtrlVistas controlador = new CtrlVistas();
        controlador.startApplication();
    }
}
