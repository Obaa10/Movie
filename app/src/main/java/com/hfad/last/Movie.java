package com.hfad.last;

import java.util.List;

public class Movie  {
    private String name ;
    private int vote;
    private String data;



    public Movie (String name,int vote,String data){
        setData(data);
        setVote(vote);
        setName(name);
    }
    public Movie(){}
    public String getData() {
        return data;
    }
    public int getVote() {
        return vote;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setVote(int vote) {
        this.vote = vote;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
