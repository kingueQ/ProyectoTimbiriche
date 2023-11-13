/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.awt.Color;

public class Colores {
    public static final String AZUL = "Azul";
    public static final String ROJO = "Rojo";
    public static final String VERDE = "Verde";
    public static final String AMARILLO = "Amarillo";

    public static Color getColor(String nombre) {
        switch (nombre) {
            case AZUL:
                return Color.BLUE;
            case ROJO:
                return Color.RED;
            case VERDE:
                return Color.GREEN;
            case AMARILLO:
                return Color.YELLOW;
            default:
                return null;
        }
    }

    public static String[] getNombresColores() {
        return new String[]{AZUL, ROJO, VERDE, AMARILLO};
    }
    
    public static Color[] getColores() {
    return new Color[]{Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW};
}
}
