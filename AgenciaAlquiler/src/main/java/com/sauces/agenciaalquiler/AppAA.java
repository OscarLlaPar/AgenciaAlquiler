/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler;

import com.sauces.agenciaalquiler.controlador.Controlador;
import com.sauces.agenciaalquiler.modelo.AgenciaAlquiler;
import com.sauces.agenciaalquiler.vista.Ventana;

/**
 *
 * @author daw1
 */
public class AppAA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AgenciaAlquiler modelo=new AgenciaAlquiler("Sauces");
        Ventana vista=new Ventana();
        Controlador controlador= new Controlador(vista, modelo);
        vista.setControlador(controlador);
        controlador.iniciar();
    }
    
}
