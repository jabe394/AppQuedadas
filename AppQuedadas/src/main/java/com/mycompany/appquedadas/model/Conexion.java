/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appquedadas.model;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
/**
 *
 * @author alumne
 */
public class Conexion {
    BaseDeDatos baseDeDatos; 
    Connection conexion;
    AppInfo info = new AppInfo();
    
    public Conexion(BaseDeDatos baseDeDatos) throws SQLException, ClassNotFoundException {
        this.baseDeDatos = baseDeDatos;
        this.conexion = getConnexionBaseDatos();
    }
    
    private Connection getConnexionBaseDatos() throws SQLException, ClassNotFoundException{
        String rutaBasedeDatos = "jdbc:mysql://localhost:" + baseDeDatos.getPuertoBaseDatos() + "/" + baseDeDatos.getNombreBaseDeDatosPorDefecto();
        Connection conDef = DriverManager.getConnection(rutaBasedeDatos, this.baseDeDatos.getNombreUsuario(), this.baseDeDatos.getContraseña());
        if (conDef != null){
            String sql = "CREATE DATABASE IF NOT EXISTS " + baseDeDatos.getNombreBaseDeDatos();
            Statement stmt = conDef.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Base de datos creada exitosamente (si no existía).");
            
            conDef.setCatalog(baseDeDatos.getNombreBaseDeDatos());
            
            for (Tabla tabla : baseDeDatos.getListaTablas()){
                sql = getConsultaCrearTabla(tabla);
                System.out.print("Consulta Inserción Tabla: " + sql);
                stmt = conDef.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("Tabla creada exitosamente (si no existía).");
            }
            
           
            stmt = conDef.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabla creada exitosamente (si no existía).");
            return conDef;      
        }
        else{
            System.out.println("Conexion no Exitosa");
        }
        return null;
    }
    
    public ResultSet getResultadoConsulta(String consulta) throws SQLException{
        Statement stm = this.conexion.createStatement();
        ResultSet res = stm.executeQuery(consulta);
        return res;
    }
    
    public String getConsultaCrearTabla(Tabla tabla){
        String sql = "CREATE TABLE IF NOT EXISTS " + tabla.getNombre() + "(";
        for (int i = 0; i < tabla.getColumnas().length; i++) {
            sql += tabla.getColumnas()[i] + " VARCHAR(100)";
            if (i < tabla.getColumnas().length - 1) {
                sql += ", ";
            }
        }
        sql += ");";
        return sql;
    }
    
    public void setConsulta(String consulta) throws SQLException{
        Statement stm = this.conexion.createStatement();
        stm.execute(consulta);
    }
     
}
