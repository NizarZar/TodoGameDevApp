package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NotesListController {

    @FXML
    Text text = new Text();

    @FXML
    Button backButton;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void addText(String title){
        text.setText(TodoAdded.getHashMapNotes().get(title));
    }

    // maincontroller values are reset - to fix
    public void onBack(ActionEvent event) throws IOException {
        root = MainSingleton.getInstance().root;
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Game Dev Todo and Note App");
        scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }


}
