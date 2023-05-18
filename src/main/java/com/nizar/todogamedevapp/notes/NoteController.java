package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainController;
import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NoteController implements Initializable {

    @FXML
    TextArea noteArea;

    @FXML
    TextField titleArea;

    @FXML
    ListView<String> categories;

    private Connection connectNotesDB(){
        String url = "jdbc:sqlite:C://sqlite/db/notes.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
    }

    public void addNote(ActionEvent event) throws IOException, SQLException {
        // stage, scene and root
        // category selected
        String categorySelected = categories.getSelectionModel().getSelectedItem();
        String noteTitle = titleArea.getText();
        String noteText = noteArea.getText();
        String sql = "INSERT INTO notes (noteTitle, noteText, category) VALUES(?,?,?)";
        // check if noteTitle or notetext are not empty
        if (!noteTitle.equals("") && !noteText.equals("")){ //&& categorySelected != null) {
            // adding to the database:
            try {
                Connection connection = this.connectNotesDB();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,noteTitle);
                preparedStatement.setString(2,noteText);
                preparedStatement.setString(3,categorySelected);
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            } finally {
                if(!connectNotesDB().isClosed()){
                    try {
                        connectNotesDB().close();
                    } catch (SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            // create the note with the title, text body and category parameters
            TodoNote todoNote = new TodoNote(noteTitle, noteText, categorySelected);
            // add it to the main scene singleton that shows all notes
            FXMLLoader loader = MainSingleton.getInstance().getMainFXML();
            Parent root = MainSingleton.getInstance().getRoot();
            MainController mainController = loader.getController();
            mainController.addNoteItem(noteTitle);
            // store the note data
            TodoNoteData.addText(todoNote);

            //System.out.println("Note added");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = root.getScene();
            stage.setTitle("Todo and Note App");
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
