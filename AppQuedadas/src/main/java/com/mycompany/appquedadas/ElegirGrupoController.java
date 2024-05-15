package com.mycompany.appquedadas;
import com.mycompany.appquedadas.App;
import com.mycompany.appquedadas.model.AppInfo;
import com.mycompany.appquedadas.model.Conexion;
import com.mycompany.appquedadas.model.Tabla;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class ElegirGrupoController {
    AppInfo info = new AppInfo();
    @FXML VBox listaGrupos_VBox;
    @FXML ListView ListView_ListaGrupos;
    Conexion conexionClass;
    ObservableList<String> listaGrupos = FXCollections.observableArrayList();
    Tabla tablaGrupo;
    
    @FXML public void crearGrupo_Button_OnAction() throws IOException{
        App.setRoot(info.getNombreFxmlController(5));
    }
    
    @FXML public void ListView_ListaGrupos_OnMouseClicked(){
        System.out.println("Redirigiendo Interfaz Grupo...");
    }
    
    
    
    public void actualizarListaGrupos() throws SQLException{
        listaGrupos.clear();
        String nombreColumnaGrupo = tablaGrupo.getColumnas()[0];
        String sql = "SELECT " + nombreColumnaGrupo + " FROM " + tablaGrupo.getNombre();
        ResultSet res = conexionClass.getResultadoConsulta(sql);
        while(res.next()){
            String nombreGrupo = res.getString(1);
            listaGrupos.add(nombreGrupo);
        }
    }
    
    public void initialize() throws SQLException, ClassNotFoundException{
        conexionClass = App.getConexionBaseDatos();
        ListView_ListaGrupos.setItems(listaGrupos);
        tablaGrupo = info.getBaseDeDatos().getTabla(2);
        actualizarListaGrupos();
    }
}
