/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.CtrlServidor;
import Controlador.CtrlVistas;
import Modelo.Jugador;
import SocketCliente.SocketCliente;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author kingu
 */
public class FrmFinJuego extends javax.swing.JFrame {

    List<Jugador> jugadores;
    SocketCliente socketCliente;

    /**
     * Creates new form FrmFinJuego
     */
    public FrmFinJuego(SocketCliente socketCliente, List<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.socketCliente = socketCliente;
        initComponents();

        Collections.sort(jugadores, Comparator.comparingInt(Jugador::getPuntuacion).reversed());
        this.getContentPane().setBackground(Color.BLACK);
        this.llenarPantalla();
    }

    public void llenarPantalla() {
        this.lblGanador.setText(this.jugadores.get(0).getUsuario());
        this.lblGanador.setForeground(this.jugadores.get(0).getColor());
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(jugadores.get(0).getAvatar()));
            Image imagenRedimensionada = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
            lblAvatarGanador.setIcon(iconoRedimensionado);
        } catch (Exception e) {
            e.printStackTrace(); // Puedes cambiar esto por un manejo más específico de la excepción
        }
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/trofeo.jpg"));
            Image imagenRedimensionada = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);
            lblTrofeo.setIcon(iconoRedimensionado);
        } catch (Exception e) {
            e.printStackTrace(); // Puedes cambiar esto por un manejo más específico de la excepción
        }
        for (int i = 0; i < jugadores.size(); i++) {
            this.tblJugadores.setValueAt(jugadores.get(i).getUsuario(), i, 0);
            this.tblJugadores.setValueAt(jugadores.get(i).getPuntuacion(), i, 1);
        }
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
        lblTrofeo = new javax.swing.JLabel();
        lblAvatarGanador = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblGanador = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblJugadores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("Fin del Juego");

        lblTrofeo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/trofeo.jpg"))); // NOI18N
        lblTrofeo.setText("Trofeo");

        lblAvatarGanador.setText("Avatar ganador");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 0, 102));
        jLabel4.setText("Ganador:");

        lblGanador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGanador.setForeground(new java.awt.Color(0, 102, 255));
        lblGanador.setText("Jugador ganador");

        tblJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Jugador", "Puntos"
            }
        ));
        jScrollPane1.setViewportView(tblJugadores);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 51));
        jButton1.setText("Revancha");

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 0, 51));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(74, 74, 74))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSalir)
                                .addGap(23, 23, 23)))
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTrofeo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblGanador))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAvatarGanador, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(lblGanador))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAvatarGanador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTrofeo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29)
                        .addComponent(btnSalir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.socketCliente.finalizarJuego(this.socketCliente.getJugador().getUsuario());
        CtrlVistas ctrlVistas = new CtrlVistas();
        ctrlVistas.showMenu(socketCliente);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatarGanador;
    private javax.swing.JLabel lblGanador;
    private javax.swing.JLabel lblTrofeo;
    private javax.swing.JTable tblJugadores;
    // End of variables declaration//GEN-END:variables
}
