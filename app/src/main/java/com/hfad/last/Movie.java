package com.hfad.last;

import android.graphics.Bitmap;

public class Movie  {
    private String name ;
    private int vote;
    private String data;
    private Bitmap poster_path;
    private int id;
    private String movie_title;
    private String overviwe;
    private String language;
    private boolean adult;
    private Bitmap backdrop_path;



   public Movie(String name, int vote, String data, Bitmap poster_path, int id,String movie_title,String overviwe,String language,boolean adult
    ,Bitmap backdrop_path){
        setId(id);
        setBackdrop_path(backdrop_path);
        setAdult(adult);
        setLanguage(language);
        setMovie_title(movie_title);
        setOverviwe(overviwe);
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
    public String getMovie_title() {
        return movie_title;
    }
    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }
    public String getOverviwe() {
        return overviwe;
    }
    public void setOverviwe(String overviwe) {
        this.overviwe = overviwe;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public boolean isAdult() {
        return adult;
    }
    public void setAdult(boolean adult) {
        this.adult = adult;
    }
    public Bitmap getBackdrop_path() {
        return backdrop_path;
    }
    public void setBackdrop_path(Bitmap backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
}