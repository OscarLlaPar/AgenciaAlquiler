/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

import java.util.Comparator;

/**
 *
 * @author daw1
 */
public class ComparadorPrecio implements Comparator<Vehiculo>{
    public int compare(Vehiculo v1, Vehiculo v2){
        int salida=0;
        float precio1, precio2;

        precio1=v1.getPrecioAlquiler();
        precio2=v2.getPrecioAlquiler();

        if(precio1<precio2){
            salida=-1;
        }
        else if(precio1>precio2){
            salida=1;
        }
        return salida;
    }
}
