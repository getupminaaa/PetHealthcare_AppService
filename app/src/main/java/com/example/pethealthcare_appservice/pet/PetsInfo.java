package com.example.pethealthcare_appservice.pet;

public class PetsInfo {

    private String pName;
    private String gender;
    private String species;
    private String breed;
    private String neutral;


    public PetsInfo(String pName, String species, String breed, String gender, String neutral) {
        this.pName = pName;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.neutral = neutral;
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
        return this.species;
    }

    public void setAddress(String species) {
        this.species = species;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getNeutral() {
        return this.neutral;
    }

    public void setNeutral(String neutral) {
        this.neutral = neutral;
    }
}