package com.example.vp_simulator;

/**
 * Represents a Cat, which is a subclass of Pet.
 * This class extends the Pet class and includes specific attributes and behaviors for cats.
 */
public class Cat extends Pet{

    private CatBreed breed;

    /**
     * Enum representing the different cat breeds supported by this class.
     */
    public enum CatBreed {
        Siamese,
        Ragdoll;
    }

    /**
     * Constructs a new Cat with the specified name and breed.
     *
     * @param name  the name of the cat
     * @param breed the breed of the cat, as a {@link CatBreed} enum value
     */
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

    /**
     * Converts the breed of the cat to a string representation.
     *
     * @return a string representation of the cat's breed
     */
    @Override
    public String breedToString() {
        switch (breed) {
            case Siamese:
                return "Siamese";
            case Ragdoll:
                return "Ragdoll";
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
