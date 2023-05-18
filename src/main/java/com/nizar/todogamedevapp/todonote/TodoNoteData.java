package com.nizar.todogamedevapp.todonote;

import java.util.HashMap;

public class TodoNoteData {


        // TODO: Change HashMap to another Data Structure to store repeated title
        private static HashMap<String, String> hashMapNotes = new HashMap<>();
        private static HashMap<String, String> hashmapTitleCategory = new HashMap<>();
        private static HashMap<String, String> hashmapNotesVK = new HashMap<>();

        public static void addData(TodoNote todonote){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
            hashmapTitleCategory.put(todonote.getTitle(), todonote.getCategory());
            hashmapNotesVK.put(todonote.getText(),todonote.getTitle());
        }

    public static HashMap<String, String> getHashMapNotes() {
        return hashMapNotes;
    }
    public static HashMap<String, String> getHashmapTitleCategory(){
            return hashmapTitleCategory;
    }
    public static HashMap<String, String> getHashmapNotesVK() {
            return hashmapNotesVK;
        }

}
