package com.hfad.last;

import java.util.List;

public class Movie  {
    private String name ;
    private String genre;
    private String data;



    public Movie (String name,String genre,String data){
        setData(data);
        setGenre(genre);
        setName(name);
    }
    public Movie(){}
    public String getData() {
        return data;
    }
    public String getGenre() {
        return genre;
    }
    public void setData(String data) {
        this.data = data;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
