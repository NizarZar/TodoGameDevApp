package com.nizar.todogamedevapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Game Dev Todo App Login");
        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            onExit(stage);
        });
    }

    public static void main(String[] args) {
        Application.launch();
    }


    public void onExit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit the application");
        alert.setContentText("Are you sure you want to exist?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Application exit successful!");
            stage.close();
        }
    }
}