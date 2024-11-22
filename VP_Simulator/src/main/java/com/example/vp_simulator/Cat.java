package com.example.vp_simulator;

public class Cat extends Pet{

    private CatBreed breed;

    public enum CatBreed {
        Tabby,
        Calico,
        Orange,
        Black;
    }

    public Cat() {
        super();
        // add random breed here
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
        setHappiness(getHappiness() + 20);
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
