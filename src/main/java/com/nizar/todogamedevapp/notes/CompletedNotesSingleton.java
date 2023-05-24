package com.nizar.todogamedevapp.notes;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class CompletedNotesSingleton {
    private static CompletedNotesSingleton completedNoteSingleton = null;
    private FXMLLoader completedNotesFXML;
    private Parent root;

    private CompletedNotesSingleton() throws IOException {
        completedNotesFXML = new FXMLLoader(getClass().getResource("completedNotes.fxml"));
        root = completedNotesFXML.load();
    }
    public static CompletedNotesSingleton getInstance() throws IOException {
        if(completedNoteSingleton == null){
            completedNoteSingleton = new CompletedNotesSingleton();
        }
        return completedNoteSingleton;
    }

    public Parent getRoot() {
        return root;
    }

    public FXMLLoader getCompletedNotesFXML() {
        return completedNotesFXML;
    }
}
