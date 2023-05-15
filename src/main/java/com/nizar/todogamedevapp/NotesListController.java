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
    Text todoTextNote;

    @FXML
    Button backButton;

    // TODO: FIX TEXT NOT APPEARING
    public void addText(String title){
        todoTextNote.setText(TodoAdded.getHashMapNotes().get(title));
        todoTextNote.setVisible(true);
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        System.out.println(TodoAdded.getHashMapNotes().values());
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }


}
