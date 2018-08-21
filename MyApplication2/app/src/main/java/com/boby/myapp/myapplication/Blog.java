package com.boby.myapp.myapplication;

/**
 * Created by boby on 3/7/2017.
 */

public class Blog{

private String imageUrl;
    private String description;
    private long numLike;

    public long getNumLike2() {
        return numLike2;
    }

    public void setNumLike2(long numLike2) {
        this.numLike2 = numLike2;
    }

    public  long numLike2;

    public long getNumLike() {
        return numLike;
    }

    public void setNumLike(long numLike) {
        this.numLike = numLike;
    }

    public Blog(String imageUrl, String description, String title , long numLike,long numLike2) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
        this.numLike= numLike;
        this.numLike2= numLike2;
    }
    public Blog(){

    }

    private String title;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




}



