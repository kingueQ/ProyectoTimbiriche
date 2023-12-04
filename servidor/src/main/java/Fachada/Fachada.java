/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;

import Controlador.CtrlServidor;
import DominioDTO.TableroDTO;
import Modelo.Jugador;
import Modelo.Sala;
import Modelo.Tablero;

/**
 *
 * @author kingu
 */
public class Fachada {
    CtrlServidor ctrlServidor=new CtrlServidor();
    public Sala crearSala(boolean publica){
        if(publica){
            return this.ctrlServidor.crearPublica();
        }else{
            return this.ctrlServidor.crearPrivada();
        }
    }
    
}
