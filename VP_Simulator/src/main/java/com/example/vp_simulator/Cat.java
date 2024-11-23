package com.example.vp_simulator;

public class Cat extends Pet{

    private CatBreed breed;

    public enum CatBreed {
        Siamese,
        Ragdoll;
    }

    public Cat(String name, CatBreed breed) {
        super(name);
        this.breed = breed;
    }

    public void setBreed(CatBreed breed) {
        this.breed = breed;
    }

    public CatBreed getBreed() {
        return breed;
    }

    @Override
    public String breedToString() {
        switch (breed) {
            case Siamese:
                return "Siamese";
            case Ragdoll:
                return "Calico";
            default:
                return "Unknown Breed";
        }
    }

    @Override
    public void feed() {
        setHunger(100);
        setEnergy(100);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        }
        else {
            setHappiness(100);
        }
    }

    @Override
    public void train() {
        setEnergy(getEnergy() - 10);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }
    }

    @Override
    public void outing() {
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }

        setEnergy(getEnergy() - 10);
    }

    @Override
    public void play() {
        setEnergy(getEnergy() - 10);
        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);

        } else {
            setHappiness(100);
        }
    }

    @Override
    public void checkup() {
        setHealth(getHealth() + 40);
        setHappiness(getHappiness() - 10);
    }

}
