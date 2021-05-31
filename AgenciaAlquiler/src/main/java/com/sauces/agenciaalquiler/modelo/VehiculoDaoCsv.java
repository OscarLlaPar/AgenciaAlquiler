/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class VehiculoDaoCsv implements VehiculoDao{
    public Path path;

    public VehiculoDaoCsv(String path) {
        this.path=Paths.get(path);
    }
    
    @Override
    public List<Vehiculo> listar() throws DaoException {
        List<Vehiculo> listado =new ArrayList<>();
        Vehiculo v=null;
        String linea;
        String[] tokens;
        String matricula;
        Grupo g;
        float capacidad;
        int plazas;
        try(BufferedReader archivo=Files.newBufferedReader(path)){
            linea=archivo.readLine();
            while(linea!=null){
                tokens=linea.split(",");
                matricula=tokens[1];
                g=Grupo.valueOf(tokens[2]);
                switch(tokens[0]){
                    case "Turismo": plazas=Integer.parseInt(tokens[3]);
                                    v=new Turismo(matricula, g, plazas);
                        break;
                    case "Furgoneta": capacidad=Float.parseFloat(tokens[3]);
                                    v=new Furgoneta(matricula, g, capacidad);
                        break;
                    default: throw new DaoException("Formato de datos incorrecto");
                }
                listado.add(v);
                linea=archivo.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(VehiculoDaoCsv.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MatriculaException ex) {
            Logger.getLogger(VehiculoDaoCsv.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        int n=0;
        try(BufferedWriter archivo =Files.newBufferedWriter(path)){
            for(Vehiculo v: vehiculos){
                archivo.write(v.getClass().getSimpleName()+","+v.toString());
                archivo.newLine();
                n++;
            }
        } catch (IOException ex) {
            throw new DaoException(ex.toString());
        }
        return n;
    }
    
}
