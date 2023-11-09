/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author kingu
 */
public class Modelo {
    Jugador jugador=new Jugador();
    
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

    public String getColor() {
        return this.jugador.getColor();
    }

    public void setColor(String color) {
        this.jugador.setColor(color);
    }
}
