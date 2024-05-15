/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appquedadas.model;

import java.util.ArrayList;

/**
 *
 * @author jbert
 */
public class BaseDeDatos {
    private static final String nombreBaseDatosPorDefecto = "mysql";
    private String nombreBaseDeDatos;
    private String puertoBaseDatos;
    private String nombreUsuario;
    private String contraseña;
    private ArrayList<Tabla> listaTablas;
    private static ArrayList<String> gmailsUsuarioApp;


    public BaseDeDatos(String nombreBaseDeDatos, String puertoBaseDatos, String nombreUsuario, String contraseña, ArrayList<Tabla> listaTablas) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
        this.puertoBaseDatos = puertoBaseDatos;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.listaTablas = listaTablas;
        this.gmailsUsuarioApp = new ArrayList<String>();
    }
    
    
    public void addTablas(Tabla tabla) {
        this.listaTablas.add(tabla);
    }
    
    public Tabla getTabla(int pos){
        return listaTablas.get((pos - 1));
    }

    public ArrayList<Tabla> getListaTablas() {
        return listaTablas;
    }

    public String getNombreBaseDeDatos() {
        return nombreBaseDeDatos;
    }

    public String getPuertoBaseDatos() {
        return puertoBaseDatos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombreBaseDeDatosPorDefecto() {
        return nombreBaseDatosPorDefecto;
    }

    public static void addGmailsUsuarioApp(String gmailUsuarioApp) {
        if (!gmailsUsuarioApp.contains(gmailUsuarioApp)) gmailsUsuarioApp.add(gmailUsuarioApp);
    }

    public static ArrayList<String> getGmailsUsuarioApp() {
        return gmailsUsuarioApp;
    }
    
    
    
    
}
