/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author daw1
 */
public class AgenciaAlquiler {
    private String nombre;
    private List<Vehiculo> flota;
    private VehiculoDao vehiculoDao;

    public AgenciaAlquiler(String nombre) {
        this.nombre = nombre;
        this.flota = new ArrayList<>();
    }

    public VehiculoDao getVehiculoDao() {
        return vehiculoDao;
    }

    public void setVehiculoDao(VehiculoDao vehiculoDao) {
        this.vehiculoDao = vehiculoDao;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Vehiculo> getFlota() {
        return List.copyOf(flota);
        //Si es un TreeMap: return new ArrayList<>(flota.values());
    }

    
    
    public boolean incluirVehiculo(Vehiculo vehiculo){
           flota.add(vehiculo);
           return true;
    }
    
    public Vehiculo consultarVehiculo(String matricula){
        Vehiculo v=null;
        Iterator<Vehiculo> it=flota.iterator();  
        
        while(it.hasNext()){
            v=it.next();
            if (matricula.equals(v.getMatricula().toString())){
                return v;
            }
        }
        
        return null;
        /*
                   ListIterator<Vehiculo> iterador=flota.listIterator();
                   Vehiculo v=null
                   while(iterador.hasNext()){
                       v=iterador.next()
                       if(v.getMatricula().equals(matricula)){
                           return v;
                      }
                   }
                   */
        
   }

    public boolean eliminarVehiculo(Vehiculo vehiculo){
        return flota.remove(vehiculo);
    }
    
    public List<Vehiculo> listarVehiculosPorPrecio(){
        List<Vehiculo> listado=new ArrayList<>(flota);
        
        
        Collections.sort(listado, new ComparadorPrecio());
        
        return listado;
        
    }
    
    public List<Vehiculo> listarVehiculos(Grupo grupo){
        List<Vehiculo> vGrupo=new ArrayList<>();
        Iterator<Vehiculo> it=flota.iterator(); 
        Vehiculo v=null;
        
        while(it.hasNext()){
            v=it.next();
            if (grupo.equals(v.getGrupo())){
                vGrupo.add(v);
            }
        }
        
        Collections.sort(vGrupo);
        
        return vGrupo;
        
    }
    
    public Vehiculo getVehiculoMasBarato(){
        
        return Collections.min(flota, new ComparadorPrecio());
    }
    
    public int guardarVehiculos() throws DaoException{
        int n=0;
        if(vehiculoDao!=null){
            
           n=vehiculoDao.insertar(flota);
        }
        return n;
    }
    
    public int cargarVehiculos() throws DaoException{
        int n=0;
        if(vehiculoDao!=null){
            for(Vehiculo v: vehiculoDao.listar()){
                if(incluirVehiculo(v)){
                    n++;
                }
            }
        }
        return n;
    }
    
}
