package com.nizar.todogamedevapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MainSingleton {

   private static MainSingleton main = null;
   public FXMLLoader mainFXML;
   public Parent root;

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


}
