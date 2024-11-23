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
        this.breed = DogBreed.Pug;
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
            case Pug:
                return "Pug";
            case Poodle:
                return "Poodle";
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
