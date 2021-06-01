/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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
public class VehiculoDaoObj implements VehiculoDao{
    public Path path;

    public VehiculoDaoObj(String path) {
        this.path=Paths.get(path);
    }
    
    @Override
    public List<Vehiculo> listar() throws DaoException {
        List<Vehiculo> listado=new ArrayList<>();
        Vehiculo v=null;
        try(InputStream is=Files.newInputStream(path);
            ObjectInputStream archivo=new ObjectInputStream(is);){
            while(is.available()>0){
                v=(Vehiculo)archivo.readObject();
                listado.add(v);
            }
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        }  catch (IOException ex) {
            throw new DaoException(ex.toString());
        } catch (ClassNotFoundException ex) {
            throw new DaoException("Error en la clase");
        }
        return listado;
    }

    @Override
    public int insertar(List<Vehiculo> vehiculos) throws DaoException {
        int n=0;
        try(ObjectOutputStream archivo=new ObjectOutputStream(Files.newOutputStream(path))){
            for(Vehiculo v: vehiculos){
            archivo.writeObject(v);
            n++;
           }
        } catch (NoSuchFileException nsfe){
            throw new DaoException("Error en el nombre del fichero");
        } catch (IOException ex) {
            throw new DaoException(ex.toString());
        }
        return n;
    }
    
}
