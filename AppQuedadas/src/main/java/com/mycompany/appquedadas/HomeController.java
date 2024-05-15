package com.mycompany.appquedadas;

import com.mycompany.appquedadas.model.AppInfo;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {    
    AppInfo info = new AppInfo();
    @FXML 
    private void Button_Registrarse_OnAction() throws IOException{
        App.setRoot(info.getNombreFxmlController(2));
    }
}
