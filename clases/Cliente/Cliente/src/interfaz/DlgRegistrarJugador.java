/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Dominio.Jugador;
import Dominio.Preferencia;
import interfaz.MenuInicial;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Abraham Quintana y Rafael Soqui
 */
public class DlgRegistrarJugador extends javax.swing.JDialog {

    private MenuInicial menu;
    String[] avatares={"bulbasaur.jpg", "squirtle.jpg", "charmander.jpg", "pikachu.jpg"};
    /**
     * Creates new form FrmRegistro
     */
    public DlgRegistrarJugador(MenuInicial parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.menu = parent;
        this.setLocationRelativeTo(menu);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Registro");

        this.getContentPane().setBackground(Color.BLACK);
        llenarComboBoxAvatar();
        llenarComboBoxColores();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nickname = new javax.swing.JTextField();
        cbxAvatar = new javax.swing.JComboBox<>();
        btnRegresar = new javax.swing.JButton();
        btnIngresar = new javax.swing.JButton();
        cbxColor = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 153));
        jLabel1.setText("Registrarse");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Usuario:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Color:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("Avatar:");

        cbxAvatar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato", "Pescado", "Paloma" }));

        btnRegresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegresar.setForeground(new java.awt.Color(255, 0, 51));
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnIngresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 0, 51));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        cbxColor.setBackground(new java.awt.Color(0, 0, 0));
        cbxColor.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(btnRegresar))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIngresar)
                            .addComponent(cbxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1)))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbxColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegresar)
                    .addComponent(btnIngresar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que quiere cerrar el juego?", "Confirmacion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String mensaje = "";

        if (nickname.getText().equalsIgnoreCase("")) {
            mensaje += "Escribe un nickname \r\n";
        }

        if (!mensaje.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, mensaje, "Registro de jugador", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Jugador jugador = new Jugador(this.nickname.getText(), this.avatares[this.cbxAvatar.getSelectedIndex()]);
            System.out.println(this.cbxAvatar.getSelectedItem().toString());
            String colorSel=this.cbxColor.getSelectedItem().toString();
            String[] otros=new String[3];
            String colorJ="";
            int pos=0;
            if(colorSel.equals("verde")){
                colorJ=this.convertirColorAHexadecimal(Color.GREEN);
            }else{
                otros[pos]=this.convertirColorAHexadecimal(Color.GREEN);
            }
            if(colorSel.equals("azul")){
                colorJ=this.convertirColorAHexadecimal(Color.BLUE);
            }else{
                otros[pos]=this.convertirColorAHexadecimal(Color.BLUE);
            }
            if(colorSel.equals("rojo")){
                colorJ=this.convertirColorAHexadecimal(Color.RED);
            }else{
                otros[pos]=this.convertirColorAHexadecimal(Color.RED);
            }
            if(colorSel.equals("amarillo")){
                colorJ=this.convertirColorAHexadecimal(Color.YELLOW);
            }else{
                otros[pos]=this.convertirColorAHexadecimal(Color.YELLOW);
            }
            Preferencia pref = new Preferencia(otros[0], otros[1], otros[2]);
            jugador.setPreferencia(pref);
            jugador.setColor(colorJ);
            menu.setJugador(jugador);
            this.dispose();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    public String convertirColorAHexadecimal(Color color) {
        // Extraer los componentes RGB del color
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        // Convertir los componentes RGB a formato hexadecimal
        String hexRed = Integer.toHexString(red);
        String hexGreen = Integer.toHexString(green);
        String hexBlue = Integer.toHexString(blue);

        // Asegurarse de que cada componente tenga dos dígitos
        hexRed = completarConCeros(hexRed);
        hexGreen = completarConCeros(hexGreen);
        hexBlue = completarConCeros(hexBlue);

        // Concatenar los componentes en el formato hexadecimal final
        return "#" + hexRed + hexGreen + hexBlue;
    }

    public String completarConCeros(String componente) {
        return componente.length() == 1 ? "0" + componente : componente;
    }
    
    private void llenarComboBoxAvatar() {
        
        // Configura el ComboBox con un nuevo modelo
        cbxAvatar.setModel(new DefaultComboBoxModel<>(avatares));

        // Establece un renderer personalizado para mostrar imágenes en lugar de texto
        cbxAvatar.setRenderer(new ImageComboBoxRenderer());
    }

    private class ImageComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {

        private static final int ICON_WIDTH = 50; // Ajusta el ancho de la imagen según tus preferencias
        private static final int ICON_HEIGHT = 50; // Ajusta el alto de la imagen según tus preferencias

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // Obtiene el nombre de la constante
            String nombre = (String) value;

            // Crea un ImageIcon a partir de la dirección de la imagen y ajusta su tamaño
            ImageIcon icono = new ImageIcon("src\\Avatares\\" + nombre);
            Image imagenRedimensionada = icono.getImage().getScaledInstance(ICON_WIDTH, ICON_HEIGHT, Image.SCALE_SMOOTH);
            ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);

            // Configura el JLabel para mostrar la imagen redimensionada
            setIcon(iconoRedimensionado);
            setText("");  // Oculta el texto

            // Establece el fondo y la selección según sea necesario
            setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

            return this;
        }

    }

    private void llenarComboBoxColores() {
        String[] colores={"verde", "azul", "rojo", "amarillo"};
        // Configura el ComboBox con un nuevo modelo que acepta objetos String
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(colores);
        cbxColor.setModel(model);

        // Agrega el ListCellRenderer personalizado
        cbxColor.setRenderer(new ColorComboBoxRenderer());
    }

    private class ColorComboBoxRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String colorNombre = (String) value;

            // Configura el color del texto según la opción seleccionada
//            Color color = Colores.getColor(colorNombre);
            Color color=getColor(colorNombre);
            if (color != null) {
                label.setForeground(color);
            }

            return label;
        }
    }
    
    private Color getColor(String nombre){
        if(nombre.equals("verde")){
            return Color.GREEN;
        }
        if(nombre.equals("azul")){
            return Color.BLUE;
        }
        if(nombre.equals("rojo")){
            return Color.RED;
        }
        return Color.YELLOW;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cbxAvatar;
    private javax.swing.JComboBox<String> cbxColor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nickname;
    // End of variables declaration//GEN-END:variables
}
