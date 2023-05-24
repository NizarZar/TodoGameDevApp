package com.nizar.todogamedevapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Files.createDirectories(Paths.get("C://sqlite/db"));
        createDatabase("categories.db");
        createDatabase("notes.db");
        createDatabase("completedNotes.db");
        createCategoriesTable();
        createNotesTable();
        createCompletedNotesTable();
        Parent root = MainSingleton.getInstance().getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Todo and Note App");
        stage.show();
        //stage.setOnCloseRequest(event -> {
           // event.consume();
            //onExit(stage);
       // });
    }

    private static void createCompletedNotesTable(){
        String url = "jdbc:sqlite:C://sqlite/db/completedNotes.db";
        String sql = "CREATE TABLE IF NOT EXISTS notes (\n" +
                "noteTitle TEXT,\n" +
                "noteText TEXT,\n" +
                "category TEXT\n" +
                ");";
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createNotesTable(){
        String url = "jdbc:sqlite:C://sqlite/db/notes.db";
        String sql = "CREATE TABLE IF NOT EXISTS notes (\n" +
                "noteTitle TEXT,\n" +
                "noteText TEXT,\n" +
                "category TEXT\n" +
                ");";
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private static void createCategoriesTable(){
        String url = "jdbc:sqlite:C://sqlite/db/categories.db";
        String sql = "CREATE TABLE IF NOT EXISTS categories (category TEXT);";
        try {
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static void createDatabase(String fileName){
        String url = "jdbc:sqlite:C://sqlite/db/"+fileName;
        try {
            Connection connection = DriverManager.getConnection(url);
            if(connection != null){
                DatabaseMetaData meta = connection.getMetaData();
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }


    public void onExit(Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit the application");
        alert.setContentText("Are you sure you want to exist?");
        if(alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("Application exit successful!");
            stage.close();
        }
    }
}