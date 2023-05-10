package com.nizar.todogamedevapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class NotesListController {

    @FXML
    Text text = new Text();

    public void addText(String title){
        text.setText(TodoAdded.hashMapNotes.get(title));
    }


}
