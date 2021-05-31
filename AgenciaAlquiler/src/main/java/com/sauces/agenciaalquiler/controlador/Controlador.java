/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.controlador;

import com.sauces.agenciaalquiler.modelo.AgenciaAlquiler;
import com.sauces.agenciaalquiler.modelo.DaoException;
import com.sauces.agenciaalquiler.modelo.Furgoneta;
import com.sauces.agenciaalquiler.modelo.Grupo;
import com.sauces.agenciaalquiler.modelo.Matricula;
import com.sauces.agenciaalquiler.modelo.MatriculaException;
import com.sauces.agenciaalquiler.modelo.Turismo;
import com.sauces.agenciaalquiler.modelo.Vehiculo;
import com.sauces.agenciaalquiler.modelo.VehiculoDao;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoCsv;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoJson;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoObj;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoXml;
import com.sauces.agenciaalquiler.vista.Ventana;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author daw1
 */
public class Controlador {
    private Ventana vista;
    private AgenciaAlquiler modelo;

    public Controlador(Ventana vista, AgenciaAlquiler modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
    
    public void crearVehiculo() throws MatriculaException{
        Vehiculo v=null;
        String matricula=vista.getMatricula();
        Grupo grupo=Grupo.valueOf(vista.getGrupo());
        String tipo=vista.getTipo();
        
        if(tipo.equals("TURISMO")){
            
                int plazas=vista.getPlazas();
                v=new Turismo(matricula,grupo,plazas);
        }    
        else{
                float capacidad=vista.getCapacidad();
                v=new Furgoneta(matricula,grupo,capacidad);
            
        }
        vista.actualizarTabla();
        if(modelo.incluirVehiculo(v)){
            vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            vista.mostrarMensaje("Vehículo incluido");
        }
        else{
            vista.mostrarMensaje("No se ha podido incluir el vehículo");
        }
        
        
        
    }
    
    public void borrarVehiculo(){
        String matricula=vista.getMatricula();
        Vehiculo v=modelo.consultarVehiculo(matricula);
        if (v!=null){
            if(modelo.eliminarVehiculo(v)){
                JOptionPane.showMessageDialog(vista, "Vehículo eliminado");
                vista.limpiarCampos();
            }
            else{
                JOptionPane.showMessageDialog(vista, "No se ha podido eliminar el vehículo");
            }
        }
        else{
            vista.mostrarMensaje("No existe el vehículo");
        }
        vista.actualizarTabla();
    }
    
    public void buscarVehiculo(){
        String matricula=JOptionPane.showInputDialog("Introduzca matrícula del vehículo");
        if(Matricula.esValida(matricula)){
            Vehiculo v=modelo.consultarVehiculo(matricula);
            if (v!=null){
                vista.mostrarMatricula(matricula);
                vista.mostrarGrupo(v.getGrupo().toString());
                vista.mostrarTipo(v.getClass().getSimpleName());
                
                if(v instanceof Turismo){
                    vista.mostrarPlazas(((Turismo) v).getPlazas());
                    vista.mostrarCapacidad(0);
                }
                else if(v instanceof Furgoneta){
                    vista.mostrarPlazas(0);
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
                
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
            }
        }
    }
    
    public void modificarVehiculo(){
        try{
        String matricula=vista.getMatricula();
        Vehiculo v=modelo.consultarVehiculo(matricula);
        v.setGrupo(Grupo.valueOf(vista.getGrupo()));
        
        
                if(v instanceof Turismo){
                    ((Turismo) v).setPlazas(vista.getPlazas());
                    vista.mostrarPlazas(((Turismo) v).getPlazas());

                }
                if(v instanceof Furgoneta){
                    ((Furgoneta) v).setCapacidad(vista.getCapacidad());
                    vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
                }
                vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());
                vista.mostrarMensaje("Vehiculo modificado");
                this.listarVehiculos();
        } catch (NumberFormatException nfe){
                vista.mostrarMensaje("Error en los datos introducidos");
        }
    }
    
    public void listarVehiculos(){
        List<Vehiculo> listado=null;
        listado=modelo.listarVehiculos(Grupo.A);
        vista.listarVehiculos(listado);
    }
    
    public void guardarVehiculos(){
        String nombreFichero;
        int posicionPunto;
        String extension="";
        VehiculoDao vehiculoDao=null;
        int n;
        try {
            
            nombreFichero=vista.getArchivo();
            
            posicionPunto=nombreFichero.lastIndexOf('.');
            if(posicionPunto!=-1){
                extension=nombreFichero.substring(posicionPunto);
            }
            switch(extension){
                case ".csv": vehiculoDao=new VehiculoDaoCsv(nombreFichero);
                    break;
                case ".obj": vehiculoDao=new VehiculoDaoObj(nombreFichero);
                    break;
                case ".json": vehiculoDao=new VehiculoDaoJson(nombreFichero);
                    break;
                case ".xml": vehiculoDao=new VehiculoDaoXml(nombreFichero);
                    break;
                default: System.out.println("Formato incorrecto");
            }
            modelo.setVehiculoDao(vehiculoDao);
            n=modelo.guardarVehiculos();
            vista.mostrarMensaje("Se han guardado "+n+" empleados");
        } catch (DaoException ex) {
            vista.mostrarMensaje("Error en el fichero");
        }
    }
    
    public void cargarVehiculos(){
        String nombreFichero;
        int posicionPunto;
        String extension="";
        VehiculoDao vehiculoDao=null;
        int n;
        try {
            
            nombreFichero=vista.getArchivo();
            
            posicionPunto=nombreFichero.lastIndexOf('.');
            if(posicionPunto!=-1){
                extension=nombreFichero.substring(posicionPunto);
            }
            switch(extension){
                case ".csv": vehiculoDao=new VehiculoDaoCsv(nombreFichero);
                    break;
                case ".obj": vehiculoDao=new VehiculoDaoObj(nombreFichero);
                    break;
                case ".json": vehiculoDao=new VehiculoDaoJson(nombreFichero);
                    break;
                case ".xml": vehiculoDao=new VehiculoDaoXml(nombreFichero);
                    break;
            }
            
            modelo.setVehiculoDao(vehiculoDao);
            n=modelo.cargarVehiculos();
            vista.mostrarMensaje("Se han cargado "+n+" empleados");
            vista.listarVehiculos(modelo.listarVehiculos(Grupo.A));
        } catch (DaoException ex) {
            vista.mostrarMensaje("Error en el fichero");
        }
    }
    
    public void buscarVehiculoMasBarato(){
        Vehiculo v=modelo.getVehiculoMasBarato();
        if(v!=null){
             vista.mostrarMatricula(v.getMatricula());
             vista.mostrarGrupo(v.getGrupo().toString());
             vista.mostrarTipo(v.getClass().getSimpleName());

             if(v instanceof Turismo){
                 vista.mostrarPlazas(((Turismo) v).getPlazas());
                 vista.mostrarCapacidad(0);
             }
             else if(v instanceof Furgoneta){
                 vista.mostrarPlazas(0);
                 vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
             }

             vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());     
        }
        
    }
    
    public void buscarVehiculoMasCaro(){
        List<Vehiculo> listado=null;
        Vehiculo v=listado.get(listado.lastIndexOf(this));
        if(v!=null){
             vista.mostrarMatricula(v.getMatricula());
             vista.mostrarGrupo(v.getGrupo().toString());
             vista.mostrarTipo(v.getClass().getSimpleName());

             if(v instanceof Turismo){
                 vista.mostrarPlazas(((Turismo) v).getPlazas());
                 vista.mostrarCapacidad(0);
             }
             else if(v instanceof Furgoneta){
                 vista.mostrarPlazas(0);
                 vista.mostrarCapacidad(((Furgoneta) v).getCapacidad());
             }

             vista.mostrarPrecioAlquiler(v.getPrecioAlquiler());     
        }
    }
    
    public void iniciar(){
        vista.setVisible(true);
    }
}
