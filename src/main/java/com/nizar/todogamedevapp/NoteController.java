package com.nizar.todogamedevapp;

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

    public void addNote(ActionEvent event) throws IOException {
        System.out.println(categories.getItems().toString());
        System.out.println(CategoriesSingleton.getCategories().toString());
        Stage stage;
        Scene scene;
        Parent root;
        String noteTitle = titleArea.getText();
        String noteText = noteArea.getText();
        // check if noteTitle or notetext are not empty
        if (!noteTitle.equals("") && !noteText.equals("")) {
            // get selected category
            String categorySelected = categories.getSelectionModel().getSelectedItem();
            // create the note with the title, text body and category parameters
            TodoNote todoNote = new TodoNote(noteTitle, noteText, categorySelected);
            // add it to the main scene singleton that shows all notes
            FXMLLoader loader = MainSingleton.getInstance().mainFXML;
            root = MainSingleton.getInstance().root;
            MainController mainController = loader.getController();
            mainController.addNoteItem(noteTitle + " (" + categorySelected + ")");
            // store the note data
            TodoAdded.addText(todoNote, categorySelected);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(String category : CategoriesSingleton.getCategories()){
            categories.getItems().add(category);
        }
    }
}
