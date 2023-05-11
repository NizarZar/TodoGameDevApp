package com.nizar.todogamedevapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class CategoriesSingleton {

    private static CategoriesSingleton categoriesSingleton = null;
    public FXMLLoader categoriesFXML;
    public Parent root;

    private CategoriesSingleton() throws IOException {
        categoriesFXML = new FXMLLoader(getClass().getResource("categories.fxml"));
        root = categoriesFXML.load();
    }

    public static CategoriesSingleton getInstance() throws IOException {
        if(categoriesSingleton == null){
            categoriesSingleton = new CategoriesSingleton();
        }
        return categoriesSingleton;
    }

}
