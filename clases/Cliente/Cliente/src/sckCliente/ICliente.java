/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckCliente;

import interfaz.IActualizable;

/**
 *
 * @author Abraham Quintana y Rafael Soqui
 */
public interface ICliente { 
    boolean conectarAlServidor(String address, int port);
    boolean enviarAlServidor(Object mensaje);
    void escucharAlServidor();
}
