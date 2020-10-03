package com.hfad.last;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyMovie extends ViewModel {
    private List<Movie> myList = null ;
    public void MovieUpdate(List<Movie> add) {myList.addAll(add);}

    public List<Movie> getMyList() {
        return myList;
    }

    public void setMyList(List<Movie> myList) {
        this.myList = myList;
    }
}
