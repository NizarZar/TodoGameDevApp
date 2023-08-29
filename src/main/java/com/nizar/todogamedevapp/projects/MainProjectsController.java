package com.nizar.todogamedevapp.projects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainProjectsController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    ListView<String> projects;

    private Connection connectDatabase(){
        String url = "";
        Connection connection = null;
        return connection;
    }

    public void onCreateProject(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("projectsCreation.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Project Creation");
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sqlProjects = "SELECT * FROM projects";
        // database connection, later



    }
}
