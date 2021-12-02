package com.example.favoriteshoe;

public class Shoe {

    private int shoeID;
    private String shoeBrand;
    private String colorway;
    private String releaseDate;

    public Shoe() {
        shoeID = -1;
    }

    public String toString() {
        return shoeID + shoeBrand + colorway + releaseDate;
    }

    public int getShoeID() {
        return shoeID;
    }

    public void setShoeID(int shoeID) {
        shoeID = shoeID;
    }

    public String getShoeBrand() {
        return shoeBrand;
    }

    public void setShoeBrand(String shoeBrand) {
        this.shoeBrand = shoeBrand;
    }

    public String getColorway() {
        return colorway;
    }

    public void setColorway(String colorway) {
        this.colorway = colorway;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}
