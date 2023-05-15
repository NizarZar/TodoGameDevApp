package com.nizar.todogamedevapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;

public class CategoriesSingleton {

    private static CategoriesSingleton categoriesSingleton = null;
    private static ArrayList<String> categories = new ArrayList<>();
    private FXMLLoader categoriesFXML;
    private Parent root;

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

    public static ArrayList<String> getCategories() {
        return categories;
    }

    public Parent getRoot() {
        return root;
    }

    public FXMLLoader getCategoriesFXML() {
        return categoriesFXML;
    }
}
