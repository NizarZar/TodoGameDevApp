package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

// class that control note /todo adding

public class NoteController {

    @FXML
    TextArea noteArea;

    @FXML
    TextField titleArea;

    @FXML
    CheckBox bugCheck;

    @FXML
    CheckBox featureCheck;

    private ArrayList<String> category = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void addNote(ActionEvent event) throws IOException {

        String noteTitle = titleArea.getText();
        String noteText = noteArea.getText();
        if (!noteTitle.equals("") && !noteText.equals("")) {
            TodoNote todoNote = new TodoNote(noteTitle, noteText);
            FXMLLoader loader = MainSingleton.getInstance().mainFXML;
            root = MainSingleton.getInstance().root;
            MainController mainController = loader.getController();
            mainController.addNoteItem(noteTitle);
            TodoAdded.addText(todoNote);


            System.out.println("Note added");
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = root.getScene();
            stage.setTitle("Game Dev Todo and Note App");
            stage.setScene(scene);
            stage.show();
        } else {
            throw new IllegalArgumentException("Text or title cannot be empty!");
        }
    }





}
