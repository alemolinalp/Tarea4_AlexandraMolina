package com.example.alexandramolina.tarea4_alexandramolina;
import android.graphics.Bitmap;

/**
 * Created by alexandramolina on 23/3/18.
 */

public class Movie {
    private String name;
    private float rating;
    private Bitmap image;
    private float metascore;
    private String imageUrl;

    public Movie() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getMetascore() {
        return metascore;
    }

    public void setMetascore(float metascore) {
        this.metascore = metascore;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
