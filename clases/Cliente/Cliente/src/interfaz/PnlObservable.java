/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import Dominio.FormaJuego;
import java.util.List;

/**
 *
 * @author Alejandro Galindo
 */
public interface PnlObservable {
    void agrega(PnlObservador observador);
    void notificaMovimiento(List<FormaJuego> movimiento);
}