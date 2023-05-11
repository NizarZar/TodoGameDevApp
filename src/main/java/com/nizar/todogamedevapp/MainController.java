package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // TODO: FIX MAINCONTROLLER RESETTING EACH TIME
    @FXML
    ListView<String> listView;

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
    public void addNoteItem(String text){
        listView.getItems().add(text);
    }

    public void onCheck(ActionEvent event) throws IOException {
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
        root = CategoriesSingleton.getInstance().root;
        //CategoriesController categoriesController = categoriesLoader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(root.getScene());
        stage.show();

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("categories.fxml"));
        //root = loader.load();
        //stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //stage.setScene(new Scene(root));
        //stage.show();
    }



}