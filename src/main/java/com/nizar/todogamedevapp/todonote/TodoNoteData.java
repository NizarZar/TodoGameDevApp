package com.nizar.todogamedevapp.todonote;

import java.util.HashMap;

public class TodoNoteData {


        // TODO: Change HashMap to another Data Structure to store repeated title
        private static HashMap<String, String> hashMapNotes = new HashMap<>();
        private static HashMap<String, String> hashmapTitleCategory = new HashMap<>();
        private static HashMap<String, String> hashmapNotesVK = new HashMap<>();

        private static HashMap<String, String> hashmapCompletedNotes = new HashMap<>();
        private static HashMap<String, String> hashmapCompletedNotesCategory = new HashMap<>();
        private static HashMap<String, String> hashmapCompletedNotesVK = new HashMap<>();

        public static void addData(TodoNote todonote){
            hashMapNotes.put(todonote.getTitle(), todonote.getText());
            hashmapTitleCategory.put(todonote.getTitle(), todonote.getCategory());
            hashmapNotesVK.put(todonote.getText(),todonote.getTitle());
            try {
                hashmapCompletedNotes.remove(todonote.getTitle(),todonote.getText());
                hashmapCompletedNotesVK.remove(todonote.getText(),todonote.getTitle());
                hashmapCompletedNotesCategory.remove(todonote.getTitle(),todonote.getCategory());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        public static void addCompletedNote(TodoNote todoNote){
            hashmapCompletedNotes.put(todoNote.getTitle(),todoNote.getText());
            hashmapCompletedNotesCategory.put(todoNote.getTitle(),todoNote.getCategory());
            hashmapCompletedNotesVK.put(todoNote.getText(),todoNote.getTitle());
             try {
                 hashMapNotes.remove(todoNote.getTitle(), todoNote.getText());
                 hashmapTitleCategory.remove(todoNote.getTitle(), todoNote.getCategory());
                 hashmapNotesVK.remove(todoNote.getText(), todoNote.getTitle());
             } catch (Exception e){
                 System.out.println(e.getMessage());
             }
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

    public static HashMap<String, String> getHashmapCompletedNotes() {
        return hashmapCompletedNotes;
    }
    public static HashMap<String, String> getHashmapCompletedNotesCategory() {
        return hashmapCompletedNotesCategory;
    }

    public static HashMap<String, String> getHashmapCompletedNotesVK() {
        return hashmapCompletedNotesVK;
    }
}
