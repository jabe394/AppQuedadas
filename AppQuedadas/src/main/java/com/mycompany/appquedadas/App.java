package com.mycompany.appquedadas;
import com.mycompany.appquedadas.HomeController;
import com.mycompany.appquedadas.model.Conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.appquedadas.model.AppInfo;

import java.io.IOException;
import java.sql.SQLException;
import static javafx.application.Application.launch;


public class App extends Application {
    
    private static Scene scene;
    private static Conexion conexionBaseDatos;
    
    private static AppInfo info = new AppInfo();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML(info.getNombreFxmlController(1)), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        for (int i = 1; i <= info.getLengthNombresFxml(); i++){
            if (fxml.equals(info.getNombreFxmlController(i))) fxmlLoader.setController(info.getControllerFxml(i));
        }
        
        

        return fxmlLoader.load();
    
    }
    
    
    
    public static Conexion getConexionBaseDatos() throws SQLException, ClassNotFoundException{
        return new Conexion(info.getBaseDeDatos());
    }
    
    public static void main(String[] args) {
        launch();
    }

    public static Scene getScene() {
        return scene;
    }
}