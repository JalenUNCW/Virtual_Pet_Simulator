package com.example.vp_simulator;

public class Dog extends Pet{

    private DogBreed breed;

    public enum DogBreed {
        Pug,
        Poodle,
        Lab,
        Shepherd;
    }

    public Dog() {
        super();
        // add random breed here
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

    public DogBreed getBreed() {
        return breed;
    }

    @Override
    public void feed() {
        setHunger(100);
        setEnergy(100);
        setHappiness(getHappiness() + 20);
    }

    @Override
    public void train() {
        setEnergy(getEnergy() - 10);
        setHappiness(getHappiness() + 20);
    }

    @Override
    public void outing() {
        setHappiness(getHappiness() + 40);
        setEnergy(getEnergy() - 10);
    }

    @Override
    public void play() {
        setEnergy(getEnergy() - 10);
        setHappiness(getHappiness() + 40);
    }

    @Override
    public void checkup() {
        setHealth(getHealth() + 40);
        setHappiness(getHappiness() - 10);
    }

}
