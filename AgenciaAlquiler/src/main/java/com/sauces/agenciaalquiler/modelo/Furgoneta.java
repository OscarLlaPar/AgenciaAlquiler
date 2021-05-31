/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

/**
 *
 * @author daw1
 */
public class Furgoneta extends Vehiculo{
    private float capacidad;

    public Furgoneta(String matricula, Grupo grupo, float capacidad) throws MatriculaException {
        super(matricula, grupo);
        this.capacidad = capacidad;
    }
   
    @Override
    public String toString() {
        return super.toString()+","+capacidad;
    }

    public float getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(float capacidad) {
        this.capacidad = capacidad;
    }
    
    
    
    @Override
    public float getPrecioAlquiler() {
        return getGrupo().getPrecioBase()+getGrupo().getFactorFurgoneta()*capacidad;
    }

    @Override
    public float getPrecioAlquiler(int dias) {
        return getPrecioAlquiler()*dias;
    }

    
}
