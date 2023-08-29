package com.nizar.todogamedevapp.projects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class MainProjectsSingleton {

    private static MainProjectsSingleton mainProjectsSingleton = null;
    private FXMLLoader mainProjectsFXML;
    private Parent root;

    public MainProjectsSingleton() throws IOException {
        mainProjectsFXML = new FXMLLoader(getClass().getResource("mainprojects.fxml"));
        root = mainProjectsFXML.load();

    }

    public static MainProjectsSingleton getInstance() throws IOException{
        if(mainProjectsSingleton == null){
            mainProjectsSingleton = new MainProjectsSingleton();

        }
        return mainProjectsSingleton;
    }

    public Parent getRoot(){
        return root;
    }

    public FXMLLoader getMainProjectsFXML() {
        return mainProjectsFXML;
    }
}
