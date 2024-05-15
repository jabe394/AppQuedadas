/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appquedadas.model;

/**
 *
 * @author jbert
 */
public class Tabla {
    String nombre;
    String[] columnas;

    public Tabla(String nombre, String[] columnas) {
        this.nombre = nombre;
        this.columnas = columnas;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getColumnas() {
        return columnas;
    }
    
    
}
