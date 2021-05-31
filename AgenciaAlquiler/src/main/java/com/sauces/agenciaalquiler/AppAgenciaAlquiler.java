/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler;

import com.sauces.agenciaalquiler.modelo.Vehiculo;
import com.sauces.agenciaalquiler.modelo.AgenciaAlquiler;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoJson;
import com.sauces.agenciaalquiler.modelo.Grupo;
import com.sauces.agenciaalquiler.modelo.MatriculaException;
import com.sauces.agenciaalquiler.modelo.DaoException;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoXml;
import com.sauces.agenciaalquiler.modelo.Furgoneta;
import com.sauces.agenciaalquiler.modelo.VehiculoDao;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoCsv;
import com.sauces.agenciaalquiler.modelo.VehiculoDaoObj;
import com.sauces.agenciaalquiler.modelo.Turismo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daw1
 */
public class AppAgenciaAlquiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MatriculaException {
        Scanner teclado = new Scanner(System.in);
        AgenciaAlquiler aa = new AgenciaAlquiler("Oscar");
        int opcion=0, opcion2=0;
        String matricula;
        Grupo grupo;
        String g;
        int plazas;
        float capacidad;
        Vehiculo v=null;
        List<Vehiculo> listado=null;
        String nombreArchivo;
        int posicionPunto;
        String extension="";
        VehiculoDao vehiculoDao=null;
        int n;
        try{
        do{
            System.out.println("1.- Añadir turismo");
            System.out.println("2.- Añadir furgoneta");
            System.out.println("3.- Consultar vehículo");
            System.out.println("4.- Eliminar vehículo");
            System.out.println("5.- Listar vehículos");
            System.out.println("6.- Consultar alquiler más barato");
            System.out.println("7.- Guardar vehiculos");
            System.out.println("8.- Cargar vehiculos");
            System.out.println("0.- Salir");
            System.out.println("Introduzca opción: ");
            
                opcion=teclado.nextInt();
                teclado.nextLine();
            
            
            switch(opcion){
                case 1: try{
                        System.out.println("Introduce matricula:");
                        matricula=teclado.nextLine();
                        System.out.println("Introduce grupo "+Arrays.toString(Grupo.values())+": ");
                        g=teclado.nextLine();
                        grupo=Grupo.valueOf(g.toUpperCase());
                        System.out.println("Introduce numero de plazas:");
                        plazas=teclado.nextInt();
                        teclado.nextLine();
                        v=new Turismo(matricula,grupo,plazas);
                        }catch(MatriculaException me){
                            System.out.println("Error en la matricula");
                        }catch(IllegalArgumentException iae){
                            System.out.println("El grupo no existe");
                        }
                        if(aa.incluirVehiculo(v)){
                            System.out.println("Vehiculo incluido");
                        }
                        else{
                            System.out.println("No se ha podido incluir el vehiculo");
                        }
                    break;
                case 2: try{
                        System.out.println("Introduce matricula:");
                        matricula=teclado.nextLine();
                        System.out.println("Introduce grupo "+Arrays.toString(Grupo.values())+": ");
                        g=teclado.nextLine();
                        grupo=Grupo.valueOf(g.toUpperCase());
                        System.out.println("Introduce capacidad:");
                        capacidad=teclado.nextFloat();
                        teclado.nextLine();
                        v=new Furgoneta(matricula,grupo,capacidad);
                        }catch(MatriculaException me){
                            System.out.println("Error en la matricula");
                        }catch(IllegalArgumentException iae){
                            System.out.println("El grupo no existe");
                        }catch(InputMismatchException ime){
                            System.out.println("Error en la capacidad");
                        }
                        if(aa.incluirVehiculo(v)){
                            System.out.println("Vehiculo incluido");
                        }
                        else{
                            System.out.println("No se ha podido incluir el vehiculo");
                        }
                    break;
                case 3: System.out.println("Introduce matricula:");
                        matricula=teclado.nextLine();
                        v=aa.consultarVehiculo(matricula);
                        if(v!=null){
                            System.out.println(v.toString());
                        }
                        else{
                            System.out.println("Vehiculo no encontrado");
                        }
                    break;
                case 4: System.out.println("Introduce matricula:");
                        matricula=teclado.nextLine();
                        v=aa.consultarVehiculo(matricula);
                        if(v!=null){
                            aa.eliminarVehiculo(v);
                            System.out.println("Vehiculo eliminado");
                        }
                        else{
                            System.out.println("Vehiculo no encontrado");
                        }
                    break;
                case 5: do{
                            System.out.println("1.- Listar por precio");
                            System.out.println("2.- Listar turismos");
                            System.out.println("3.- Listar furgonetas");
                            System.out.println("0.- Volver atras");
                            System.out.println("Introduzca opción: ");
                            opcion2=teclado.nextInt();
                            teclado.nextLine();
                            listado=new ArrayList<>();
                            switch(opcion2){
                                case 1: listado=aa.listarVehiculosPorPrecio();
                                    break;
                                case 2: for(Vehiculo veh: aa.getFlota()){
                                            if(veh instanceof Turismo){
                                                listado.add(veh);
                                            }
                                        }
                                    break;
                                case 3: for(Vehiculo veh: aa.getFlota()){
                                            if(veh instanceof Furgoneta){
                                                listado.add(veh);
                                            }
                                        }
                                    break;
                                default: System.out.println("Error en la opción");
                            }
                            for(Vehiculo veh: listado){
                                System.out.println(veh);
                            }
                        }while(opcion2!=0);
                        
                    break;
                case 6: try{
                            System.out.println(aa.getVehiculoMasBarato());
                            System.out.println(aa.getVehiculoMasBarato().getPrecioAlquiler()+"€");
                        }catch(NoSuchElementException nsee){
                            System.out.println(nsee.getMessage());
                        }
                        
                    break;
                case 7: System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        posicionPunto=nombreArchivo.lastIndexOf('.');
                        if(posicionPunto!=-1){
                            extension=nombreArchivo.substring(posicionPunto);
                        }
                        switch(extension){
                            case ".obj": vehiculoDao=new VehiculoDaoObj(nombreArchivo);
                                break;
                            case ".csv": vehiculoDao=new VehiculoDaoCsv(nombreArchivo);
                                break;
                            case ".json": vehiculoDao=new VehiculoDaoJson(nombreArchivo);
                                break;
                            case ".xml": vehiculoDao=new VehiculoDaoXml(nombreArchivo);
                                break;
                            default: System.out.println("Error en la extension");
                        }
                        if(vehiculoDao!=null){
                            try{
                                aa.setVehiculoDao(vehiculoDao);
                                n=aa.guardarVehiculos();
                                System.out.println("Se han guardado "+n+" vehiculos");
                            } catch (DaoException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    break;
                case 8: System.out.println("Introduzca nombre del archivo: ");
                        nombreArchivo=teclado.nextLine();
                        posicionPunto=nombreArchivo.lastIndexOf('.');
                        if(posicionPunto!=-1){
                            extension=nombreArchivo.substring(posicionPunto);
                        }
                        switch(extension){
                            case ".obj": vehiculoDao=new VehiculoDaoObj(nombreArchivo);
                                break;
                            case ".csv": vehiculoDao=new VehiculoDaoCsv(nombreArchivo);
                                break;
                            case ".json": vehiculoDao=new VehiculoDaoJson(nombreArchivo);
                                break;
                            case ".xml": vehiculoDao=new VehiculoDaoXml(nombreArchivo);
                                break;
                            default: System.out.println("Error en la extension");
                        }
                        if(vehiculoDao!=null){
                            try{
                                aa.setVehiculoDao(vehiculoDao);
                                n=aa.cargarVehiculos();
                                System.out.println("Se han cargado "+n+" vehiculos");
                            } catch (DaoException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    break;
                case 0: System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Error en la entrada");
                    break;
            }
        }while(opcion!=0);
        }catch(InputMismatchException ime){
                            System.out.println("Error en la entrada");
            }
    }
    
}
