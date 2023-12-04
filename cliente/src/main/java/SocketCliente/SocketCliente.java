package SocketCliente;

import DominioDTO.TableroDTO;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Sala;
import Observer.InterfazObserver;
import Observer.ServidorObserver;
import SocketServidor.SocketServidor;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketCliente implements ServidorObserver {

    private static final String SERVIDOR_IP = "192.168.1.65";
    private static final int PUERTO = 12345;

    private Jugador jugador;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private List<InterfazObserver> observadores = new ArrayList<>();
    private boolean isRunning;

    public SocketCliente() {
        try {
            this.establecerConexion();
            SocketServidor.getInstance().agregarObservador(this);
            isRunning = true;
//            new Thread(this::escucharMensajes).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void establecerConexion() throws IOException {
        socket = new Socket(SERVIDOR_IP, PUERTO);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    private void escucharMensajes() {
        try {
            while (isRunning) {
                String mensaje = recibirRespuesta();
                if (mensaje != null && mensaje.equals("CONEXION_CERRADA")) {
                    isRunning = false;
                    cerrarConexion();
                } else {
                    notificarObservadores(mensaje);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void enviarMensaje(String mensaje) throws IOException {
        this.establecerConexion();
        try {
            if (socket != null && socket.isConnected() && !socket.isClosed()) {
                out.println(mensaje);
            } else {
                System.err.println("El socket está cerrado o no conectado.");
            }
        } catch (Exception e) {
            System.err.println("Error al enviar mensaje: " + e.getMessage());
            e.printStackTrace();
        } 
    }

    private String recibirRespuesta() {
        try {
            return in.readLine();
        } catch (SocketException se) {
            se.printStackTrace();
            return "CONEXION_CERRADA";
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR_DE_LECTURA";
        }
    }

    public void cerrarConexion() {
        try {
            isRunning = false;
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

    public Boolean validarRegistro(final String nombreUsuario, final String avatarSeleccionado, final String colorSeleccionado) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                try {
                    enviarMensaje("REGISTRO," + nombreUsuario + "," + avatarSeleccionado + "," + colorSeleccionado);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    boolean res = (boolean) objectIn.readObject();
                    return res;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Devuelve false en caso de excepción
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

    public Sala unirseASala(String codigo, String nombreUsuario) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("UNIRSEASALA," + codigo + "," + nombreUsuario);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sala unirsePublica(String nombreUsuario) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("UNIRSEPUBLICA," + nombreUsuario);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sala crearSala(String jugador, String codigo) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("CREARSALA," + jugador + "," + codigo);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sala eliminarJugador(String jugador) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("ELIMINARJUGADOR," + jugador);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Sala listo(String jugador) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("LISTO," + jugador);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TableroDTO hacerMovimiento(int fila, int columna, String jugador) {
        SwingWorker<TableroDTO, Void> worker = new SwingWorker<TableroDTO, Void>() {
            @Override
            protected TableroDTO doInBackground() throws Exception {
                try {
                    enviarMensaje("HACERMOVIMIENTO," + fila + "," + columna + "," + jugador);
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    TableroDTO salaRecibida = (TableroDTO) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String finalizarJuego(final String nombreUsuario) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    enviarMensaje("JUEGOTERMINADO," + nombreUsuario);
                    return recibirRespuesta();
                } catch (Exception e) {
                    e.printStackTrace();
                    return "ERROR";
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Actualización del servidor: " + mensaje);
        if (mensaje.equals("SALA_ACTUALIZADA")) {
            notificarObservadores("SALA_ACTUALIZADA");
        } else if (mensaje.equals("PARTIDA_ACTUALIZADA")) {
            notificarObservadores("PARTIDA_ACTUALIZADA");
        }
    }

    public void notificarObservadores(String mensaje) {
        for (InterfazObserver observador : observadores) {
            observador.actualizar(mensaje);
        }
    }

    public void agregarObservador(InterfazObserver observador) {
        observadores.add(observador);
    }

    public Sala obtenerSala() {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("SALAACTUALIZADA," + jugador.getUsuario());
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();
                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Partida obtenerPartida() {
        SwingWorker<Partida, Void> worker = new SwingWorker<Partida, Void>() {
            @Override
            protected Partida doInBackground() throws Exception {
                try {
                    enviarMensaje("PARTIDAACTUALIZADA," + jugador.getUsuario());
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Partida partidaRecibida = (Partida) objectIn.readObject();
                    return partidaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        worker.execute();
        try {
            return worker.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void iniciarJuego() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    enviarMensaje("INICIARJUEGO," + jugador.getUsuario());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cerrarConexion();
                }
                return null;
            }
        };
        worker.execute();
    }

    public void eliminarObservador() {
        SocketServidor.getInstance().eliminarObservador(this);
    }
}
