/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author daw1
 */
public abstract class Vehiculo implements Comparable<Vehiculo>, Serializable{
    private Matricula matricula;
    private Grupo grupo;

    public Vehiculo(String matricula, Grupo grupo) throws MatriculaException {
        this.matricula = new Matricula(matricula);
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return  matricula + "," + grupo;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    public String getMatricula() {
        return matricula.toString();
    }

    public void setMatricula(String matricula) throws MatriculaException {
        this.matricula = new Matricula(matricula);
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public boolean equals(Object obj) {
        
        if(this==obj){
            return true;
        }
        if(obj!=null){
            if(obj instanceof Vehiculo){
                Vehiculo v=(Vehiculo)obj;
                if (this.matricula.equals(v.matricula)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    public int compareTo(Vehiculo v){
        return this.matricula.compareTo(v.matricula);
    }
    
    public abstract float getPrecioAlquiler();
    
    public abstract float getPrecioAlquiler(int dias);
}
