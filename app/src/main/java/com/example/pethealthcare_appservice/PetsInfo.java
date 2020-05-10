package com.example.pethealthcare_appservice;

public class PetsInfo {

    private String pName;
    private String gender;
    private String species;
    private String breed;


    public PetsInfo(String pName, String gender, String species, String breed) {
        this.pName = pName;
        this.gender = gender;
        this.species = species;
        this.breed = breed;
    }

    public String getPName() {
        return this.pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return this.species ;
    }

    public void setAddress(String species) {
        this.species = species;
    }

    public String getBreed() {
        return this.breed ;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


}