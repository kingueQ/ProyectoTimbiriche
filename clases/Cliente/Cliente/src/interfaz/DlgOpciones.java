/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Color;

/**
 *
 * @author Abraham Quintana y Rafael Soqui
 */
public class DlgOpciones extends javax.swing.JDialog {

    private String tipoSeleccionado;
    /**
     * Creates new form DlgOpciones
     */
    public DlgOpciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.getContentPane().setBackground(Color.BLACK);
        tipoSeleccionado = null;
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
        btnRapido = new javax.swing.JButton();
        btnCodigo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("Opciones");

        btnRapido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRapido.setForeground(new java.awt.Color(255, 0, 51));
        btnRapido.setText("Unión Rápida");
        btnRapido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRapidoActionPerformed(evt);
            }
        });

        btnCodigo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCodigo.setForeground(new java.awt.Color(255, 0, 51));
        btnCodigo.setText("Tengo un Código de Sala");
        btnCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigoActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 0, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnRapido))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnCodigo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel1)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(btnRapido)
                .addGap(38, 38, 38)
                .addComponent(btnCodigo)
                .addGap(41, 41, 41)
                .addComponent(btnCancelar)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigoActionPerformed
        // TODO add your handling code here:
        tipoSeleccionado = "Privada";
        dispose();
    }//GEN-LAST:event_btnCodigoActionPerformed

    private void btnRapidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRapidoActionPerformed
        // TODO add your handling code here:
        tipoSeleccionado = "Publica";
        dispose();
    }//GEN-LAST:event_btnRapidoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        tipoSeleccionado = null;  // Restablece la selección a null
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public String getTipoSeleccionado() {
        return tipoSeleccionado;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCodigo;
    private javax.swing.JButton btnRapido;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
