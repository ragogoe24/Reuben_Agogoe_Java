package com.company.chatterbook.models;

import java.util.List;

public class User {
    private String name;
    private List<ChatterPost> chatterPosts;

    public User(String name){
        this.name = name;
    }

    public void setChatterPosts(List<ChatterPost> posts){
        chatterPosts = posts;
    }

    public List<ChatterPost> getChatterPosts(){
        return chatterPosts;
    }

    public String getName(){
        return name;
    }


}
