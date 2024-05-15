package com.mycompany.appquedadas;

import com.mycompany.appquedadas.model.AppInfo;
import com.mycompany.appquedadas.model.BaseDeDatos;
import com.mycompany.appquedadas.model.Conexion;
import com.mycompany.appquedadas.model.Tabla;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



public class CrearGrupoController {
    AppInfo info;
    Tabla tablaUsuarios;
    Tabla tablaGrupos;
    Conexion conexionClass;
    ObservableList<String> listaUsuariosNoGrupo = FXCollections.observableArrayList();
    ObservableList<String> listaUsuariosGrupo = FXCollections.observableArrayList();
    @FXML ListView ListView_NoMiembros;
    @FXML ListView ListView_Miembros;
    @FXML TextField TextField_títuloGrupo;
    
    @FXML public void ListView_NoMiembros_OnMouseClicked(){
        String usuarioSeleccionado = (ListView_NoMiembros.getSelectionModel().getSelectedItem()).toString();
        listaUsuariosGrupo.add(usuarioSeleccionado);
        listaUsuariosNoGrupo.remove(usuarioSeleccionado);
    }
    
    @FXML public void ListView_Miembros_OnMouseClicked() {
        String usuarioSeleccionado = ListView_Miembros.getSelectionModel().getSelectedItem().toString();
        if (usuarioSeleccionado == null) return;
        if (!esUnUsuarioRegistrado(usuarioSeleccionado)){
            listaUsuariosNoGrupo.add(usuarioSeleccionado);
            listaUsuariosGrupo.remove(usuarioSeleccionado);
        }
    }
    
    @FXML public void Button_RefrescarLista_OnAction() throws SQLException{
        actualizar_ListaUsuariosNoGrupo();
    }
    
    @FXML public void Button_Crear_OnAction() throws SQLException, IOException{
        String tituloGrupo = TextField_títuloGrupo.getText();
        if (tituloGrupo != ""){
            String valores = "";
            for (String usuario : listaUsuariosGrupo) {
                valores += "('" + tituloGrupo + "','" + usuario + "'),";
            }
            valores = valores.substring(0, valores.length() - 1); // Eliminar la coma extra al final
            String sql = "INSERT INTO " + tablaGrupos.getNombre() + " VALUES " + valores;
            conexionClass.setConsulta(sql);
            App.setRoot(info.getNombreFxmlController(4));
        }
        
        
    }
    
    public boolean esUnUsuarioRegistrado(String gmailUsuario){
        boolean contieneMismoGmailUsuarioRegistrado = BaseDeDatos.getGmailsUsuarioApp().contains(gmailUsuario); // Habilitar esta variable para que asi: Simular como si el servidor Mysql LocalHost fuese de tipo Server(Que los datos son compartidos por más de un usuario)
        return contieneMismoGmailUsuarioRegistrado;
    }
    
    public boolean usuarioEstáEnAlgunLista(String gmailUsuario){
        boolean estáEnAlgunaLista = listaUsuariosGrupo.contains(gmailUsuario) || listaUsuariosNoGrupo.contains(gmailUsuario); // Esto lo hago para verificar si existe el usuario en alguna de estas 2 listas para que no me lo vuelva a añadir si es que existe;
        return estáEnAlgunaLista;
    }
    
    public void actualizar_ListaUsuariosNoGrupo() throws SQLException{
        listaUsuariosGrupo.clear();
        String nombreColumnaGmail = tablaUsuarios.getColumnas()[0];
        String sql = "SELECT " + nombreColumnaGmail + " FROM " + tablaUsuarios.getNombre();
        ResultSet res = conexionClass.getResultadoConsulta(sql);
        while(res.next()){
            String gmailUsuario = res.getString(1);
            boolean estáEnAlgunaLista = usuarioEstáEnAlgunLista(gmailUsuario);
            if ((esUnUsuarioRegistrado(gmailUsuario)) && !(estáEnAlgunaLista)) listaUsuariosGrupo.add(gmailUsuario);
            if ((!esUnUsuarioRegistrado(gmailUsuario)) && !(estáEnAlgunaLista)) listaUsuariosNoGrupo.add(gmailUsuario); // Si cumple con los requisitos anteriores será añadido en la lista de usuarios que no están assignados al grupo
        }
    }
    
    public void initialize() throws SQLException, ClassNotFoundException{
        ListView_NoMiembros.setItems(listaUsuariosNoGrupo);
        ListView_Miembros.setItems(listaUsuariosGrupo);
        this.conexionClass = App.getConexionBaseDatos();
        this.info = new AppInfo();
        this.tablaUsuarios = info.getBaseDeDatos().getTabla(1);
        this.tablaGrupos = info.getBaseDeDatos().getTabla(2);
        actualizar_ListaUsuariosNoGrupo();
    }
}
