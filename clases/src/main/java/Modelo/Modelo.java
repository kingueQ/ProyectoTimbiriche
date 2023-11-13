/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Color;
import java.security.SecureRandom;

/**
 *
 * @author kingu
 */
public class Modelo {
    Jugador jugador=new Jugador();
    Colores colores=new Colores();
    
    public String getUsuario() {
        return jugador.getUsuario();
    }

    public void setUsuario(String usuario) {
        this.jugador.setUsuario(usuario);
    }

    public String getAvatar() {
        return this.jugador.getAvatar();
    }

    public void setAvatar(String avatar) {
        this.jugador.setAvatar(avatar);
    }

    public Color getColor() {
        return this.jugador.getColor();
    }

    public void setColor(Color color) {
        this.jugador.setColor(color);
    }
    
    public Color getColor(String nombre){
        return this.colores.getColor(nombre);
    }
    
    public char[][] crearTablero(int tamanio){
        Tablero tablero=new Tablero();
        tablero.crearTablero(tamanio);
        return tablero.getTablero();
    }
    
    public Sala crearSalaPublica(){
        Sala sala=new Sala();
        sala.setDisponibilidad(4);
        sala.setPublica(true);
        sala.setCodigo("Publica");
        return sala;
    }
    
    public Sala crearSalaPrivada(){
        Sala sala=new Sala();
        sala.setDisponibilidad(4);
        sala.setPublica(true);
        String CARACTERES_ALFANUMERICOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int indice = random.nextInt(CARACTERES_ALFANUMERICOS.length());
            char caracter = CARACTERES_ALFANUMERICOS.charAt(indice);
            codigo.append(caracter);
        }
        sala.setCodigo(codigo.toString());
        return sala;
    }
}
