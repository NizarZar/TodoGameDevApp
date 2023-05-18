package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.todonote.TodoNote;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML
    TextField titleTextField;

    private String originalText;
    private String originalTitle;

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

    public void onEdit(ActionEvent event) throws IOException, SQLException {
        String text = editTextArea.getText();
        String title = titleTextField.getText();
        String sql = "UPDATE notes SET noteText = ?, noteTitle = ?, category = ? WHERE noteTitle = ?";
        try {
            Connection connection = this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,title);
            if(categories.getSelectionModel().isEmpty()){
                preparedStatement.setString(3,TodoNoteData.getHashmapTitleCategory().get(originalTitle));
            } else {
                preparedStatement.setString(3,categories.getSelectionModel().getSelectedItem());
            }
            preparedStatement.setString(4, TodoNoteData.getHashmapNotesVK().get(originalText));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            if(!connect().isClosed()){
                try {
                    connect().close();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        TodoNote editedNote;
        if(categories.getSelectionModel().isEmpty()){
            editedNote = new TodoNote(title,text, TodoNoteData.getHashmapTitleCategory().get(originalTitle));
        } else {
            editedNote = new TodoNote(title,text,categories.getSelectionModel().getSelectedItem());
        }
        TodoNoteData.getHashMapNotes().remove(originalTitle);
        TodoNoteData.getHashmapTitleCategory().remove(originalTitle);
        TodoNoteData.addText(editedNote);
        //System.out.println(TodoNoteData.getHashMapNotes().toString());
        //System.out.println(TodoNoteData.getHashmapNotesVK());
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }

    public void setOriginalText(String title,String text){
        editTextArea.setText(text);
        titleTextField.setText(title);
        originalText = text;
        originalTitle = title;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories.getItems().addAll(CategoriesSingleton.getCategories());
    }
}
