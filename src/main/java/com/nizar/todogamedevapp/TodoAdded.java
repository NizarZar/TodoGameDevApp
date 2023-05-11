package com.nizar.todogamedevapp;

import java.util.HashMap;

public class TodoAdded {

        private static HashMap<String, String> hashMapNotes = new HashMap<>();
        private static HashMap<String, String> hashmapTitleCategory = new HashMap<>();

        public static void addText(TodoNote todonote, String category){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
            hashmapTitleCategory.put(todonote.getTitle(),category);
        }

    public static HashMap<String, String> getHashMapNotes() {
        return hashMapNotes;
    }
    public static HashMap<String, String> getHashmapTitleCategory(){
            return hashmapTitleCategory;
    }
}
