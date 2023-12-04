/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketServidor;

import Controlador.CtrlJuego;
import DominioDTO.TableroDTO;
import Fachada.Fachada;
import Modelo.Colores;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Sala;
import Modelo.Tablero;
import PipesAndFilters.ServidorObserver;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SocketServidor {

    private static final int PUERTO = 12345;
    private List<Jugador> jugadores = new ArrayList<>();
    private ServerSocket serverSocket;
    private List<Sala> salas = new ArrayList<>();
    private List<Partida> partidas = new ArrayList<>();
    private List<PrintWriter> clientesConectados = new ArrayList<>();
    Fachada fachada = new Fachada();
    private List<ServidorObserver> observadores = new ArrayList<>();

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

    public void eliminarJugadorSala(Sala sala, Jugador jugador) {
        for (int i = 0; i < this.salas.size(); i++) {
            if (salas.get(i).equals(sala)) {
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

    public Sala listo(Sala sala, Jugador jugador) {
        for (int i = 0; i < this.salas.size(); i++) {
            if (salas.get(i).equals(sala)) {
                salas.get(i).listo(jugador);
                return salas.get(i);
            }
        }
        return sala;
    }

    public Sala obtenerActualizada(String nombre) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getUsuario().equals(nombre)) {
                for (int j = 0; j < salas.size(); j++) {
                    if (salas.get(j).getJugadores().contains(jugadores.get(i))) {
                        return salas.get(j);
                    }
                }
            }
        }
        return null;
    }

    public Partida partidaActualizada(String nombre) {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getUsuario().equals(nombre)) {
                for (int j = 0; j < partidas.size(); j++) {
                    if (partidas.get(j).getDatos().getJugadores().contains(jugadores.get(i))) {
                        return partidas.get(j);
                    }
                }
            }
        }
        return null;
    }

    public Tablero validarLinea(int fila, int columna, Tablero tablero) {
        char[][] campo = tablero.dibujarLinea(fila, columna);
        if (campo != null) {
            tablero.setTablero(campo);
            return tablero;
        } else {
            return null;
        }
    }

    public TableroDTO verificarCuadro(Jugador jugador, Tablero tablero) {
        TableroDTO campo = tablero.validarCuadro(jugador);
        if (campo != null) {
            return campo;
        } else {
            return null;
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
                String mensajeCliente = in.readLine();

                if (mensajeCliente != null) {
                    System.out.println("Mensaje del cliente: " + mensajeCliente);

                    String[] partes = mensajeCliente.split(",");

                    if (partes.length > 0) {
                        String comando = partes[0];

                        if (comando.equals("REGISTRO")) {
                            String nombre = partes[1];
                            Color color = Colores.getColor(partes[3]);
                            String avatar = partes[2];
                            Jugador jugador = new Jugador(nombre, avatar, color);
                            boolean res;
                            if (agregarJugador(jugador)) {
                                res = true;
                                System.out.println("Usuario registrado correctamente");
                            } else {
                                res = false;
                                System.out.println("El usuario ya se encuentra registrado");
                            }
                            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                            objectOut.writeObject(res);
                            objectOut.flush();
                        } else if (comando.equals("UNIRSEASALA")) {
                            String codigo = partes[1];
                            String nombre = partes[2];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            if (jugador != null) {
                                Sala sala = unirseSala(codigo, jugador);
                                try {
                                    notificarObservadores("SALA_ACTUALIZADA");
                                    ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                    objectOut.writeObject(sala);
                                    objectOut.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("El jugador no existe");
                            }
                        } else if (comando.equals("UNIRSEPUBLICA")) {
                            String nombre = partes[1];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            if (jugador != null) {
                                Sala sala = unirsePublica(jugador);
                                try {
                                    notificarObservadores("SALA_ACTUALIZADA");
                                    ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                    objectOut.writeObject(sala);
                                    objectOut.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("El jugador no existe");
                            }
                        } else if (comando.equals("CREARSALA")) {
                            String nombre = partes[1];
                            String tipo = partes[2];
                            Sala sala = null;
                            if (tipo.equals("PUBLICA")) {
                                sala = fachada.crearSala(true);
                                System.out.println("publica");
                            } else {
                                sala = fachada.crearSala(false);
                                System.out.println("privada");
                            }
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    System.out.println("encontrado");
                                    break;
                                }
                            }
                            if (sala != null && jugador != null) {
                                System.out.println("creada");
                                if (sala.añadirJugador(jugador)) {
                                    salas.add(sala);
                                    try {
                                        ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                        objectOut.writeObject(sala);
                                        objectOut.flush();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    System.out.println("El jugador ya se encuentra en la sala");
                                }
                            } else {
                                System.out.println("El jugador no existe");
                            }
                        } else if (comando.equals("ELIMINARJUGADOR")) {
                            String nombre = partes[1];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            for (int i = 0; i < salas.size(); i++) {
                                if (salas.get(i).getJugadores().contains(jugador)) {
                                    salas.get(i).eliminarJugador(jugador);
                                    notificarObservadores("SALA_ACTUALIZADA");
                                    ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                    objectOut.writeObject(salas.get(i));
                                    objectOut.flush();
                                }
                            }
                        } else if (comando.equals("LISTO")) {
                            String nombre = partes[1];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            for (int i = 0; i < salas.size(); i++) {
                                if (salas.get(i).getJugadores().contains(jugador)) {
                                    salas.get(i).listo(jugador);
                                    notificarObservadores("SALA_ACTUALIZADA");
                                    ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                    objectOut.writeObject(salas.get(i));
                                    objectOut.flush();
                                }
                            }
                        } else if (comando.equals("HACERMOVIMIENTO")) {
                            int fila = Integer.parseInt(partes[1]);
                            int columna = Integer.parseInt(partes[2]);
                            String nombre = partes[3];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            for (int i = 0; i < partidas.size(); i++) {
                                if (partidas.get(i).getDatos().getJugadores().contains(jugador)) {
                                    Tablero tablero = validarLinea(fila, columna, partidas.get(i).getTablero());
                                    if (tablero != null) {
                                        partidas.get(i).setTablero(tablero);
                                        TableroDTO campo = verificarCuadro(jugador, partidas.get(i).getTablero());
                                        if (campo != null) {
                                            partidas.get(i).getTablero().setTablero(campo.getTablero());
                                            partidas.get(i).setCuadrosLlenos(partidas.get(i).getCuadrosLlenos() + campo.getCuadrosCambiados());

                                        } else {
                                            campo = new TableroDTO();
                                            campo.setTablero(partidas.get(i).getTablero().getTablero());
                                        }
                                        notificarObservadores("PARTIDA_ACTUALIZADA");
                                        ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                        objectOut.writeObject(campo);
                                        objectOut.flush();
                                    } else {
                                        TableroDTO campo = null;
                                        ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                                        objectOut.writeObject(campo);
                                        objectOut.flush();
                                    }
                                }
                            }
                        } else if (comando.equals("JUEGOTERMINADO")) {
                            String nombre = partes[1];
                            Jugador jugador = null;
                            for (Jugador j : jugadores) {
                                if (j.getUsuario().equals(nombre)) {
                                    jugador = j;
                                    break;
                                }
                            }
                            if (jugador != null) {
                                for (int i = 0; i < salas.size(); i++) {
                                    List<Jugador> js = salas.get(i).getJugadores();
                                    for (int j = 0; j < js.size(); j++) {
                                        if (js.get(j).getUsuario().equals(jugador.getUsuario())) {
                                            for (int k = 0; k < partidas.size(); k++) {
                                                if (partidas.get(k).getDatos().getJugadores() == js) {
                                                    partidas.remove(k);
                                                    salas.remove(i);
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        } else if (comando.equals("SALAACTUALIZADA")) {
                            String nombre = partes[1];
                            Sala sala = obtenerActualizada(nombre);
                            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                            objectOut.writeObject(sala);
                            objectOut.flush();
                        } else if (comando.equals("PARTIDAACTUALIZADA")) {
                            String nombre = partes[1];
                            Partida partida = partidaActualizada(nombre);
                            ObjectOutputStream objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
                            objectOut.writeObject(partida);
                            objectOut.flush();
                        } else if (comando.equals("INICIARJUEGO")) {
                            String nombre = partes[1];
                            Sala datos = obtenerActualizada(nombre);
                            CtrlJuego ctrlJuego = new CtrlJuego();
                            char[][] tablero = ctrlJuego.obtenerTablero(datos.getJugadores().size());
                            Tablero campo = new Tablero();
                            campo.setTablero(tablero);
                            if (datos.getJugadores().size() == 2) {
                                campo.setTotalCuadros(81);
                                campo.setTamanio(10);
                            } else if (datos.getJugadores().size() == 3) {
                                campo.setTotalCuadros(361);
                                campo.setTamanio(20);
                            } else {
                                campo.setTotalCuadros(1521);
                                campo.setTamanio(40);
                            }
                            campo.setNumJugadores(jugadores.size());
                            Collections.shuffle(datos.getJugadores());
                            Partida partida = new Partida(datos, campo, 0);
                            partidas.add(partida);
                        }

                    } else {
                        System.out.println("Mensaje sin comandos recibido del cliente.");
                    }
                } else {
                    System.out.println("Mensaje nulo recibido del cliente.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void notificarObservadores(String mensaje) {
        for (ServidorObserver observador : observadores) {
            observador.actualizar(mensaje);
        }
    }

    private static SocketServidor instancia;

    public static SocketServidor getInstance() {
        if (instancia == null) {
            instancia = new SocketServidor();
        }
        return instancia;
    }

    public void agregarObservador(ServidorObserver observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ServidorObserver observador) {
        observadores.remove(observador);
    }
}
