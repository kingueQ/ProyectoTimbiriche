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
    private List<Jugador> jugadores = new ArrayList<>();
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

    private Sala buscarSala(String codigo) {
        for (Sala sala : salas) {
            if (sala.getCodigo().equals(codigo)) {
                return sala;
            }
        }
        return null;
    }

    public boolean agregarJugador(Jugador jugador) {
        String nombre = jugador.getUsuario();
        for (Jugador jugadorr : jugadores) {
            if (jugadorr.getUsuario().equals(nombre)) {
                return false;
            }
        }
        jugadores.add(jugador);
        return true;
    }

    public Sala unirseSala(String codigo, Jugador jugador) {
        Sala sala = this.buscarSala(codigo);
        if (sala != null) {
            if (sala.añadirJugador(jugador)) {
                return sala;
            }
        }
        return null;
    }
    
    public void eliminarJugadorSala(Sala sala, Jugador jugador){
        for(int i=0;i<this.salas.size();i++){
            if(salas.get(i).equals(sala)){
                salas.get(i).eliminarJugador(jugador);
            }
        }
    }

    private List<Sala> salasPublicas() {
        ArrayList<Sala> salas2 = new ArrayList<>();
        for (Sala sala : salas) {
            if (sala.getCodigo().equals("Publica")) {
                salas2.add(sala);
            }
        }
        return salas2;
    }

    public Sala unirsePublica(Jugador jugador) {
        List<Sala> salas1 = this.salasPublicas();
        for (Sala sala : salas1) {
            if (sala.añadirJugador(jugador)) {
                return sala;
            }
        }
        return null;
    }

    public void crearSala(Sala sala) {
        this.salas.add(sala);
    }
    
    public Sala listo(Sala sala, Jugador jugador){
        for(int i=0;i<this.salas.size();i++){
            if(salas.get(i).equals(sala)){
                salas.get(i).listo(jugador);
                return salas.get(i);
            }
        }
        return sala;
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

                } else {
                    System.out.println("Mensaje nulo recibido del cliente.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
