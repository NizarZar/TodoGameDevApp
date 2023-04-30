module com.nizar.todogamedevapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.nizar.todogamedevapp to javafx.fxml;
    exports com.nizar.todogamedevapp;
}