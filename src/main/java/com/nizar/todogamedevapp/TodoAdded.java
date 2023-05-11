package com.nizar.todogamedevapp;

import java.util.HashMap;

public class TodoAdded {

        private static HashMap<String, String> hashMapNotes = new HashMap<>();

        public static void addText(TodoNote todonote){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
        }

    public static HashMap<String, String> getHashMapNotes() {
        return hashMapNotes;
    }
}
