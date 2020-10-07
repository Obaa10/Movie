package com.hfad.last.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailsResponse {

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
    @SerializedName("poster_path")
    @Expose
    private String posterPath;




    public Boolean getAdult() {
        return adult;
    }
    public String getOriginalLanguage() {
        return originalLanguage;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public String getOverview() {
        return overview;
    }
    public Float getPopularity() {
        return popularity;
    }
    public Integer getRuntime() {
        return runtime;
    }
    public Float getVoteAverage() {
        return voteAverage;
    }
    public String getPosterPath() {
        return posterPath;
    }

}

