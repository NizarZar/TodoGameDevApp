package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NoteController {

    @FXML
    TextArea noteArea = new TextArea();

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addNote(ActionEvent event) throws IOException {

        String noteText = noteArea.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();

        MainController mainController = loader.getController();
        mainController.displayNote1(noteText);

        System.out.println("Note added");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Note Add");
        stage.setScene(scene);
        stage.show();
    }


}
