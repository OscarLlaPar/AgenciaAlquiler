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
public class Matricula implements Comparable<Matricula>, Serializable{
    private String matricula;

    public Matricula(String matricula) throws MatriculaException {
        if(!Matricula.esValida(matricula)){
            throw new MatriculaException("Matricula incorrecta");
        }
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return matricula;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(Matricula m) {
        return this.matricula.compareTo(m.matricula);
    }
    
    public static boolean esValida(String matricula){
        String expresionRegular="([0-9]{4})([B-Z &&[^EIOUQ]]{3})";
        Pattern p=Pattern.compile(expresionRegular);
        Matcher m=p.matcher(matricula);
        return m.matches();
    }
    
}
