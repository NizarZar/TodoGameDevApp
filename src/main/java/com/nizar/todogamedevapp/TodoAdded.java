package com.nizar.todogamedevapp;

import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TodoAdded {

        public static HashMap<String, String> hashMapNotes = new HashMap<>();

        public static void addText(TodoNote todonote){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
        }



}
