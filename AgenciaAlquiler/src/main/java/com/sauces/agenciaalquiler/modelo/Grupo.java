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
public enum Grupo {
    A  (50,1.5f,5),B (55,2,10),C (60,2.5f,15);
    
    private final float precioBase;
    
    private final float factorTurismo;
    
        private final float factorFurgoneta;

    private Grupo(float precioBase, float factorTurismo, float factorFurgoneta) {
        this.precioBase = precioBase;
        this.factorTurismo = factorTurismo;
        this.factorFurgoneta = factorFurgoneta;
    }
        
    

    /**
     * Get the value of factorFurgoneta
     *
     * @return the value of factorFurgoneta
     */
    public float getFactorFurgoneta() {
        return factorFurgoneta;
    }


    /**
     * Get the value of factorTurismo
     *
     * @return the value of factorTurismo
     */
    public float getFactorTurismo() {
        return factorTurismo;
    }


    /**
     * Get the value of precioBase
     *
     * @return the value of precioBase
     */
    public float getPrecioBase() {
        return precioBase;
    }

}
