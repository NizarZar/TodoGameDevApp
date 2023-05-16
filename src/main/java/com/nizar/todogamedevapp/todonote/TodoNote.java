package com.nizar.todogamedevapp.todonote;

public class TodoNote {

    private String title;
    private String text;
    private String category;
    public TodoNote(String title, String text, String category){
        this.title = title;
        this.text = text;
        this.category = category;
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

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
