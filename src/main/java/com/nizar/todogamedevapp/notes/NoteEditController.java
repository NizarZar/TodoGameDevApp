package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NoteEditController implements Initializable {

    @FXML
    ListView<String> categories;

    @FXML
    TextArea editTextArea;

    private String originalText;

    private Connection connect(){
        String url = "jdbc:sqlite:C://sqlite/db/notes.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void onEdit(ActionEvent event) throws IOException {
        String text = editTextArea.getText();
        String sql = "UPDATE notes SET noteText = ? WHERE noteTitle = ?";
        try {
            Connection connection = this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.setString(2, TodoNoteData.getHashmapNotesVK().get(originalText));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        TodoNoteData.getHashMapNotes().put(TodoNoteData.getHashmapNotesVK().get(originalText),text);
        String tempKey = TodoNoteData.getHashmapNotesVK().get(originalText);
        TodoNoteData.getHashmapNotesVK().put(text,tempKey);

        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }

    public void setOriginalText(String text){
        editTextArea.setText(text);
        originalText = text;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories.getItems().addAll(CategoriesSingleton.getCategories());
    }
}
