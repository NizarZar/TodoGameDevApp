package com.nizar.todogamedevapp;

public class TodoNote {

    private String title;
    private String text;
    public TodoNote(String title, String text){
        this.title = title;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
