package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NotesListController {

    @FXML
    Text text = new Text();

    @FXML
    Button backButton;

    public void addText(String title){
        text.setText(TodoAdded.getHashMapNotes().get(title));
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }


}
