/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

import java.util.InputMismatchException;

/**
 *
 * @author daw1
 */
public class Turismo extends Vehiculo{
    private int plazas;

    public Turismo(String matricula, Grupo grupo, int plazas) throws MatriculaException  {
        super(matricula, grupo);
        
            this.plazas = plazas;
        
    }

    @Override
    public String toString() {
        return super.toString()+","+plazas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
            this.plazas = plazas;
    }

    
    
    @Override
    public float getPrecioAlquiler() {
        return getGrupo().getPrecioBase()+getGrupo().getFactorTurismo()*plazas;
    }

    @Override
    public float getPrecioAlquiler(int dias) {
        
        return getPrecioAlquiler()*dias;
    }

    
}
