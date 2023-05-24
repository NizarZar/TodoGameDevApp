package com.nizar.todogamedevapp;

import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.notes.CompletedNotesSingleton;
import com.nizar.todogamedevapp.notes.NoteEditController;
import com.nizar.todogamedevapp.notes.NoteTextController;
import com.nizar.todogamedevapp.todonote.TodoNote;
import com.nizar.todogamedevapp.todonote.TodoNoteData;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

// SQL IS SAVED at: C://sqlite/db for now

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> listView;

    @FXML
    ChoiceBox<String> choiceSortCategories;

    @FXML
    Label noteCategoryLabel;

    // handling sql connections
    private Connection connectCategoriesDB(){
        String url = "jdbc:sqlite:C://sqlite/db/categories.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
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

    private Connection connectCompletedNotesDB(){
        String url = "jdbc:sqlite:C://sqlite/db/completed_notes.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // method for Add Note button that opens a scene to create your own note
    public void addNote(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notes/note.fxml")));
        System.out.println(TodoNoteData.getHashMapNotes().toString());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Note Add");
        stage.setScene(scene);
        stage.show();
    }

    /*
    public void onLogout(ActionEvent event) throws IOException{
        System.out.println("Logged out!");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     */
    // method called to add an item to the note listview of main scene

    // add note to the listview (called by other classes)
    public void addNoteItem(String text){
        //System.out.println("Note Item Added");
        listView.getItems().add(text);
    }

    public void onCheck(ActionEvent event) throws IOException {
        if (listView.getSelectionModel().isEmpty()) {
            throw new IllegalStateException("You need to select a note");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("notes/notetext.fxml"));
            root = loader.load();
            NoteTextController notesListController = loader.getController();
            notesListController.addText(listView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void deleteNoteItem(String title){
        listView.getItems().remove(title);
        String sql = "DELETE FROM notes" +
                " WHERE noteTitle = ?" +
                " AND noteText = ?" +
                " AND category = ?";
        try {
            Connection connection = this.connectNotesDB();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,TodoNoteData.getHashMapNotes().get(title));
            preparedStatement.setString(3,TodoNoteData.getHashmapTitleCategory().get(title));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(TodoNoteData.getHashMapNotes().containsKey(title) && TodoNoteData.getHashmapTitleCategory().containsKey(title)){
            TodoNoteData.getHashMapNotes().remove(title);
            TodoNoteData.getHashmapTitleCategory().remove(title);
        }
    }

    public void deleteNoteItem(){
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        String sql = "DELETE FROM notes" +
                " WHERE noteTitle = ?" +
                    " AND noteText = ?" +
                    " AND category = ?";
        //debug
        //System.out.println("Note Item deleted");
        listView.getItems().remove(selectedItem);
        try {
            Connection connection = this.connectNotesDB();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, selectedItem);
            preparedStatement.setString(2,TodoNoteData.getHashMapNotes().get(selectedItem));
            preparedStatement.setString(3,TodoNoteData.getHashmapTitleCategory().get(selectedItem));
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        if(TodoNoteData.getHashMapNotes().containsKey(selectedItem) && TodoNoteData.getHashmapTitleCategory().containsKey(selectedItem)){
            TodoNoteData.getHashMapNotes().remove(selectedItem);
            TodoNoteData.getHashmapTitleCategory().remove(selectedItem);
        }
    }
    public void OnOpenCompletedNotes(ActionEvent event) throws IOException{
        root = CompletedNotesSingleton.getInstance().getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if(root.getScene() == null){
            scene = new Scene(root);
        } else {
            scene = root.getScene();
        }
        stage.setTitle("Completed Notes");
        stage.setScene(scene);
        stage.show();
    }
    public void onOpenCategories(ActionEvent event) throws IOException {
        root = CategoriesSingleton.getInstance().getRoot();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if(root.getScene() == null){
            scene = new Scene(root);
        } else {
            scene = root.getScene();
        }
        stage.setTitle("Categories");
        stage.setScene(scene);
        stage.show();
        //System.out.println(CategoriesSingleton.getCategories().toString());
    }

    public void onOpenEditNote(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("notes/noteedit.fxml"));
        root = loader.load();
        NoteEditController noteEditController = loader.getController();
        noteEditController.setOriginalText(listView.getSelectionModel().getSelectedItem(),TodoNoteData.getHashMapNotes().get(listView.getSelectionModel().getSelectedItem()));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sqlCategories = "SELECT * FROM categories";
        String sqlNotes = "SELECT * FROM notes";
        String sqlCompletedNotes = "SELECT * FROM completed_notes";
        // check and add categories from database at launch
        try {
            Connection connection = this.connectCategoriesDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCategories);
            while(resultSet.next()){
                CategoriesSingleton.getCategories().add(resultSet.getString("category"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        // check and add notes from database at launch
        try {
            Connection connection = this.connectNotesDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlNotes);
            while(resultSet.next()){
                TodoNote todoNote = new TodoNote(resultSet.getString("noteTitle"),resultSet.getString("noteText"),resultSet.getString("category"));
                TodoNoteData.addData(todoNote);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        try {
            Connection connection = this.connectCompletedNotesDB();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCompletedNotes);
            while(resultSet.next()){
                TodoNote completedNote = new TodoNote(resultSet.getString("noteTitle"),resultSet.getString("noteText"),resultSet.getString("category"));
                TodoNoteData.addCompletedNote(completedNote);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        // adding all notes
        listView.getItems().addAll(TodoNoteData.getHashMapNotes().keySet());
        // adding all categories to choiceboxList and the default category "all"
        choiceSortCategories.getItems().add("All");
        choiceSortCategories.getItems().addAll(CategoriesSingleton.getCategories());
        animationTimer.start();
        // clicking on list of notes
        listView.setOnMouseClicked(event -> {
            noteCategoryLabel.setText(TodoNoteData.getHashmapTitleCategory().get(listView.getSelectionModel().getSelectedItem()));
        });
        // event when selecting from choiceboxList to sort
        choiceSortCategories.setOnAction(event -> {try {
            String selectedCategory = choiceSortCategories.getSelectionModel().getSelectedItem();
            // System.out.println(selectedCategory);
            //debug
            //System.out.println("AFTER CLICKING CHOICEBOX:");
            //System.out.println(TodoNoteData.getHashMapNotes().toString());
            //System.out.println(TodoNoteData.getHashmapTitleCategory().toString());
            // sorted hashmap from choiceboxList
            HashMap<String, String> sortedHash = new HashMap<>();
            if (selectedCategory.equalsIgnoreCase("all") || selectedCategory.equalsIgnoreCase("")) {
                sortedHash = TodoNoteData.getHashMapNotes();
            } else {
                for (String noteTitle : TodoNoteData.getHashmapTitleCategory().keySet()) {
                    if (TodoNoteData.getHashmapTitleCategory().get(noteTitle).equals(selectedCategory)) {
                        sortedHash.put(noteTitle, TodoNoteData.getHashMapNotes().get(noteTitle));
                    }
                }
            }
            listView.getItems().clear();
            listView.getItems().addAll(sortedHash.keySet());

        } catch (Exception e){
            try {
                throw new Exception("Error in choice-box sorting", e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        });
    }

    // frame updater
    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            for (String category : CategoriesSingleton.getCategories()) {
                if (!choiceSortCategories.getItems().contains(category)) {
                    choiceSortCategories.getItems().add(category);
                }
            }
            choiceSortCategories.getItems().removeIf(category -> !CategoriesSingleton.getCategories().contains(category) && !category.equalsIgnoreCase("All"));
        }};
}