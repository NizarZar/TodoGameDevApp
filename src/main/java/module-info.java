module com.nizar.todogamedevapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.nizar.todogamedevapp to javafx.fxml;
    exports com.nizar.todogamedevapp;
    exports com.nizar.todogamedevapp.categories;
    opens com.nizar.todogamedevapp.categories to javafx.fxml;
    exports com.nizar.todogamedevapp.todonote;
    opens com.nizar.todogamedevapp.todonote to javafx.fxml;
    exports com.nizar.todogamedevapp.notes;
    opens com.nizar.todogamedevapp.notes to javafx.fxml;
    exports com.nizar.todogamedevapp.authentication;
    opens com.nizar.todogamedevapp.authentication to javafx.fxml;
}