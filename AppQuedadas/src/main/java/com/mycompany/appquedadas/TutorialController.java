package com.mycompany.appquedadas;
import javafx.fxml.FXML;
import com.mycompany.appquedadas.model.AppInfo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TutorialController {
    String rutaPacketeImagenesTutorial = "file:src/main/resources/com/mycompany/appquedadas/imagenes";
    
    String[] listaNombreImagenes = {"añadirUsuarios.png", "crearGrupo.png"};
    ArrayList<String> listaRutasImagenesTutorial = new ArrayList<String>();
    //Image[] listaImagenesTutorial = cargarImagenes(rutaPacketeImagenesTutorial); // Fer: Cargar Imatges Tutorial
    
    AppInfo info = new AppInfo();
    @FXML ImageView imagenTutorial_ImageView;
    
    
    @FXML public void siguiente_Button_OnAction() {
        cambiarImagen(1);
    }
    
    @FXML public void atrás_Button_OnAction() {
        cambiarImagen(-1);
    }
    
    @FXML public void saltarTutorial_Button_OnAction() throws IOException{
        App.setRoot(info.getNombreFxmlController(4));
    }
    
    int c = 0;
    public void cambiarImagen(int mov){
        c +=mov;
        
        if (c >= listaRutasImagenesTutorial.size()){
            c -=1;
        }
        else if (c < 0){
            c ++;
        }        
        imagenTutorial_ImageView.setImage(new Image(listaRutasImagenesTutorial.get(c)));
        
    }
    
    public void setListaRutasImagenesTutorial(){
        for (String nombre : listaNombreImagenes){
            listaRutasImagenesTutorial.add(rutaPacketeImagenesTutorial + "/" + nombre);
        }
    }
    
    public void initialize(){
        setListaRutasImagenesTutorial();
        imagenTutorial_ImageView.setImage(new Image(listaRutasImagenesTutorial.get(c)));
    }
    
}
