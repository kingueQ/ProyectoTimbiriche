/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServidor;

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
                // Puedes implementar un protocolo para enviar/recibir mensajes
                // Aquí un ejemplo muy simple

                String mensajeCliente = in.readLine();
                if (mensajeCliente != null) {

                    System.out.println("Mensaje del cliente: " + mensajeCliente);

                    // Dividir el mensaje en partes
                    String[] partes = mensajeCliente.split(",");
                    if (partes.length >= 3) {
                        String nombreUsuario = partes[0];
                        String avatarSeleccionado = partes[1];
                        String colorSeleccionado = partes[2];
// Verificar nombre de usuario
                        if (!nombresUsuarios.contains(nombreUsuario)) {
                            // Usuario disponible, agregar a la lista de nombres de usuario
                            nombresUsuarios.add(nombreUsuario);
                            out.println("OK"); // Confirmar al cliente que el nombre de usuario es válido
                        } else {
                            out.println("DUPLICADO"); // Informar al cliente que el nombre de usuario ya está en uso
                        }
                        // Resto del código...
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
    }
}
