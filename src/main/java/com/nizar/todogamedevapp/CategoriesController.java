package com.nizar.todogamedevapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CategoriesController {

    @FXML
    TextField categoryTextField;

    @FXML
    ListView<String> categoriesListView;


    public void onAddCategory(){
        categoriesListView.getItems().add(categoryTextField.getText());
        CategoriesSingleton.getCategories().add(categoryTextField.getText());
        categoryTextField.setText("");
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().root;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
    }
    public void onDeleteCategory(){
        categoriesListView.getItems().remove(categoriesListView.getSelectionModel().getSelectedItem());
    }


}
