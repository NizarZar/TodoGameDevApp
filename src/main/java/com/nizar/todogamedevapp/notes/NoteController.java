package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainController;
import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.todonote.TodoAdded;
import com.nizar.todogamedevapp.todonote.TodoNote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    @FXML
    TextArea noteArea;

    @FXML
    TextField titleArea;

    @FXML
    ListView<String> categories;

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public void addNote(ActionEvent event) throws IOException {
        // stage, scene and root
        // category selected
        String categorySelected = categories.getSelectionModel().getSelectedItem();
        String noteTitle = titleArea.getText();
        String noteText = noteArea.getText();
        // check if noteTitle or notetext are not empty
        if (!noteTitle.equals("") && !noteText.equals("")){ //&& categorySelected != null) {
            // get selected category
            // create the note with the title, text body and category parameters
            TodoNote todoNote = new TodoNote(noteTitle, noteText, categorySelected);
            // add it to the main scene singleton that shows all notes
            FXMLLoader loader = MainSingleton.getInstance().getMainFXML();
            Parent root = MainSingleton.getInstance().getRoot();
            MainController mainController = loader.getController();
            mainController.addNoteItem(noteTitle + " (" + categorySelected + ")");
            // store the note data
            TodoAdded.addText(todoNote);

            System.out.println("Note added");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = root.getScene();
            stage.setTitle("Game Dev Todo and Note App");
            stage.setScene(scene);
            stage.show();
        } else {
            throw new IllegalArgumentException("Text, title or category cannot be empty!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories.getItems().addAll(CategoriesSingleton.getCategories());
    }
}
