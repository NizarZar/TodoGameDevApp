package com.nizar.todogamedevapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    Label noteLabel = new Label();

    public void addNote(ActionEvent event) throws IOException {
        System.out.println("Note Add Button Clicked");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("note.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Note Add");
        stage.setScene(scene);
        stage.show();

    }

    public void onLogout(ActionEvent event) throws IOException{
        System.out.println("Logged out!");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void displayNote1(String text){
        noteLabel.setText(text);


    }



}