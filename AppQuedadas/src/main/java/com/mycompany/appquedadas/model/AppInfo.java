/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appquedadas.model;

import com.mycompany.appquedadas.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppInfo {
    private static HomeController home_Controller = new HomeController();
    private static RegistraseController registrarse_Controller = new RegistraseController();
    private  static ElegirGrupoController elegirGrupo_Controller = new ElegirGrupoController();
    private static TutorialController tutorial_Controller = new TutorialController();
    private static CrearGrupoController crearGrupo_Controller = new CrearGrupoController();
    
    private static final String nombreBaseDatos = "AppQuedadas";
    private static final String puertoBaseDatos = "3306";
    private static final String nombreUsuario = "root";
    private static final String contraseñaBaseDatos = "debian";
    
    private static final Tabla tablaUsuarios  = new Tabla("Usuarios", new String[]{"Gmail", "Contraseña"});
    private  static final Tabla tablaGrupos = new Tabla("Grupos", new String[]{"NombreGrupo", "Miembros"});
    
    private static final ArrayList<Tabla> listaTablasBaseDeDatos = new ArrayList<Tabla>(List.of(tablaUsuarios, tablaGrupos));
    private static final BaseDeDatos baseDeDatos = new BaseDeDatos(nombreBaseDatos, puertoBaseDatos, nombreUsuario, contraseñaBaseDatos, listaTablasBaseDeDatos);
    
    
    private Object[][] nombresFxml = {{"home", home_Controller}, {"registrarse", registrarse_Controller}, {"tutorial", tutorial_Controller}, {"elegirGrupo", elegirGrupo_Controller}, {"crearGrupo", crearGrupo_Controller}};
    
    public int getLengthNombresFxml(){
        return nombresFxml.length;
    }
    
    public String getNombreFxmlController(int pos) {
        return (String)nombresFxml[pos - 1][0];
    }
    
    public Object getControllerFxml(int pos) {
        return (Object)nombresFxml[pos - 1][1];
    }

    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public String getPuertoBaseDatos() {
        return puertoBaseDatos;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseñaBaseDatos() {
        return contraseñaBaseDatos;
    }

    public BaseDeDatos getBaseDeDatos() {
        return baseDeDatos;
    }
}
