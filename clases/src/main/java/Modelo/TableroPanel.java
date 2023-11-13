/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.awt.Color;
import java.awt.Graphics;

public class TableroPanel extends javax.swing.JPanel {
    private char[][] matrizTablero;

    public TableroPanel(char[][] matrizTablero) {
        System.out.println("Ancho TableroPanel: " + getWidth());
        System.out.println("Alto TableroPanel: " + getHeight());
        this.matrizTablero = matrizTablero;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int filaHeight = getHeight() / matrizTablero.length;
        int columnaWidth = getWidth() / matrizTablero[0].length;

        for (int fila = 0; fila < matrizTablero.length; fila++) {
            for (int columna = 0; columna < matrizTablero[0].length; columna++) {
                if (matrizTablero[fila][columna] == '-') {
                    g.drawLine(columna * columnaWidth, fila * filaHeight, (columna + 1) * columnaWidth, fila * filaHeight);
                } else if (matrizTablero[fila][columna] == '|') {
                    g.drawLine(columna * columnaWidth, fila * filaHeight, columna * columnaWidth, (fila + 1) * filaHeight);
                } else if (matrizTablero[fila][columna] != ' ') {
                    // Aquí puedes personalizar la representación de los jugadores en el tablero
                    g.drawString(String.valueOf(matrizTablero[fila][columna]), columna * columnaWidth + columnaWidth / 2, fila * filaHeight + filaHeight / 2);
                }
            }
        }
    }
}
