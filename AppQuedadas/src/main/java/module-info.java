module com.mycompany.appquedadas {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql; 
    opens com.mycompany.appquedadas to javafx.fxml;
    exports com.mycompany.appquedadas;
}
