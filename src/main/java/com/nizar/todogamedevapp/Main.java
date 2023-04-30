package com.nizar.todogamedevapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root);
        Text text = new Text();

        text.setText("Game Dev App");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Veranda", 50));
        text.setFill(Color.RED);
        root.getChildren().add(text);

        stage.setTitle("Game Dev Application");
        stage.setHeight(800);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}