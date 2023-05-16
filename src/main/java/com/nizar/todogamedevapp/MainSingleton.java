package com.nizar.todogamedevapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MainSingleton {

   private static MainSingleton main = null;
   private FXMLLoader mainFXML;
   private Parent root;

    private MainSingleton() throws IOException {
        mainFXML = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = mainFXML.load();
    }
    public static MainSingleton getInstance() throws IOException {
        if(main == null){
            main = new MainSingleton();
        }
        return main;
    }

    public Parent getRoot() {
        return root;
    }

    public FXMLLoader getMainFXML() {
        return mainFXML;
    }
}
