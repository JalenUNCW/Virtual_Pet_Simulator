package com.example.vp_simulator;

public class Dog extends Pet{

    private DogBreed breed;

    public enum DogBreed {
        Lab,
        Shepherd;
    }

    public Dog(String name, DogBreed breed) {
        super(name);
        this.breed = breed;
    }

    public void setBreed(DogBreed breed) {
        this.breed = breed;
    }

    public DogBreed getBreed() {
        return breed;
    }

    @Override
    public String breedToString() {
        switch (breed) {
            case Lab:
                return "Lab";
            case Shepherd:
                return "Shepherd";
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
        setEnergy(getEnergy() - 10);

        if (getHappiness() < 80) {
            setHappiness(getHappiness() + 20);
        } else {
            setHappiness(100);
        }

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
        setHealth(100);
        if (getHappiness() > 20) {
            setHappiness(getHappiness() - 20);

        } else {
            setHappiness(0);
        }
    }

}
