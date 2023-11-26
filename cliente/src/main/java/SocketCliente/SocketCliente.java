package SocketCliente;

import Modelo.Jugador;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketCliente {

    //192.168.1.64
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 12345;

    private Jugador jugador;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketCliente() {
        try {
            socket = new Socket(SERVIDOR_IP, PUERTO);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje(final String mensaje) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    out.println(mensaje);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        worker.execute();
    }

    private String recibirRespuesta() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public String validarRegistro(final String nombreUsuario, final String avatarSeleccionado, final String colorSeleccionado) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    enviarMensaje(nombreUsuario + "," + avatarSeleccionado + "," + colorSeleccionado);
                    return recibirRespuesta();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "ERROR";
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public void cerrarConexion() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public String validarCodigoSala(String codigo) {
        if (codigo.length() != 6) {
            return "NO_EXISTE";
        } else {
            return "OK";
        }
    }

    
}
