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
public class Avatar {
    public final String CHARMANDER = "/img/charmander.jpg";
    public final String SQUIRTLE = "/img/squirtle.jpg";
    public final String BULBASAUR = "/img/bulbasaur.jpg";
    public final String PIKACHU = "/img/pikachu.jpg";
    
    public String[] getNombres() {
        return new String[]{CHARMANDER, SQUIRTLE, BULBASAUR, PIKACHU};
    }
}
