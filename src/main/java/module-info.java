module com.example.filosofos {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.logging;

    opens com.example.filosofos to javafx.fxml;
    exports com.example.filosofos;
    exports com.example.filosofos.model;
    opens com.example.filosofos.model to javafx.fxml;
    exports com.example.filosofos.controller;
    opens com.example.filosofos.controller to javafx.fxml;
    exports com.example.filosofos.view;
    opens com.example.filosofos.view to javafx.fxml;
}