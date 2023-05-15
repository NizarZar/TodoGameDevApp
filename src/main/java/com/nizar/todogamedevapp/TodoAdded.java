package com.nizar.todogamedevapp;

import java.util.HashMap;

public class TodoAdded {


        // TODO: Change HashMap to another Data Structure to store repeated title
        private static HashMap<String, String> hashMapNotes = new HashMap<>();
        private static HashMap<String, String> hashmapTitleCategory = new HashMap<>();

        public static void addText(TodoNote todonote){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
            hashmapTitleCategory.put(todonote.getCategory(), todonote.getTitle());
        }
    public static HashMap<String, String> getHashMapNotes() {
        return hashMapNotes;
    }
    public static HashMap<String, String> getHashmapTitleCategory(){
            return hashmapTitleCategory;
    }
}
