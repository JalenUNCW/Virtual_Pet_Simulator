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
        this.breed = CatBreed.Tabby;
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
            case Tabby:
                return "Tabby";
            case Calico:
                return "Calico";
            case Orange:
                return "Orange";
            case Black:
                return "Black";
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
        } else {
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
