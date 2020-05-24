package com.example.todolist;

public class TodoListClass {

    private String title;
    private String description;
    private int uid;

    public TodoListClass() {
    }

    public TodoListClass(String title, String description, int uid) {
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
