package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompletedNotesController implements Initializable {

    @FXML
    ListView<String> completedNotes;

    @FXML
    Label category;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            completedNotes.getItems().addAll(TodoNoteData.getHashmapCompletedNotes().keySet());
            completedNotes.setOnMouseClicked(mouseEvent -> {
                category.setText(TodoNoteData.getHashmapCompletedNotesCategory().get(completedNotes.getSelectionModel().getSelectedItem()));
            });
            category.setVisible(true);
    }

    public void onCheck(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (completedNotes.getSelectionModel().isEmpty()) {
            throw new IllegalStateException("You need to select a note");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("notetext.fxml"));
            root = loader.load();
            NoteTextController notesListController = loader.getController();
            notesListController.addText(completedNotes.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void OnBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //System.out.println(TodoNoteData.getHashMapNotes().values());
        stage.setTitle("Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
    }
}
