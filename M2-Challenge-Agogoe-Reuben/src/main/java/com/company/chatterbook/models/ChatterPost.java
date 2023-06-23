package com.company.chatterbook.models;

public class ChatterPost {

    private String text;
    public ChatterPost(String text){
        this.text = text;
    }
    // getter to serialize
    public String getPost(){
        return text;
    }


}
