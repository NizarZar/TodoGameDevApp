package com.nizar.todogamedevapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NoteController {

    @FXML
    TextArea noteArea = new TextArea();

    @FXML
    TextField titleArea = new TextField();

    @FXML
    CheckBox bugCheck = new CheckBox();

    @FXML
    CheckBox featureCheck = new CheckBox();

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void addNote(ActionEvent event) throws IOException {

        String noteTitle = titleArea.getText();
        String noteText = noteArea.getText();

        TodoNote todoNote = new TodoNote(noteTitle,noteText);
        FXMLLoader loader = MainSingleton.getInstance().mainFXML;
        root = MainSingleton.getInstance().root;


        MainController mainController = loader.getController();
        mainController.displayNote(noteTitle);
        TodoAdded.addText(todoNote);


        System.out.println("Note added");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = root.getScene();
        stage.setTitle("Game Dev Todo App");
        stage.setScene(scene);
        stage.show();
    }





}
