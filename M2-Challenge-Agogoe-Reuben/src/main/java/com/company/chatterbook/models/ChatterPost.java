package com.company.chatterbook.models;

public class ChatterPost {

    private String text;
    public ChatterPost(String text){
        this.text = text;
    }
    // to test on terminal
    public String getPost(){
        return text;
    }
}
