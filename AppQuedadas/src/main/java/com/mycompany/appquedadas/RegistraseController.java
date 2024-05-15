package com.mycompany.appquedadas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.appquedadas.model.Conexion;
import com.mycompany.appquedadas.model.AppInfo;
import com.mycompany.appquedadas.model.BaseDeDatos;
import com.mycompany.appquedadas.model.Tabla;
import com.mycompany.appquedadas.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.text.Text;

public class RegistraseController {
    @FXML TextField TextField_Gmail;
    @FXML Text Text_Error;
    @FXML TextField TextField_Contraseña;
    private Conexion conexionClass;
    private AppInfo info = new AppInfo();
    private BaseDeDatos baseDeDatos;
    private Tabla tablaUsuarios;
    
    public boolean usuarioExiste(Usuario usuario) throws SQLException {
        String nombreTabla = tablaUsuarios.getNombre();
        String nombreColumnaGmail = tablaUsuarios.getColumnas()[0];
        String consulta = "SELECT " + "*" + " FROM " + nombreTabla + 
                      " WHERE " + nombreColumnaGmail + " = '" + usuario.getGmail()+ "'";
        ResultSet res = conexionClass.getResultadoConsulta(consulta);
        System.out.print(consulta);
        return res.next();
    }

    public void crearUsuario(Usuario usuario) throws SQLException, IOException{
        String consulta = "INSERT INTO " + tablaUsuarios.getNombre() + " VALUES('" + usuario.getGmail() + "', '" + usuario.getContraseña() + "')";
        conexionClass.setConsulta(consulta);

    }

    @FXML
    public void Button_IniciarSesion_OnAction() throws SQLException, IOException{ 
        String nombreColumnaContraseña = "Contraseña";
        Map<String,String> tiposErrores = Map.of(
                "Formato", "Error: El Gmail debe terminar en: " + convertirArrayAString(getListaTiposFormatosGmail())
                , "Contraseña", "La contraseña con el Usuario no Coincide");
        String gmail = TextField_Gmail.getText();
        String contraseñaIntroducida = TextField_Contraseña.getText();
        Usuario usuario = new Usuario(gmail, contraseñaIntroducida, nombreColumnaContraseña);
        
        if (!formatoGmailValido(gmail)){
            String error = tiposErrores.get("Formato");
            Text_Error.setText(error);
            return;
        }
        
        
        if (!usuarioExiste(usuario)){
            System.out.println("Se ha creado un nuevo perfil de Usuario!");
            crearUsuario(usuario);
            App.setRoot(info.getNombreFxmlController(3));
        }
        else{
            if (!contraseñaCorrecta(usuario)){
                String error = tiposErrores.get("Contraseña");
                Text_Error.setText(error);
                return;
            }
            System.out.println("El usuario ya existe!");
            BaseDeDatos.addGmailsUsuarioApp(gmail);
            App.setRoot(info.getNombreFxmlController(4));
        }
    }
    
    private static final String[] listaTiposFormatosGmail = {"@gmail.com", "@googlemail.com"};
    public boolean formatoGmailValido(String gmail){
        for (String formato : listaTiposFormatosGmail){
            if (gmail.contains(formato)){
                return true;
            }
        }
        return false;
    }
    
    public String convertirArrayAString(String[] array){
        return Arrays.toString(array);
    }

    public static String[] getListaTiposFormatosGmail() {
        return listaTiposFormatosGmail;
    }
    
    
    
    public boolean contraseñaCorrecta(Usuario usuario) throws SQLException{
        String nombreColumnaGmail = tablaUsuarios.getColumnas()[0];
        String nombreColumnaContraseña = tablaUsuarios.getColumnas()[1];
        String sql = "SELECT " + nombreColumnaContraseña + " FROM " + tablaUsuarios.getNombre() +  " WHERE " + nombreColumnaGmail + " = " + "'" + usuario.getGmail() + "'";
        ResultSet res = conexionClass.getResultadoConsulta(sql);
        if (res.next()){
            System.out.print("Contraseña esperada: " + res.getString(nombreColumnaContraseña));
            return usuario.getContraseña().equals(res.getString(nombreColumnaContraseña));
        }
        return false; // Afegir un metode de comparació entre contraseña del usuari existent amb la constraseña introduida
    }
    
    public void initialize() throws SQLException, ClassNotFoundException{
        this.conexionClass = App.getConexionBaseDatos();
        this.baseDeDatos = info.getBaseDeDatos();
        this.tablaUsuarios = baseDeDatos.getTabla(1);
    }
}