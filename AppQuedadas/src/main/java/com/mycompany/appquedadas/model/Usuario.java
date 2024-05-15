/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appquedadas.model;

/**
 *
 * @author alumne
 */
public class Usuario {
    String gmail;
    String contraseña;
    String nombreColumnaContraseña;

    public Usuario(String gmail, String contraseña, String nombreColumnaContraseña) {
        this.gmail = gmail;
        this.contraseña = contraseña;
        this.nombreColumnaContraseña = nombreColumnaContraseña;
    }

    public String getGmail() {
        return gmail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombreColumnaContraseña() {
        return nombreColumnaContraseña;
    }
}
