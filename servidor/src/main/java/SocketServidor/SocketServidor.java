/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServidor;

import Modelo.Colores;
import Modelo.Jugador;
import Modelo.Sala;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServidor {

    private static final int PUERTO = 12345;
    private List<String> nombresUsuarios = new ArrayList<>();
    private ServerSocket serverSocket;
    private List<Sala> salas = new ArrayList<>();

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Conexión del servidor cerrada");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {

        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // Manejar la lógica de comunicación con el cliente
                String mensajeCliente = in.readLine();

                if (mensajeCliente != null) {
                    System.out.println("Mensaje del cliente: " + mensajeCliente);

                    // Dividir el mensaje en partes
                    String[] partes = mensajeCliente.split(",");

                    if (partes.length >= 4) {
                        String nombreUsuario = partes[0];
                        String avatarSeleccionado = partes[1];
                        String colorSeleccionado = partes[2];
                        String codigoSala = partes[3];

                        // Buscar la sala por código
                        Sala sala = buscarSala(codigoSala);

                        if (sala != null) {
                            // Verificar nombre de usuario en la sala
                            if (!sala.jugadorExiste(nombreUsuario)) {
                                // Usuario disponible, agregar a la lista de nombres de usuario de la sala
                                sala.agregarJugador(new Jugador(nombreUsuario, avatarSeleccionado, Colores.getColor(colorSeleccionado)));
                                out.println("OK"); // Confirmar al cliente que el nombre de usuario es válido
                            } else {
                                out.println("DUPLICADO"); // Informar al cliente que el nombre de usuario ya está en uso
                            }
                        } else {
                            out.println("SALA_NO_EXISTE"); // Informar al cliente que la sala no existe
                        }
                    } else {
                        System.out.println("Mensaje del cliente no tiene el formato esperado.");
                    }
                } else {
                    System.out.println("Mensaje nulo recibido del cliente.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private Sala buscarSala(String codigo) {
            for (Sala sala : salas) {
                if (sala.getCodigo().equals(codigo)) {
                    return sala;
                }
            }
            return null;
        }
    }
}
