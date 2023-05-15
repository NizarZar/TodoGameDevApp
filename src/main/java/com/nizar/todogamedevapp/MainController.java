package com.nizar.todogamedevapp;

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
import java.util.*;

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    ListView<String> listView;

    @FXML
    ChoiceBox<String> categoriesChoiceSort;

    // method for Add Note button that opens a scene to create your own note /todo
    public void addNote(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("note.fxml")));
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
        listView.getItems().add(text);
    }

    public void onCheck(ActionEvent event) throws IOException {
        // load selected note or todo
        FXMLLoader loader = new FXMLLoader(getClass().getResource("noteslist.fxml"));
        root = loader.load();
        NotesListController notesListController = loader.getController();
        notesListController.addText(listView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void deleteNoteItem(){
        System.out.println("Note deleted");
        listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
    }

    public void onOpenCategories(ActionEvent event) throws IOException {
        //FXMLLoader categoriesLoader = CategoriesSingleton.getInstance().categoriesFXML;
        //root = CategoriesSingleton.getInstance().root;
        //CategoriesController categoriesController = categoriesLoader.getController();
        //stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //stage.setScene(root.getScene());
        //stage.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoriesChoiceSort.getItems().add("All");
        categoriesChoiceSort.getItems().addAll(CategoriesSingleton.getCategories());
        animationTimer.start();
        // event when selecting from choicebox to sort
        categoriesChoiceSort.setOnAction(event -> {
            try {
                String selectedCategory = categoriesChoiceSort.getSelectionModel().getSelectedItem();
                HashMap<String, String> sortedHash = new HashMap<>();
                if (selectedCategory.equalsIgnoreCase("all") || selectedCategory.equalsIgnoreCase("")) {
                    sortedHash = TodoAdded.getHashMapNotes();
                } else {
                    for (String noteTitle : TodoAdded.getHashmapTitleCategory().keySet()) {
                        if (TodoAdded.getHashmapTitleCategory().get(noteTitle).equals(selectedCategory)) {
                            sortedHash.put(noteTitle, TodoAdded.getHashMapNotes().get(noteTitle));
                        }
                    }
                }
                listView.getItems().clear();
                for (String noteTitle : sortedHash.keySet()) {
                    listView.getItems().add(noteTitle + " (" + TodoAdded.getHashmapTitleCategory().get(noteTitle) + ")");
                }
            } catch (Exception e){
                try {
                    throw new Exception("Error in choice-box sorting", e);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        System.out.println(CategoriesSingleton.getCategories());
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
        }
    };
}