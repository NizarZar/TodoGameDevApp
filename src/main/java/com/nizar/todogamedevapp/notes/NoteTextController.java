package com.nizar.todogamedevapp.notes;

import com.nizar.todogamedevapp.MainController;
import com.nizar.todogamedevapp.MainSingleton;
import com.nizar.todogamedevapp.todonote.TodoNote;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NoteTextController{

    @FXML
    Text todoTextNote;

    @FXML
    Button backButton;

    @FXML
    CheckBox completedCheckBox;

    private String title = "";

    private Connection connectNotes(){
        String url = "jdbc:sqlite:C://sqlite/db/notes.db";
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
    private Connection connect(){
        String url = "jdbc:sqlite:C://sqlite/db/completed_notes.db";
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void addText(String title){
        if(TodoNoteData.getHashMapNotes().containsKey(title)){
            todoTextNote.setText(TodoNoteData.getHashMapNotes().get(title));
        } else {
            todoTextNote.setText(TodoNoteData.getHashmapCompletedNotes().get(title));
        }
        this.title = title;
        todoTextNote.setVisible(true);
    }


    // back button
    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //System.out.println(TodoNoteData.getHashMapNotes().values());
        stage.setTitle("Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();

    }


    // completed note check
    public void onCompletedCheck() throws IOException {
        if(completedCheckBox.isSelected()){
            String sql ="INSERT INTO completed_notes (noteTitle, noteText, category) VALUES(?,?,?)";
            try {
                Connection connection = this.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,title);
                preparedStatement.setString(2, todoTextNote.getText());
                preparedStatement.setString(3,TodoNoteData.getHashmapTitleCategory().get(title));
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
            System.out.println("NOTE TITLE:");
            System.out.println(TodoNoteData.getHashmapTitleCategory().toString());
            TodoNote note = new TodoNote(title, todoTextNote.getText(),
                    TodoNoteData.getHashmapTitleCategory().get(title));
            TodoNoteData.addCompletedNote(note);
            MainController mainController = MainSingleton.getInstance().getMainFXML().getController();
            mainController.deleteNoteItem(title);
        } else if(!completedCheckBox.isSelected()){
            if(TodoNoteData.getHashmapCompletedNotes().containsValue(todoTextNote.getText())){
                String sql = "DELETE FROM completed_notes" +
                        " WHERE noteTitle = ?" +
                        " AND noteText = ?" +
                        " AND category = ?";
                String sqlInsert ="INSERT INTO notes (noteTitle, noteText, category) VALUES(?,?,?)";
                try {
                    Connection connection = this.connect();
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1,title);
                    preparedStatement.setString(2,todoTextNote.getText());
                    preparedStatement.setString(3,TodoNoteData.getHashmapCompletedNotesCategory().get(title));
                    preparedStatement.executeUpdate();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                try {
                    Connection connection = this.connectNotes();
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
                    preparedStatement.setString(1,title);
                    preparedStatement.setString(2,todoTextNote.getText());
                    preparedStatement.setString(3,TodoNoteData.getHashmapCompletedNotesCategory().get(title));
                    preparedStatement.executeUpdate();
                } catch (SQLException e){
                    System.out.println(e.getMessage());
                }
                MainController mainController = MainSingleton.getInstance().getMainFXML().getController();
                mainController.addNoteItem(title);
                TodoNote note = new TodoNote(title,todoTextNote.getText(), TodoNoteData.getHashmapCompletedNotesCategory().get(title));
                TodoNoteData.addData(note);
                TodoNoteData.removeCompletedNote(note);

            }
        }
    }

}
