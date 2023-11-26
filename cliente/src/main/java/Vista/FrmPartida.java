/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CtrlJuego;
import Controlador.CtrlServidor;
import Controlador.CtrlVistas;
import DominioDTO.TableroDTO;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Sala;
import Modelo.Tablero;
import Modelo.TableroPanel;
import SocketCliente.SocketCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author kingu
 */
public class FrmPartida extends javax.swing.JFrame {

    CtrlJuego ctrlJuego = new CtrlJuego();
    List<Jugador> jugadores;
    SocketCliente socketCliente;
    CtrlServidor ctrlServidor;
    private int indiceTurnoActual = 0;
    Jugador jugadorActual;
    Sala datos;
    Partida partida;
    TableroPanel tableroPanel;
    /**
     * Creates new form Partida
     */
    public FrmPartida(Sala datos, SocketCliente socketCliente, CtrlServidor ctrlServidor) {
        this.datos=datos;
        this.jugadores=datos.getJugadores();
        this.socketCliente = socketCliente;
        this.ctrlServidor = ctrlServidor;
        char[][] tablero = ctrlJuego.obtenerTablero(jugadores.size());
        Tablero campo=new Tablero();
        campo.setTablero(tablero);
        if(jugadores.size()==2){
            campo.setTotalCuadros(81);
            campo.setTamanio(10);
        }else if(jugadores.size()==3){
            campo.setTotalCuadros(361);
            campo.setTamanio(20);
        }else{
            campo.setTotalCuadros(1521);
            campo.setTamanio(40);
        }
        campo.setNumJugadores(jugadores.size());
        partida=new Partida(this.datos, campo, 0);
        initComponents();

        this.getContentPane().setBackground(Color.BLACK);
        tableroPanel = new TableroPanel(tablero);
        tableroPanel.setOpaque(false);  // Hacer que el panel sea transparente

        // Agregar el TableroPanel al pnlTablero
        pnlTablero.setLayout(new BorderLayout());
        pnlTablero.add(tableroPanel, BorderLayout.CENTER);

        pnlTablero.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Obtener las coordenadas del clic
                int x = evt.getX();
                int y = evt.getY();

                // Calcular las coordenadas en la matriz según el tamaño del tablero y la posición del clic
                int fila = (y * tablero.length) / pnlTablero.getHeight();
                int columna = (x * tablero[0].length) / pnlTablero.getWidth();

                hacerMovimiento(fila, columna);
                // Mostrar las coordenadas en la consola (puedes adaptar esto según tus necesidades)
                System.out.println("Coordenadas del clic: (" + fila + ", " + columna + ")");
            }
        });

        Collections.shuffle(this.jugadores);
        this.cargarJugadores();
        this.siguienteTurno();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblAvatar1 = new javax.swing.JLabel();
        lblAvatar2 = new javax.swing.JLabel();
        lblAvatar3 = new javax.swing.JLabel();
        lblAvatar4 = new javax.swing.JLabel();
        btnAbandonar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblJugadorActual = new javax.swing.JLabel();
        lblJugador1 = new javax.swing.JLabel();
        lblJugador2 = new javax.swing.JLabel();
        lblJugador3 = new javax.swing.JLabel();
        lblJugador4 = new javax.swing.JLabel();
        pnlTablero = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCuadros = new javax.swing.JLabel();
        lblPuntos1 = new javax.swing.JLabel();
        lblPuntos2 = new javax.swing.JLabel();
        lblPuntos3 = new javax.swing.JLabel();
        lblPuntos4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("Partida");

        btnAbandonar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAbandonar.setForeground(new java.awt.Color(255, 0, 51));
        btnAbandonar.setText("Abandonar");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Turno actual:");

        lblJugadorActual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblJugadorActual.setForeground(new java.awt.Color(0, 102, 255));
        lblJugadorActual.setText("Jugador 1");

        lblJugador1.setText("Jugador 1");

        lblJugador2.setText("Jugador 2");

        lblJugador3.setText("Jugador 3");

        lblJugador4.setText("Jugador 4");

        pnlTablero.setMinimumSize(new java.awt.Dimension(500, 500));
        pnlTablero.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout pnlTableroLayout = new javax.swing.GroupLayout(pnlTablero);
        pnlTablero.setLayout(pnlTableroLayout);
        pnlTableroLayout.setHorizontalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTableroLayout.setVerticalGroup(
            pnlTableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Cuadros llenados:");

        lblCuadros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCuadros.setForeground(new java.awt.Color(0, 102, 255));
        lblCuadros.setText("Numero");

        lblPuntos1.setText("puntos1");

        lblPuntos2.setText("puntos2");

        lblPuntos3.setText("puntos3");

        lblPuntos4.setText("puntos4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(224, 224, 224))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblJugador1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAvatar3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblJugador3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAbandonar)
                                .addGap(228, 228, 228)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblAvatar2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                .addComponent(lblAvatar4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPuntos2)
                                    .addComponent(lblJugador2)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblPuntos4)
                                .addComponent(lblJugador4)))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(lblJugadorActual)
                            .addComponent(lblPuntos1)
                            .addComponent(lblPuntos3))
                        .addGap(50, 50, 50)
                        .addComponent(pnlTablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(lblCuadros)))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(lblAvatar2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJugador1)
                            .addComponent(lblJugador2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPuntos1)
                                .addGap(172, 172, 172)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lblJugadorActual))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPuntos2)
                                .addGap(164, 164, 164)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblCuadros)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPuntos3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPuntos4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJugador4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblJugador3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAvatar3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(lblAvatar4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(pnlTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAbandonar)
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
        // TODO add your handling code here:
        CtrlVistas controlador = new CtrlVistas();
        controlador.showMenu(this.socketCliente, this.ctrlServidor);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnAbandonarActionPerformed

    private void cargarJugadores() {
        cargarJugador(0, lblJugador1, lblAvatar1, lblPuntos1);
        cargarJugador(1, lblJugador2, lblAvatar2, lblPuntos2);
        cargarJugador(2, lblJugador3, lblAvatar3, lblPuntos3);
        cargarJugador(3, lblJugador4, lblAvatar4, lblPuntos4);
    }

    private void cargarJugador(int indice, JLabel lblJugador, JLabel lblAvatar, JLabel lblPuntos) {
        if (jugadores.size() > indice) {
            lblPuntos.setText(jugadores.get(indice).getPuntuacion() + "");
            lblPuntos.setForeground(jugadores.get(indice).getColor());
            lblJugador.setText(jugadores.get(indice).getUsuario());
            lblJugador.setForeground(jugadores.get(indice).getColor());
            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(jugadores.get(indice).getAvatar()));
                Image imagenRedimensionada = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
                lblAvatar.setIcon(iconoRedimensionado);
            } catch (Exception e) {
                e.printStackTrace(); // Puedes cambiar esto por un manejo más específico de la excepción
            }
        } else {
            lblJugador.setText("");
            lblAvatar.setIcon(null);
        }
    }

    public void siguienteTurno() {
        indiceTurnoActual = (indiceTurnoActual + 1) % jugadores.size();
        jugadorActual = jugadores.get(indiceTurnoActual);
        lblJugadorActual.setText(jugadorActual.getUsuario());
        lblJugadorActual.setForeground(jugadorActual.getColor());
    }
    
    public void hacerMovimiento(int fila, int columna){
        Tablero nuevo=this.ctrlServidor.validarLinea(fila, columna, this.partida.getTablero());
        if(nuevo!=null){
            this.partida.setTablero(nuevo);
            TableroDTO nuevo2=this.ctrlServidor.verificarCuadro(this.jugadorActual, partida.getTablero());
            if(nuevo2!=null){
                this.partida.getTablero().setTablero(nuevo2.getTablero());
                this.partida.setCuadrosLlenos(this.partida.getCuadrosLlenos()+nuevo2.getCuadrosCambiados());
                this.jugadores.get(indiceTurnoActual).setPuntuacion(this.jugadorActual.getPuntuacion()+nuevo2.getCuadrosCambiados());
                this.jugadorActual=this.jugadores.get(indiceTurnoActual);
                this.actualizarTablero(partida.getTablero());
                if(this.partida.getCuadrosLlenos()==this.partida.getTablero().getTotalCuadros()){
                    JOptionPane.showMessageDialog(this, "El juego ha terminado");
                    CtrlVistas ctrl=new CtrlVistas();
                    ctrl.finJuego(this.socketCliente, ctrlServidor, this.jugadores);
                    this.setVisible(false);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(this, "Haz creado un cuadro, es tu turno nuevamente " + this.jugadorActual.getUsuario());
                }
            }else{
                this.actualizarTablero(partida.getTablero());
                this.siguienteTurno();
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ese movimiento es inválido, intenta de nuevo");
        }
    }
    
    public void actualizarTablero(Tablero tablero1){
        this.cargarJugadores();
        this.lblCuadros.setText(this.partida.getCuadrosLlenos() + "/" + this.partida.getTablero().getTotalCuadros());
        
        char[][] tablero=tablero1.getTablero();
        tableroPanel=new TableroPanel(tablero);
        tableroPanel.setOpaque(false);  // Hacer que el panel sea transparente

        // Agregar el TableroPanel al pnlTablero
        pnlTablero.setLayout(new BorderLayout());
        pnlTablero.add(tableroPanel, BorderLayout.CENTER);

//        pnlTablero.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                // Obtener las coordenadas del clic
//                int x = evt.getX();
//                int y = evt.getY();
//
//                // Calcular las coordenadas en la matriz según el tamaño del tablero y la posición del clic
//                int fila = (y * tablero.length) / pnlTablero.getHeight();
//                int columna = (x * tablero[0].length) / pnlTablero.getWidth();
//
//                hacerMovimiento(fila, columna);
//                // Mostrar las coordenadas en la consola (puedes adaptar esto según tus necesidades)
//                System.out.println("Coordenadas del clic: (" + fila + ", " + columna + ")");
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblAvatar1;
    private javax.swing.JLabel lblAvatar2;
    private javax.swing.JLabel lblAvatar3;
    private javax.swing.JLabel lblAvatar4;
    private javax.swing.JLabel lblCuadros;
    private javax.swing.JLabel lblJugador1;
    private javax.swing.JLabel lblJugador2;
    private javax.swing.JLabel lblJugador3;
    private javax.swing.JLabel lblJugador4;
    private javax.swing.JLabel lblJugadorActual;
    private javax.swing.JLabel lblPuntos1;
    private javax.swing.JLabel lblPuntos2;
    private javax.swing.JLabel lblPuntos3;
    private javax.swing.JLabel lblPuntos4;
    private javax.swing.JPanel pnlTablero;
    // End of variables declaration//GEN-END:variables
}
