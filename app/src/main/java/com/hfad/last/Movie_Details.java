package com.hfad.last;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie_Details {

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
     @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Float popularity;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("vote_average")
    @Expose
    private Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;




    public Boolean getAdult() {
        return adult;
    }
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    public String getBackdropPath() {
        return backdropPath;
    }
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
    public String getHomepage() {
        return homepage;
    }
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getOriginalLanguage() {
        return originalLanguage;
    }
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public Float getPopularity() {
        return popularity;
    }
    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public Integer getRuntime() {
        return runtime;
    }
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }
    public Float getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }
    public Integer getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}

