package SocketCliente;

import DominioDTO.TableroDTO;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Sala;
import PipesAndFilters.InterfazObserver;
import PipesAndFilters.ServidorObserver;
import SocketServidor.SocketServidor;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketCliente implements ServidorObserver {

    //192.168.1.64 10.175.1.100
    private static final String SERVIDOR_IP = "10.178.1.177";
    private static final int PUERTO = 12345;

    private Jugador jugador;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private List<InterfazObserver> observadores = new ArrayList<>();

    public SocketCliente() {
        try {
            socket = new Socket(SERVIDOR_IP, PUERTO);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            iniciarHiloEscucha();
            SocketServidor.getInstance().agregarObservador(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void iniciarHiloEscucha() {
        Thread hiloEscucha = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String respuesta = recibirRespuesta();
                        // Maneja la respuesta según tus necesidades
                        System.out.println("Respuesta del servidor: " + respuesta);
                        // Aquí puedes actualizar tu interfaz gráfica con la respuesta recibida
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        hiloEscucha.start();
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

    private String recibirRespuesta() throws IOException {
        return in.readLine();
    }

    public String validarRegistro(final String nombreUsuario, final String avatarSeleccionado, final String colorSeleccionado) {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                try {
                    enviarMensaje("REGISTRO," + nombreUsuario + "," + avatarSeleccionado + "," + colorSeleccionado);
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

    public Sala unirseASala(String codigo, String nombreUsuario) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("UNIRSEASALA," + codigo + "," + nombreUsuario);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }

    public Sala unirsePublica(String nombreUsuario) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("UNIRSEPUBLICA," + nombreUsuario);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }

    public Sala crearSala(String jugador, String codigo) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("CREARSALA," + jugador + "," + codigo);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }

    public Sala eliminarJugador(String jugador) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("ELIMINARJUGADOR," + jugador);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }

    public Sala listo(String jugador) {
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("LISTO," + jugador);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }

    public TableroDTO hacerMovimiento(int fila, int columna, String jugador) {
        SwingWorker<TableroDTO, Void> worker = new SwingWorker<TableroDTO, Void>() {
            @Override
            protected TableroDTO doInBackground() throws Exception {
                try {
                    enviarMensaje("HACERMOVIMIENTO," + fila + columna + jugador);

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    TableroDTO salaRecibida = (TableroDTO) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
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
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @Override
    public void actualizar(String mensaje) {
        // Manejar la actualización según tus necesidades
        System.out.println("Actualización del servidor: " + mensaje);

        if (mensaje.equals("SALA_ACTUALIZADA")) {
            notificarObservadores("SALA_ACTUALIZADA");
        } else if (mensaje.equals("PARTIDA_ACTUALIZADA")) {
            notificarObservadores("PARTIDA_ACTUALIZADA");
        }

        // Actualizar la interfaz gráfica aquí
    }
    
    public void notificarObservadores(String mensaje) {
        for (InterfazObserver observador : observadores) {
            observador.actualizar(mensaje);
        }
    }
    
    public void agregarObservador(InterfazObserver observador) {
        observadores.add(observador);
    }
    
    public Sala obtenerSala(){
        SwingWorker<Sala, Void> worker = new SwingWorker<Sala, Void>() {
            @Override
            protected Sala doInBackground() throws Exception {
                try {
                    enviarMensaje("SALAACTUALIZADA," + jugador.getUsuario());

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Sala salaRecibida = (Sala) objectIn.readObject();

                    return salaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }
    
    public Partida obtenerPartida(){
        SwingWorker<Partida, Void> worker = new SwingWorker<Partida, Void>() {
            @Override
            protected Partida doInBackground() throws Exception {
                try {
                    enviarMensaje("PARTIDAACTUALIZADA," + jugador.getUsuario());

                    // Aquí puedes esperar la respuesta del servidor, que debería ser un objeto Sala
                    // Asegúrate de manejar la deserialización correctamente
                    ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
                    Partida partidaRecibida = (Partida) objectIn.readObject();

                    return partidaRecibida;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null; // Maneja el error según tus necesidades
                }
            }
        };

        worker.execute();

        try {
            return worker.get(); // Espera hasta que el SwingWorker termine
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Maneja el error según tus necesidades
        }
    }
}
