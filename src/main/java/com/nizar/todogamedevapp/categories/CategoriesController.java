package com.nizar.todogamedevapp.categories;

import com.nizar.todogamedevapp.MainSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {

    @FXML
    TextField categoryTextField;

    @FXML
    ListView<String> categoriesListView;

    private Connection connect(){
        String url = "jdbc:sqlite:C://sqlite/db/categories.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void onAddCategory(){
        categoriesListView.getItems().add(categoryTextField.getText());
        CategoriesSingleton.getCategories().add(categoryTextField.getText());
        String sql = "INSERT INTO categories(category) VALUES(?)";
        try {
            Connection connection = this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,categoryTextField.getText());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        categoryTextField.setText("");
    }

    public void onBack(ActionEvent event) throws IOException {
        Parent root = MainSingleton.getInstance().getRoot();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Game Dev Todo and Note App");
        Scene scene = root.getScene();
        stage.setScene(scene);
        stage.show();
    }

    //TODO: When deleting it always delete first one on ChoiceBox
    public void onDeleteCategory(){
        String selectedCategoryItem = categoriesListView.getSelectionModel().getSelectedItem();
        String sql = "DELETE FROM categories WHERE category = ?";
        try{
            Connection connection = this.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,selectedCategoryItem);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        categoriesListView.getItems().remove(selectedCategoryItem);
        CategoriesSingleton.getCategories().remove(selectedCategoryItem);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sql = "SELECT * FROM categories";
        try {
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                categoriesListView.getItems().add(resultSet.getString("category") + "\t");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
