package com.hfad.last;

import android.graphics.Bitmap;

public class Movie  {
    private String name ;
    private int vote;
    private String data;
    private Bitmap poster_path;
    private int id;



    public Movie(String name, int vote, String data, Bitmap poster_path, int id){
        setId(id);
        setData(data);
        setVote(vote);
        setName(name);
        setPoster_path(poster_path);
    }

    public Movie(){
            }


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
    public Bitmap getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(Bitmap poster_path) {
        this.poster_path = poster_path;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
