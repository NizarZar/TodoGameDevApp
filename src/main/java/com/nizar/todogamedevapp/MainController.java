package com.nizar.todogamedevapp;

import com.nizar.todogamedevapp.categories.CategoriesSingleton;
import com.nizar.todogamedevapp.notes.NoteTextController;
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
    ChoiceBox<String> categoriesChoiceSort;
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

    // method for Add Note button that opens a scene to create your own note /todo
    public void addNote(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("notes/note.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Note Add");
        stage.setScene(scene);
        stage.show();
    }

    public void onLogout(ActionEvent event) throws IOException{
        System.out.println("Logged out!");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    // method called to add an item to the note listview of main scene

    public void addNoteItem(String text){
        System.out.println("Note Item Added");
        listView.getItems().add(text);
    }

    public void onCheck(ActionEvent event) throws IOException {
        // load selected note or todo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("notes/notetext.fxml"));
        root = loader.load();
        NoteTextController notesListController = loader.getController();
        notesListController.addText(listView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteNoteItem(){
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(selectedItem);
        if(TodoNoteData.getHashMapNotes().containsKey(selectedItem)){
            TodoNoteData.getHashMapNotes().remove(selectedItem);
            TodoNoteData.getHashmapTitleCategory().remove(selectedItem);
        }
        //debug
        System.out.println("Note Item deleted");
        System.out.println("AFTER DELETING:");
        System.out.println(TodoNoteData.getHashmapTitleCategory());
        System.out.println(TodoNoteData.getHashMapNotes());
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
        System.out.println(CategoriesSingleton.getCategories().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sqlCategories = "SELECT * FROM categories";
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
        categoriesChoiceSort.getItems().add("all");
        categoriesChoiceSort.getItems().addAll(CategoriesSingleton.getCategories());
        animationTimer.start();
        // event when selecting from choicebox to sort
        categoriesChoiceSort.setOnAction(event -> {
            try {
                String selectedCategory = categoriesChoiceSort.getSelectionModel().getSelectedItem();
                System.out.println(selectedCategory.toString());
                //debug
                System.out.println("AFTER CLICKING CHOICEBOX:");
                System.out.println(TodoNoteData.getHashMapNotes().toString());
                System.out.println(TodoNoteData.getHashmapTitleCategory().toString());
                // sorted hashmap from choicebox
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
            for(String category : CategoriesSingleton.getCategories()){
                if(!categoriesChoiceSort.getItems().contains(category)){
                    categoriesChoiceSort.getItems().add(category);
                }
            }
            categoriesChoiceSort.getItems().removeIf(category -> !CategoriesSingleton.getCategories().contains(category));
        }
    };
}