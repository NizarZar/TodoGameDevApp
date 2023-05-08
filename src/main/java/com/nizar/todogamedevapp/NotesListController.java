package com.nizar.todogamedevapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotesListController {

    @FXML
    Label label = new Label();

    public void addText(){
        for(String s : TodoAdded.hashMapNotes.keySet()){
                label.setText(TodoAdded.hashMapNotes.get(s));
        }

    }


}
