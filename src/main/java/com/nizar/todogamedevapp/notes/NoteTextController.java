package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class NoteTextController {

    @FXML
    Text todoTextNote;

    @FXML
    Button backButton;

    public void addText(String title){
        todoTextNote.setText(TodoNoteData.getHashMapNotes().get(title));
        todoTextNote.setVisible(true);
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //System.out.println(TodoNoteData.getHashMapNotes().values());
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }


}
