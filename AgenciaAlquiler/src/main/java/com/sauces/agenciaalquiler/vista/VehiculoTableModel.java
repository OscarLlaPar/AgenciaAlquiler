/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sauces.agenciaalquiler.vista;

import com.sauces.agenciaalquiler.modelo.Furgoneta;
import com.sauces.agenciaalquiler.modelo.Turismo;
import com.sauces.agenciaalquiler.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author daw1
 */
public class VehiculoTableModel extends AbstractTableModel{
    private  List<Vehiculo> listadoVehiculos;
    private String [] columnas = new String[]{"MATRÍCULA","TIPO","GRUPO","PLAZAS","CAPACIDAD","PRECIO ALQUILER"};

    public VehiculoTableModel() {
        listadoVehiculos  = new ArrayList<>();
    }

    public List<Vehiculo> getListadoVehiculos() {
        return listadoVehiculos;
    }
    
    public void setListadoVehiculos(List<Vehiculo> listadoVehiculos) {
        this.listadoVehiculos = listadoVehiculos;
        this.fireTableDataChanged();
    }

    public String[] getColumnas() {
        return columnas;
    }

    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }
    
    @Override
    public int getRowCount() {
        return listadoVehiculos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o=null;
        Vehiculo v=listadoVehiculos.get(rowIndex);
        
        switch(columnIndex){
            case 0: o=v.getMatricula().toString();
                break;
            case 1: o=v.getClass().getSimpleName().toUpperCase();
                break;
            case 2: o=v.getGrupo();
                break;
            case 3: if(v instanceof Turismo){
                        o=((Turismo) v).getPlazas();
                    }
                break;
            case 4: if(v instanceof Furgoneta){
                        o=((Furgoneta) v).getCapacidad();
                    }
                break;
            case 5: o=v.getPrecioAlquiler();
                break;
        }
        
        return o;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> clase=null;
        switch(columnIndex){
            case 0: clase=String.class;
                break;
            case 1: clase=String.class;
                break;
            case 2: clase=String.class;
                break;
            case 3: clase=Integer.class;
                break;
            case 4: clase=Float.class;
                break;
            case 5: clase=Float.class;
                break;
                
        }
        return clase;
    }
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }
    
}
